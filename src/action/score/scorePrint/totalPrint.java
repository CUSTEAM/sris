package action.score.scorePrint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

/**
 * 期中成績總表
 * @author John
 *
 */
public class totalPrint extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>cls, Map info) throws IOException{
		
		
		
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
		out.println ("  <LastPrinted>2013-05-21T08:40:32Z</LastPrinted>");
		out.println ("  <Created>2013-05-21T07:47:43Z</Created>");
		out.println ("  <LastSaved>2013-05-21T08:41:27Z</LastSaved>");
		out.println ("  <Version>14.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>13455</WindowHeight>");
		out.println ("  <WindowWidth>28035</WindowWidth>");
		out.println ("  <WindowTopX>360</WindowTopX>");
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
		out.println ("  <Style ss:ID='s16'>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='8'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s17'>");
		out.println ("   <Alignment ss:Vertical='Top' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='8'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s19'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='標楷體' x:CharSet='136' x:Family='Roman' ss:Size='7'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		
		out.println ("<Style ss:ID='s62'>");
		out.println ("<NumberFormat ss:Format='0.00_ '/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='8'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("</Style>");
		
		out.println (" </Styles>");
		
		List<Map>stds;
		List<Map>dtimes;
		List<Map>selds;
		List<Map>dtimeInfo;
		boolean match;
		StringBuilder sb;
		
		float total,pass,avg,total_score;
		
		int dtimeSize;
		
		for(int i=0; i<cls.size(); i++){
			total=0;pass=0;avg=0;total_score=0;
			stds=(List)cls.get(i).get("stds");
			dtimes=(List)cls.get(i).get("dtimes");	
			dtimeInfo=(List)cls.get(i).get("dtimeInfo");
			if(stds.size()<1){
				continue;
			}
			
			dtimeSize=dtimes.size();
			if(dtimes.size()>=16)dtimeSize=16;
			
			out.println (" <Worksheet ss:Name='"+cls.get(i).get("ClassName")+"'>");
			out.println ("  <Names>");			
			out.println ("   <NamedRange ss:Name='Print_Titles' ss:RefersTo='="+cls.get(i).get("ClassNo")+"!R1:R2'/>");
			out.println ("  </Names>");
			out.println ("  <Table ss:ExpandedColumnCount='23' ss:ExpandedRowCount='"+(stds.size()*2)+100+"' x:FullColumns='1'");
			out.println ("   x:FullRows='1' ss:StyleID='s16' ss:DefaultColumnWidth='37.5' ss:DefaultRowHeight='15'>");			
			out.println ("   <Column ss:StyleID='s16' ss:AutoFitWidth='0' ss:Width='45'/>");
			out.println ("   <Column ss:Index='19' ss:StyleID='s16' ss:AutoFitWidth='0' ss:Width='28.5' ss:Span='4'/>");
			
			//科目名稱
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='37.5' ss:StyleID='s17'>");
			out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			
			for(int j=0; j<dtimeSize; j++){
				dtimes.get(j).put("score", 0.0f);
				out.println ("    <Cell><Data ss:Type='String'>"+dtimes.get(j).get("chi_name")+"</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			}
			
			out.println ("    <Cell ss:Index='19'><Data ss:Type='String'>修習學分總數</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>實得學分總數</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>平均</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>名次</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>操行</Data><NamedCell ss:Name='Print_Titles'/></Cell>");			
			out.println ("   </Row>");
			
			//學分選別
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='12' ss:StyleID='s17'>");
			out.println ("    <Cell><Data ss:Type='String'>學號</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>姓名</Data><NamedCell ss:Name='Print_Titles'/></Cell>");			
			for(int j=0; j<dtimeSize; j++){				
				out.println ("    <Cell><Data ss:Type='String'>"+dtimes.get(j).get("oName")+","+dtimes.get(j).get("credit")+"</Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			}			
			out.println ("    <Cell ss:Index='19'><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Titles'/></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data><NamedCell ss:Name='Print_Titles'/></Cell>");			
			out.println ("   </Row>");
			
			//學生成績
			for(int j=0; j<stds.size(); j++){
				
				selds=(List)stds.get(j).get("selds");
				out.println ("   <Row ss:AutoFitHeight='0'>");				
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_name")+"</Data></Cell>");				
				
				for(int k=0; k<dtimeSize; k++){
					//System.out.println(dtimes.get(k));
					match=false;
					for(int l=0; l<selds.size(); l++){		
						//if(dtimes.get(k).get("cscode").toString().equals(selds.get(l).get("cscode").toString())){
						if(dtimes.get(k).get("cscode").toString().equals(selds.get(l).get("cscode").toString())&&
						dtimes.get(k).get("depart_class").toString().equals(selds.get(l).get("depart_class").toString())){	
							out.println ("    <Cell><Data ss:Type='String'>"+selds.get(l).get("score")+"</Data></Cell>");							
							match=true;
							selds.remove(l);
							continue;
						}						
					}
					if(!match){
						out.println ("    <Cell><Data ss:Type='String'>/</Data></Cell>");
					}					
				}
				
				out.println ("    <Cell ss:Index='19'><Data ss:Type='String'>"+stds.get(j).get("total")+"</Data></Cell>");
				
				total=total+Float.parseFloat(stds.get(j).get("total").toString());
				pass=pass+Float.parseFloat(stds.get(j).get("pass").toString());
				avg=avg+Float.parseFloat(stds.get(j).get("avg").toString());				
				total_score=total_score+Float.parseFloat(stds.get(j).get("total_score").toString());
				
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("pass")+"</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s62'><Data ss:Type='Number'>"+stds.get(j).get("avg")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("no")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("total_score")+"</Data></Cell>");
				out.println ("   </Row>");
				
				sb=new StringBuilder();
				for(int k=0; k<selds.size(); k++){
					sb.append(selds.get(k).get("ClassName")+"-"+selds.get(k).get("chi_name")+"("+selds.get(k).get("oName")+selds.get(k).get("credit")+"):"+selds.get(k).get("score")+", ");
				}
				if(sb.length()>0){
					//字太多
					if(sb.length()>100){
						out.println ("   <Row ss:AutoFitHeight='0' ss:Height='9'>");
						out.println ("    <Cell ss:MergeAcross='22' ss:StyleID='s19'><Data ss:Type='String'>"+sb.substring(0,99)+"</Data></Cell>");
						out.println ("   </Row>");
						out.println ("   <Row ss:AutoFitHeight='0' ss:Height='9'>");
						out.println ("    <Cell ss:MergeAcross='22' ss:StyleID='s19'><Data ss:Type='String'>"+sb.substring(99,sb.length())+"</Data></Cell>");
						out.println ("   </Row>");
					}else{
						out.println ("   <Row ss:AutoFitHeight='0' ss:Height='9'>");
						out.println ("    <Cell ss:MergeAcross='22' ss:StyleID='s19'><Data ss:Type='String'>"+sb+"</Data></Cell>");
						out.println ("   </Row>");
					}					
				}	
			}
			
			//統計資料-平均
			out.println ("   <Row ss:AutoFitHeight='0'>");				
			out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>平均</Data></Cell>");			
			for(int k=0; k<dtimeSize; k++){		
				match=false;
				for(int l=0; l<dtimeInfo.size(); l++){
					//白痴要改的
					//if(dtimeInfo.get(l).get("cscode").toString().equals(dtimes.get(k).get("cscode").toString())){
					if(dtimeInfo.get(l).get("cscode").toString().equals(dtimes.get(k).get("cscode").toString())
							&&dtimeInfo.get(l).get("depart_class").toString().equals(dtimes.get(k).get("depart_class").toString())
							){
						out.println ("    <Cell><Data ss:Type='String'>"+dtimeInfo.get(l).get("avg")+"</Data></Cell>");
						match=true;
						continue;
					}					
				}
				if(!match)out.println ("    <Cell><Data ss:Type='String'>/</Data></Cell>");
			}			
			out.println ("    <Cell ss:Index='19'><Data ss:Type='String'>"+Math.rint((total/stds.size())*10)/10+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+Math.rint((pass/stds.size())*100)/100+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+Math.rint((avg/stds.size())*100)/100+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+Math.rint((stds.size()/2)*100)/100    +"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+Math.rint((total_score/stds.size())*100)/100        +"</Data></Cell>");
			out.println ("   </Row>");
			
			//統計資料-不及格
			out.println ("   <Row ss:AutoFitHeight='0'>");				
			out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>不及格</Data></Cell>");			
			for(int k=0; k<dtimeSize; k++){
				match=false;
				for(int l=0; l<dtimeInfo.size(); l++){
					//白痴要改的
					//if(dtimeInfo.get(l).get("cscode").equals(dtimes.get(k).get("cscode"))
					if(dtimeInfo.get(l).get("cscode").equals(dtimes.get(k).get("cscode"))
						&&dtimeInfo.get(l).get("depart_class").equals(dtimes.get(k).get("depart_class"))	
					){
						match=true;
						out.println ("    <Cell><Data ss:Type='String'>"+dtimeInfo.get(l).get("fail")+"</Data></Cell>");						
						dtimeInfo.remove(l);
						continue;
					}					
				}
				if(!match)out.println ("    <Cell><Data ss:Type='String'>/</Data></Cell>");
			}			
			out.println ("    <Cell ss:Index='19'><Data ss:Type='String'></Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
			out.println ("   </Row>");
			
			
			for(int j=0; j<dtimeInfo.size(); j++){
				out.println ("   <Row ss:AutoFitHeight='0' ss:Height='9'>");
				out.println ("    <Cell ss:MergeAcross='22' ss:StyleID='s19'><Data ss:Type='String'>"+dtimeInfo.get(j).get("ClassName")+
						","+dtimeInfo.get(j).get("chi_name")+"本班"+
				dtimeInfo.get(j).get("sld")+"人選修,不及格"+dtimeInfo.get(j).get("fail")+"人,平均成績"+dtimeInfo.get(j).get("avg")+"分</Data></Cell>");
				out.println ("   </Row>");
			}
			
			
			
			//內容結束
			
			out.println ("  </Table>");
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			out.println ("    <Layout x:Orientation='Landscape'/>");
			out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;-,粗體&quot;&amp;18 "+info.get("title")+" ("+cls.get(i).get("ClassName")+")'/>");
			out.println ("    <Footer x:Margin='0.3' x:Data='&amp;R&amp;10&amp;D - &amp;T&#10;第&amp;P頁 共&amp;N頁'/>");
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
			out.println ("   <Panes>");
			out.println ("    <Pane>");
			out.println ("     <Number>3</Number>");
			out.println ("     <ActiveRow>15</ActiveRow>");
			out.println ("     <ActiveCol>2</ActiveCol>");
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
