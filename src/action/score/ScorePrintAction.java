package action.score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import service.impl.DataFinder;
import action.score.scorePrint.ScoreHistPrint;
import action.score.scorePrint.avgScorePrint;
import action.score.scorePrint.csPrint;
import action.score.scorePrint.gradCreditCount;
import action.score.scorePrint.honorPrint;
import action.score.scorePrint.mailPrint;
import action.score.scorePrint.scorePrintBase;
import action.score.scorePrint.stdsClsPrint;
import action.score.scorePrint.stdsPrint;
import action.score.scorePrint.totalPrint;
import action.score.scorePrint.yeaReach;
import action.score.scorePrint.signFormPrint;
import model.Message;
public class ScorePrintAction extends scorePrintBase{
	
	public String cno;
	public String tno;	
	public String sno;
	public String dno;
	public String gno;
	public String zno;	
	public String grade, student_no;		

	public String execute() throws IOException{
		//成績單外部單筆進入點
		if(request.getParameter("scoreHist")!=null){
			List<Map>stds=getStd(request.getParameter("scoreHist"));			
			for(int i=0; i<stds.size(); i++){		
				stds.get(i).put("MasterData", df.sqlGetMap("SELECT*FROM MasterData WHERE student_no='"+stds.get(i).get("student_no")+"'"));
				stds.get(i).put("years", getScoreHist(stds.get(i).get("graduate").toString(), stds.get(i).get("student_no").toString(), stds.get(i).get("SchNo").toString()));			
			}		
			ScoreHistPrint p=new ScoreHistPrint();
			p.print(response, stds);
			return null;
		}
		
		return SUCCESS;
	}	
	
	/**
	 * 期中1/2
	 * @return
	 * @throws IOException 
	 */
	public String midHalf() throws IOException{		
		stdsPrint p=new stdsPrint();
		Map info=getInfo(cno, tno, grade);
		List<Map>stds=getScore("score2", cno, tno, sno, dno, gno, zno, grade, false);
		List list=new ArrayList();
		float np, total;
		for(int i=0; i<stds.size(); i++){			
			np=Float.parseFloat(stds.get(i).get("np").toString());
			total=Float.parseFloat(stds.get(i).get("total").toString());			
			if(np>=(total/2)&&np<(total/3)*2&&total>=10){
				list.add(stds.get(i));
			}			
		}		
		
		info.put("title", info.get("school_year")+"學年"+info.get("school_term")+"學期"+info.get("sname")+"期中1/2不及格名單");
		p.print(response, list, info);
		p=null;
		return null;
	}
	
	/**
	 * 期末1/2
	 * @return
	 * @throws IOException 
	 */
	public String endHalf() throws IOException{
		stdsPrint p=new stdsPrint();
		Map info=getInfo(cno, tno, grade);
		List<Map>stds=getScore("score", cno, tno, sno, dno, gno, zno, grade, true);
		List list=new ArrayList();
		float np, total;
		for(int i=0; i<stds.size(); i++){			
			np=Float.parseFloat(stds.get(i).get("np").toString());
			total=Float.parseFloat(stds.get(i).get("total").toString());			
			if(np>=(total/2)&&np<(total/3)*2&&total>=10){
				list.add(stds.get(i));
			}			
		}
		
		info.put("title", info.get("school_year")+"學年"+info.get("school_term")+"學期"+info.get("sname")+"期末1/2不及格名單");
		p.print(response, list, info);
		p=null;
		return null;
	}	
	
	/**
	 * 期中導師通知單
	 * @return
	 * @throws IOException 
	 */
	public String midNotice() throws IOException{
		stdsClsPrint p=new stdsClsPrint();		
		List<Map>cls=getClsScore("score2", cno, tno, sno, dno, gno, zno, grade, false);
		List stds;
		List<Map>tmp;
		List list=new ArrayList();
		float np, total;
		
		for(int i=0; i<cls.size(); i++){			
			tmp=(List)cls.get(i).get("stds");
			stds=new ArrayList();			
			for(int j=0; j<tmp.size(); j++){				
				np=Float.parseFloat(tmp.get(j).get("np").toString());
				total=Float.parseFloat(tmp.get(j).get("total").toString());			
				//二分之一不及格學生
				if(np>=(total/2)&&np<(total/3)*2&&total>=10){
					tmp.get(j).put("note", "1/2");
					stds.add(tmp.get(j));
				}
				//3分之2不及格學生
				if(np>=(total/3)*2&&total>9){
					tmp.get(j).put("note", "2/3");
					stds.add(tmp.get(j));
				}
			}		
			if(stds.size()<1){
				continue;
			}
			cls.get(i).put("stds", stds);
			list.add(cls.get(i));
		}	
		p.print(response, list);
		p=null;
		return null;
	}
	
