package action.student.CheckStmdProfile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 新生填報統計
 * @author John
 *
 */
public class CheckEditPrint {
	
	public void print(HttpServletResponse response, List<Map>cls) throws IOException{
		
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
		out.println ("  <Author>John</Author>");
		out.println ("  <LastAuthor>John</LastAuthor>");
		out.println ("  <LastPrinted>2013-08-19T02:39:02Z</LastPrinted>");
		out.println ("  <Created>2013-08-19T02:30:57Z</Created>");
		out.println ("  <Version>14.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <RelyOnVML/>");
		out.println ("  <AllowPNG/>");
		out.println ("  <DoNotUseLongFilenames/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>11430</WindowHeight>");
		out.println ("  <WindowWidth>24795</WindowWidth>");
		out.println ("  <WindowTopX>240</WindowTopX>");
		out.println ("  <WindowTopY>105</WindowTopY>");
		out.println ("  <ActiveSheet>"+cls.size()+"</ActiveSheet>");
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
		out.println ("  <Style ss:ID='m81669632'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m81669652'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m81669672'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("<Style ss:ID='s61' ss:Name='超連結'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		//out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#0000FF' ss:Underline='Single'/>");
		out.println ("  </Style>");
		
		out.println ("  <Style ss:ID='s62'>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		
		out.println ("  <Style ss:ID='s63'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#404040' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s64'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#404040' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s65'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF' ss:Bold='1'/>");
		out.println ("   <Interior ss:Color='#404040' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s66'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s67'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s75'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF'/>");
		out.println ("   <Interior ss:Color='#404040' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s76'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s77'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s78'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF'/>");
		out.println ("   <Interior ss:Color='#404040' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s80'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s81'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#FFFFFF'/>");
		out.println ("   <Interior ss:Color='#404040' ss:Pattern='Solid'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s82'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s83'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s84'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");		
		
		int x,y;
		float z;
		List<Map>stds;		
		for(int i=0; i<cls.size(); i++){			
			stds=(List)cls.get(i).get("stds");			
			out.println (" <Worksheet ss:Name='"+cls.get(i).get("ClassNo")+"'>");
			out.println ("  <Table ss:ExpandedColumnCount='9' ss:ExpandedRowCount='"+(stds.size()*3)+100+"' x:FullColumns='1'");
			out.println ("   x:FullRows='1' ss:StyleID='s62' ss:DefaultColumnWidth='54'");
			out.println ("   ss:DefaultRowHeight='16.5'>");
			out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='60'/>");
			out.println ("   <Column ss:Index='4' ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='64.5'/>");
			out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='57.75'/>");
			out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='63.75'/>");
			out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='123'/>");
			out.println ("   <Column ss:StyleID='s62' ss:AutoFitWidth='0' ss:Width='62.25'/>");
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='17.25'>");
			out.println ("    <Cell ss:StyleID='s63'><Data ss:Type='String'>學號</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>姓名</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>身份別</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>畢業學校</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>畢業科系</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>電話</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>行動電話</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s64'><Data ss:Type='String'>家長代表</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s65'><Data ss:Type='String'>已填報</Data></Cell>");
			out.println ("   </Row>");
			
			
			stds=(List<Map>)cls.get(i).get("stds");
			for(int j=0; j<stds.size(); j++){
				
				out.println ("   <Row ss:AutoFitHeight='0'>");
				out.println ("    <Cell ss:StyleID='s66'><Data ss:Type='String'>"+stds.get(j).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s66'><Data ss:Type='String'>"+stds.get(j).get("student_name")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s66'><Data ss:Type='String'>"+stds.get(j).get("name")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s66'><Data ss:Type='String'>"+stds.get(j).get("schl_name")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s66'><Data ss:Type='String'>"+stds.get(j).get("grad_dept")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s66'><Data ss:Type='String'>"+stds.get(j).get("telephone")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s66'><Data ss:Type='String'>"+stds.get(j).get("CellPhone")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s67'><Data ss:Type='String'>"+stds.get(j).get("parent_name")+"</Data></Cell>");
				if(stds.get(j).get("edited").toString().equals("1")){
					out.println ("    <Cell ss:MergeDown='2' ss:StyleID='m81669632'><Data ss:Type='String'>是</Data></Cell>");
				}else{
					out.println ("    <Cell ss:MergeDown='2' ss:StyleID='m81669632'><Data ss:Type='String'></Data></Cell>");
				}				
				out.println ("   </Row>");
				out.println ("   <Row ss:AutoFitHeight='0'>");
				out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>通訊地址</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s76'><Data ss:Type='String'>"+stds.get(j).get("curr_addr")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s77'/>");
				out.println ("    <Cell ss:StyleID='s77'/>");
				out.println ("    <Cell ss:StyleID='s77'/>");
				out.println ("    <Cell ss:StyleID='s78'><Data ss:Type='String'>電子郵件</Data></Cell>");
				out.println ("    <Cell ss:StyleID='Default'><Data ss:Type='String'>"+stds.get(j).get("Email")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s80'/>");
				out.println ("   </Row>");
				out.println ("   <Row ss:AutoFitHeight='0' ss:Height='17.25'>");
				out.println ("    <Cell ss:StyleID='s81'><Data ss:Type='String'>戶籍地址</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>"+stds.get(j).get("perm_addr")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s83'/>");
				out.println ("    <Cell ss:StyleID='s83'/>");
				out.println ("    <Cell ss:StyleID='s83'/>");
				out.println ("    <Cell ss:StyleID='s78'><Data ss:Type='String'>照片</Data></Cell>");				
				if(stds.get(j).get("stdimage")==null){
					out.println ("    <Cell ss:StyleID='s61'/>");	
				}else{
					out.println ("    <Cell ss:StyleID='s61' ss:HRef='http://ap.cust.edu.tw/eis/getStdimage?myStdNo="+stds.get(j).get("student_no")+"' x:HRefScreenTip='111'><Data ss:Type='String'>檢視</Data></Cell>");					
				}				
				out.println ("    <Cell ss:StyleID='s84'/>");
				out.println ("   </Row>");
			}			
			out.println ("  </Table>");
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			out.println ("    <Header x:Margin='0.3'");
			
			
			try{x=Integer.parseInt(cls.get(i).get("cnt").toString());}catch(Exception e){x=0;}
			try{y=Integer.parseInt(cls.get(i).get("edited").toString());}catch(Exception e){y=0;}
			z=(float)y/x;
			cls.get(i).put("x", x);
			cls.get(i).put("y", y);
			cls.get(i).put("z", z);
			out.println ("     x:Data='&amp;L&amp;8全班人數:"+x+"&#10;填報人數:"+y+"&#10;填報比例:"+Math.round(z*100)+"%&amp;C&amp;&quot;-,粗體&quot;"+cls.get(i).get("ClassName")+"&#10;學生填報基本資料一覽表&amp;R&amp;8導師:"+cls.get(i).get("cname")+"&#10;"+cls.get(i).get("CellPhone")+"&#10;"+cls.get(i).get("Email")+"'/>");
			out.println ("    <Footer x:Margin='0.3' x:Data='&amp;L&#10;&amp;D &amp;T&amp;R第&amp;P頁/共&amp;N頁'/>");
			out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
			out.println ("   </PageSetup>");
			out.println ("   <Unsynced/>");
			out.println ("   <Print>");
			out.println ("    <ValidPrinterInfo/>");
			out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
			out.println ("    <HorizontalResolution>600</HorizontalResolution>");
			out.println ("    <VerticalResolution>600</VerticalResolution>");
			out.println ("   </Print>");
			out.println ("   <Selected/>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");			
			out.println (" </Worksheet>");			
		}
		
		
		
		out.println (" <Worksheet ss:Name='統計列表'>");
		out.println ("  <Table ss:ExpandedColumnCount='7' ss:ExpandedRowCount='"+(cls.size()+999)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='100'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='30'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='30'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='30'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='60'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='100'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='100'/>");
		
		out.println ("   <Row>");
		out.println ("    <Cell><Data ss:Type='String'>班級名稱</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>人數</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>已填報</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>比例</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>導師</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>電話</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>郵件</Data></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<cls.size(); i++){
			out.println ("   <Row>");
			
			
			
			out.println ("    <Cell><Data ss:Type='String'>"+cls.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+cls.get(i).get("x")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+cls.get(i).get("y")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+Math.round(Float.parseFloat(cls.get(i).get("z").toString())*100)+"%</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+cls.get(i).get("cname")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+cls.get(i).get("CellPhone")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+cls.get(i).get("Email")+"</Data></Cell>");
			
			
			out.println ("   </Row>");
		}
		
		
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3'");
		
		
		//try{x=Integer.parseInt(cls.get(i).get("cnt").toString());}catch(Exception e){x=0;}
		//try{y=Integer.parseInt(cls.get(i).get("edited").toString());}catch(Exception e){y=0;}
		//z=(float)y/x;
		out.println ("     x:Data='學生填報基本資料一覽表'/>");
		out.println ("    <Footer x:Margin='0.3' x:Data='&amp;L&#10;&amp;D &amp;T&amp;R第&amp;P頁/共&amp;N頁'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>600</HorizontalResolution>");
		out.println ("    <VerticalResolution>600</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		
		
		out.println ("</Workbook>");
		
		
		
		
		out.close();
		out.flush();
		
		
	}

}
