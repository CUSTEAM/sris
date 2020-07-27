package action.fee;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import action.BasePrintXmlAction;
import model.Dipost;
import model.Message;

public class PayInfoManagerAction extends BasePrintXmlAction{
	public String saly, begin, end;
	
	public String 
	StudentNo,
	//OfficeNo,
	//AcctNo,
	//Money,
	Kind,
	Oid[],
	//Modifier,
	//LastModified,
	//SchoolYear,
	//SchoolTerm,
	//occur_month,
	insurance_begin[],
	insurance_end[],
	disabled[],
	hirer_labor[],
	hirer_health[],
	hirer_health_two[],
	hirer_retire[],
	self_labor[],
	self_health[],
	self_health_two[],
	pubmoney[],
	payables[],
	origin_edu[],
	origin_mst[],
	origin_self[],
	origin_other[],
	hours[],
	job_research[],
	job_teach[],
	job_service[],
	pay_study[],
	pay_labor[],
	pay_service[],
	money_work[],
	money_life[],
	unit[],
	Rc_aio[];

	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	public String execute() {
		
		
		
		
		return SUCCESS;
	}
	
	
	public String search() {
		
		if(StudentNo.indexOf(",")<0){
			Message msg=new Message();
			msg.setError("請輸入學號並點選清單中的1名學生");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		try{
			request.setAttribute("units", getUnits());
			request.setAttribute("rcs", getRcs());
			
			StringBuilder sb=new StringBuilder("SELECT c.*, s.student_name, d.* FROM Dipost d, stmd s, CODE_DIPOST c WHERE d.StudentNo=s.student_no AND d.Kind=c.id AND d.StudentNo='"+StudentNo.substring(0, StudentNo.indexOf(","))+"'");
			if(!saly.equals(""))sb.append("AND c.saly='1'");
			if(!begin.equals(""))sb.append("AND d.SchoolYear>='"+begin+"'");
			if(!end.equals(""))sb.append("AND d.SchoolYear<='"+end+"'");
			
			request.setAttribute("diposts", df.sqlGet(sb.toString()));
		}catch(Exception e){
			Message msg=new Message();
			msg.setError("輸入欄位不完整");
			this.savMessage(msg);
		}
		
		
		return SUCCESS;
	}
	
	private List getRcs(){
		Integer syear=Integer.parseInt(getContext().getAttribute("school_year").toString());
		
		System.out.println("SELECT r.Oid, r.school_year, c.name, r.Rc_name FROM Rc_aio r, CODE_RC_TABLE c WHERE "
				+ "c.id !='Rcact' AND r.school_year>="+(syear-3)+" AND r.rc_table=c.id AND r.idno='"+getSession().getAttribute("userid")+"' ORDER BY r.school_year DESC");
		
		List<Map>list=df.sqlGet("SELECT r.Oid, r.school_year, c.name, r.Rc_name FROM Rc_aio r, CODE_RC_TABLE c WHERE "
		+ "c.id !='Rcact' AND r.school_year>="+(syear-3)+" AND r.rc_table=c.id AND r.idno='"+getSession().getAttribute("userid")+"' ORDER BY r.school_year DESC");
		
		return list;
	}
	
	public String save() throws ParseException {
		
		
		for(int i=0; i<Oid.length; i++){
			try{
				if(Oid[i].equals(""))continue;
				Dipost d=(Dipost)df.hqlGetListBy("FROM Dipost WHERE Oid="+Oid[i]).get(0);
				
				d.setDis(disabled[i]);
				
				if(!insurance_begin[i].equals(""))d.setInsuranceBegin(sf.parse(insurance_begin[i]));
				if(!insurance_end[i].equals(""))d.setInsuranceEnd(sf.parse(insurance_end[i]));
				
				if(!hirer_labor[i].equals(""))d.setHirerLabor(Integer.parseInt(hirer_labor[i]));
				if(!hirer_health[i].equals(""))d.setHirerHealth(Integer.parseInt(hirer_health[i]));
				if(!hirer_health_two[i].equals(""))d.setHirerHealthTwo(Integer.parseInt(hirer_health_two[i]));
				if(!hirer_retire[i].equals(""))d.setHirerRetire(Integer.parseInt(hirer_retire[i]));
				if(!self_labor[i].equals(""))d.setSelfLabor(Integer.parseInt(self_labor[i]));
				if(!self_health[i].equals(""))d.setSelfHealth(Integer.parseInt(self_health[i]));
				if(!self_health_two[i].equals(""))d.setSelfHealthTwo(Integer.parseInt(self_health_two[i]));
				if(!pubmoney[i].equals(""))d.setPubmoney(Integer.parseInt(pubmoney[i]));
				if(!payables[i].equals(""))d.setPayables(Integer.parseInt(payables[i]));
				if(!origin_edu[i].equals(""))d.setOriginEdu(Integer.parseInt(origin_edu[i]));
				if(!origin_mst[i].equals(""))d.setOriginMst(Integer.parseInt(origin_mst[i]));
				if(!origin_self[i].equals(""))d.setOriginSelf(Integer.parseInt(origin_self[i]));
				if(!origin_other[i].equals(""))d.setOriginOther(Integer.parseInt(origin_other[i]));
				if(!hours[i].equals(""))d.setHours(Short.parseShort(hours[i]));
				
				//i=(i<0)?0:i-1;
				//System.out.println(score == 70 ? "條件成立  score:" + score : "不條件成立  score:" + score);
				//job_research[i].equals("") ? d.setJobResearch("0") : d.setJobResearch("1");
				
				if(job_research[i].equals("1")){
					d.setJobResearch("1");
				}else{
					d.setJobResearch("0");
				}			
				if(job_teach[i].equals("1")){
					d.setJobTeach("1");
				}else{
					d.setJobTeach("0");
				}			
				if(job_service[i].equals("1")){
					d.setJobService("1");
				}else{
					d.setJobService("0");
				}
				
				if(pay_study[i].equals("1")){
					d.setPayStudy("1");
				}else{
					d.setPayStudy("0");
				}
				if(pay_labor[i].equals("1")){
					d.setPayLabor("1");
				}else{
					d.setPayLabor("0");
				}
				if(pay_service[i].equals("1")){
					d.setPayService("1");
				}else{
					d.setPayService("0");
				}
				
				if(money_work[i].equals("1")){
					d.setMoneyWork("1");
				}else{
					d.setMoneyWork("0");
				}
				if(money_life[i].equals("1")){
					d.setMoneyLife("1");
				}else{
					d.setMoneyLife("0");
				}
				
				if(!Rc_aio[i].equals(""))d.setRcAio(Integer.parseInt(Rc_aio[i]));
				if(!unit[i].equals(""))d.setUnit(unit[i]);
				
				df.update(d);
			}catch(Exception e){
				Message msg=new Message();
				msg.setError("輸入欄位不完整");
				this.savMessage(msg);
			}			
		}		
		
		return search();
	}
	
	private List getUnits(){
		
		List<Map>list=df.sqlGet("SELECT ''as pname, c.id, c.name FROM CODE_UNIT c WHERE c.pid='0'");
		List units=new ArrayList();
		for(int i=0; i<list.size(); i++){
			
			units.add(list.get(i));
			units.addAll(df.sqlGet("SELECT '"+list.get(i).get("name")+"'as pname, c.id, c.name FROM CODE_UNIT c WHERE c.pid='"+list.get(i).get("id")+"'"));
			
			
		}
		
		return units;
		
	}
	
	public String dis,i_begin, i_end, job_res, job_tech, job_ser, pay_std, pay_lab, pay_ser, money_w, money_l;
	
	public String printAll() throws IOException{
		
		StringBuilder sb=new StringBuilder("SELECT u.name as uname, rc.Rc_name, "
		+ "cr.name as rct_name, c.*, s.student_name, d.* FROM((Dipost d LEFT "
		+ "OUTER JOIN Rc_aio rc ON d.Rc_aio=rc.Oid)LEFT OUTER JOIN CODE_RC_TABLE cr ON "
		+ "rc.Rc_table=cr.id)LEFT OUTER JOIN CODE_UNIT u ON d.unit=u.id,stmd s,"
		+ "CODE_DIPOST c WHERE d.StudentNo=s.student_no AND d.Kind=c.id ");
		
		if(!begin.equals(""))sb.append("AND d.SchoolYear>='"+begin+"'");
		if(!end.equals(""))sb.append("AND d.SchoolYear<='"+end+"'");
		
		if(!saly.equals("0"))sb.append("AND c.saly='1'");		
		if(!dis.equals("0"))sb.append("AND d.dis='1'");
		
		if(!i_begin.equals(""))sb.append("AND d.insurance_begin<='"+i_begin+"'");
		if(!i_end.equals(""))sb.append("AND d.insurance_end<='"+i_end+"'");
		
		if(!job_res.equals("0"))sb.append("AND d.job_research='1'");
		if(!job_tech.equals("0"))sb.append("AND d.job_teach='1'");
		if(!job_ser.equals("0"))sb.append("AND d.job_service='1'");
		
		if(!pay_std.equals("0"))sb.append("AND d.pay_study='1'");
		if(!pay_lab.equals("0"))sb.append("AND d.pay_labor='1'");
		if(!pay_ser.equals("0"))sb.append("AND d.pay_service='1'");
		
		
		if(!money_w.equals("0"))sb.append("AND d.money_work='1'");
		if(!money_l.equals("0"))sb.append("AND d.money_life='1'");
		
		sb.append("ORDER BY d.SchoolYear, d.occur_month");
		
		
		System.out.println(sb);
		
		
		List<Map>d=df.sqlGet(sb.toString());
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
		out.println ("  <Author>John</Author>");
		out.println ("  <LastAuthor>John</LastAuthor>");
		out.println ("  <LastPrinted>2018-01-08T02:46:24Z</LastPrinted>");
		out.println ("  <Created>2018-01-08T02:25:08Z</Created>");
		out.println ("  <LastSaved>2018-01-08T03:18:31Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>9690</WindowHeight>");
		out.println ("  <WindowWidth>24000</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>0</WindowTopY>");
		out.println ("  <ProtectStructure>False</ProtectStructure>");
		out.println ("  <ProtectWindows>False</ProtectWindows>");
		out.println (" </ExcelWorkbook>");
		out.println (" <Styles>");
		
		
		out.println ("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat/>");
		out.println ("   <Protection/>");
		out.println ("  </Style>");
		
		out.println ("  <Style ss:ID='s16'>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s17'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s18'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s19'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s20'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s21'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s22'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s23'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s24'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s25'>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s72'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='Short Date'/>");
		out.println ("  </Style>");
		
		
		
		
		
		
		/*out.println ("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat/>");
		out.println ("   <Protection/>");
		out.println ("  </Style>");*/
		
		
		out.println ("  <Style ss:ID='s165'>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s166'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s167'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s168'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s169'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s170'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s171'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s176'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s177'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s178'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s179'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		
		
		
		
		
		
		out.println (" </Styles>");
		out.println (" <Worksheet ss:Name='工作表1'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='_FilterDatabase' ss:RefersTo='=工作表1!R1C1:R3C34'");
		out.println ("    ss:Hidden='1'/>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=工作表1!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='34' ss:ExpandedRowCount='"+(d.size()+999)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s16' ss:DefaultColumnWidth='75'");
		out.println ("   ss:DefaultRowHeight='21'>");
		out.println ("   <Column ss:StyleID='s17' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s18' ss:AutoFitWidth='0' ss:Span='4'/>");
		out.println ("   <Column ss:Index='7' ss:StyleID='s19' ss:AutoFitWidth='0'/>");
		out.println ("   <Column ss:StyleID='s18' ss:AutoFitWidth='0' ss:Span='1'/>");
		out.println ("   <Column ss:Index='10' ss:StyleID='s18' ss:Width='79.5'/>");
		out.println ("   <Column ss:StyleID='s18' ss:AutoFitWidth='0' ss:Span='22'/>");
		out.println ("   <Column ss:Index='34' ss:StyleID='s20' ss:AutoFitWidth='0'/>");
		out.println ("   <Row ss:StyleID='s25'>");
		out.println ("    <Cell ss:StyleID='s21'><Data ss:Type='String'>學年</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>發放月份</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>單位</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>專案計畫</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>計畫類型</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>本校發放類型</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s23'><Data ss:Type='String'>學號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>姓名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>時數</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>加保日期</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>退保日期</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>身心障礙</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>僱主勞保</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>僱主健保</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>僱主二代健保</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>僱主勞退</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>勞保</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>健保</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>二代健保</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>申報金額</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>應付金額</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>實付金額</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>教育部補助</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>科技部補助</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>學校自籌</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>其他來源</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>研究助理</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>教學助理</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>工讀附服務</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>純助學</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>勞僱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>附服務負擔</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>工讀助學</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s24'><Data ss:Type='String'>生活助學</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/><NamedCell ss:Name='_FilterDatabase'/></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<d.size(); i++){
			
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("SchoolYear")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			if(d.get(i).get("occur_month")!=null){
				out.println ("    <Cell><Data ss:Type='String'>"+d.get(i).get("occur_month")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			
			if(d.get(i).get("uname")!=null){
				out.println ("    <Cell><Data ss:Type='String'>"+d.get(i).get("uname")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			
			if(d.get(i).get("Rc_name")!=null){
				out.println ("    <Cell><Data ss:Type='String'>"+d.get(i).get("Rc_name")+"<</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("rct_name")!=null){
				out.println ("    <Cell><Data ss:Type='String'>"+d.get(i).get("rct_name")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			out.println ("    <Cell><Data ss:Type='String'>"+d.get(i).get("name")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			
			
			out.println ("    <Cell><Data ss:Type='String'>"+d.get(i).get("StudentNo")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+d.get(i).get("student_name")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			
			if(d.get(i).get("hours")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("hours")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("insurance_begin")!=null){
				out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>"+d.get(i).get("insurance_begin")+"</String><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell ss:StyleID='s72'><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}

			if(d.get(i).get("insurance_end")!=null){
				out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'>"+d.get(i).get("insurance_end")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell ss:StyleID='s72'><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("dis")!=null){
				if(d.get(i).get("dis").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			
			if(d.get(i).get("hirer_labor")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("hirer_labor")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("hirer_health")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("hirer_health")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("hirer_health_two")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("hirer_health_two")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			if(d.get(i).get("hirer_retire")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("hirer_retire")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}

			if(d.get(i).get("self_labor")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("self_labor")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			if(d.get(i).get("self_health")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("self_health")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			if(d.get(i).get("self_health_two")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("self_health_two")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}

			if(d.get(i).get("pubmoney")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("pubmoney")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			if(d.get(i).get("payables")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("payables")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}

			out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("Money")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");


			if(d.get(i).get("origin_edu")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("origin_edu")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			if(d.get(i).get("origin_mst")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("origin_mst")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			if(d.get(i).get("origin_self")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("origin_self")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			if(d.get(i).get("origin_other")!=null){
				out.println ("    <Cell><Data ss:Type='Number'>"+d.get(i).get("origin_other")+"</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}else{
				out.println ("    <Cell><Data ss:Type='Number'>0</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			

			if(d.get(i).get("job_research")!=null){
				if(d.get(i).get("job_research").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}				
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("job_teach")!=null){
				if(d.get(i).get("job_teach").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}				
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("job_service")!=null){
				if(d.get(i).get("job_service").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}				
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("pay_study")!=null){
				if(d.get(i).get("pay_study").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}				
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("pay_labor")!=null){
				if(d.get(i).get("pay_labor").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}				
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("pay_service")!=null){
				if(d.get(i).get("pay_service").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}				
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("money_work")!=null){
				if(d.get(i).get("money_work").equals("1")){
					out.println ("    <Cell><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}				
			}else{
				out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			
			if(d.get(i).get("money_life")!=null){
				if(d.get(i).get("money_life").equals("1")){
					out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>是</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}else{
					out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>否</Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
				}				
			}else{
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'></Data><NamedCell ss:Name='_FilterDatabase'/></Cell>");
			}
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Layout x:Orientation='Landscape'/>");
		out.println ("    <Header x:Margin='0.31496062992125984'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984'/>");
		out.println ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.23622047244094491'");
		out.println ("     x:Right='0.23622047244094491' x:Top='0.74803149606299213'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
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
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println ("  <AutoFilter x:Range='R1C1:R3C34'");
		out.println ("   xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  </AutoFilter>");
		out.println (" </Worksheet>");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.println (" <Worksheet ss:Name='工作表22'>");
		out.println ("  <Table ss:ExpandedColumnCount='7' ss:ExpandedRowCount='13' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s165' ss:DefaultColumnWidth='117.75'");
		out.println ("   ss:DefaultRowHeight='21'>");
		out.println ("   <Row ss:Height='126' ss:StyleID='s179'>");
		out.println ("    <Cell ss:StyleID='s176'><Data ss:Type='String'>服務月數&#10;月支領金額</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s177'><Data ss:Type='String'>月領&#10;3000元&#10;以下</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s177'><Data ss:Type='String'>月領&#10;3001~&#10;6000元&#10;以下</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s177'><Data ss:Type='String'>月領&#10;6001~&#10;9000元&#10;以下</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s177'><Data ss:Type='String'>月領&#10;9001~&#10;11100元&#10;以下</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s177'><Data ss:Type='String'>月領&#10;11101~&#10;21009元&#10;以下</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s178'><Data ss:Type='String'>月領&#10;21010元&#10;以上</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>1個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'><Data ss:Type='String'>23</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'><Data ss:Type='String'>23</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'><Data ss:Type='String'>23</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'><Data ss:Type='String'>23</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'><Data ss:Type='String'>23</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s168'><Data ss:Type='String'>23</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>2個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>3個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>4個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>5個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>6個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>7個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>8個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>9個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>10個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s166'><Data ss:Type='String'>11個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s167'/>");
		out.println ("    <Cell ss:StyleID='s168'/>");
		out.println ("   </Row>");
		out.println ("   <Row ss:Height='21.75'>");
		out.println ("    <Cell ss:StyleID='s169'><Data ss:Type='String'>12個月</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s170'/>");
		out.println ("    <Cell ss:StyleID='s170'/>");
		out.println ("    <Cell ss:StyleID='s170'/>");
		out.println ("    <Cell ss:StyleID='s170'/>");
		out.println ("    <Cell ss:StyleID='s170'/>");
		out.println ("    <Cell ss:StyleID='s171'/>");
		out.println ("   </Row>");
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Layout x:Orientation='Landscape'/>");
		out.println ("    <Header x:Margin='0.3'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
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
	
	public String print710(){
		
		
		return null;
	}

}