	/**
	 * 期末導師通知單
	 * @return
	 * @throws IOException 
	 */
	public String endNotice() throws IOException{
		stdsClsPrint p=new stdsClsPrint();		
		List<Map>cls=getClsScore("score", cno, tno, sno, dno, gno, zno, grade, true);
		List stds;
		List<Map>tmp;
		List list=new ArrayList();
		float np, total;
		
		for(int i=0; i<cls.size(); i++){			
			tmp=(List)cls.get(i).get("stds");
			stds=new ArrayList();			
			for(int j=0; j<tmp.size(); j++){				
				np=Float.parseFloat(tmp.get(j).get("np").toString());
				total=Float.parseFloat(tmp.get(j).get("total").toString());			
				//二分之一不及格學生
				if(np>=(total/2)&&np<(total/3)*2&&total>=10){
					tmp.get(j).put("note", "1/2");
					stds.add(tmp.get(j));
				}
				//3分之2不及格學生
				if(np>=(total/3)*2&&total>9){
					tmp.get(j).put("note", "2/3");
					stds.add(tmp.get(j));
				}
			}		
			if(stds.size()<1){
				continue;
			}
			cls.get(i).put("stds", stds);
			list.add(cls.get(i));
		}	
		p.print(response, list);
		p=null;
		return null;
	}
	
	/**
	 * 期中2/3
	 * @return
	 * @throws IOException 
	 */
	public String midMoreHalf() throws IOException{
		stdsPrint p=new stdsPrint();
		Map info=getInfo(cno, tno, grade);
		List<Map>stds=getScore("score2", cno, tno, sno, dno, gno, zno, grade, false);
		List list=new ArrayList();
		float np, total;
		for(int i=0; i<stds.size(); i++){			
			np=Float.parseFloat(stds.get(i).get("np").toString());
			total=Float.parseFloat(stds.get(i).get("total").toString());			
			if(np>=(total/3)*2&&total>9){
				list.add(stds.get(i));
			}			
		}		
		
		info.put("title", info.get("school_year")+"學年"+info.get("school_term")+"學期"+info.get("sname")+"期中2/3不及格名單");
		p.print(response, list, info);
		p=null;
		return null;
	}
	
	/**
	 * 期末2/3
	 * @return
	 * @throws IOException 
	 */
	public String endMoreHalf() throws IOException{
		stdsPrint p=new stdsPrint();
		Map info=getInfo(cno, tno, grade);
		List<Map>stds=getScore("score", cno, tno, sno, dno, gno, zno, grade, true);
		List list=new ArrayList();
		float np, total;
		for(int i=0; i<stds.size(); i++){			
			np=Float.parseFloat(stds.get(i).get("np").toString());
			total=Float.parseFloat(stds.get(i).get("total").toString());			
			if(np>=(total/3)*2&&total>9){
				list.add(stds.get(i));
			}			
		}		
		
		info.put("title", info.get("school_year")+"學年"+info.get("school_term")+"學期"+info.get("sname")+"期末2/3不及格名單");
		p.print(response, list, info);
		p=null;
		return null;
	}	
	
	/**
	 * 期中科目1/2
	 * @return
	 * @throws IOException 
	 */
	public String midCsHalf() throws IOException{
		csPrint p=new csPrint();
		Map info=getInfo(cno, tno, grade);
		List<Map>cs=getCsScore("score2", cno, tno, sno, dno, gno, zno, grade, false);
		List list=new ArrayList();
		Float cnt, np, pa;
		for(int i=0; i<cs.size(); i++){
			
			cnt=Float.parseFloat(cs.get(i).get("cnt").toString());
			np=Float.parseFloat(cs.get(i).get("np").toString());			
			if(np>=(cnt/2)&&np<((cnt/3)*2)){
				pa=cnt-np;
				cs.get(i).put("pa", Math.round(pa));
				cs.get(i).put("avgs", Math.round((np/cnt)*100));
				list.add(cs.get(i));
			}
		}		
		info.put("title", info.get("school_year")+"學年"+info.get("school_term")+"學期"+info.get("sname")+"期中科目1/2不及格名單");
		p.print(response, list, info);
		p=null;
		return null;
	}	
	
	/**
	 * 期中科目2/3
	 * @return
	 * @throws IOException 
	 */
	public String midCsMoreHalf() throws IOException{
		csPrint p=new csPrint();
		Map info=getInfo(cno, tno, grade);
		List<Map>cs=getCsScore("score2", cno, tno, sno, dno, gno, zno, grade, false);
		List list=new ArrayList();
		Float cnt, np, pa;
		for(int i=0; i<cs.size(); i++){		
			cnt=Float.parseFloat(cs.get(i).get("cnt").toString());
			np=Float.parseFloat(cs.get(i).get("np").toString());
			
			if(np>=((cnt/3)*2)){
				pa=cnt-np;
				cs.get(i).put("pa", pa);
				cs.get(i).put("avgs", Math.round((np/cnt)*100));
				list.add(cs.get(i));
			}
		}		
		info.put("title", info.get("school_year")+"學年"+info.get("school_term")+"學期"+info.get("sname")+"期中科目2/3不及格名單");
		p.print(response, list, info);
		p=null;
		return null;
	}
	
	/**
	 * 期末科目1/2
	 * @return
	 * @throws IOException
	 */
	public String endCsHalf() throws IOException{
		csPrint p=new csPrint();
		Map info=getInfo(cno, tno, grade);
		List<Map>cs=getCsScore("score2", cno, tno, sno, dno, gno, zno, grade, true);
		List list=new ArrayList();
		Float cnt, np, pa;
		for(int i=0; i<cs.size(); i++){		
			cnt=Float.parseFloat(cs.get(i).get("cnt").toString());
			np=Float.parseFloat(cs.get(i).get("np").toString());			
			if(np>=(cnt/2)&&np<((cnt/3)*2)){
				pa=cnt-np;
				cs.get(i).put("pa", pa);
				cs.get(i).put("avgs", Math.round((np/cnt)*100));
				list.add(cs.get(i));
			}
		}		
		info.put("title", info.get("school_year")+"學年"+info.get("school_term")+"學期"+info.get("sname")+"期末科目1/2不及格名單");
		p.print(response, list, info);
		p=null;
		return null;		
	}
	
