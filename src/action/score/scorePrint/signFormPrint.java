package action.score.scorePrint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

/**
 * 成績冊簽收單
 * @author John
 *
 */
public class signFormPrint extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>cs) throws IOException{
		
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
		out.println ("  <Author>shawn</Author>");
		out.println ("  <LastAuthor>John</LastAuthor>");
		out.println ("  <LastPrinted>2018-04-19T03:09:01Z</LastPrinted>");
		out.println ("  <Created>2014-05-08T03:36:34Z</Created>");
		out.println ("  <LastSaved>2017-11-07T06:19:40Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>10875</WindowHeight>");
		out.println ("  <WindowWidth>19320</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>45</WindowTopY>");
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
		out.println ("  <Style ss:ID='s70'>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='14'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s71'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:ShrinkToFit='1'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s72'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center' ss:ShrinkToFit='1'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s73'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:ShrinkToFit='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s74'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:ShrinkToFit='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s75'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:ShrinkToFit='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s79'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s85'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s87'>");
		out.println ("   <Alignment ss:Vertical='Center' ss:ShrinkToFit='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s110'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:ShrinkToFit='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='14'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		out.println (" <Worksheet ss:Name='基本資料'>");
		out.println ("  <Names>");
		out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='=基本資料!R1'/>");
		out.println ("  </Names>");
		out.println ("  <Table ss:ExpandedColumnCount='11' ss:ExpandedRowCount='"+(cs.size()+999)+"' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s70' ss:DefaultColumnWidth='77.25'");
		out.println ("   ss:DefaultRowHeight='19.5'>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='118.5'/>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='48'/>");
		out.println ("   <Column ss:StyleID='s72' ss:AutoFitWidth='0' ss:Width='171.75'/>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='63.75'/>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='28.5'/>");
		out.println ("   <Column ss:StyleID='s71' ss:AutoFitWidth='0' ss:Width='63.75' ss:Span='5'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s73'><Data ss:Type='String'>班級名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s73'><Data ss:Type='String'>科目代碼</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s73'><Data ss:Type='String'>科目名稱</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s73'><Data ss:Type='String'>教師姓名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s74'><Data ss:Type='String'>選別</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>期中考考卷</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>期中考成績冊</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>老師簽名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>期末考考卷</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>學期成績冊</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("    <Cell ss:StyleID='s75'><Data ss:Type='String'>老師簽名</Data><NamedCell");
		out.println ("      ss:Name='Print_Titles'/></Cell>");
		out.println ("   </Row>");
		
		for(int i=0; i<cs.size(); i++){
			out.println ("   <Row ss:AutoFitHeight='0'>");
			out.println ("    <Cell ss:StyleID='s79'><Data ss:Type='String'>"+cs.get(i).get("ClassName")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s85'><Data ss:Type='String'>"+cs.get(i).get("cscode")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s85'><Data ss:Type='String'>"+cs.get(i).get("chi_name")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s79'><Data ss:Type='String'>"+cs.get(i).get("cname")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s87'><Data ss:Type='String'>"+cs.get(i).get("optName")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s110'/>");
			out.println ("    <Cell ss:StyleID='s110'/>");
			out.println ("    <Cell ss:StyleID='s110'/>");
			out.println ("    <Cell ss:StyleID='s110'/>");
			out.println ("    <Cell ss:StyleID='s110'/>");
			out.println ("    <Cell ss:StyleID='s110'/>");
			out.println ("   </Row>");
		}
		
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Layout x:Orientation='Landscape'/>");
		out.println ("    <Header x:Margin='0.31496062992125984'/>");
		out.println ("    <Footer x:Margin='0.31496062992125984' x:Data='第 &amp;P 頁，共 &amp;N 頁'/>");
		out.println ("    <PageMargins x:Bottom='0.55118110236220474' x:Left='0.51181102362204722'");
		out.println ("     x:Right='0.31496062992125984' x:Top='0.35433070866141736'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
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
		out.println ("   <TopRowBottomPane>2</TopRowBottomPane>");
		out.println ("   <ActivePane>2</ActivePane>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("    </Pane>");
		out.println ("    <Pane>");
		out.println ("     <Number>2</Number>");
		out.println ("     <ActiveRow>8</ActiveRow>");
		out.println ("     <ActiveCol>2</ActiveCol>");
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