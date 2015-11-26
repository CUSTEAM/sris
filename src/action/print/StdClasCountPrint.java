package action.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BaseAction;

public class StdClasCountPrint extends BaseAction{
	
	public String execute() throws IOException{
		
		print(response);
		return null;
	}
	
	public void print(HttpServletResponse response) throws IOException{
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
		out.println ("  <LastPrinted>2015-11-18T04:08:54Z</LastPrinted>");
		out.println ("  <Created>2015-11-16T08:46:33Z</Created>");
		out.println ("  <LastSaved>2015-11-18T04:10:15Z</LastSaved>");
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
		out.println ("  <TabRatio>731</TabRatio>");
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
		out.println ("  <Style ss:ID='m131840629376'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629396'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629416'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629436'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629456'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629476'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629496'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629516'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629536'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629556'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629576'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840629596'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840643488'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840643508'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840643528'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840643548'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m131840643568'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s19'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s20'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s21'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s23'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s24'>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s28'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s33'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s34'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s44'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s45'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s46'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s150'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s151'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s152'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s153'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s161'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s162'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s163'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s181'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s182'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s189'>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		
		String sql="SELECT cc.name as CampusName, cst.name as TypeName, cs.name "
		+ "as SchoolName, cco.name as CollegeName,cd.name as DeptName,SUM(f.Money)as Money,"
		+ "(SELECT COUNT(*)FROM stmd WHERE depart_class=c.ClassNo AND sex='2')as stds2, c.* "
		+ "FROM (Class c LEFT OUTER JOIN FeePay f ON c.ClassNo=f.DepartClass)LEFT OUTER JOIN "
		+ "CODE_COLLEGE cco ON c.InstNo=cco.id, CODE_CAMPUS cc, CODE_DEPT cd,CODE_SCHOOL cs, "
		+ "CODE_SCHOOL_TYPE cst WHERE c.SchoolType=cst.id AND c.SchoolNo=cs.id AND c.DeptNo=cd.id "
		+ "AND c.CampusNo=cc.id GROUP BY c.ClassNo HAVING stds>0;";
		//System.out.println(sql);
		List<Map>s=df.sqlGet(sql);
		
		
		Map<String, Integer>map=count(s, null, null);
		
		out.println (" <Worksheet ss:Name='全校'>");
		out.println ("  <Table ss:ExpandedColumnCount='6' ss:ExpandedRowCount='9' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s28' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='32.25'>");
		out.println ("   <Column ss:StyleID='s28' ss:AutoFitWidth='0' ss:Width='98.25'/>");
		out.println ("   <Column ss:StyleID='s28' ss:AutoFitWidth='0' ss:Width='87.75'/>");
		out.println ("   <Column ss:StyleID='s28' ss:AutoFitWidth='0' ss:Width='84'/>");
		out.println ("   <Column ss:StyleID='s28' ss:AutoFitWidth='0' ss:Width='140.25'/>");
		out.println ("   <Column ss:StyleID='s28' ss:AutoFitWidth='0' ss:Width='114'/>");
		out.println ("   <Column ss:StyleID='s28' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840643488'><Data ss:Type='String'>印制繳費單的班級</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840643508'><Data ss:Type='String'>未印制繳費單且非延修班級</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840643528'><Data ss:Type='String'>延修班級</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629456'><Data ss:Type='String'>常態班級</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629536'><Data ss:Type='String'>非常態班級</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840643548'><Data ss:Type='String'>延修班級</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629476'><Data ss:Type='Number'>"+map.get("usual")+"</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629556'><Data ss:Type='Number'>"+map.get("unusual")+"</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840643568'><Data ss:Type='Number'>"+map.get("delay")+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s33'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s34'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s33'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s34'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s33'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s34'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:Height='33'>");
		out.println ("    <Cell ss:StyleID='s181'><Data ss:Type='Number'>"+(map.get("usCount")-map.get("usFemale"))+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s182'><Data ss:Type='Number'>"+map.get("usFemale")+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s181'><Data ss:Type='Number'>"+(map.get("unCount")-map.get("unFemale"))+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s182'><Data ss:Type='Number'>"+map.get("unFemale")+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s181'><Data ss:Type='Number'>"+(map.get("deCount")-map.get("deFemale"))+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s182'><Data ss:Type='Number'>"+map.get("deFemale")+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:Height='33'>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629496'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629576'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629376'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:Height='33'>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629516'><Data ss:Type='Number'>"+map.get("usCount")+"</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629596'><Data ss:Type='Number'>"+map.get("unCount")+"</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m131840629396'><Data ss:Type='Number'>"+map.get("deCount")+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:Height='33'>");
		out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='m131840629416'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:Height='33'>");
		out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='m131840629436'><Data ss:Type='Number'>"+(map.get("usCount")+map.get("unCount")+map.get("deCount"))+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;新細明體,粗體&quot;&amp;20全校班級數統計&amp;G'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("    <Gridlines/>");
		out.println ("   </Print>");
		out.println ("   <Zoom>70</Zoom>");
		out.println ("   <Selected/>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <RangeSelection>R1C1:R1C2</RangeSelection>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		List<Map<String,String>>t=df.sqlGet("SELECT id, name FROM CODE_CAMPUS");
		List<Map<String, Integer>>r=new ArrayList();
		for(int i=0; i<t.size(); i++){			
			r.add(count(s, "CampusNo", t.get(i).get("id").toString()));
		}		
		out.println (" <Worksheet ss:Name='校區'>");
		out.println ("  <Table ss:ExpandedColumnCount='14' ss:ExpandedRowCount='"+(r.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='44.25'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='27.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='13' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>常態班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>非常態班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		/*
		map.put("unusual", unusual);
		map.put("unCount", unCount);
		map.put("unFemale", unFemale);
		map.put("usual", usual);
		map.put("usCount", usCount);
		map.put("usFemale", usFemale);
		map.put("delay", delay);
		map.put("deCount", deCount);
		map.put("deFemale", deFemale);
		*/
		for(int i=0; i<r.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unusual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("unCount")-r.get(i).get("unFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");			
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("unCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;20校區班級數統計&amp;G'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		t=df.sqlGet("SELECT id, name FROM CODE_COLLEGE");
		r=new ArrayList();
		for(int i=0; i<t.size(); i++){
			r.add(count(s, "InstNo", t.get(i).get("id")));
		}
		out.println (" <Worksheet ss:Name='院'>");
		out.println ("  <Table ss:ExpandedColumnCount='14' ss:ExpandedRowCount='"+(r.size()+9)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='44.25'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='27.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='13' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>常態班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>非常態班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<r.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unusual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("unCount")-r.get(i).get("unFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");			
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("unCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;20院班級數統計&amp;G'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		t=df.sqlGet("SELECT id, name FROM CODE_SCHOOL_TYPE");
		r=new ArrayList();
		for(int i=0; i<t.size(); i++){			
			r.add(count(s, "SchoolType", t.get(i).get("id")));
		}
		out.println (" <Worksheet ss:Name='部制'>");
		out.println ("  <Table ss:ExpandedColumnCount='14' ss:ExpandedRowCount='"+(r.size()+9)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='44.25'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='27.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='13' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>常態班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>非常態班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<r.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unusual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("unCount")-r.get(i).get("unFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");			
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("unCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;20部制班級數統計&amp;G'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		t=df.sqlGet("SELECT id, name FROM CODE_SCHOOL");
		r=new ArrayList();
		for(int i=0; i<t.size(); i++){			
			r.add(count(s, "SchoolNo", t.get(i).get("id")));
		}
		out.println (" <Worksheet ss:Name='學制'>");
		out.println ("  <Table ss:ExpandedColumnCount='14' ss:ExpandedRowCount='"+(r.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='135.75'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='44.25'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='39.75'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='44.25'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='27.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='13' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='39.75'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>常態班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>非常態</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<r.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unusual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("unCount")-r.get(i).get("unFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");			
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("unCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;20學制班級數統計&amp;G'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		t=df.sqlGet("SELECT id, name FROM CODE_DEPT");
		r=new ArrayList();
		for(int i=0; i<t.size(); i++){			
			r.add(count(s, "DeptNo", t.get(i).get("id")));
		}
		out.println (" <Worksheet ss:Name='系'>");
		out.println ("  <Table ss:ExpandedColumnCount='14' ss:ExpandedRowCount='"+(r.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='127.5'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s19' ss:AutoFitWidth='0' ss:Width='44.25'/>");
		out.println ("   <Column ss:StyleID='s20' ss:AutoFitWidth='0' ss:Width='27.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='13' ss:StyleID='s21' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Column ss:StyleID='s23' ss:AutoFitWidth='0' ss:Width='44.25'/>");
		out.println ("   <Row ss:StyleID='s189'>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>常態班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>非常態</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s151'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s152'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s153'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s150'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<r.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unusual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("unCount")-r.get(i).get("unFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("unCount")+"</Data></Cell>");
			
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");			
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("unCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;20系所班級數統計&amp;G'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		
		out.println (" <Worksheet ss:Name='班級'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=班級!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(s.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s24' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='75.75'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='96'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='21'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='23.25'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>校區</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>學院</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>部制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>學制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>系</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>型態</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>合計</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<s.size(); i++){
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("CampusName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("CollegeName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("TypeName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("SchoolName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("DeptName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+getTypeName(s.get(i).get("Type").toString())+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(Integer.parseInt(s.get(i).get("stds").toString()) - Integer.parseInt( s.get(i).get("stds2").toString() )   )+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+s.get(i).get("stds2")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+s.get(i).get("stds")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984' x:Data='&amp;C&amp;20全校班級明細&amp;G'/>");
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
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.println (" <Worksheet ss:Name='常態列表'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=常態列表!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(s.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s24' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='75.75'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='96'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='21'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='23.25'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>校區</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>學院</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>部制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>學制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>系</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>型態</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>合計</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<s.size(); i++){
			if(s.get(i).get("Money")==null)continue;
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("CampusName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("CollegeName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("TypeName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("SchoolName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("DeptName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+getTypeName(s.get(i).get("Type").toString())+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(  Integer.parseInt(s.get(i).get("stds").toString()) - Integer.parseInt( s.get(i).get("stds2").toString() )   )+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+s.get(i).get("stds2")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+s.get(i).get("stds")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984' x:Data='&amp;C&amp;20全校班級明細&amp;G'/>");
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
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		
		
		
		
		out.println (" <Worksheet ss:Name='非常態列表'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=非常態列表!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(s.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s24' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='75.75'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='96'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='21'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='23.25'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>校區</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>學院</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>部制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>學制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>系</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>型態</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>合計</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<s.size(); i++){
			if(s.get(i).get("Money")!=null)continue;
			if(s.get(i).get("Type").toString().equals("E"))continue;
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("CampusName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("CollegeName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("TypeName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("SchoolName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("DeptName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+getTypeName(s.get(i).get("Type").toString())+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(  Integer.parseInt(s.get(i).get("stds").toString()) - Integer.parseInt( s.get(i).get("stds2").toString() )   )+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+s.get(i).get("stds2")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+s.get(i).get("stds")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984' x:Data='&amp;C&amp;20全校班級明細&amp;G'/>");
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
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		
		out.println (" <Worksheet ss:Name='延修列表'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=延修列表!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(s.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s24' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='75.75'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='96'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s44' ss:AutoFitWidth='0' ss:Width='21'/>");
		out.println ("   <Column ss:StyleID='s45' ss:AutoFitWidth='0' ss:Width='23.25'/>");
		out.println ("   <Column ss:StyleID='s46' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Row>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>校區</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>學院</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>部制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>學制</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>系</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>型態</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s161'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s162'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s163'><Data ss:Type='String'>合計</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<s.size(); i++){			
			if(!s.get(i).get("Type").toString().equals("E"))continue;
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("CampusName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("CollegeName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("TypeName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("SchoolName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("DeptName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+s.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+getTypeName(s.get(i).get("Type").toString())+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(  Integer.parseInt(s.get(i).get("stds").toString()) - Integer.parseInt( s.get(i).get("stds2").toString() )   )+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+s.get(i).get("stds2")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+s.get(i).get("stds")+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.31496062992125984' x:Data='&amp;C&amp;20全校班級明細&amp;G'/>");
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
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		out.println ("</Workbook>");
	}
	
	private Map count(List<Map>s, String key, String value){
		Map map=new HashMap();
		int unusual=0, unCount=0, unFemale=0;
		int usual=0, usCount=0, usFemale=0;
		int delay=0, deCount=0, deFemale=0;
		
		for(int i=0; i<s.size(); i++){
			if(key!=null){				
				if(s.get(i).get(key)==null){
					continue;
				}
				if(!s.get(i).get(key).equals(value)){
					continue;
				}
			}
			
			if(s.get(i).get("Money")!=null){//常態班
				usual++;
				usCount+=(Integer.parseInt(s.get(i).get("stds").toString()));
				usFemale+=(Integer.parseInt(s.get(i).get("stds2").toString()));
			}else{				
				if(s.get(i).get("Type").toString().equals("E")){//延修班					
					delay++;
					deCount+=(Integer.parseInt(s.get(i).get("stds").toString()));
					deFemale+=(Integer.parseInt(s.get(i).get("stds2").toString()));
				}else{//異常班
					unusual++;
					unCount+=(Integer.parseInt(s.get(i).get("stds").toString()));
					unFemale+=(Integer.parseInt(s.get(i).get("stds2").toString()));
				}				
			}
			
		}		
		map.put("unusual", unusual);
		map.put("unCount", unCount);
		map.put("unFemale", unFemale);
		map.put("usual", usual);
		map.put("usCount", usCount);
		map.put("usFemale", usFemale);
		map.put("delay", delay);
		map.put("deCount", deCount);
		map.put("deFemale", deFemale);
		return map;
	}
	
	/**
	 * 班級類別:P)實體班級; V)虛擬班級; E)延修班級; O)廢止班級;跨校:C,學分:N
	 */
	private String getTypeName(String type){
		
		switch(type) {
	        case "P":return "實體班級";            
	        case "V":return "虛擬班級";
	        case "E":return "延修班級";
	        case "O":return "廢止班級";
	        case "C":return "跨校班級";
	        case "N":return "學分班級";        
	        default:return "未知班級";
		}
	}
	
	

}