	/**
	 * 期末科目2/3
	 * @return
	 * @throws IOException
	 */
	public String endCsMoreHalf() throws IOException{
		csPrint p=new csPrint();
		Map info=getInfo(cno, tno, grade);
		List<Map>cs=getCsScore("score", cno, tno, sno, dno, gno, zno, grade, true);
		List list=new ArrayList();
		Float cnt, np, pa;
		for(int i=0; i<cs.size(); i++){		
			cnt=Float.parseFloat(cs.get(i).get("cnt").toString());
			np=Float.parseFloat(cs.get(i).get("np").toString());
			
			if(np>=((cnt/3)*2)){
				pa=cnt-np;
				cs.get(i).put("pa", pa);
				cs.get(i).put("avgs", Math.round((np/cnt)*100));
				list.add(cs.get(i));
			}
		}		
		info.put("title", info.get("school_year")+"學年"+info.get("school_term")+"學期"+info.get("sname")+"期末科目2/3不及格名單");
		p.print(response, list, info);
		p=null;
		return null;		
	}
	
	/**
	 * 期末成績總表
	 * @return
	 * @throws IOException 
	 */
	public String endScore() throws IOException{
		ServletContext servletContext = request.getServletContext();
		
		List<Map>cls=getCls(cno, tno, sno, dno, gno, zno, grade);
		
		for(int i=0; i<cls.size(); i++){			
			cls.get(i).put("stds", getStdTotalScore(cls.get(i).get("ClassNo").toString(), "score", servletContext.getAttribute("school_term").toString(), true));			
			cls.get(i).put("dtimes", df.sqlGet("SELECT d.depart_class,d.cscode,d.Oid,c.chi_name,cd.name as oName,d.credit FROM CODE_DTIME_OPT cd, Dtime d, Csno c WHERE cd.id=d.opt AND d.cscode !='50000'AND " +
			"d.Sterm='"+servletContext.getAttribute("school_term")+"' AND d.cscode=c.cscode AND d.depart_class='"+cls.get(i).get("ClassNo").toString()+"'"));
			cls.get(i).put("dtimeInfo", getSeldInfo(cls.get(i).get("ClassNo").toString(), servletContext.getAttribute("school_term").toString(), "score", true));
		}
		
		Map info=new HashMap();
		info.put("title", servletContext.getAttribute("school_year")+"學年第 "+servletContext.getAttribute("school_term")+"學期成績總表");
		totalPrint p=new totalPrint();		
		p.print(response, cls, info);
		return null;
	}
	
	/**
	 * 期中成績總表
	 * score2
	 * @return
	 * @throws IOException 
	 */
	public String midScore() throws IOException{
		ServletContext servletContext = request.getServletContext();
		
		List<Map>cls=getCls(cno, tno, sno, dno, gno, zno, grade);
		
		for(int i=0; i<cls.size(); i++){			
			cls.get(i).put("stds", getStdTotalScore(cls.get(i).get("ClassNo").toString(), "score2", servletContext.getAttribute("school_term").toString(), false));
			cls.get(i).put("dtimes", df.sqlGet("SELECT d.depart_class,d.cscode,d.Oid,c.chi_name,cd.name as oName,d.opt,d.credit FROM CODE_DTIME_OPT cd, Dtime d, Csno c WHERE cd.id=d.opt AND d.cscode !='50000'AND " +
			"d.Sterm='"+servletContext.getAttribute("school_term")+"' AND d.cscode=c.cscode AND d.depart_class='"+cls.get(i).get("ClassNo").toString()+"'"));
			cls.get(i).put("dtimeInfo", getSeldInfo(cls.get(i).get("ClassNo").toString(), servletContext.getAttribute("school_term").toString(), "score2", false));
		}
		
		Map info=new HashMap();
		info.put("title", servletContext.getAttribute("school_year")+"學年第 "+servletContext.getAttribute("school_term")+"學期 (期中) 成績總表");
		totalPrint p=new totalPrint();		
		p.print(response, cls, info);
		return null;
	}
	
