package action.fee;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.BasePrintXmlAction;
import model.Message;

/**
 * 收費單
 * 收費項目不在報表格式中的情況下,合計金額仍會加總,但報表中無法顯示該收費項目金額
 * @author shawn
 *
 */
public class TuitionManagerAction extends BasePrintXmlAction{
	
	public String cno,sno,tno,dno,gno,zno;	
	public String acc_fee_m, acc_fee_f, sec_fee_m, sec_fee_f, air_fee_m, air_fee_f;
	public String ClassNo[],quota[],no[];	
	public String term,edate;	
	public String cnt;
	
	public String type,scope;
	
	public String execute(){	
		return SUCCESS;
	}
	
	public String del(){		
		for(int i=0; i<ClassNo.length; i++){
			if(!ClassNo[i].equals("")){
				df.exSql("DELETE FROM FeePay WHERE DepartClass='"+ClassNo[i]+"'");
			}				
		}
		return search();
	}
	
	public String add(){
		Message msg=new Message();
		
		try{
			String ClassNo=df.sqlGetStr("SELECT ClassNo FROM Class WHERE CampusNo='"+
			cno+"'AND SchoolNo='"+sno+"'AND DeptNo='"+dno+"'AND Grade='"+gno+"'AND SeqNo='"+zno+"'");			
						
			df.exSql("INSERT INTO FeePay(DepartClass,Fcode,Kind,Money)VALUES('"+ClassNo+"','N','2',450);");
			msg.setSuccess("已建立");
		}catch(Exception e){
			e.printStackTrace();
			msg.setError("建立失敗");
		}		
		this.savMessage(msg);
		return search();
	}
	
	public String search(){
		request.setAttribute("fee", getCs());		
		return SUCCESS;
	}
	
	/**
	 * 取範圍內班級
	 * @return
	 */
	private List getCs(){		
		//範圍
		StringBuilder sb=new StringBuilder("SELECT d.fname, fq.quota,fq.no, c.*,cc.name as CampusName,cd.name as DeptName,"
		+ "(SELECT COUNT(*)FROM stmd WHERE depart_class=c.ClassNo)as cnt,"
		+ "IFNULL((SELECT SUM(Money)FROM FeePay WHERE Kind='1'AND DepartClass=c.ClassNo),0)as mon1,"
		+ "IFNULL((SELECT SUM(Money)FROM FeePay WHERE Kind='2'AND DepartClass=c.ClassNo),0)as mon2 "
		+ "FROM CODE_CAMPUS cc, CODE_DEPT cd, (Class c LEFT OUTER JOIN FeeQuota fq ON c.ClassNo=fq.ClassNo)LEFT OUTER JOIN dept d ON d.no=c.Dept, FeePay f WHERE c.CampusNo=cc.id AND c.DeptNo=cd.id AND f.DepartClass=c.ClassNo ");						
		sb=genSql(sb);
		
		System.out.println(sb);
		List<Map>list=df.sqlGet(sb.toString());
		List result=new ArrayList();
		if(term.equals("1")){//第1學期需升級
			for(int i=0; i<list.size(); i++){	
				//重整升級後人數
				list.get(i).put("cnt", df.sqlGetInt("SELECT COUNT(*) FROM stmd s, Class c WHERE "
				+ "s.depart_class=c.ClassNo AND c.CampusNo='"+list.get(i).get("CampusNo")+"'AND SchoolNo='"+list.get(i).get("SchoolNo")+"'AND "
				+ "DeptNo='"+list.get(i).get("DeptNo")+"'AND Grade='"+(Integer.parseInt(list.get(i).get("Grade").toString())-1)+"'AND SeqNo='"+list.get(i).get("SeqNo")+"'"));
			}			
		}
		//取各項金額	
		for(int i=0; i<list.size(); i++){			
			
			//if(Integer.parseInt(list.get(i).get("mon2").toString())<1)continue;//無金額跳過			
			
			list.get(i).put("pay1", df.sqlGet("SELECT fc.Name, fc.No, fc.No as Fcode, fc.kind,(SELECT Oid FROM FeePay WHERE DepartClass=c.ClassNo AND Fcode=fc.No)as Oid,"
			+ "(SELECT Money FROM FeePay WHERE DepartClass=c.ClassNo AND Fcode=fc.No)as Money "
			+ "FROM Class c, (SELECT * FROM FeeCode)fc WHERE fc.kind='1'AND c.ClassNo='"+list.get(i).get("ClassNo")+"'"));
			
			list.get(i).put("pay2", df.sqlGet("SELECT fc.Name, fc.No, fc.No as Fcode, fc.kind,(SELECT Oid FROM FeePay WHERE DepartClass=c.ClassNo AND Fcode=fc.No)as Oid,"
			+ "(SELECT Money FROM FeePay WHERE DepartClass=c.ClassNo AND Fcode=fc.No)as Money "
			+ "FROM Class c, (SELECT * FROM FeeCode)fc WHERE fc.kind='2'AND c.ClassNo='"+list.get(i).get("ClassNo")+"'"));
			//學生數大於0時
			if(Integer.parseInt(list.get(i).get("cnt").toString())>0){
				result.add(list.get(i));
				continue;
			}
			//1年級人數為0仍要列入
			if(list.get(i).get("Grade").equals("1")){
				result.add(list.get(i));
			}				
		}		
		return result;
	}
	
