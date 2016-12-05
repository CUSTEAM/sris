package action.fee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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

import model.Message;
import model.Stmd;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import service.impl.DataFinder;
import action.BaseAction;

public class PayAccountManager extends BaseAction{
	
	public File upload;	
	public String occur_month, cno, tno, year, term, StudentNo, OfficeNo, AcctNo, Money, Kind, del[];
	SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");	
	Date birthday;
	Calendar c=Calendar.getInstance();
	public String execute(){
		
		return SUCCESS;
	}
	
	public String search(){
		List list=mainSearch("stmd");
		list.addAll(mainSearch("Gstmd"));
		
		request.setAttribute("dipost", list);
		return SUCCESS;
	}
	
	private List mainSearch(String target){
		List list;
		StringBuilder sql=new StringBuilder("SELECT d.Oid,s.student_no,s.idno, s.student_name, "
		+ "c.ClassName, cd.name, d.OfficeNo, d.AcctNo,d.Money, d.LastModified, d.SchoolYear, d.SchoolTerm, d.occur_month FROM "
		+target+ " s, Dipost d, Class c, CODE_DIPOST cd WHERE cd.id=d.Kind AND "
		+ "s.student_no=d.StudentNo AND s.depart_class=c.ClassNo AND "
		+ "d.SchoolYear='"+year+"'"
		+ "AND d.Kind='"+Kind+"'");		
		
		if(!tno.equals(""))sql.append("AND c.SchoolType='"+tno+"'");
		if(!cno.equals(""))sql.append("AND c.CampusNo='"+cno+"'");
		if(StudentNo.indexOf(",")>0)sql.append("AND StudentNo ='"+StudentNo.substring(0, StudentNo.indexOf(","))+"'");
		if(!occur_month.equals(""))sql.append("AND occur_month='"+occur_month+"'");
		if(Kind.equals("3"))sql.append("AND occur_month LIKE'"+occur_month+"%'");
		if(!term.equals(""))sql.append("AND d.SchoolTerm='"+term+"'");
		list=df.sqlGet(sql.toString());
		return list;
	}
	
	private List subSearch(String target){	
		SimpleDateFormat sf1=new SimpleDateFormat("yyyy-MM-dd");		
		return df.sqlGet("SELECT d.Oid,s.student_no, s.student_name, c.ClassName, "
		+ "cd.name, d.OfficeNo, d.AcctNo,d.Money, d.LastModified, d.SchoolYear, d.SchoolTerm, "
		+ "d.occur_month FROM "+target+" s, Dipost d, Class c, CODE_DIPOST cd WHERE cd.id=d.Kind AND "
		+ "s.student_no=d.StudentNo AND s.depart_class=c.ClassNo AND d.LastModified LIKE'"+sf1.format(new Date())+"%' ORDER BY d.LastModified DESC");
	}
	
	public String del(){
		Message msg=new Message();
		if(del!=null){
			for(int i=0; i<del.length; i++){
				df.exSql("DELETE FROM Dipost WHERE Oid="+del[i]);				
			}
			msg.setSuccess("已刪除"+del.length+"筆資料");
		}else{
			msg.setSuccess("未勾選資料");
		}
		this.savMessage(msg);
		return search();
	}
	
	public String add(){
		Message msg=new Message();
		if(Money.equals("")|| AcctNo.equals("")|| OfficeNo.equals("")|| StudentNo.indexOf(",")<1){
			msg.setError("欄位不完整");
			this.savMessage(msg);
			return SUCCESS;
		}
		List list;
		try{
			if(Kind.equals("3")){
				df.exSql("INSERT INTO Dipost(StudentNo,OfficeNo,AcctNo,Money,Kind,Modifier,SchoolYear,SchoolTerm,occur_month)"
				+ "VALUES('"+StudentNo.substring(0, StudentNo.indexOf(","))+"', '"+OfficeNo+"', '"+AcctNo+"', '"
				+Money+"', '"+Kind+"', '"+getSession().getAttribute("userid")+"', '"+year+"', '"+term+"','"+occur_month+"');");
			}else{
				df.exSql("INSERT INTO Dipost(StudentNo,OfficeNo,AcctNo,Money,Kind,Modifier,SchoolYear,SchoolTerm)"
				+ "VALUES('"+StudentNo.substring(0, StudentNo.indexOf(","))+"', '"+OfficeNo+"', '"+AcctNo+"', '"
				+Money+"', '"+Kind+"', '"+getSession().getAttribute("userid")+"', '"+year+"', '"+term+"');");
			}			
			msg.setSuccess("新增完成");
			this.savMessage(msg);
			list=subSearch("stmd");
			list.addAll(subSearch("Gstmd"));
			request.setAttribute("dipost", list);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			msg.setError("請檢查是否為重複資料");
			this.savMessage(msg);	
			list=subSearch("stmd");
			list.addAll(subSearch("Gstmd"));
			request.setAttribute("dipost", list);
			return SUCCESS;
		}
	}
	