	/**
	 * 學優獎學金
	 * @return
	 * @throws IOException
	 */
	public String honors()throws IOException{
		ServletContext s = request.getServletContext();		
		String term=s.getAttribute("school_term").toString();
		List<Map>cls=getNotMasterCls(cno, tno, sno, dno, gno, zno, grade);
		List<Map>stds;
		List<Map>tmp;
		
		for(int i=0; i<cls.size(); i++){
			if(cls.get(i).get("ClassName").toString().indexOf("碩")>0){
				cls.remove(i);
				continue;
			}
			tmp=df.sqlGet("SELECT ROUND(SUM(h.credit*h.score)/SUM(h.credit),3) as score, s.student_no, s.student_name,(SELECT COUNT(*)FROM Seld, Dtime WHERE " +
			"Dtime.Oid=Seld.Dtime_oid AND Dtime.Sterm='"+term+"' AND Seld.student_no=s.student_no AND Seld.score<60)as fail, " +
			"(SELECT total_score FROM Just WHERE Just.student_no=s.student_no)as just,(SELECT score FROM Seld, Dtime, " +
			"Csno WHERE Dtime.Oid=Seld.Dtime_oid AND Dtime.Sterm='"+term+"' AND Seld.student_no=s.student_no AND " +
			"Dtime.cscode=Csno.cscode AND Csno.chi_name LIKE'%體育%' LIMIT 1)as sport FROM stmd s, Seld h, " +
			"Class c, Dtime d WHERE d.Oid=h.Dtime_oid AND d.Sterm='"+term+"' AND s.student_no=h.student_no AND " +
			"c.ClassNo=s.depart_class AND c.ClassNo='"+cls.get(i).get("ClassNo")+"' AND c.Type='P' GROUP BY h.student_no");
			
			stds=new ArrayList();
			for(int j=0; j<tmp.size(); j++){
				//學業80				
				try{
					if(Float.parseFloat(tmp.get(j).get("score").toString())<80){continue;}
					if(Integer.parseInt(tmp.get(j).get("just").toString())<80){continue;}					
					if(tmp.get(j).get("sport")!=null){
						if(Float.parseFloat(tmp.get(j).get("sport").toString())<70){
							continue;					
						}
					}				
					stds.add(tmp.get(j));
				}catch(Exception e){
					continue;
				}			
			}			
			stds=mi.sortListByKeyDESC(stds, "just");
			cls.get(i).put("stds", mi.sortListByKeyDESC(stds, "score"));						
		}	
		
		honorPrint p=new honorPrint();
		p.print(response, cls, getInfo(cno, tno, grade));		
		return null;
	}
	
	/**
	 * 連續不及格
	 * @return
	 * @throws IOException 
	 */
	public String yeaReach() throws IOException{
		ServletContext s = request.getServletContext();		
		int year=Integer.parseInt(s.getAttribute("school_year").toString());
		int term=Integer.parseInt(s.getAttribute("school_term").toString());	
		
		//20140702新增 (因上學期成績抓取有誤而加的判斷)
		if(term==1){
			year=year-1;
			term=2;
		}else{
			term=term-1;
		}
		
		
		List<Map>stds=new ArrayList();//上學期21
		
		//復學生上學期成績
		List<Map>st=df.sqlGet("SELECT student_no FROM stmd WHERE occur_status='4'AND occur_year='"+s.getAttribute("school_year")+"'AND occur_term='"+s.getAttribute("school_term")+"'");
		List<Map>tmp=new ArrayList();
		Map<String, String>sc;
		for(int i=0; i<st.size(); i++){			
			sc=df.sqlGetMap("SELECT school_year, school_term FROM ScoreHist WHERE "
			+ "(school_year!="+s.getAttribute("school_year")+" OR school_term!="+s.getAttribute("school_term")+") AND "
			+ "student_no='"+st.get(i).get("student_no")+"' ORDER BY school_year DESC, school_term DESC LIMIT 1");			
			tmp.addAll(df.sqlGet("SELECT c.ClassName,s.student_no, s.student_name, SUM(h.credit)as cnt, SUM(IF(score<60, h.credit, 0))as fail FROM ScoreHist h, stmd s, Class c "
			+ "WHERE s.student_no=h.student_no AND c.ClassNo=s.depart_class AND (h.school_year!='"+s.getAttribute("school_year")+"' OR h.school_term!='"+s.getAttribute("school_term")+
			"') AND s.student_no='"+st.get(i).get("student_no")+"'GROUP BY s.student_no ORDER BY c.ClassNo"));			
		}		
		
		//上學期成績
		tmp.addAll(df.sqlGet("SELECT c.ClassName,s.student_no, s.student_name, SUM(h.credit)as cnt, SUM(IF(score<60, h.credit, 0))as fail " +
		"FROM ScoreHist h, stmd s, Class c WHERE s.student_no=h.student_no AND c.ClassNo=s.depart_class AND " +
		"h.school_year='"+year+"' AND h.school_term='"+term+"' AND c.CampusNo='"+cno+"'AND c.SchoolType='"+tno+"' AND c.SchoolNo LIKE'"+sno+"%' AND c.DeptNo LIKE'"+dno+"%' AND c.Grade LIKE'"+gno+"%' AND c.SeqNo LIKE'"+zno+"%' " +
		"GROUP BY s.student_no ORDER BY c.ClassNo"));
		
		float cnt;
		float fail;
		for(int i=0; i<tmp.size(); i++){
			fail=Float.parseFloat(tmp.get(i).get("fail").toString());	
			if(fail<1){				
				continue;
			}			
			cnt=Float.parseFloat(tmp.get(i).get("cnt").toString());
			if(cnt<9)continue;//小於9學分不加入統計
			if(fail>(cnt/3)*2){				
				stds.add(tmp.get(i));
			}
		}
		
		List<Map>stds1=new ArrayList();	//本學期21
		
		//本學期成績
		List<Map>tmp1=df.sqlGet("SELECT c.ClassName,s.student_no, s.student_name, SUM(h.credit)as cnt, SUM(IF((h.score<60 OR h.status IS NOT NULL), h.credit, 0))as fail " +
		"FROM Seld h, stmd s, Class c, Dtime d WHERE d.Oid=h.Dtime_oid AND d.Sterm='"+s.getAttribute("school_term")+"' AND " +
		"s.student_no=h.student_no AND c.ClassNo=s.depart_class AND c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"' " +
		"GROUP BY s.student_no ORDER BY c.ClassNo");		
		
		//本學期21
		for(int i=0; i<tmp1.size(); i++){
			fail=Float.parseFloat(tmp1.get(i).get("fail").toString());	
			if(fail<1){//沒有不及格跳出
				continue;
			}			
			cnt=Float.parseFloat(tmp1.get(i).get("cnt").toString());
			if(cnt<9)continue;//小於九學分無二一
			if(fail>(   (cnt/3)*2    )){				
				stds1.add(tmp1.get(i));
			}
		}		
		
		List stds2=new ArrayList();//連續32//2021-6-3註冊組改校規
		Map map;
		for(int i=0; i<stds1.size(); i++){			
			for(int j=0; j<stds.size(); j++){				
				//學號相同即加入				
				if(stds1.get(i).get("student_no").equals(stds.get(j).get("student_no"))){					
					map=new HashMap();
					map.put("student_no", stds1.get(i).get("student_no"));
					map.put("student_name", stds1.get(i).get("student_name"));
					map.put("ClassName", stds1.get(i).get("ClassName"));
					map.put("last", stds.get(j).get("fail")+" / "+stds.get(j).get("cnt"));
					map.put("now", stds1.get(i).get("fail")+" / "+stds1.get(i).get("cnt"));
					stds2.add(map);
				}				
			}			
		}		
		yeaReach y=new yeaReach();
		y.print(response, stds, stds1, stds2, getInfo(cno, tno, grade));		
		return null;
	}
	