	/**
	 * 班級sql過濾
	 * @param sb
	 * @return
	 */
	private StringBuilder genSql(StringBuilder sb){
		if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!tno.equals(""))sb.append("AND c.SchoolType='"+tno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		
		if(scope.equals("")){//不設限新生
			if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		}else{
			if(scope.equals("1"))sb.append("AND c.Grade='1'");//只限新生
			if(scope.equals("2"))sb.append("AND c.Grade!='1'");//只限舊生
		}
		
		sb.append("GROUP BY c.ClassNo ORDER BY c.ClassNo");
		return sb;
	}
	
	private String fdate(String edate) throws ParseException{		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf1=new SimpleDateFormat("yyyMMdd");
		Calendar c=Calendar.getInstance();
		c.setTime(sf.parse(edate));
		c.add(Calendar.YEAR, -1911);
		return sf1.format(c.getTime());
	}
	
	private int row;
	
	/**
	 * 建立學生
	 * @return
	 * @throws ParseException
	 */
	private List genStds() throws ParseException{
		List<Map>list=getCs();
		List<Map>stds,pays;
		edate=fdate(edate);
		row=0;
		Map pay1, pay2;
		
		for(int i=0; i<list.size(); i++){
			
			if(term.equals("1")){
				stds=df.sqlGet("SELECT s.sex, s.student_no, s.student_name, s.idno FROM stmd s, Class c WHERE "
				+ "s.depart_class=c.ClassNo AND c.CampusNo='"+list.get(i).get("CampusNo")+"'AND SchoolNo='"+list.get(i).get("SchoolNo")+"'AND "
				+ "DeptNo='"+list.get(i).get("DeptNo")+"'AND Grade='"+(Integer.parseInt(list.get(i).get("Grade").toString())-1)+"'AND SeqNo='"+list.get(i).get("SeqNo")+"'ORDER BY s.student_no");
			
				if(stds.size()<1 && list.get(i).get("quota")!=null){//建立無學號
					stds=genStds(Integer.parseInt(list.get(i).get("quota").toString()), list.get(i).get("no").toString());
				}				
			}else{
				stds=df.sqlGet("SELECT s.sex, s.student_no,s.student_name, s.idno "
				+ "FROM stmd s WHERE s.depart_class='"+list.get(i).get("ClassNo")+"'ORDER BY s.student_no");
			}			
			list.get(i).put("stds", stds);
			row+=stds.size();//總數
			
			pays=(List<Map>)list.get(i).get("pay1");
			pay1=new HashMap();
			for(int j=0; j<pays.size(); j++){
				pay1.put(pays.get(j).get("Fcode"), pays.get(j).get("Money"));
			}
			
			pays=(List<Map>)list.get(i).get("pay2");
			pay2=new HashMap();
			for(int j=0; j<pays.size(); j++){
				pay2.put(pays.get(j).get("Fcode"), pays.get(j).get("Money"));
			}			
			list.get(i).put("pay1", pay1);
			list.get(i).put("pay2", pay2);
		}
		return list;
	}
	
