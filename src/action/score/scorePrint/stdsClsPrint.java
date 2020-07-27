package action.score.scorePrint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

/**
 * 
 * @author John
 *
 */
public class stdsClsPrint extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>cls) throws IOException{
		
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
		out.println ("  <LastPrinted>2012-12-21T02:00:00Z</LastPrinted>");
		out.println ("  <Created>2012-12-21T00:00:00Z</Created>");
		out.println ("  <LastSaved>2012-12-21T00:00:00Z</LastSaved>");
		out.println ("  <Version>14.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>11880</WindowHeight>");
		out.println ("  <WindowWidth>28035</WindowWidth>");
		out.println ("  <WindowTopX>360</WindowTopX>");
		out.println ("  <WindowTopY>105</WindowTopY>");
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
		out.println ("  <Style ss:ID='s76'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='18'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s78'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='18'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		
		
		
		out.println ("	<Style ss:ID='s63'>");				
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='18'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		
		out.println (" </Styles>");	
		
		if(cls.size()<1){
			out.println (" <Worksheet ss:Name='無資料'>");			
			out.println ("  <Table ss:ExpandedColumnCount='7' ss:ExpandedRowCount='9999' x:FullColumns='1'");				
			out.println ("   x:FullRows='1' ss:StyleID='s78' ss:DefaultColumnWidth='20'");
			out.println ("   ss:DefaultRowHeight='25.5'>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");		
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='60'/>");
			
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>班級</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>學號</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>姓名</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>共修學分</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>不及格學分</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>平均分數</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>備註</Data></Cell>");	
			out.println ("   </Row>");
			out.println ("  </Table>");
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			out.println ("    <Header x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp; &#10;人數:  / &amp;C&amp;20 &amp;R&amp;D-&amp;T&#10;第&amp;P頁 共&amp;N頁'/>");			
			out.println ("    <Footer x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp;12大學部含以下及格標準為60&#10;碩士班及格標準為70'/>");
			
			out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
			out.println ("   </PageSetup>");
			out.println ("   <FitToPage/>");
			out.println ("   <Print>");
			out.println ("    <FitHeight>0</FitHeight>");
			out.println ("    <ValidPrinterInfo/>");
			out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
			out.println ("    <Scale>52</Scale>");
			out.println ("    <HorizontalResolution>600</HorizontalResolution>");
			out.println ("    <VerticalResolution>600</VerticalResolution>");
			out.println ("   </Print>");
			out.println ("   <Selected/>");
			out.println ("   <Panes>");
			out.println ("    <Pane>");
			out.println ("     <Number>3</Number>");
			out.println ("     <ActiveRow>16</ActiveRow>");
			out.println ("     <ActiveCol>7</ActiveCol>");
			out.println ("    </Pane>");
			out.println ("   </Panes>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");
			out.println (" </Worksheet>");
		}
		
		
		List<Map>list;
		if(cls.size()>0)
		for(int i=0; i<cls.size(); i++){
			
			list=(List)cls.get(i).get("stds");
			out.println (" <Worksheet ss:Name='"+cls.get(i).get("ClassName")+"'>");			
			out.println ("  <Table ss:ExpandedColumnCount='7' ss:ExpandedRowCount='"+list.size()+1+"' x:FullColumns='1'");				
			out.println ("   x:FullRows='1' ss:StyleID='s78' ss:DefaultColumnWidth='20'");
			out.println ("   ss:DefaultRowHeight='25.5'>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");		
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='60'/>");
			
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>班級</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>學號</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>姓名</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>共修學分</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>不及格學分</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>平均分數</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>備註</Data></Cell>");
			out.println ("   </Row>");
			
			for(int j=0; j<list.size(); j++){
				
				out.println ("   <Row>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("className")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("student_name")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("total")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("np")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("avgs")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("note")+"</Data></Cell>");
				out.println ("   </Row>");
			}
			
				
				
				
			
			out.println ("  </Table>");
			
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			
			out.println ("    <Header x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp; &#10;人數: "+list.size()+" / "+cls.get(i).get("cnt")+"&amp;C&amp;20 "+
					cls.get(i).get("ClassName")+" &amp;R&amp;D-&amp;T&#10;第&amp;P頁 共&amp;N頁'/>");
			
			out.println ("    <Footer x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp;12大學部含以下及格標準為60&#10;碩士班及格標準為70'/>");
			
			out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
			out.println ("   </PageSetup>");
			out.println ("   <FitToPage/>");
			out.println ("   <Print>");
			out.println ("    <FitHeight>0</FitHeight>");
			out.println ("    <ValidPrinterInfo/>");
			out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
			out.println ("    <Scale>52</Scale>");
			out.println ("    <HorizontalResolution>600</HorizontalResolution>");
			out.println ("    <VerticalResolution>600</VerticalResolution>");
			out.println ("   </Print>");
			out.println ("   <Selected/>");
			out.println ("   <Panes>");
			out.println ("    <Pane>");
			out.println ("     <Number>3</Number>");
			out.println ("     <ActiveRow>16</ActiveRow>");
			out.println ("     <ActiveCol>7</ActiveCol>");
			out.println ("    </Pane>");
			out.println ("   </Panes>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");
			out.println (" </Worksheet>");
		}
		
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.println ("</Workbook>");	
		out.close();
		out.flush();
		
		
	}

}