	/**
	 * 歷年平均
	 * @return
	 * @throws IOException 
	 */
	public String avgScore() throws IOException{
		ServletContext s = request.getServletContext();		
		String term=s.getAttribute("school_term").toString();
		List<Map>cls=getCls(cno, tno, sno, dno, gno, zno, grade);
		List<Map>tmp;
		List<Map>stds,scores;
		
		for(int i=0; i<cls.size(); i++){
			//應要求將固定成績改為動態重新計算
			/*
			stds=df.sqlGet("SELECT SUM(h.score)as total, ROUND(AVG(h.score),2)as avg, s.student_no, s.student_name " +
			"FROM stmd s, ScoreHist h WHERE s.student_no=h.student_no AND s.depart_class='"+cls.get(i).get("ClassNo")+"' " +
			"GROUP BY s.student_no");
			
			
			scores=df.sqlGet("SELECT sh.student_no,ROUND(AVG(sh.score),2)as score FROM " +
			"ScoreHist sh, stmd s WHERE s.student_no=sh.student_no AND s.depart_class='"+cls.get(i).get("ClassNo")+"' " +
			"GROUP BY sh.school_year, sh.school_term, sh.student_no ORDER BY sh.student_no,sh.school_year, sh.school_term");
			*/
			
			stds=df.sqlGet("SELECT ROUND(AVG(h.score),2)as avg, s.student_no, s.student_name FROM " +
			"stmd s, Stavg h WHERE s.student_no=h.student_no AND s.depart_class='"+cls.get(i).get("ClassNo")+"'GROUP BY s.student_no");
			
			scores=df.sqlGet("SELECT sh.student_no,ROUND(AVG(sh.score),2)as score FROM " +
			"Stavg sh, stmd s WHERE s.student_no=sh.student_no AND s.depart_class='"+cls.get(i).get("ClassNo")+"'" +
			"GROUP BY sh.school_year, sh.school_term, sh.student_no ORDER BY sh.student_no,sh.school_year, sh.school_term");
			
			
			tmp=new ArrayList();
			tmp.addAll(stds);
			try{
				tmp=mi.sortListByKeyDESC(tmp, "avg");
			}catch(Exception e){
				//System.out.println(tmp);
			}
			
			for(int j=0; j<tmp.size(); j++){
				for(int k=0; k<stds.size();k++){
					if(tmp.get(j).get("student_no").equals(stds.get(k).get("student_no"))){
						stds.get(k).put("no", j+1);
					}
				}
			}
			
			cls.get(i).put("stds", getAvgScore(stds, scores));
			
		}
		
		avgScorePrint p=new avgScorePrint();
		p.print(response, cls, getInfo(cno, tno, grade));
		
		return null;
	}
	
