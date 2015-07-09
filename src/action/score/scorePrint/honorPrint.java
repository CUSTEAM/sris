package action.score.scorePrint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import service.impl.DataFinder;

/**
 * 學優
 * @author John
 *
 */
public class honorPrint {
	
	public void print(HttpServletResponse response, List<Map>cls, Map info) throws IOException{
		
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
		
		
		
		
		List<Map>list;
		
			
			out.println (" <Worksheet ss:Name='學優'>");			
			out.println ("  <Table ss:ExpandedColumnCount='6' ss:ExpandedRowCount='999' x:FullColumns='1'");		
			
			out.println ("   x:FullRows='1' ss:StyleID='s78' ss:DefaultColumnWidth='20'");
			out.println ("   ss:DefaultRowHeight='25.5'>");
			
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");		
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			
			
			
			int size=5;
			int cnt;
			int be=0;
			for(int i=0; i<cls.size(); i++){
				list=(List)cls.get(i).get("stds");
				
				cnt=Integer.parseInt(cls.get(i).get("cnt").toString());			
				
				//if(cnt<16){size=1;be=1;}
				//if(cnt>=16&&cnt<=30){size=2;be=2;}
				//if(cnt>=16&&cnt>=31){size=3;be=3;}
				if(cnt<30){size=1;be=1;}
				if(cnt>=30&&cnt<=40){size=2;be=2;}
				if(cnt>40){size=3;be=3;}
				
				if(list.size()<size){
					size=list.size();
				}
				if(cnt==0)continue;
				out.println ("   <Row>");					
				out.println ("    <Cell><Data ss:Type='String'>"+cls.get(i).get("ClassName")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>全班人數"+cnt+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>符合資格"+list.size()+"人,</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>應取"+be+"人,</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>實取"+size+"人</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("   </Row>");
				if(list.size()<1){
					out.println ("   <Row>");					
					out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					out.println ("   </Row>");
					continue;
				}
				out.println ("   <Row>");					
				out.println ("    <Cell><Data ss:Type='String'>學號</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>姓名</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>學業成績</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>操行成績</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>體育成績</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("   </Row>");
				
				
				for(int j=0; j<size; j++){					
					out.println ("   <Row>");					
					
					out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("student_no")+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("student_name")+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("score")+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("just")+"</Data></Cell>");
					if(list.get(j).get("sport")==null){
						out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					}else{
						out.println ("    <Cell><Data ss:Type='String'>"+list.get(j).get("sport")+"</Data></Cell>");
					}
					
					out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");					
					out.println ("   </Row>");
				}
				out.println ("   <Row>");					
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("   </Row>");
			}
			
			out.println ("  </Table>");			
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");			
			out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;18 "+info.get("school_year")+"學年 "+info.get("school_term")+"學期  "+info.get("sname")+"學業優良學生名單'/>");
			out.println ("    <Footer x:Margin='0.3' x:Data='&amp;R&amp;10&amp;D - &amp;T&#10;第&amp;P頁 共&amp;N頁'/>");
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
		
		out.println ("</Workbook>");	
		out.close();
		out.flush();
		
		
	}

}
