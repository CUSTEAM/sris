package action.score.scorePrint;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import service.impl.DataFinder;
import service.impl.base.BaseMathImpl;
import action.BaseAction;

public class scorePrintBase extends BaseAction{
	
	protected DataFinder df = (DataFinder) get("DataFinder");
	protected BaseMathImpl mi= (BaseMathImpl) get("BaseMathImpl");
	
	/**
	 * 以班級範圍找學生
	 * @param cno
	 * @param tno
	 * @param sno
	 * @param dno
	 * @param gno
	 * @param zno
	 * @param grade
	 * @return
	 */
	protected List getStds(String cno, String tno, String sno, String dno, String gno, String zno, String grade){
		//StringBuilder sb=new StringBuilder("SELECT (SELECT COUNT(DISTINCT school_year) FROM ScoreHist WHERE student_no=s.student_no)as year, s.student_no, c.ClassName, s.idno, s.student_name FROM Class c, stmd s WHERE s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"'");
		StringBuilder sb=new StringBuilder("SELECT c.graduate, c.SchNo, s.student_no, cs.name as SchoolName, cd.name as DeptName, s.idno, s.student_name FROM CODE_DEPT cd, CODE_SCHOOL cs, Class c, stmd s WHERE cs.id=c.SchoolNo AND c.DeptNo=cd.id AND s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"'");
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
		}else{
			sb.append("AND (Type='P'||Type='E'||Type='C'||Type='N')");
		}		
		sb.append("ORDER BY c.ClassNo, s.student_no");
		//System.out.println(sb);
		return df.sqlGet(sb.toString());
	}
	
	protected List<Map>getStd(String stdNo){
		List stds;
		//StringBuilder sb=new StringBuilder("SELECT (SELECT COUNT(DISTINCT school_year) FROM ScoreHist WHERE student_no=s.student_no)as year, s.student_no, c.ClassName, s.idno, s.student_name FROM Class c, stmd s WHERE s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"'");
		StringBuilder sb=new StringBuilder("SELECT c.graduate, c.SchNo, s.student_no, cs.name as SchoolName, cd.name as DeptName, s.idno, s.student_name FROM CODE_DEPT cd, CODE_SCHOOL cs, Class c, stmd s WHERE cs.id=c.SchoolNo AND c.DeptNo=cd.id AND s.depart_class=c.ClassNo AND s.student_no='"+stdNo+"'");
		
		stds=df.sqlGet(sb.toString());
		if(stds.size()<1){
			sb=new StringBuilder("SELECT c.graduate, c.SchNo, s.student_no, cs.name as SchoolName, cd.name as DeptName, s.idno, s.student_name FROM CODE_DEPT cd, CODE_SCHOOL cs, Class c, Gstmd s WHERE cs.id=c.SchoolNo AND c.DeptNo=cd.id AND s.depart_class=c.ClassNo AND s.student_no='"+stdNo+"'");
			stds=df.sqlGet(sb.toString());
		}
		
		return stds;
	}
	
