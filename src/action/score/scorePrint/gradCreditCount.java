package action.score.scorePrint;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import service.impl.DataFinder;

/**
 * 學分下限
 * @author John
 *
 */
public class gradCreditCount {
	
	public void print(HttpServletResponse response, List<Map>list, List<Map>schools) throws IOException{
		
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
		out.println ("  <Created>2016-08-23T00:19:36Z</Created>");
		out.println ("  <LastSaved>2016-08-23T07:07:55Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>13845</WindowHeight>");
		out.println ("  <WindowWidth>28800</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>0</WindowTopY>");
		out.println ("  <ActiveSheet>1</ActiveSheet>");
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
		out.println ("  <Style ss:ID='m725687896288'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m725687896308'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center' ss:Indent='1'");
		out.println ("    ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m725687896328'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center' ss:Indent='1'");
		out.println ("    ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m725687896348'>");
		out.println ("   <Alignment ss:Horizontal='Justify' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='m725493543676'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s62'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s83'>");
		out.println ("   <Alignment ss:Horizontal='Justify' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s84'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s85'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s93'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s94'>");
		out.println ("   <Alignment ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s95'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center' ss:Indent='1'");
		out.println ("    ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s96'>");
		out.println ("   <Alignment ss:Vertical='Center' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'");
		out.println ("     ss:Color='#000000'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Script' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s107'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s108'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s110'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s111'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s112'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s113'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='2'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s114'>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		out.println (" <Worksheet ss:Name='查詢結果'>");
		out.println ("  <Table ss:ExpandedColumnCount='8' ss:ExpandedRowCount='13' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='113.25'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='57'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='44.25'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='63'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='54.75'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='65.25'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='60'/>");
		out.println ("   <Column ss:AutoFitWidth='0' ss:Width='63'/>");
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='String'>指標項目說明</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='6' ss:StyleID='m725687896288'><Data ss:Type='String'>辦理情形（含質化及量化成果）</Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0' ss:Height='36'>");
		out.println ("    <Cell ss:MergeDown='11' ss:StyleID='m725687896308'/>");
		out.println ("    <Cell ss:MergeAcross='6' ss:StyleID='m725687896328'><Data ss:Type='String'>◎檢討各系所畢業學分數下限之機制及成果，與各系所畢業生修習外系或外院學分數情形 </Data></Cell>");
		out.println ("   </Row>");
		out.println ("   <Row ss:AutoFitHeight='0' ss:Height='137.25'>");
		out.println ("    <Cell ss:Index='2' ss:StyleID='s83'><Data ss:Type='String'>教學增能學校名稱</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s83'><Data ss:Type='String'>學年度</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s84'><Data ss:Type='String'>畢業生實際之平均畢業學分數(A)</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s84'><Data ss:Type='String'>畢業生平均修習外系學分數(不含共同通識課程)(B)</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s84'><Data ss:Type='String'>畢業生平均修習外院學分數(不含共同通識課程)(C)</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s84'><Data ss:Type='String'>畢業生平均修習外系學分數之比例(B/A)%</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s85'><Data ss:Type='String'>畢業生平均修習外院學分數之比例 (C/A)%</Data></Cell>");
		out.println ("   </Row>");
		
		Double a, b, c, d, e;
		a=Double.parseDouble(list.get(0).get("all_credit").toString());		
		b=Double.parseDouble(list.get(0).get("dept_credit").toString());
		c=Double.parseDouble(list.get(0).get("inst_credit").toString());
		d=new BigDecimal(b/a).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		e=new BigDecimal(c/a).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:Index='2' ss:MergeDown='9' ss:StyleID='m725687896348'><Data");
		out.println ("      ss:Type='String'>1.中華科技大學</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s93'><Data ss:Type='String'>"+list.get(0).get("year")+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s94'><Data ss:Type='String'>"+a+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s83'><Data ss:Type='String'>"+b+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s83'><Data ss:Type='String'>"+c+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s95'><Data ss:Type='String'>"+d+"%</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s96'><Data ss:Type='String'>"+e+"%</Data></Cell>");
		out.println ("   </Row>");
		
		for(int i=1; i<list.size(); i++){
			//System.out.println(i);
			try{
				a=Double.parseDouble(list.get(i).get("all_credit").toString());		
				b=Double.parseDouble(list.get(i).get("dept_credit").toString());
				c=Double.parseDouble(list.get(i).get("inst_credit").toString());
				d=new BigDecimal(b/a).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				e=new BigDecimal(c/a).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
			}catch(Exception ex){
				a=0d;b=0d;c=0d;d=0d;e=0d;
			}
			
			out.println ("   <Row ss:AutoFitHeight='0'>");
			out.println ("    <Cell ss:Index='3' ss:StyleID='s93'><Data ss:Type='String'>"+list.get(i).get("year")+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s94'><Data ss:Type='String'>"+a+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s83'><Data ss:Type='String'>"+b+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s83'><Data ss:Type='String'>"+c+"</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s95'><Data ss:Type='String'>"+d+"%</Data></Cell>");
			out.println ("    <Cell ss:StyleID='s96'><Data ss:Type='String'>"+e+"%</Data></Cell>");
			out.println ("   </Row>");
			
		}
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Unsynced/>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>600</HorizontalResolution>");
		out.println ("    <VerticalResolution>600</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>7</ActiveRow>");
		out.println ("     <ActiveCol>9</ActiveCol>");
		out.println ("    </Pane>");
		out.println ("   </Panes>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		out.println (" <Worksheet ss:Name='全校參考'>");
		out.println ("  <Table ss:ExpandedColumnCount='31' ss:ExpandedRowCount='999' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s114' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='16.5'>");
		out.println ("   <Column ss:StyleID='s111' ss:Width='83.25'/>");
		out.println ("   <Column ss:StyleID='s112' ss:Width='28.5'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='19.5' ss:Span='1'/>");
		out.println ("   <Column ss:Index='5' ss:StyleID='s112' ss:Width='28.5'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='18'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='19.5' ss:Span='1'/>");
		out.println ("   <Column ss:Index='9' ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='18'/>");
		out.println ("   <Column ss:StyleID='s112' ss:Width='28.5' ss:Span='1'/>");
		out.println ("   <Column ss:Index='12' ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='19.5'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='16.5'/>");
		out.println ("   <Column ss:StyleID='s112' ss:Width='28.5'/>");
		out.println ("   <Column ss:StyleID='s112' ss:AutoFitWidth='0' ss:Width='21.75' ss:Span='1'/>");
		out.println ("   <Column ss:Index='17' ss:StyleID='s112' ss:Width='28.5' ss:Span='13'/>");
		out.println ("   <Column ss:Index='31' ss:StyleID='s113' ss:Width='28.5'/>");
		out.println ("   <Row ss:StyleID='s110'>");
		out.println ("    <Cell ss:StyleID='s107'/>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>95</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>96</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>97</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>98</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>99</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>100</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>101</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>102</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='s108'><Data ss:Type='Number'>103</Data></Cell>");
		out.println ("    <Cell ss:MergeAcross='2' ss:StyleID='m725493543676'><Data ss:Type='Number'>104</Data></Cell>");
		out.println ("   </Row>");
		
		List<Map>tmp;
		for(int i=0; i<schools.size(); i++){
			
			tmp=(List<Map>) schools.get(i).get("cnt");
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+schools.get(i).get("name")+"</Data></Cell>");
			for(int j=0; j<10; j++){
				if(tmp.get(j).get("all_credit")!=null){
					out.println ("    <Cell><Data ss:Type='Number'>"+tmp.get(j).get("all_credit")+"</Data></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				}				
				if(tmp.get(j).get("dept_credit")!=null){
					out.println ("    <Cell><Data ss:Type='Number'>"+tmp.get(j).get("dept_credit")+"</Data></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				}
				if(tmp.get(j).get("inst_credit")!=null){
					out.println ("    <Cell><Data ss:Type='Number'>"+tmp.get(j).get("inst_credit")+"</Data></Cell>");
				}else{
					out.println ("    <Cell><Data ss:Type='Number'>0</Data></Cell>");
				}
			}
			
			
			
			out.println ("   </Row>");
			
			
		}
		
		
		
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
		out.println ("    <HorizontalResolution>600</HorizontalResolution>");
		out.println ("    <VerticalResolution>600</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <Panes>");
		out.println ("    <Pane>");
		out.println ("     <Number>3</Number>");
		out.println ("     <ActiveRow>8</ActiveRow>");
		out.println ("     <ActiveCol>8</ActiveCol>");
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