	/**
	 * 郵寄通知
	 * @return
	 * @throws IOException
	 */
	public String mail() throws IOException{
		ServletContext servletContext = request.getServletContext();
		DataFinder manager = (DataFinder) get("DataFinder");

		String term = servletContext.getAttribute("school_term").toString();
		String year = servletContext.getAttribute("school_year").toString();
		//String classLess = "All".equalsIgnoreCase(departClass) ? campusInCharge
		//+ schoolInCharge + deptInCharge : departClass;

		String schoolAddr = "115台北市南港區研究院路三段245號 中華科技大學註冊組(02)2782-1862＃125、126、203";
		if (tno.equals("N")) {
			schoolAddr = "115台北市南港區研究院路三段245號 中華科技大學進修部教務組(02)2782-1862＃136、163";
		}
		if (cno.equals("2")) {
			schoolAddr = "31241 新竹縣橫山鄉中華街200號 中華科技大學新竹分部教務組(03)593-5707＃102、103";
		}

		List<Map>stmds = manager.sqlGet("SELECT s.student_no, s.student_name, t.total_score, s.telephone," +
		"c.ClassName, s.curr_addr, s.curr_post, s.parent_name, c.CampusNo, c.SchoolNo, c.SchoolType FROM stmd s LEFT " +
		"OUTER JOIN Just t ON s.student_no=t.student_no, Class c WHERE " +
		"c.ClassNo=s.depart_class AND c.CampusNo='"+cno+"'AND c.SchoolType='"+tno+"' AND c.SchoolNo LIKE'"+sno+"%' AND c.DeptNo LIKE'"+dno+"%' AND c.Grade LIKE'"+gno+"%' AND c.SeqNo LIKE'"+zno+"%'ORDER BY s.depart_class,s.student_no");

		List<Map>selds;
		float total;
		float totalCredit;
		float nopa;
		int pa = 60;
		float score;
		float credit;
		float count;
		
		for (int i = 0; i < stmds.size(); i++) {//扣考標記為0分	
			
			selds = manager.sqlGet("SELECT o.name as optName, IF(s.status='1',0,s.score)as score, c.chi_name, s.credit, d.opt " +
			"FROM Seld s, Csno c, Dtime d, CODE_DTIME_OPT o WHERE "
			+ "o.id=d.opt AND s.Dtime_oid=d.Oid AND s.Dtime_oid=d.Oid AND c.cscode=d.cscode AND s.student_no='"+ 
			stmds.get(i).get("student_no")+ "' AND d.Sterm='"+term+"' AND s.cscode!='50000' AND c.chi_name NOT LIKE'%論文%'");

			total = 0;
			nopa = 0;
			totalCredit = 0;
			count=0f;
			for (int j = 0; j < selds.size(); j++) {
				if (selds.get(j).get("score") != null) {
					score = Float.parseFloat(selds.get(j).get("score").toString());
					credit = Float.parseFloat(selds.get(j).get("credit").toString());
					total = total + score;
					totalCredit = totalCredit + credit;
					
					count=(credit*score)+count;
					
					if (score >= pa) {
						nopa = nopa + credit;
					}
				}
			}

			stmds.get(i).put("totalCredit", totalCredit);
			stmds.get(i).put("nopa", nopa);
			if (total < 1) {
				stmds.get(i).put("total", 0);
			} else {
				
				stmds.get(i).put("total", count / totalCredit);
			}

			stmds.get(i).put("selds", selds);
		}
		mailPrint p=new mailPrint();
		p.print(response, year, term, stmds, schoolAddr);
		return null;
	}	
	
	/**
	 * 畢業學分下限
	 * @return
	 * @throws IOException
	 */
	public String gradCredit() throws IOException{
		int start=Integer.parseInt(getContext().getAttribute("school_year").toString());
		StringBuilder sql;
		List<Map>list=new ArrayList();
		List<Map>schools=new ArrayList();		
		for(int i=(start-10); i<start; i++){
			sql=new StringBuilder("SELECT '"+i+"'as year,ROUND(AVG(credit.all_credit),2)as all_credit,"
			+ "ROUND(AVG(credit.dept_credit),2)as dept_credit,"
			+ "ROUND(AVG(credit.inst_credit),2)as inst_credit "
			+ "FROM(SELECT(SELECT SUM(credit)FROM ScoreHist WHERE student_no=st.student_no AND "
			+ "(score IS NULL OR score='' OR score>=60))as all_credit,"
			+ "(SELECT SUM(credit)FROM ScoreHist s1, Class c1 WHERE "
			+ "c1.DeptNo!=c.DeptNo AND c1.ClassNo=s1.stdepart_class AND "
			+ "s1.student_no=st.student_no AND c1.Dept!='0' AND"
			+ "(s1.score IS NULL OR s1.score='' OR s1.score>=60))as dept_credit,"
			+ "(SELECT SUM(credit)FROM ScoreHist s2, Class c2 WHERE "
			+ "c2.InstNo!=c.InstNo AND c2.ClassNo=s2.stdepart_class AND "
			+ "s2.student_no=st.student_no AND(s2.score IS NULL OR s2.score='' "
			+ "OR s2.score>=60))as inst_credit FROM Gstmd st, Class c WHERE st.depart_class=c.ClassNo "
			+ "AND st.occur_status='6'AND st.occur_year='"+i+"'");
			if(!cno.equals(""))sql.append("AND c.CampusNo='"+cno+"'");
			if(!tno.equals(""))sql.append("AND c.SchoolType='"+tno+"'");
			if(!sno.equals(""))sql.append("AND c.SchoolNo='"+sno+"'");
			if(!dno.equals(""))sql.append("AND c.DeptNo='"+dno+"'");
			if(!gno.equals(""))sql.append("AND c.Grade='"+gno+"'");
			if(!zno.equals(""))sql.append("AND c.SeqNo='"+zno+"'");
			sql.append(")as credit");
			list.add(df.sqlGetMap(sql.toString()));
		}
		
		List<Map>s=df.sqlGet("SELECT cs.id, cs.name,(COUNT(*))as cnt FROM Gstmd s, Class c, CODE_SCHOOL cs "
		+ "WHERE s.depart_class=c.ClassNo AND c.SchoolNo=cs.id AND s.occur_status='6'GROUP BY cs.id");
		List tmp;
		for(int i=0; i<s.size(); i++){
			
			tmp=new ArrayList();
			for(int j=(start-10); j<start; j++){
				tmp.add(df.sqlGetMap("SELECT ROUND(AVG(credit.all_credit),2)as all_credit,"
				+ "ROUND(AVG(credit.dept_credit),2)as dept_credit,"
				+ "ROUND(AVG(credit.inst_credit),2)as inst_credit "
				+ "FROM(SELECT(SELECT SUM(credit)FROM ScoreHist WHERE "
				+ "student_no=st.student_no AND "
				+ "(score IS NULL OR score='' OR score>=60))as all_credit,"
				+ "(SELECT SUM(credit)FROM ScoreHist s1, Class c1 WHERE c1.DeptNo!=c.DeptNo AND c1.ClassNo=s1.stdepart_class "
				+ "AND s1.student_no=st.student_no AND c1.Dept!='0' AND(s1.score IS NULL OR s1.score='' OR s1.score>=60))as dept_credit,"
				+ "(SELECT SUM(credit)FROM ScoreHist s2, Class c2 WHERE c2.InstNo!=c.InstNo "
				+ "AND c2.ClassNo=s2.stdepart_class AND s2.student_no=st.student_no "
				+ "AND(s2.score IS NULL OR s2.score='' OR s2.score>=60))as inst_credit "
				+ "FROM Gstmd st, Class c WHERE "
				+ "st.depart_class=c.ClassNo "
				+ "AND st.occur_year='"+j+"' AND st.occur_status='6'"
				+ "AND c.SchoolNo='"+s.get(i).get("id")+"')as credit"));				
			}
			
			s.get(i).put("cnt", tmp);
			
		}
		
		gradCreditCount p=new gradCreditCount();
		p.print(response, list, s);
		return null;
	}
	
