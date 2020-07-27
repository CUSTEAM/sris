package action.score.scorePrint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;
import service.impl.DataFinder;

/**
 * 歷年平均
 * @author John
 *
 */
public class avgScorePrint extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>cls, Map info) throws IOException{
		
		Date date=new Date();
		xml2ods(response, getRequest(), date);
		PrintWriter out=response.getWriter();
		
		out.print ("<?xml version='1.0'?>");
		out.print ("<?mso-application progid='Excel.Sheet'?>");
		out.print ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.print (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.print (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.print (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.print (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.print (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.print ("  <Author>John</Author>");
		out.print ("  <LastAuthor>John</LastAuthor>");
		out.print ("  <LastPrinted>2013-06-24T03:44:51Z</LastPrinted>");
		out.print ("  <Created>2013-06-24T02:54:59Z</Created>");
		out.print ("  <LastSaved>2013-06-24T03:46:34Z</LastSaved>");
		out.print ("  <Version>14.00</Version>");
		out.print (" </DocumentProperties>");
		out.print (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.print ("  <AllowPNG/>");
		out.print (" </OfficeDocumentSettings>");
		out.print (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.print ("  <WindowHeight>11430</WindowHeight>");
		out.print ("  <WindowWidth>24795</WindowWidth>");
		out.print ("  <WindowTopX>240</WindowTopX>");
		out.print ("  <WindowTopY>105</WindowTopY>");
		out.print ("  <ProtectStructure>False</ProtectStructure>");
		out.print ("  <ProtectWindows>False</ProtectWindows>");
		out.print (" </ExcelWorkbook>");
		out.print (" <Styles>");
		out.print ("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.print ("   <Alignment ss:Vertical='Center'/>");
		out.print ("   <Borders/>");
		out.print ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.print ("    ss:Color='#000000'/>");
		out.print ("   <Interior/>");
		out.print ("   <NumberFormat/>");
		out.print ("   <Protection/>");
		out.print ("  </Style>");
		out.print ("  <Style ss:ID='s82'>");
		out.print ("   <Borders>");
		out.print ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.print ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.print ("   </Borders>");
		out.print ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Color='#000000'/>");
		out.print ("  </Style>");
		out.print ("  <Style ss:ID='s89'>");
		out.print ("   <Borders>");
		out.print ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.print ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.print ("   </Borders>");
		out.print ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.print ("    ss:Color='#000000'/>");
		out.print ("  </Style>");
		out.print ("  <Style ss:ID='s91'>");
		out.print ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center'/>");
		out.print ("   <Borders>");
		out.print ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.print ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.print ("   </Borders>");
		out.print ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.print ("    ss:Color='#000000'/>");
		out.print ("  </Style>");
		out.print ("  <Style ss:ID='s92'>");
		out.print ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.print ("    ss:Color='#000000'/>");
		out.print ("  </Style>");
		out.print ("  <Style ss:ID='s93'>");
		out.print ("   <Borders>");
		out.print ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.print ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.print ("   </Borders>");
		out.print ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.print ("    ss:Color='#000000'/>");
		//out.print ("   <NumberFormat ss:Format='0%'/>");
		out.print ("   <NumberFormat ss:Format='0.0_ '/>");
		
		out.print ("  </Style>");
		out.print (" </Styles>");
		
		
		
		
		List<Map>stds;
		List<Map>score;
		
		for(int i=0; i<cls.size(); i++){
			
			stds=(List)cls.get(i).get("stds");
			out.print (" <Worksheet ss:Name='"+cls.get(i).get("ClassName").toString()+"'>");
			out.print ("  <Names>");
			out.print ("   <NamedRange ss:Name='Print_Area' ss:RefersTo='="+cls.get(i).get("ClassNo")+"!C1:C14'/>");
			out.print ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='="+cls.get(i).get("ClassNo")+"!R1'/>");
			out.print ("  </Names>");
			out.print ("  <Table ss:ExpandedColumnCount='14' ss:ExpandedRowCount='"+(stds.size()+1)+"' x:FullColumns='1'");
			out.print ("   x:FullRows='1' ss:StyleID='s92' ss:DefaultColumnWidth='54'");
			out.print ("   ss:DefaultRowHeight='16.5'>");
			out.print ("   <Column ss:StyleID='s89' ss:AutoFitWidth='0' ss:Width='63.75'/>");
			out.print ("   <Column ss:StyleID='s89' ss:AutoFitWidth='0' ss:Width='48'/>");
			out.print ("   <Column ss:StyleID='s89' ss:AutoFitWidth='0' ss:Width='39' ss:Span='11'/>");
			out.print ("   <Row>");
			out.print ("    <Cell><Data ss:Type='String'>學號</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell><Data ss:Type='String'>姓名</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'>一上</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'>一下</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'>二上</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'>二下</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'>三上</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'>三下</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'>四上</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'>四下</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s91'><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell><Data ss:Type='String'>平均</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>百分比</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("    <Cell><Data ss:Type='String'>名次</Data><NamedCell ss:Name='Print_Titles'/><NamedCell ss:Name='Print_Area'/></Cell>");
			out.print ("   </Row>");
			
			
			for(int j=0; j<stds.size(); j++){
				
				score=(List)stds.get(j).get("scores");				
				out.print ("   <Row>");
				out.print ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_no")+"</Data></Cell>");
				out.print ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_name")+"</Data></Cell>");
				
				for(int k=0; k<8; k++){
					try{if(score.get(k).get("score")!=null){
						out.println ("    <Cell><Data ss:Type='String'>"+score.get(k).get("score")+"</Data></Cell>");
					}else{
						out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					}
						
					}catch(Exception e){
						out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					}
				}
				
				out.print ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Area'/></Cell>");
				out.print ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("avg")+"</Data></Cell>");
				out.print ("    <Cell ss:StyleID='s93'><Data ss:Type='Number'>"+(Float.parseFloat(stds.get(j).get("no").toString())/stds.size())*100+"</Data></Cell>");
				out.print ("    <Cell><Data ss:Type='Number'>"+stds.get(j).get("no")+"</Data></Cell>");
				out.print ("   </Row>");
			}			
			
			out.print ("  </Table>");
			out.print ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.print ("   <PageSetup>");
			out.print ("    <Header x:Margin='0.31496062992125984'");
			out.print ("     x:Data='&amp;L&amp;11班級: "+cls.get(i).get("ClassName")+"&#10;人數: "+stds.size()+" 百分之五十: "+(stds.size()/2)+"&amp;C&amp;&quot;標楷體,標準&quot;&amp;14中華科技大學"+info.get("school_year")+"學年應屆畢業生平均成績表'/>");
			out.print ("    <Footer x:Margin='0.31496062992125984' x:Data='&amp;R&amp;D &amp;T&#10;第 &amp;P頁共 &amp;N頁'/>");
			out.print ("    <PageMargins x:Bottom='0.74803149606299213' x:Left='0.23622047244094491'");
			out.print ("     x:Right='0.23622047244094491' x:Top='0.74803149606299213'/>");
			out.print ("   </PageSetup>");
			out.print ("   <Print>");
			out.print ("    <ValidPrinterInfo/>");
			out.print ("    <PaperSizeIndex>9</PaperSizeIndex>");
			out.print ("    <HorizontalResolution>600</HorizontalResolution>");
			out.print ("    <VerticalResolution>600</VerticalResolution>");
			out.print ("   </Print>");
			out.print ("   <Selected/>");
			out.print ("   <Panes>");
			out.print ("    <Pane>");
			out.print ("     <Number>3</Number>");
			out.print ("     <ActiveRow>13</ActiveRow>");
			out.print ("     <ActiveCol>17</ActiveCol>");
			out.print ("    </Pane>");
			out.print ("   </Panes>");
			out.print ("   <ProtectObjects>False</ProtectObjects>");
			out.print ("   <ProtectScenarios>False</ProtectScenarios>");
			out.print ("  </WorksheetOptions>");
			out.print (" </Worksheet>");			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.print ("</Workbook>");
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.close();
		out.flush();
		
		
	}

}