	/**
	 * upload
	 * @return
	 * @throws IOException
	 */
	public String upload() throws IOException{
		Message msg=new Message();
		if(upload==null){			
			msg.setError("未指定檔案");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		FileInputStream fis = new FileInputStream(upload);		
		XSSFWorkbook xwb = new XSSFWorkbook(fis);
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow row;	
		
		int check=0;
		boolean b=false;
		String y;
		String t;
		String k;
		String s;
		String o;
		String a;
		String m;
		String mm;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {				
			row = sheet.getRow(i);
			//if(row.getCell(0)==null)break;	
				/*if(i==0)
				try{//檢查標題
					
					Integer.parseInt(row.getCell(0).toString());
				}catch(Exception e){
					msg.setError("發現標題欄位, 已跳過讀取<br>");
					continue;
				}*/
				
				/*for(int j=0; j<=6; j++){//檢查欄位null
					if(String.valueOf(row.getCell(j)).trim().equals("")||row.getCell(j)==null){	
						msg.addError("第"+i+"行發現空白");
						b=true;
					}
				}				
				if(b==true){
					msg.addError("<br>發現嚴重問題, 文件未匯入");
					this.savMessage(msg);
					return SUCCESS;
				}*/				
				try{
					y=readCellAsString(row.getCell(0));
					t=readCellAsString(row.getCell(1));
					k=readCellAsString(row.getCell(2));
					s=readCellAsString(row.getCell(3));
					o=readCellAsString(row.getCell(4));
					a=readCellAsString(row.getCell(5));
					m=readCellAsString(row.getCell(6));	
					mm=readCellAsString(row.getCell(7));
					//試刪除原資料
					df.exSql("DELETE FROM Dipost WHERE Kind='"+k+"'AND StudentNo='"+s+"' AND SchoolYear='"+y+"' AND SchoolTerm='"+t+"'");
					//建立
					df.exSql("INSERT INTO Dipost(StudentNo,OfficeNo,AcctNo,Money,Kind,Modifier,SchoolYear,SchoolTerm,occur_month)"
					+ "VALUES('"+s+"', '"+o+"', '"+a+"', '"+m+"', '"+k+"', '"+getSession().getAttribute("userid")+"', '"+y+"', '"+t+"','"+mm+"');");
					check+=1;
				}catch(Exception e){
					msg.addError("第"+i+"行在儲存中發生錯誤");
					continue;
				}										
						
		}
		
		fis.close();
		//request.setAttribute("complete", complete);
		//request.setAttribute("fail", fail);
		msg.setSuccess("文件匯入完成, 共"+check+"筆資料");
		this.savMessage(msg);
		return SUCCESS;
	}
	
	
	private void saveTxtFile() throws IOException{
		
		FileInputStream fis = new FileInputStream(upload);
		FileReader fr = new FileReader(fis.getFD());
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine(); //讀第一行
		df.exSql("INSERT INTO LC_exam_stmds (student_no) VALUES ('"+line+"')ON DUPLICATE KEY UPDATE student_no=VALUES(student_no);");
		StringBuffer sb = new StringBuffer();
		while ((line=br.readLine())!=null){
			df.exSql("INSERT INTO LC_exam_stmds (student_no) VALUES ('"+line+"')ON DUPLICATE KEY UPDATE student_no=VALUES(student_no);");
		}	
		fis.close();
		fr.close();
		br.close();
		
		
		
		
		
		
		
		
		
	}
	
	/**
	 * excel欄位強制轉文字 2003-
	 * @param cell
	 * @return
	 */
	private String readCellAsString(HSSFCell cell) {
		if (cell == null) {
			return null;
		}
		final DecimalFormat df = new DecimalFormat("####################0.##########");

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return Boolean.valueOf(cell.getBooleanCellValue()).toString();
		case HSSFCell.CELL_TYPE_NUMERIC:
			return df.format(cell.getNumericCellValue());
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case HSSFCell.CELL_TYPE_ERROR:
			return Byte.toString(cell.getErrorCellValue());
		default:
			return "##POI## Unknown cell type";
		}
	}
	