	/**
	 * 成績單批次
	 * @return
	 * @throws IOException
	 */
	public String scoreHist() throws IOException{
		List<Map>stds=getStds(cno, tno, sno, dno, gno, zno, grade);
		
		for(int i=0; i<stds.size(); i++){
			//System.out.println(stds.get(i));
			stds.get(i).put("MasterData", df.sqlGetMap("SELECT*FROM MasterData WHERE student_no='"+stds.get(i).get("student_no")+"'"));
			stds.get(i).put("years", getScoreHist(stds.get(i).get("graduate").toString(),stds.get(i).get("student_no").toString(), stds.get(i).get("SchNo").toString()));			
		}		
		ScoreHistPrint p=new ScoreHistPrint();
		p.print(response, stds);
		return null;
	}
	
	/**
	 * 成績冊
	 * @return
	 * @throws IOException 
	 */
	public String signFormPrint() throws IOException{
		StringBuilder sb=new StringBuilder("SELECT (SELECT COUNT(*)FROM stmd WHERE depart_class=c.ClassNo)as cnt, c.ClassName, c.ClassNo FROM Class c WHERE c.CampusNo='"+cno+"'");
		//if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
		if(!tno.equals(""))sb.append("AND c.SchoolType='"+tno+"'");
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		if(!grade.equals("")){			
			if(grade.equals("0"))sb.append("AND graduate='0'");
			if(grade.equals("1"))sb.append("AND graduate='1'");
			if(grade.equals("2"))sb.append("AND Type='E'");
			if(grade.equals("3"))sb.append("AND Type='C'");
			if(grade.equals("4"))sb.append("AND Type='N'");
		}
		/*else{
			sb.append("AND (Type='P'||Type='E'||Type='C'||Type='N')");
		}*/		
		sb.append("ORDER BY c.ClassNo");		
		//System.out.println(sb);;
		List<Map>cls=df.sqlGet(sb.toString());
		signFormPrint p=new signFormPrint();
		StringBuilder sb1=new StringBuilder("SELECT DISTINCT cl.ClassName, cdo.name as optName, IFNULL(e.cname, '')as cname, c.chi_name, c.cscode "
		+ "FROM Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, CODE_DTIME_OPT cdo, Csno c, Class cl, Seld s WHERE s.Dtime_oid=d.Oid AND "
		+ "d.depart_class=cl.ClassNo AND cdo.id=d.opt AND d.Sterm='"+getContext().getAttribute("school_term")+"'AND d.cscode=c.cscode AND "
		+ "d.depart_class IN(");		
		//sb.append(")ORDER BY cl.DeptNo, cl.Grade, d.opt");
		for(int i=0; i<cls.size(); i++){
			sb1.append("'"+cls.get(i).get("ClassNo")+"',");
		}
		sb1.delete(sb1.length()-1, sb1.length());
		sb1.append(")AND c.cscode!='50000'ORDER BY cl.DeptNo, cl.Grade, d.opt");
		p.print(response, df.sqlGet(sb1.toString()));
		return null;
	}
	
