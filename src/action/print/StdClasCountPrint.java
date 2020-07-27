package action.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class StdClasCountPrint extends BasePrintXmlAction{
	
	public String execute() throws IOException{
		
		print(response);
		return null;
	}
	
	public void print(HttpServletResponse response) throws IOException{
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
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
		out.println ("  <Author>shawn</Author>");
		out.println ("  <LastAuthor>shawn</LastAuthor>");
		out.println ("  <LastPrinted>2015-11-18T04:08:54Z</LastPrinted>");
		out.println ("  <Created>2015-11-16T08:46:33Z</Created>");
		out.println ("  <LastSaved>2015-11-27T00:50:34Z</LastSaved>");
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
		out.println ("  <Style ss:ID='m347608619184'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m347608619204'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m347608619224'>");
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
		out.println ("  <Style ss:ID='m347608619244'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m347608626880'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m347608626900'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#808080' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m347608626920'>");
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
		out.println ("  <Style ss:ID='m347608626940'>");
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
		out.println ("  <Style ss:ID='m347608626960'>");
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
		out.println ("  <Style ss:ID='m347608626980'>");
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
		out.println ("  <Style ss:ID='s62'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s75'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s76'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s77'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s78'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s97'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s98'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s99'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s100'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s101'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s102'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
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
		out.println ("  <Style ss:ID='s103'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
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
		out.println ("  <Style ss:ID='s104'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
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
		out.println ("  <Style ss:ID='s105'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
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
		out.println ("  <Style ss:ID='s106'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
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
		out.println ("  <Style ss:ID='s107'>");
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
		out.println ("  <Style ss:ID='s108'>");
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
		out.println ("  <Style ss:ID='s109'>");
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
		out.println ("  <Style ss:ID='s110'>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s111'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s112'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s113'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		df.exSql("UPDATE Class SET stds=(SELECT COUNT(*)FROM stmd WHERE depart_class=Class.ClassNo)");
		
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
		out.println ("  <Table ss:ExpandedColumnCount='4' ss:ExpandedRowCount='8' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s62' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='32.25'>");
		out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='137.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='164.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='149.25'/>");
		out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='135'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m347608626880'><Data ss:Type='String'>在籍學生</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m347608626900'><Data ss:Type='String'>延修學生</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m347608626920'><Data ss:Type='String'>"+map.get("usual")+"班</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m347608626940'><Data ss:Type='String'>"+map.get("delay")+"班</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s76'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s76'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0' ss:Height='33'>");
		out.println ("    <Cell ss:StyleID='s77'><Data ss:Type='Number'>"+(map.get("usCount")-map.get("usFemale"))+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s78'><Data ss:Type='Number'>"+map.get("usFemale")+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s77'><Data ss:Type='Number'>"+(map.get("deCount")-map.get("deFemale"))+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s78'><Data ss:Type='Number'>"+map.get("deFemale")+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0' ss:Height='33'>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m347608626960'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m347608626980'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0' ss:Height='33'>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m347608619184'><Data ss:Type='Number'>"+map.get("usCount")+"</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='1' ss:StyleID='m347608619204'><Data ss:Type='Number'>"+map.get("deCount")+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:MergeAcross='3' ss:StyleID='m347608619224'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:MergeAcross='3' ss:StyleID='m347608619244'><Data ss:Type='Number'>"+(map.get("usCount")+map.get("deCount"))+"</Data></Cell>");
		out.println ("   </Row>");
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3'");
		out.println ("     x:Data='&amp;C&amp;&quot;新細明體,粗體&quot;&amp;24全校班級數統計&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("    <Gridlines/>");
		out.println ("   </Print>");
		out.println ("   <PageBreakZoom>60</PageBreakZoom>");
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
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(t.size()+9)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s97' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='199.5'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:Width='35.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s102'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>班級數</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s106'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");		
		for(int i=0; i<r.size(); i++){			
			out.println ("   <Row ss:AutoFitHeight='0'>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;24院班級數統計&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
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
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(t.size()+9)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s97' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='199.5'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:Width='35.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s102'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>班級數</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s106'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		
		
		for(int i=0; i<r.size(); i++){			
			out.println ("   <Row ss:AutoFitHeight='0'>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;24院班級數統計&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
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
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(t.size()+9)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s97' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='199.5'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:Width='35.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s102'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>班級數</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s106'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<r.size(); i++){			
			out.println ("   <Row ss:AutoFitHeight='0'>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;24院班級數統計&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
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
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(t.size()+9)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s97' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='199.5'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:Width='35.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s102'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>班級數</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s106'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<r.size(); i++){			
			out.println ("   <Row ss:AutoFitHeight='0'>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;24院班級數統計&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
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
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(t.size()+9)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s97' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='199.5'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:Width='35.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s99' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s100' ss:AutoFitWidth='0' ss:Width='32.25' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s101' ss:Width='38.25'/>");
		out.println ("   <Column ss:StyleID='s98' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s102'><Data ss:Type='String'>名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>班級數</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s103'><Data ss:Type='String'>延修班</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>男</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s104'><Data ss:Type='String'>女</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s105'><Data ss:Type='String'>小計</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s106'><Data ss:Type='String'>合計</Data></Cell>");
		out.println ("   </Row>");
		for(int i=0; i<r.size(); i++){			
			out.println ("   <Row ss:AutoFitHeight='0'>");
			out.println ("    <Cell><Data ss:Type='String'>"+t.get(i).get("name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usual")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("usCount")-r.get(i).get("usFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("usCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("delay")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+((r.get(i).get("deCount")-r.get(i).get("deFemale")))+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deFemale")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+r.get(i).get("deCount")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='Number'>"+(r.get(i).get("usCount")+r.get(i).get("deCount"))+"</Data></Cell>");
			out.println ("   </Row>");
		}
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;24院班級數統計&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
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
		
		
		out.println (" <Worksheet ss:Name='全部班級'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='_FilterDatabase' ss:RefersTo='=班級!R1C1:R336C10'");
		out.println ("    ss:Hidden='1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(s.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s110' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='75.75'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='96'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='21'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='23.25'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>校區</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>學院</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>部制</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>學制</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>系</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>型態</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>合計</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("   </Row>");		
		for(int i=0; i<s.size(); i++){
			out.println ("   <Row ss:AutoFitHeight='0'>");			
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
		out.println ("    <Header x:Margin='0.3'");
		out.println ("     x:Data='&amp;C&amp;&quot;新細明體,粗體&quot;&amp;24全校班級明細&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
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
		out.println ("  <AutoFilter x:Range='R1C1:R336C10'");
		out.println ("   xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  </AutoFilter>");
		out.println (" </Worksheet>");
		
		
		
		out.println (" <Worksheet ss:Name='在籍班'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='_FilterDatabase' ss:RefersTo='=在籍班!R1C1:R336C10'");
		out.println ("    ss:Hidden='1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(s.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s110' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='75.75'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='96'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='21'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='23.25'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>校區</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>學院</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>部制</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>學制</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>系</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>型態</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>合計</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("   </Row>");		
		for(int i=0; i<s.size(); i++){
			if(s.get(i).get("Money")==null)continue;
			out.println ("   <Row ss:AutoFitHeight='0'>");
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
		out.println ("    <Header x:Margin='0.3'");
		out.println ("     x:Data='&amp;C&amp;&quot;新細明體,粗體&quot;&amp;24全校班級明細&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
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
		out.println ("  <AutoFilter x:Range='R1C1:R336C10'");
		out.println ("   xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  </AutoFilter>");
		out.println (" </Worksheet>");
		
		
		
		
		out.println (" <Worksheet ss:Name='非在籍班'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='_FilterDatabase' ss:RefersTo='=非在籍班!R1C1:R336C10'");
		out.println ("    ss:Hidden='1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(s.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s110' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='75.75'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='96'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='21'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='23.25'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>校區</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>學院</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>部制</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>學制</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>系</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>型態</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>合計</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("   </Row>");		
		for(int i=0; i<s.size(); i++){
			
			if(s.get(i).get("Money")!=null)continue;
			if(s.get(i).get("Type").toString().equals("E"))continue;
			out.println ("   <Row ss:AutoFitHeight='0'>");
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
		out.println ("    <Header x:Margin='0.3'");
		out.println ("     x:Data='&amp;C&amp;&quot;新細明體,粗體&quot;&amp;24全校班級明細&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
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
		out.println ("  <AutoFilter x:Range='R1C1:R336C10'");
		out.println ("   xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  </AutoFilter>");
		out.println (" </Worksheet>");
		
		
		
		out.println (" <Worksheet ss:Name='延修班'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='_FilterDatabase' ss:RefersTo='=延修班!R1C1:R336C10'");
		out.println ("    ss:Hidden='1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='10' ss:ExpandedRowCount='"+(s.size()+99)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s110' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='75.75'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='103.5'/>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='96'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='55.5'/>");
		out.println ("   <Column ss:StyleID='s111' ss:AutoFitWidth='0' ss:Width='21'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='23.25'/>");
		out.println ("   <Column ss:StyleID='s113' ss:AutoFitWidth='0' ss:Width='32.25'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>校區</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>學院</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>部制</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>學制</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>系</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>型態</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s107'><Data ss:Type='String'>男</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s108'><Data ss:Type='String'>女</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("    <Cell ss:StyleID='s109'><Data ss:Type='String'>合計</Data><NamedCell");
		out.println ("      ss:Name='_FilterDatabase'/></Cell>");
		out.println ("   </Row>");		
		for(int i=0; i<s.size(); i++){			
			if(!s.get(i).get("Type").toString().equals("E"))continue;
			out.println ("   <Row ss:AutoFitHeight='0'>");
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
		out.println ("    <Header x:Margin='0.3'");
		out.println ("     x:Data='&amp;C&amp;&quot;新細明體,粗體&quot;&amp;24全校班級明細&amp;R&amp;&quot;新細明體,粗體&quot;統計日期 "+sf.format(date)+"'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;C&amp;P/&amp;N'/>");
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
		out.println ("  <AutoFilter x:Range='R1C1:R336C10'");
		out.println ("   xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  </AutoFilter>");
		out.println (" </Worksheet>");
		
		out.println ("</Workbook>");
		
		out.close();
		out.flush();
		
	}
	
	private Map count(List<Map>s, String key, String value){
		Map map=new HashMap();
		//int unusual=0, unCount=0, unFemale=0;
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
				}/*else{//異常班
					unusual++;
					unCount+=(Integer.parseInt(s.get(i).get("stds").toString()));
					unFemale+=(Integer.parseInt(s.get(i).get("stds2").toString()));
				}*/				
			}
			
		}		
		//map.put("unusual", unusual);
		//map.put("unCount", unCount);
		//map.put("unFemale", unFemale);
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