	/**
	 * excel欄位強制轉文字 2003+
	 * @param cell
	 * @return
	 */
	private String readCellAsString(XSSFCell cell) {
		if (cell == null) {
			return null;
		}
		final DecimalFormat df = new DecimalFormat("####################0.##########");

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return Boolean.valueOf(cell.getBooleanCellValue()).toString().trim();
		case HSSFCell.CELL_TYPE_NUMERIC:
			return df.format(cell.getNumericCellValue()).trim();
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue().trim();
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula().trim();
		case HSSFCell.CELL_TYPE_ERROR:
			return Byte.toString(cell.getErrorCellValue());
		default:
			return "##POI## Unknown cell type";
		}
	}
	
	/**
	 * 班級是否存在
	 * @param ClassNo
	 * @return
	 */
	private boolean checkClass(String ClassNo){
		if(df.sqlGetInt("SELECT COUNT(*)FROM Class WHERE ClassNo='"+ClassNo+"'")<1){
			return false;
		}
		return true;
	}
	
	/**
	 * 取生日
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	private Date getBirthday(String str) throws ParseException{		
		if(str.length()==6){
			str="00"+str;
		}else{
			str="0"+str;
		}		
		
		birthday=sf.parse(str);
		c.setTime(birthday);
		c.add(Calendar.YEAR, 1911);
		return c.getTime();
	}
	
	/**
	 * 取性別
	 * @param idno
	 * @return
	 */
	private String getSex(String idno){	
		try{
			return idno.substring(1,2);
		}catch(Exception e){
			return "1";
		}
	}
	
	/**
	 * 訊息
	 * @param stdNo
	 * @param msg
	 * @return
	 */
	private Map setMsg(String stdNo, String msg){
		Map m=new HashMap();
		m.put("stdNo", stdNo);
		m.put("msg", msg);
		return m;
	}
	
	/**
	 * 列印
	 * @return
	 * @throws IOException 
	 */
	public String print() throws IOException{
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");		
		PrintWriter out=response.getWriter();
		
		List<Map>list=mainSearch("stmd");
		list.addAll(mainSearch("Gstmd"));
		
		out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>user</Author>");
		out.println ("  <LastAuthor>John</LastAuthor>");
		out.println ("  <LastPrinted>2016-12-05T01:10:12Z</LastPrinted>");
		out.println ("  <Created>2009-12-23T01:19:55Z</Created>");
		out.println ("  <LastSaved>2016-12-01T02:12:35Z</LastSaved>");
		out.println ("  <Company>chit</Company>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>12390</WindowHeight>");
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
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s63'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='0.00_ '/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s64'>");
		out.println ("   <Alignment ss:Horizontal='Distributed' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s65'>");
		out.println ("   <Alignment ss:Horizontal='Distributed' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s66'>");
		out.println ("   <Alignment ss:Horizontal='Distributed' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s67'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='0.00_ '/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s68'>");
		out.println ("   <Alignment ss:Horizontal='Distributed' ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s69'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s70'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'/>");
		out.println ("   <NumberFormat ss:Format='#,##0'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		out.println (" <Worksheet ss:Name='學生轉帳帳號資料'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=學生轉帳帳號資料!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='13' ss:ExpandedRowCount='"+(list.size()+999)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s62' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='15.75'>");
		out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='116.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='68.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='52.5'/>");
		out.println ("   <Column ss:StyleID='s62' ss:Width='63' ss:Span='1'/>");
		out.println ("   <Column ss:Index='6' ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='81.75'/>");
		out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='52.5'/>");
		out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='75'/>");
		out.println ("   <Row ss:AutoFitHeight='0' ss:Height='37.5' ss:StyleID='s64'>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>班級</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>學號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s66'><Data ss:Type='String'>姓名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>立帳局號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>儲戶帳號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>身份證號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s67'><Data ss:Type='String'>轉帳金額</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>種類</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s68'><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s68'><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s68'><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s68'><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s68'><NamedCell ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		int sum=0;
		for(int i=0; i<list.size(); i++){
			sum+=Integer.parseInt(list.get(i).get("Money").toString());
			out.println ("   <Row ss:AutoFitHeight='0'>");
			out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>"+list.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>"+list.get(i).get("student_no")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>"+list.get(i).get("student_name")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>"+list.get(i).get("OfficeNo")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>"+list.get(i).get("AcctNo")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>"+list.get(i).get("idno")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s70'><Data ss:Type='Number'>"+list.get(i).get("Money")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s69'><Data ss:Type='String'>"+list.get(i).get("name")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984'");
		
		if(occur_month.equals("")){
			out.println ("     x:Data='&#10;&amp;L&#10;"+(list.size()+1)+"筆, "+sum+"元 &amp;C&amp;&quot;微軟正黑體,粗體&quot;&amp;16 "+year+"學年第"+term+"學期轉帳清冊      &#10;&amp;R&#10;&amp;D&amp;T'/>");
		}else{
			out.println ("     x:Data='&#10;&amp;L&#10;"+(list.size()+1)+"筆, "+sum+"元 &amp;C&amp;&quot;微軟正黑體,粗體&quot;&amp;16 "+year+"學年"+occur_month+"月份轉帳清冊      &#10;&amp;R&#10;&amp;D&amp;T'/>");
		}
		
		
		
		out.println ("    <Footer x:Margin='0.31496062992125984' x:Data='&amp;R&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.23622047244094491'");
		out.println ("     x:Right='0.23622047244094491' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>600</HorizontalResolution>");
		out.println ("    <VerticalResolution>600</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <FreezePanes/>");
		out.println ("   <FrozenNoSplit/>");
		out.println ("   <SplitHorizontal>1</SplitHorizontal>");
		out.println ("   <TopRowBottomPane>1</TopRowBottomPane>");
		out.println ("   <ActivePane>2</ActivePane>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("    </Pane>");
		out.println ("    <Pane>");
		out.println ("     <Number>2</Number>");
		out.println ("     <ActiveRow>0</ActiveRow>");
		out.println ("     <ActiveCol>6</ActiveCol>");
		out.println ("     <RangeSelection>C7</RangeSelection>");
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

}