	/**
	 * 建立一年級無學籍學生
	 * @param num
	 * @return
	 */
	private List genStds(int num, String no){
		
		DecimalFormat f = new DecimalFormat("000");
		if(no.length()<3){
			no="學號001";
		}
		String head=no.substring(0, no.length()-3);
		int beginNo;
		try{
			beginNo=Integer.parseInt(no.substring(no.length()-3, no.length()));			
		}catch(Exception e){
			beginNo=1;
		}
		Map std;
		List list=new ArrayList();
		for(int i=beginNo; i<=(beginNo+num-1); i++){
			std=new HashMap();
			std.put("student_no", head+f.format(i));
			std.put("student_name", "");
			std.put("sex", "1");
			
			list.add(std);
		}
		
		
		return list;
	}
	
	/**
	 * 學雜費
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public String tuitionPrint() throws IOException, ParseException{
		
		if(edate.equals("")){
			Message msg=new Message();
			msg.setError("繳款期限");
			this.savMessage(msg);
			return search();
		}
		
		Date date=new Date();
		xml2ods(response, getRequest(), date);
		PrintWriter out=response.getWriter();
		out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>landbank</Author>");
		out.println ("  <LastAuthor>shawn</LastAuthor>");
		out.println ("  <Created>2010-05-12T06:29:34Z</Created>");
		out.println ("  <LastSaved>2015-03-23T09:33:13Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>11880</WindowHeight>");
		out.println ("  <WindowWidth>28800</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>0</WindowTopY>");
		out.println ("  <ProtectStructure>False</ProtectStructure>");
		out.println ("  <ProtectWindows>False</ProtectWindows>");
		out.println (" </ExcelWorkbook>");
		out.println (" <Styles>");
		out.println ("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat/>");
		out.println ("   <Protection/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s62'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Bottom'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s63'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Bottom'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s64'>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s65'>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Modern' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		List<Map>list=genStds();
		List<Map>stds;
		out.println (" <Worksheet ss:Name='SHEET1'>");
		out.println ("  <Table ss:ExpandedColumnCount='21' ss:ExpandedRowCount='"+(row+1)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:Index='1' ss:AutoFitWidth='0' ss:Width='66.75'/>");
		out.println ("   <Column ss:Index='3' ss:AutoFitWidth='0' ss:Width='79.75'/>");
		out.println ("   <Column ss:Index='7' ss:AutoFitWidth='0' ss:Width='141'/>");
		out.println ("   <Column ss:Index='11' ss:AutoFitWidth='0' ss:Width='147'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='71.25'/>");
		out.println ("   <Row>");
		out.println ("    <Cell><Data ss:Type='String'>學號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>姓名</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>身分證字號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>電話</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>通訊地址</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>班級代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>班級名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>部別代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>部別名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>系所代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>系所名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>學分費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>雜費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>電腦及網路通訊使用費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>平安保險費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>助學貸款</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>繳費金額合計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>年級</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>繳款期限</Data></Cell>");
		out.println ("   </Row>");	
		
		String ClassName;
		Map pay1;
		for(int i=0; i<list.size(); i++){			
			stds=(List<Map>)list.get(i).get("stds");			
			for(int j=0; j<stds.size();j++){
				ClassName=list.get(i).get("CampusName").toString().substring(0, 2)+list.get(i).get("ClassName").toString();
				if(term.equals("1")&&list.get(i).get("Grade").toString().equals("1")){
					ClassName=ClassName.substring(0, ClassName.length()-1);
				}		
				out.println ("   <Row>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_name")+"</Data></Cell>");
				if(stds.get(j).get("idno")==null){
					out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_no")+"A</Data></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("idno")+"</Data></Cell>");
				}
				out.println ("    <Cell></Cell>");
				out.println ("    <Cell></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("ClassNo")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+ClassName+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='Number'>"+list.get(i).get("CampusNo")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("CampusName")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("CampusNo")+list.get(i).get("SchoolNo")+list.get(i).get("DeptNo")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("fname")+"</Data></Cell>");

				pay1=(Map)list.get(i).get("pay1");
				//學分費
				if(pay1.get("G")==null){
					out.println ("    <Cell ss:Index='12'><Data ss:Type='Number'>0</Data></Cell>");
				}else{
					out.println ("    <Cell ss:Index='12'><Data ss:Type='Number'>"+pay1.get("G")+"</Data></Cell>");
				}
				
				if(pay1.get("F")==null){
					out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='Number'>"+pay1.get("F")+"</Data></Cell>");
				}	
				
				if(pay1.get("H")==null){
					out.println ("    <Cell></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='Number'>"+pay1.get("H")+"</Data></Cell>");
				}			
				//out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				
				if(pay1.get("J")==null){
					out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='Number'>"+pay1.get("J")+"</Data></Cell>");
				}
				out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");	
				out.println ("    <Cell><Data ss:Type='Number'>"+list.get(i).get("mon1")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+Integer.parseInt(list.get(i).get("Grade").toString())+"</Data></Cell>");
				//out.println ("    <Cell ss:Index='20'><Data ss:Type='String'>"+bl.getWeekOfDay4Zh(Integer.parseInt(list.get(i).get("Grade").toString()), null)+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>"+edate+"</Data></Cell>");
				out.println ("   </Row>");
			}
		}		
		out.println ("  </Table>");
		
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <Selected/>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>13</ActiveRow>");
		out.println ("     <ActiveCol>8</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");		
		out.println ("</Workbook>");	
		out.close();
		out.flush();
		return null;
	}	
	
	/**
	 * 代辦
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public String nottuitionPrint() throws IOException, ParseException{
		
		if(edate.equals("")){
			Message msg=new Message();
			msg.setError("繳款期限");
			this.savMessage(msg);
			return search();
		}
		
		List<Map>list=genStds();
		List<Map>stds;
		Date date=new Date();
		xml2ods(response, getRequest(), date);
		PrintWriter out=response.getWriter();
		out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>landbank</Author>");
		out.println ("  <LastAuthor>shawn</LastAuthor>");
		out.println ("  <Created>2010-05-12T06:30:53Z</Created>");
		out.println ("  <LastSaved>2015-03-24T02:05:06Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>8055</WindowHeight>");
		out.println ("  <WindowWidth>10425</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>0</WindowTopY>");
		out.println ("  <Iteration/>");
		out.println ("  <ProtectStructure>False</ProtectStructure>");
		out.println ("  <ProtectWindows>False</ProtectWindows>");
		out.println (" </ExcelWorkbook>");
		out.println (" <Styles>");
		out.println ("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat/>");
		out.println ("   <Protection/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s62'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Bottom'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s63'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Bottom'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s64'>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s65'>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s66'>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Modern' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s67'>");
		out.println ("   <Font ss:FontName='Times New Roman' x:Family='Roman' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		out.println (" <Worksheet ss:Name='SHEET1'>");
		out.println ("  <Table ss:ExpandedColumnCount='21' ss:ExpandedRowCount='"+row+1+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='68.25'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='66.75'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='100.5'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='147'/>");
		out.println ("   <Column ss:Index='19' ss:AutoFitWidth='0' ss:Width='75'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell><Data ss:Type='String'>學號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>姓名</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>身分證字號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>電話</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>通訊地址</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>班級代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>班級名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>部別代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>部別名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>系所代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>系所名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>刊物費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>課外活動費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>學生手冊費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>健康檢查費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>學生(聯)會費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s67'><ss:Data ss:Type='String'");
		out.println ("      xmlns='http://www.w3.org/TR/REC-html40'>X<Font html:Face='新細明體'");
		out.println ("       x:Family='Roman'>光費</Font></ss:Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>代辦費合計</Data></Cell>");
		//out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>存繳帳號</Data></Cell>");
		//out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>家長姓名</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>年級</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>繳款期限</Data></Cell>");
		out.println ("   </Row>");
		String ClassName;
		for(int i=0; i<list.size(); i++){
			
			ClassName=list.get(i).get("CampusName").toString().substring(0, 2)+list.get(i).get("ClassName").toString();
			if(term.equals("1")&&list.get(i).get("Grade").toString().equals("1")){
				ClassName=ClassName.substring(0, ClassName.length()-1);
			}
			
			stds=(List<Map>)list.get(i).get("stds");			
			for(int j=0; j<stds.size();j++){
				if(list.get(i).get("mon2").toString().equals("0"))continue;
				out.println ("   <Row ss:AutoFitHeight='0'>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_name")+"</Data></Cell>");
				if(stds.get(j).get("idno")==null){
					out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_no")+"A</Data></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("idno")+"</Data></Cell>");
				}
				//out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("idno")+"</Data></Cell>");
				out.println ("    <Cell></Cell>");
				out.println ("    <Cell></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("ClassNo")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+ClassName+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='Number'>"+list.get(i).get("CampusNo")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("CampusName")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("CampusNo")+list.get(i).get("SchoolNo")+list.get(i).get("DeptNo")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("fname")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				
				//if(((Map)list.get(i).get("pay2")).get("1")==null)((Map)list.get(i).get("pay2")).put("1","");
				//out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='Number'>"+((Map)list.get(i).get("pay2")).get("1")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				
				//if(((Map)list.get(i).get("pay2")).get("8")==null)((Map)list.get(i).get("pay2")).put("8", "");
				//out.println ("    <Cell><Data ss:Type='Number'>"+((Map)list.get(i).get("pay2")).get("8")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				
				//if(((Map)list.get(i).get("pay2")).get("T")==null)((Map)list.get(i).get("pay2")).put("T","");
				//out.println ("    <Cell><Data ss:Type='Number'>"+((Map)list.get(i).get("pay2")).get("T")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				
				if(((Map)list.get(i).get("pay2")).get("N")==null)((Map)list.get(i).get("pay2")).put("N", "");
				out.println ("    <Cell><Data ss:Type='Number'>"+((Map)list.get(i).get("pay2")).get("N")+"</Data></Cell>");
				
				//if(((Map)list.get(i).get("pay2")).get("A")==null)((Map)list.get(i).get("pay2")).put("A", "");
				//out.println ("    <Cell><Data ss:Type='Number'>"+((Map)list.get(i).get("pay2")).get("A")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				
				if(list.get(i).get("mon2")==null)list.get(i).put("mon2", "");
				out.println ("    <Cell><Data ss:Type='Number'>"+list.get(i).get("mon2")+"</Data></Cell>");	
				
				
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("Grade").toString()+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>"+edate+"</Data></Cell>");
				out.println ("   </Row>");
			}
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <Unsynced/>");
		out.println ("   <Selected/>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>14</ActiveRow>");
		out.println ("     <ActiveCol>3</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");		
		out.println ("</Workbook>");
		out.close();
		out.flush();
		return null;
	}
	
	/**
	 * 住宿
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public String acc_print() throws IOException, ParseException {
		if(edate.equals("")){
			Message msg=new Message();
			msg.setError("繳款期限");
			this.savMessage(msg);
			return search();
		}
		
		List<Map>list=genStds();
		List<Map>stds;
		Date date=new Date();
		xml2ods(response, getRequest(), date);
		PrintWriter out=response.getWriter();
		
		
		out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>landbank</Author>");
		out.println ("  <LastAuthor>John</LastAuthor>");
		out.println ("  <Created>2010-05-12T06:29:34Z</Created>");
		out.println ("  <LastSaved>2020-10-05T01:05:02Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>11400</WindowHeight>");
		out.println ("  <WindowWidth>28800</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>0</WindowTopY>");
		out.println ("  <ProtectStructure>False</ProtectStructure>");
		out.println ("  <ProtectWindows>False</ProtectWindows>");
		out.println (" </ExcelWorkbook>");
		out.println (" <Styles>");
		out.println ("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat/>");
		out.println ("   <Protection/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s59'>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s80'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s81'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s88'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s93'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s94'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s96'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s97'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Bottom'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s98'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s100'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s101'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s103'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s104'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s105'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s106'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='3'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='3'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s107'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='3'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s108'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='3'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='3'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s109'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='3'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s110'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='3'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#FF0000'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		out.println (" <Worksheet ss:Name='SHEET1'>");
		out.println ("  <Table ss:ExpandedColumnCount='18' ss:ExpandedRowCount='"+(row+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s59' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s101' ss:AutoFitWidth='0' ss:Width='66.75'/>");
		out.println ("   <Column ss:StyleID='s80' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s80' ss:AutoFitWidth='0' ss:Width='79.5'/>");
		out.println ("   <Column ss:StyleID='s80' ss:AutoFitWidth='0' ss:Span='2'/>");
		out.println ("   <Column ss:Index='7' ss:StyleID='s80' ss:AutoFitWidth='0' ss:Width='141'/>");
		out.println ("   <Column ss:StyleID='s80' ss:AutoFitWidth='0' ss:Span='2'/>");
		out.println ("   <Column ss:Index='11' ss:StyleID='s81' ss:AutoFitWidth='0' ss:Width='147'/>");
		out.println ("   <Column ss:StyleID='s109' ss:AutoFitWidth='0' ss:Width='71.25'/>");
		out.println ("   <Column ss:StyleID='s88' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s88' ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:StyleID='s88' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s110' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s93' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s103' ss:AutoFitWidth='0'/>");
		out.println ("   <Row ss:AutoFitHeight='0' ss:StyleID='s94'>");
		out.println ("    <Cell ss:StyleID='s96'><Data ss:Type='String'>學號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s97'><Data ss:Type='String'>姓名</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s97'><Data ss:Type='String'>身分證字號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s97'><Data ss:Type='String'>電話</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s97'><Data ss:Type='String'>通訊地址</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s98'><Data ss:Type='String'>班級代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s97'><Data ss:Type='String'>班級名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s98'><Data ss:Type='String'>部別代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s98'><Data ss:Type='String'>部別名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s98'><Data ss:Type='String'>系所代號</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>系所名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s106'><Data ss:Type='String'>住宿費</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>保證金</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>冷氣使用卡</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>助學貸款</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>年級</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s100'><Data ss:Type='String'>繳款期限</Data></Cell>");
		out.println ("   </Row>");
		
		
		//public String acc_fee_m, acc_fee_f, sec_fee_m, sec_fee_f, air_fee_m, air_fee_f;
		int sum_m, sum_f;
		sum_m=Integer.parseInt(acc_fee_m)+Integer.parseInt(sec_fee_m)+Integer.parseInt(air_fee_m);
		sum_f=Integer.parseInt(acc_fee_f)+Integer.parseInt(sec_fee_f)+Integer.parseInt(air_fee_f);
		String ClassName;
		//edate=fdate(edate);
		Map pay1;
		for(int i=0; i<list.size(); i++){			
			stds=(List<Map>)list.get(i).get("stds");	
			
			for(int j=0; j<stds.size();j++){
				System.out.println(stds.get(j));
				ClassName=list.get(i).get("CampusName").toString().substring(0, 2)+list.get(i).get("ClassName").toString();
				if(term.equals("1")&&list.get(i).get("Grade").toString().equals("1")){
					ClassName=ClassName.substring(0, ClassName.length()-1);
				}	
				
				out.println ("   <Row ss:AutoFitHeight='0'>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_name")+"</Data></Cell>");
				
				if(stds.get(j).get("idno")==null)stds.get(j).put("idno", "");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("idno")+"</Data></Cell>");
				
				
				out.println ("    <Cell></Cell>");
				out.println ("    <Cell></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("ClassNo")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+ClassName+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>1</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("CampusName")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("Dept")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("fname")+"</Data></Cell>");
				
				
				if(stds.get(j).get("sex").equals("1")) {
					out.println ("    <Cell><Data ss:Type='Number'>"+acc_fee_m+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='Number'>"+sec_fee_m+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='Number'>"+air_fee_m+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='Number'>"+sum_m+"</Data></Cell>");
				}else {
					out.println ("    <Cell><Data ss:Type='Number'>"+acc_fee_f+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='Number'>"+sec_fee_f+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='Number'>"+air_fee_f+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='Number'>"+sum_f+"</Data></Cell>");
				}			
				
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("Grade")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+edate+"</Data></Cell>");
				out.println ("   </Row>");				
				
			}
			
		}		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <Unsynced/>");
		out.println ("   <Selected/>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		out.println ("</Workbook>");		
		out.close();
		out.flush();
		return null;
	}
	
	/**
	 * 儲存新生名額
	 * @return
	 */
	public String save(){		
		for(int i=0; i<ClassNo.length; i++){
			if(!ClassNo[i].equals(""))
			if(quota[i].trim().equals("")||no[i].trim().equals("")){
				df.exSql("DELETE FROM FeeQuota WHERE ClassNo='"+ClassNo[i]+"'");
			}else{
				df.exSql("INSERT INTO FeeQuota(ClassNo,quota,no) VALUES ('"+ClassNo[i]+"','"+quota[i]+"','"+no[i]+"')ON DUPLICATE KEY UPDATE quota='"+quota[i]+"',no='"+no[i]+"'");
			}
		}
		return search();
	}

}
