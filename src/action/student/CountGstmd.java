package action.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Message;
import action.BaseAction;

public class CountGstmd extends BaseAction{
	
	
	public String year;
	public String term;
	public String cno;
	public String tno;	
	public String gno;
	
	public String execute(){
		
		
		return SUCCESS;
	}
	
	public String print() throws IOException{
		
		try{
			Integer.parseInt(year);
		}catch(Exception e){
			Message msg=new Message();
			msg.setError("學年度為必要欄位");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		
		Date date=new Date();
		
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");			
		
		PrintWriter out=response.getWriter();
		
		out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>shawn</Author>");
		out.println ("  <LastAuthor>shawn</LastAuthor>");
		out.println ("  <LastPrinted>2014-04-01T01:41:57Z</LastPrinted>");
		out.println ("  <Created>2014-04-01T00:20:25Z</Created>");
		out.println ("  <LastSaved>2014-04-01T01:42:06Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>12540</WindowHeight>");
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
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat/>");
		out.println ("   <Protection/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s16' ss:Name='輸出'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#3F3F3F' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#F2F2F2' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s17'>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s18'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s19' ss:Parent='s16'>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s20'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s21' ss:Parent='s16'>");
		out.println ("   <Alignment ss:Vertical='Justify'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s22'>");
		out.println ("   <Alignment ss:Vertical='Justify'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s23'>");
		out.println ("   <Alignment ss:Vertical='Justify'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s70' ss:Parent='s16'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s71'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s74' ss:Parent='s16'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#3F3F3F'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='9'");
		out.println ("    ss:Color='#3F3F3F' ss:Bold='1'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		List<Map>list=df.sqlGet("SELECT IFNULL(e.cname, '')as cname,c.ClassName, s.student_no, s.student_name, IF(s.occur_status='1', '休學', '退學')as occur_status,"
		+ "c5.name,(SELECT COUNT(*)FROM StudCounseling WHERE studentNo=s.student_no AND ctype='T' AND schoolYear='102' AND schoolTerm='1')as t,"
		+ "(SELECT COUNT(*)FROM StudCounseling WHERE studentNo=s.student_no AND ctype='U' AND schoolYear='102' AND schoolTerm='1')as u,"
		+ "(SELECT COUNT(*)FROM StudCounseling WHERE studentNo=s.student_no AND ctype='L' AND schoolYear='102' AND schoolTerm='1')as l "
		+ "FROM Gstmd s, code5 c5, Class c LEFT OUTER JOIN empl e ON c.tutor=e.idno WHERE c5.idno=s.occur_cause AND c5.category='Cause'AND "
		+ "s.depart_class=c.ClassNo AND c.SchoolType LIKE'"+tno+"%'AND c.Grade LIKE'"+gno+"%' AND s.occur_year='"+year+"' AND s.occur_term='"+term+"'ORDER BY c.DeptNo, c.ClassNo");
		
		out.println (" <Worksheet ss:Name='SHEET1'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=SHEET1!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='9' ss:ExpandedRowCount='"+(list.size()+100)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s17' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='81'/>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='63'/>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='159.75'/>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='40.5'/>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='41.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='45'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s70'><Data ss:Type='String'>班級</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s70'><Data ss:Type='String'>學號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s70'><Data ss:Type='String'>姓名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s70'><Data ss:Type='String'>狀態</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s70'><Data ss:Type='String'>原因</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s74'><Data ss:Type='String'>導師關懷</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s74'><Data ss:Type='String'>導師學習</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s74'><Data ss:Type='String'>教師學習</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s70'><Data ss:Type='String'>導師</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		
		for(int i=0; i<list.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("student_no")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("student_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("occur_status")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("t")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("u")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("l")+"</Data></Cell>");
			out.println ("    <Cell ss:Index='9'><Data ss:Type='String'>"+list.get(i).get("cname")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
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
		out.println ("     <ActiveRow>6</ActiveRow>");
		out.println ("     <ActiveCol>5</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		
		list=df.sqlGet("SELECT c.ClassName, s.student_no, s.student_name, sc.ctype, IFNULL(e.cname,'')as cname, sc.content, sc.cdate FROM "
				+ "Gstmd s, Class c, StudCounseling sc LEFT OUTER JOIN empl e ON e.idno=sc.teacherId WHERE "
				+ "sc.studentNo=s.student_no AND sc.schoolYear='"+year+"' AND sc.schoolTerm='"+term+"' AND "
				+ "s.depart_class=c.ClassNo AND c.SchoolType LIKE'"+tno+"%'AND c.Grade LIKE'"+gno+"%' AND s.occur_year='"+year+"' "
				+ "AND s.occur_term='"+term+"'ORDER BY c.DeptNo, s.student_no");
		
		
		
		out.println (" <Worksheet ss:Name='SHEET2'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=SHEET2!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='6' ss:ExpandedRowCount='"+(list.size()+100)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='80.25'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='67.5'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Span='2'/>");
		out.println ("   <Column ss:Index='6' ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='271.5'/>");
		out.println ("   <Row ss:StyleID='s17'>");
		out.println ("    <Cell ss:StyleID='s19'><Data ss:Type='String'>班級</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s19'><Data ss:Type='String'>學號</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s19'><Data ss:Type='String'>姓名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s19'><Data ss:Type='String'>輔導類型</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s19'><Data ss:Type='String'>輔導老師</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s21'><Data ss:Type='String'>輔導內容</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<list.size(); i++){
			try{
				out.println ("   <Row ss:Height='33' ss:StyleID='s17'>");
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>"+list.get(i).get("ClassName")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>"+list.get(i).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>"+list.get(i).get("student_name")+"</Data></Cell>");
				if(list.get(i).get("ctype").equals("T")){
					out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>導師關懷</Data></Cell>");
				}				
				if(list.get(i).get("ctype").equals("U")){
					out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>導師學習</Data></Cell>");
				}				
				if(list.get(i).get("ctype").equals("L")){
					out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>教師學習</Data></Cell>");
				}				
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>"+list.get(i).get("cname")+"</Data></Cell>");			
				out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>"+replaceChar4XML(list.get(i).get("content").toString())+list.get(i).get("cdate")+"</Data></Cell>");
				out.println ("   </Row>");
			}catch(Exception e){
				out.println ("   <Row ss:Height='33' ss:StyleID='s17'>");
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>"+list.get(i).get("ClassName")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>"+list.get(i).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>"+list.get(i).get("student_name")+"</Data></Cell>");				
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>???</Data></Cell>");				
				out.println ("    <Cell ss:StyleID='s18'><Data ss:Type='String'>???</Data></Cell>");			
				out.println ("    <Cell ss:StyleID='s22'><Data ss:Type='String'>???</Data></Cell>");
				out.println ("   </Row>");
			}
						
		}		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
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
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>9</ActiveRow>");
		out.println ("     <ActiveCol>6</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		out.println ("</Workbook>");
		out.println ("");
		
		return null;
	}
	
	private String replaceChar4XML(String str){
		try{
			str=str.replaceAll("&", "&amp;");
			str=str.replaceAll("<", "&lt;");
			str=str.replaceAll(">", "&gt;");
			str=str.replaceAll("\"", "&quot;");
			str=str.replaceAll("", "\\n");
			
			//str=str.replaceAll(",", "&cedil;");
			str=str.replaceAll("'", "&apos;");
		}catch(NullPointerException e){
			return str;
		}
		
		return str;
	}

}