	public String scoreHistOne() throws IOException{
		List<Map>stds;
		if(student_no.indexOf(",")>-1){
			stds=getStd(student_no.substring(0, student_no.indexOf(",")));
		}else{
			stds=getStd(student_no);
		}
		
		if(stds.size()>0){
			for(int i=0; i<stds.size(); i++){	
				stds.get(i).put("MasterData", df.sqlGetMap("SELECT*FROM MasterData WHERE student_no='"+stds.get(i).get("student_no")+"'"));
				stds.get(i).put("years", getScoreHist(stds.get(i).get("graduate").toString(), stds.get(i).get("student_no").toString(), stds.get(i).get("SchNo").toString()));			
			}		
			ScoreHistPrint p=new ScoreHistPrint();
			p.print(response, stds);
			return null;
		}
		Message msg=new Message();
		msg.setError("請指定學生");
		this.savMessage(msg);
		return execute();
	}
	
	private List getScoreTerm(String stdNo, String year, String term, int pass){		
		return df.sqlGet("SELECT cs.chi_name, c.sname,IF(score<"+pass+", '*', '')as np, cs.cscode, s.credit, s.score FROM ScoreHist s, CODE_DTIME_OPT c, Csno cs WHERE "
		+ "s.student_no='"+stdNo+"'AND s.school_year='"+year+"'AND s.school_term='"+term+"'AND c.id=s.opt AND s.cscode=cs.cscode AND s.cscode!='99999'ORDER BY s.opt");
	}
	
	private Map getExtraTerm(String stdNo, String year, String term, int pass){		
		StringBuilder sql=new StringBuilder("SELECT(SELECT remark FROM Gmark WHERE student_no='"+stdNo+"'AND school_year='"+year+"'AND school_term='"+term+"'LIMIT 1)as Gmark,"
		+ "(SELECT score FROM cond WHERE student_no='"+stdNo+"'AND school_year='"+year+"'AND school_term='"+term+"')as cond,"
		+ "(SELECT SUM(case when (score>="+pass+" OR score IS NULL) then credit else 0 end)FROM ");
		if(term.equals("2")){
			sql.append("ScoreHist WHERE student_no='"+stdNo+"'AND school_year<='"+year+"')as cnt, ");
		}else{
			sql.append("ScoreHist WHERE student_no='"+stdNo+"'AND (school_year<'"+year+"'OR(school_year='"+year+"'AND school_term='1')))as cnt, ");
		}		
		//sql.append("ROUND(AVG(score),1)as score,SUM(case when (score>="+pass+" OR score IS NULL) then credit else 0 end)as credit "
		sql.append("ROUND(SUM(score*credit)/SUM(credit), 1)as score, ");
		sql.append("SUM(case when (score>="+pass+" OR score IS NULL) then credit else 0 end)as credit ");
		sql.append("FROM ScoreHist WHERE student_no='"+stdNo+"'AND school_year='"+year+"'AND school_term='"+term+"'AND cscode!='99999'");
		//System.out.println(sql);
		Map m=df.sqlGetMap(sql.toString());
		
		//System.out.println(m);
		if(m.get("score")!=null){
			return m;
		}else{
			return null;
		}
		
	}
	
	private List getScoreHist(String grad, String stdNo, String SchNo){
		int pass=60;
		if(SchNo.equals("M"))pass = 70;
		List<Map>years=df.sqlGet("SELECT DISTINCT school_year FROM ScoreHist WHERE student_no='"+stdNo+"'ORDER BY school_year");
		for(int i=0; i<years.size(); i++){			
			if(years.get(i).get("school_year")==null)continue;
			years.get(i).put("extra1", getExtraTerm(stdNo, years.get(i).get("school_year").toString(), "1", pass));
			years.get(i).put("term1", getScoreTerm(stdNo, years.get(i).get("school_year").toString(), "1", pass));
			years.get(i).put("extra2", getExtraTerm(stdNo, years.get(i).get("school_year").toString(), "2", pass));
			years.get(i).put("term2", getScoreTerm(stdNo, years.get(i).get("school_year").toString(), "2", pass));
			//System.out.println(years.get(i));			
		}
		if(grad.equals("0")){
			return years;//非畢業班
		}else{			
			Map m=new HashMap();
			m=df.sqlGetMap("SELECT ''as Gmark, ''as cond,(SELECT SUM(case when (score>="+pass+" OR score IS NULL) then credit else 0 end)FROM ScoreHist WHERE student_no=se.student_no)as cnt,"
			+ "SUM(dt.credit)as credit FROM Seld se, Dtime dt WHERE se.Dtime_oid=dt.Oid AND dt.Sterm='"+getContext().getAttribute("school_term")+"' AND se.student_no='"+stdNo+"'");
			
			try{
				m.put("cnt", Float.parseFloat(m.get("cnt").toString())+Float.parseFloat(m.get("credit").toString()));
				years.get(years.size()-1).put("school_year", getContext().getAttribute("school_year"));
				years.get(years.size()-1).put("extra"+getContext().getAttribute("school_term"), m);
				years.get(years.size()-1).put("term"+getContext().getAttribute("school_term"), df.sqlGet("SELECT cd.sname, c.chi_name, c.cscode, d.credit, ''as score, ''as np FROM Seld s, Dtime d, Csno c, CODE_DTIME_OPT cd WHERE d.opt=cd.id AND c.cscode=d.cscode AND s.Dtime_oid=d.Oid AND d.Sterm='"+getContext().getAttribute("school_term")+"' AND s.student_no='"+stdNo+"'"));
				
			}catch(Exception e){
				
			}
			return years;
		}
		
		
	}
}