	/**
	 * 取班級
	 * @return
	 */
	protected List getCls(String cno, String tno, String sno, String dno, String gno, String zno, String grade){
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
		}else{
			sb.append("AND (Type='P'||Type='E'||Type='C'||Type='N')");
		}		
		sb.append("ORDER BY c.ClassNo");
		//System.out.println(sb);
		return df.sqlGet(sb.toString());
	}
	
	
	protected List getAvgScore(List<Map>stds, List<Map>scores){
		List score;
		for(int i=0; i<stds.size(); i++){
			
			score=new ArrayList();
			for(int j=0; j<scores.size(); j++){
				
				if(stds.get(i).get("student_no").equals(scores.get(j).get("student_no"))){
					
					score.add(scores.get(j));
				}				
				stds.get(i).put("scores", score);				
			}			
		}
		
		return stds;
	}
	
	/**
	 * 取班級非碩
	 * 僅學優用
	 * @param cno
	 * @param tno
	 * @param grade
	 * @return
	 */
	protected List getNotMasterCls(String cno, String tno, String sno, String dno, String gno, String zno, String grade){
		StringBuilder sb=new StringBuilder("SELECT (SELECT COUNT(*)FROM stmd WHERE depart_class=c.ClassNo)as cnt, c.ClassName, c.ClassNo FROM Class c WHERE c.SchNo!='M'AND c.Type='P'");
		if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
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
		}else{
			sb.append("AND (Type='P'||Type='E'||Type='C'||Type='N')");
		}
		sb.append("ORDER BY c.ClassNo");
		return df.sqlGet(sb.toString());
	}
	
	/**
	 * 頁首頁尾基本資料
	 * @return
	 */
	protected Map getInfo(String cno, String tno,String grade){
		
		ServletContext servletContext = request.getServletContext();
		Map info=new HashMap();
		info.put("school_year", servletContext.getAttribute("school_year"));
		info.put("school_term", servletContext.getAttribute("school_term"));
		
		StringBuilder sb=new StringBuilder("SELECT COUNT(*)FROM stmd s, Class c WHERE s.depart_class=c.ClassNo AND " +
		"c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"'");
		if(!grade.equals("")){			
			if(grade.equals("0"))sb.append("AND graduate='0'");
			if(grade.equals("1"))sb.append("AND graduate='1'");
			if(grade.equals("2"))sb.append("AND Type='E'");
			if(grade.equals("3"))sb.append("AND Type='C'");
			if(grade.equals("4"))sb.append("AND Type='N'");
		}else{
			sb.append("AND (Type='P'||Type='E'||Type='C'||Type='N')");
		}
		info.put("allStds", df.sqlGetInt(sb.toString()));
		info.put("sname", df.sqlGetStr("SELECT name FROM CODE_SCHOOL_TYPE WHERE type='"+tno+"'"));
		return info;
	}
	
	/**
	 * 成績總表取學生成績
	 * @param ClassNo
	 * @param stype
	 * @param term
	 * @return
	 * @throws IOException
	 */
	protected List getStdTotalScore(String ClassNo, String stype, String term, boolean endTerm) throws IOException{	
		
		int pa=df.getPassLine(ClassNo);
		float totalCredit,pass,credit,score,count;
		StringBuilder sb;
		BigDecimal b;
		List<Map>selds;
		//操行空白給予85
		List<Map>stds=df.sqlGet("SELECT(SELECT ROUND(SUM(h.credit*h.score)/SUM(h.credit),10)FROM Seld h, Dtime d WHERE " +
				"d.Sterm='"+term+"' AND h.Dtime_oid=d.Oid AND h.student_no=s.student_no)as avg, " +
				"s.student_name, s.student_no, IFNULL(j.total_score, 85)as total_score FROM stmd s LEFT OUTER JOIN Just j ON " +
				"j.student_no=s.student_no, Class c WHERE s.depart_class=c.ClassNo AND c.ClassNo='"+ClassNo+"'");
		
		for(int i=0; i<stds.size(); i++){
			sb=new StringBuilder("SELECT s.status, d.depart_class,d.cscode,s.credit,d.opt,cd.name as oName,cl.ClassName,d.Oid,c.chi_name, IFNULL(s."+stype+",0)" +
			"as score FROM CODE_DTIME_OPT cd, Seld s,stmd st,Dtime d,Csno c,Class cl WHERE c.cscode=d.cscode AND s.student_no=st.student_no AND " +
			"cd.id=d.opt AND s.Dtime_oid=d.Oid AND d.Sterm='"+term+"' AND st.student_no='"+stds.get(i).get("student_no")+
			"'AND cl.ClassNo=d.depart_class AND d.cscode !='50000'");
			if(!endTerm)sb.append("AND d.Oid NOT IN(SELECT Dtime.Oid FROM Dtime, MidtermExcluded WHERE Dtime.depart_class=MidtermExcluded.DepartClass AND Dtime.cscode=MidtermExcluded.Cscode)");
			selds=df.sqlGet(sb.toString());			
			
			pass=0;//過
			score=0;
			totalCredit = 0;
			count=0f;
			credit=0f;
			for(int j=0; j<selds.size(); j++){
				if(selds.get(j).get("status")!=null){
					selds.get(j).put("score", 0);
					selds.get(j).put("score2", 0);
				}
				try{
					score=Float.parseFloat(selds.get(j).get("score").toString());	
					credit=Float.parseFloat(selds.get(j).get("credit").toString());//無論如何加入已選課學分數
					totalCredit=totalCredit+credit;
					count+=(credit*score);	
					if(Float.parseFloat(selds.get(j).get("score").toString())>=pa){
						pass=pass+credit;
					}
				}catch(Exception e){
					
				}
			}
			
			try{
				b=new BigDecimal(count / totalCredit);//取小數第2位
				stds.get(i).put("avg", b.setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue());
			}catch(Exception e){
				stds.get(i).put("avg", 0);
			}
			
			stds.get(i).put("total", totalCredit);
			stds.get(i).put("pass", pass);			
			stds.get(i).put("selds", selds);
		}
		
		List<Map>tmp=new ArrayList();
		tmp.addAll(stds);
		tmp=mi.sortListByKeyDESC(tmp, "avg");
		//tmp=sortList(tmp, "avg", true);
		for(int i=0; i<tmp.size(); i++){			
			for(int j=0; j<stds.size();j++){
				if(tmp.get(i).get("student_no").equals(stds.get(j).get("student_no"))){
					stds.get(j).put("no", i+1);
				}
			}
		}
		
		return stds;
	}
	
	protected List getSeldInfo(String ClassNo, String term, String score, boolean endTerm){	
		StringBuilder sb=new StringBuilder("SELECT d.depart_class, d.cscode,cl.ClassName,c.chi_name, COUNT(*)as sld," +
		"COUNT(IF(s."+score+" <"+df.getPassLine(ClassNo)+",1,null) )as fail,IFNULL(ROUND(AVG(s."+score+"),2), 0)as avg " +
		"FROM Seld s, stmd st, Dtime d,Csno c, Class cl WHERE cl.ClassNo=d.depart_class AND c.cscode=d.cscode AND " +
		"s.student_no=st.student_no AND d.Oid=s.Dtime_oid AND d.Sterm='"+term+"' AND st.depart_class='"+ClassNo+"' AND d.cscode!='50000' ");
		if(!endTerm)sb.append("AND d.Oid NOT IN(SELECT Dtime.Oid FROM Dtime, MidtermExcluded WHERE Dtime.depart_class=MidtermExcluded.DepartClass AND Dtime.cscode=MidtermExcluded.Cscode)");
		sb.append("GROUP BY d.cscode ORDER BY cl.Grade,cl.ClassNo DESC");
		return df.sqlGet(sb.toString());
	}
	
	/**
	 * 取大範圍學生成績
	 * @param stype 期中或期末:Seld欄位:score2 or score
	 * @return
	 */
	protected List getScore(String stype, String cno, String tno, String sno, String dno, String gno, String zno, String grade, boolean endTerm){		
		ServletContext servletContext = request.getServletContext();
		List stds;		
		StringBuilder sb=new StringBuilder("SELECT c.ClassName, st.student_no, st.student_name, " +
		"SUM(IF(s."+stype+"<60||s.status='1', s.credit,0))as np, " +//不及格和扣考不計學分
		"SUM(s.credit)as total, ROUND(AVG (IF(s.status='1',0,s.score2)) )as avgs FROM Seld s, stmd st, Dtime d, Class c WHERE " +
		"c.ClassNo=st.depart_class AND d.Oid=s.Dtime_oid AND s.student_no=st.student_no AND c.schNo!='M' AND c.Type='P'AND d.Sterm='"+servletContext.getAttribute("school_term")+"'");		
		if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
		if(!tno.equals(""))sb.append("AND c.SchoolType='"+tno+"'");
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		if(!grade.equals(""))sb.append("AND graduate='"+grade+"'");			
		//期中不列入計算
		if(!endTerm)sb.append("AND d.Oid NOT IN(SELECT Dtime.Oid FROM Dtime, MidtermExcluded WHERE Dtime.depart_class=MidtermExcluded.DepartClass AND Dtime.cscode=MidtermExcluded.Cscode)");
		
		sb.append("GROUP BY s.student_no ORDER BY c.ClassNo");
		stds=df.sqlGet(sb.toString());
		//碩士班70
		sb=new StringBuilder("SELECT c.ClassName, st.student_no, st.student_name, SUM(IF(s."+stype+"<70, s.credit,0))as np, " +
		"SUM(s.credit)as total, AVG("+stype+")as avgs FROM Seld s, stmd st, Dtime d, Class c WHERE " +
		"c.ClassNo=st.depart_class AND d.Oid=s.Dtime_oid AND s.student_no=st.student_no AND " +
		"c.schNo='M' AND c.Type='P' AND d.Sterm='"+servletContext.getAttribute("school_term")+"'");
		
		if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
		if(!tno.equals(""))sb.append("AND c.SchoolType='"+tno+"'");
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		if(!grade.equals(""))sb.append("AND graduate='"+grade+"'");			
		
		if(!endTerm)sb.append("AND d.Oid NOT IN(SELECT Dtime.Oid FROM Dtime, MidtermExcluded WHERE Dtime.depart_class=MidtermExcluded.DepartClass AND Dtime.cscode=MidtermExcluded.Cscode)");
		if(!grade.equals("")){sb.append("AND c.graduate='"+grade+"'");}
		sb.append("GROUP BY s.student_no ORDER BY c.ClassNo");
		stds.addAll(df.sqlGet(sb.toString()));
		return stds;
	}
	
	/**
	 * 取科目成績
	 * @param stype 期中或期末:Seld欄位:score2 or score
	 * @return
	 */
	protected List getCsScore(String stype, String cno, String tno, String sno, String dno, String gno, String zno, String grade, boolean endTerm){
		ServletContext servletContext = request.getServletContext();
		List cls;
		StringBuilder sb=new StringBuilder("SELECT e.cname, cs.chi_name, c.ClassName, COUNT(*)as cnt," +
		"SUM(IF(s."+stype+"<60, 1,0))as np FROM Class c, Dtime d, empl e, Csno cs, Seld s " +
		"WHERE s.Dtime_oid=d.Oid AND cs.cscode=d.cscode AND d.techid=e.idno AND " +
		"d.depart_class=c.ClassNo AND d.Sterm='"+servletContext.getAttribute("school_term")+"' AND " +
		"c.SchNo!='M'");
		
		if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
		if(!tno.equals(""))sb.append("AND c.SchoolType='"+tno+"'");
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		if(!grade.equals(""))sb.append("AND graduate='"+grade+"'");		
		//期中不列入計算
		if(!endTerm)sb.append("AND d.Oid NOT IN(SELECT Dtime.Oid FROM Dtime, MidtermExcluded WHERE Dtime.depart_class=MidtermExcluded.DepartClass AND Dtime.cscode=MidtermExcluded.Cscode)");
		
		sb.append("GROUP BY d.Oid ORDER BY c.ClassNo");
		cls=df.sqlGet(sb.toString());		
		
		//碩士班70
		sb=new StringBuilder("SELECT e.cname, cs.chi_name, c.ClassName, COUNT(*)as cnt," +
		"SUM(IF(s."+stype+"<70, 1,0))as np FROM Class c, Dtime d, empl e, Csno cs, Seld s " +
		"WHERE s.Dtime_oid=d.Oid AND cs.cscode=d.cscode AND d.techid=e.idno AND " +
		"d.depart_class=c.ClassNo AND d.Sterm='"+servletContext.getAttribute("school_term")+"' AND " +
		"c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"'AND c.SchoolNo LIKE'"+sno+"%' AND c.DeptNo LIKE'"+dno+"%' AND c.Grade LIKE'"+gno+"%' AND c.SeqNo LIKE'"+zno+"%' AND c.SchNo='M'");
		if(!endTerm)sb.append("AND d.Oid NOT IN(SELECT Dtime.Oid FROM Dtime, MidtermExcluded WHERE Dtime.depart_class=MidtermExcluded.DepartClass AND Dtime.cscode=MidtermExcluded.Cscode)");
		if(!grade.equals("")){sb.append("AND c.graduate='"+grade+"'");}
		sb.append("GROUP BY d.Oid ORDER BY c.ClassNo");
		cls.addAll(df.sqlGet(sb.toString()));
		return cls;
	}
	

	/**
	 * 取班級學生成績
	 * @param stype 期中或期末:Seld欄位:score2 or score
	 * @return
	 */
	protected List getClsScore(String stype, String cno, String tno, String sno, String dno, String gno, String zno, String grade, boolean endTerm){		
		ServletContext servletContext = request.getServletContext();
		int pa;
		
		StringBuilder sb=new StringBuilder("SELECT (SELECT COUNT(*)FROM stmd WHERE depart_class=ClassNo)as cnt, " +
		"ClassName,ClassNo,SchNo FROM Class c WHERE c.Type='P'");
		
		if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
		if(!tno.equals(""))sb.append("AND c.SchoolType='"+tno+"'");
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		if(!grade.equals(""))sb.append("AND c.graduate='"+grade+"'");	
		
		List<Map>list=df.sqlGet(sb.toString());
		
		List cls=new ArrayList();
		List<Map>tmp;
		for(int i=0; i<list.size(); i++){			
			pa=df.getPassLine(list.get(i).get("ClassNo").toString());
			sb=new StringBuilder("SELECT c.ClassName, st.student_no, st.student_name, SUM(IF(s."+stype+"<"+pa+", s.credit,0))as np, " +
			"SUM(s.credit)as total, ROUND(AVG("+stype+"),1)as avgs FROM Seld s, stmd st, Dtime d, Class c WHERE " +
			"c.ClassNo=st.depart_class AND d.Oid=s.Dtime_oid AND s.student_no=st.student_no AND " +
			"d.Sterm='"+servletContext.getAttribute("school_term")+"' AND c.ClassNo='"+list.get(i).get("ClassNo")+"'");
			
			//期中不列入計算
			if(!endTerm)sb.append("AND d.Oid NOT IN(SELECT Dtime.Oid FROM Dtime, MidtermExcluded WHERE Dtime.depart_class=MidtermExcluded.DepartClass AND Dtime.cscode=MidtermExcluded.Cscode)");
			sb.append("GROUP BY s.student_no ORDER BY s.student_no");
			tmp=df.sqlGet(sb.toString());
			if(tmp.size()<1){
				continue;
			}
			list.get(i).put("stds", tmp);		
			cls.add(list.get(i));
		}
		
		return cls;
	}

}
