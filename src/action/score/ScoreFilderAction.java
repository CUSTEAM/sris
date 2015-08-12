package action.score;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import model.Message;

import service.impl.DataFinder;

import action.BaseAction;

public class ScoreFilderAction extends BaseAction{
	
	public String cno;
	public String tno;
	public String grade;
	
	DataFinder df = (DataFinder) get("DataFinder");
	public String execute(){
		request.setAttribute("checklist", df.sqlGet("SELECT * FROM ScoreHistLog ORDER BY Oid DESC"));
		return SUCCESS;
	}
	
	public String confirm(){
		ServletContext servletContext = request.getServletContext();		
		String term=(String)servletContext.getAttribute("school_term");
		String year=(String)servletContext.getAttribute("school_year");		
		Message msg=new Message();
		
		del50000();
		setZero();//將扣考歸0
		delScoreHist(year, term);//刪除範圍中ScoreHist
		writScoreHist(msg, year, term);//轉ScoreHist
		chEvgrType(year, term);//將ScoreHist轉換隨班附讀標記
		saveCondToScoreHist(year, term);//轉操行至ScoreHIst
		delData(year, term, "Stavg");//刪歷年平均Stavg
		writeStavg(year, term);//轉歷年平均		
		delData(year, term, "comb1");//刪comb1
		writComb1(year, term);//轉comb1			
		delData(year, term, "cond");//刪cond
		writeCond(year, term);//轉cond		
		delData(year, term, "comb2");//刪comb2
		writeComb2(year, term);		
		delData(year, term, "Savedesd");
		writeSavedesd(year, term);		
		savMessage(msg);
		//寫標記
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");				
		df.exSql("INSERT INTO ScoreHistLog(school_year, school_term, CampuseNo, SchoolType, checkDate, cname)VALUES" +
		"('"+year+"', '"+term+"', '"+cno+"', '"+tno+"', '"+sf.format(new Date())+"', '"+df.sqlGetStr("SELECT cname FROM empl WHERE idno='"+getSession().getAttribute("userid")+"'")+"')");
		return execute();
	}
	
	private void del50000(){
		df.exSql("DELETE FROM Seld WHERE Dtime_oid IN"
		+ "(SELECT Oid FROM Dtime WHERE (cscode='50000'OR cscode='T0001')AND Sterm='"+getContext().getAttribute("school_term")+"')AND "
		+ "Dtime_oid IN(SELECT Oid FROM Dtime WHERE Sterm='"+getContext().getAttribute("school_term")+"')AND student_no IN(SELECT s.student_no FROM stmd s, Class c WHERE "
		+ "s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"'AND c.SchoolType='"+tno+"'AND c.graduate LIKE'"+grade+"%')");		
		
	}
	
	/**
	 * null與扣考歸零
	 */
	private void setZero(){		
		df.exSql("UPDATE Seld SET score=0 WHERE (status='1'OR score IS null) AND student_no IN(SELECT s.student_no FROM stmd s, Class c WHERE "
		+ "s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"'AND c.SchoolType='"+tno+"'AND c.graduate LIKE'"+grade+"%')AND "
		+ "Dtime_oid IN(SELECT Oid FROM Dtime WHERE Sterm='"+getContext().getAttribute("school_term")+"')");		
	}
	
	/**
	 * 轉學科成績
	 * @param msg
	 * @return
	 */
	private Message writScoreHist(Message msg, String year, String term){
		String score;
		String evgrType;
		StringBuilder error=new StringBuilder();
		//學科成績
		List<Map>list=df.sqlGet("SELECT s.status, s.student_no, d.depart_class, d.cscode, d.opt, s.credit, " +
		"score, d.Oid FROM Dtime d, Seld s, stmd st, Class c WHERE c.graduate LIKE'"+grade+"%' AND " +
		"d.Sterm='"+term+"' AND d.Oid=s.Dtime_oid AND st.student_no=s.student_no AND " +
		"st.depart_class=c.ClassNo AND c.CampusNo='"+cno+"' AND c.SchoolType='"+
		tno+"' AND d.cscode NOT IN('50000', 'T0001')");//TODO 班會系時間不處理, 預備在Dtime或Csno上定義		
		
		StringBuilder sb=new StringBuilder("INSERT INTO ScoreHist(student_no, school_year, school_" +
		"term, stdepart_class, evgr_type, cscode, opt, credit, score)VALUES");//將insert資料以LOAD的方式匯入ScoreHist
		for(int i=0; i<list.size(); i++){	
			
			if(list.get(i).get("status")==null){				
				score=String.valueOf(list.get(i).get("score"));
				evgrType="1";//正常修課
			}else{
				score="0";
				evgrType="7";//扣考
			}
			
			try{
				sb.append("('"+list.get(i).get("student_no")+"', "+year+", '"+term+
				"',  '"+list.get(i).get("depart_class")+"', '"+evgrType+"',  '"+list.get(i).get("cscode")+"', '"+list.get(i).get("opt")+
				"',  '"+list.get(i).get("credit")+"',  "+score+"),");
			}catch(Exception e){
				e.printStackTrace();
				error.append(list.get(i).get("student_no")+", ");
			}			
		}
				
		sb.delete(sb.length()-1, sb.length());
		try{
			df.exSql(sb.toString());
		}catch(Exception e){	
			error.append("建立失敗");
		}
				
		if(error.length()>0){
			msg.setError(list.size()+"筆成績全部匯入完成, 但匯入中出現錯誤<br>");					
		}else{
			msg.setSuccess(list.size()+"筆成績全部匯入完成<br>包含學科平均、操行、全勤、評語、獎懲、體育、軍訓等獨立資料");					
		}
		
		if(grade.equals("1")){
			deleteGradeDown(msg);
		}
		return msg;
	}
	
	/**
	 * 將畢業下修學生刪除
	 * @param list
	 * @return
	 */
	private void deleteGradeDown(Message msg){	
		StringBuilder sb=new StringBuilder("");
		List<Map>stds=df.sqlGet("SELECT s.student_no,(SELECT COUNT(*)FROM Seld se, Dtime d, Class cl WHERE "
				+ "se.Dtime_oid=d.Oid AND d.depart_class=cl.ClassNo AND se.student_no=s.student_no AND "
				+ "cl.graduate='0')as cnt FROM stmd s, Class c WHERE s.depart_class=c.ClassNo AND "
				+ "c.CampusNo='1' AND c.SchoolType='D' AND c.graduate='1'");
		for(int i=0; i<stds.size(); i++){			
			if(Integer.parseInt(stds.get(i).get("cnt").toString())>0){
				//刪除scoreHist
				df.exSql("DELETE FROM ScoreHist WHERE school_year='"+
				getContext().getAttribute("school_year")+"' AND school_term='"+
				getContext().getAttribute("school_term")+"' AND student_no='"+
				stds.get(i).get("student_no")+"'");
				//刪除stavg
				df.exSql("DELETE FROM Stavg WHERE school_year='"+
				getContext().getAttribute("school_year")+"' AND school_term='"+
				getContext().getAttribute("school_term")+"' AND student_no='"+
				stds.get(i).get("student_no")+"'");
				sb.append(stds.get(i).get("student_no")+"因有下修低年級課程不予轉檔<br>");
			}
		}
		msg.addSuccess(sb.toString());		
	}
	
	/**
	 * 儲存操行成績至SchoolHist
	 */
	private void saveCondToScoreHist(String year, String term){
		
		List<Map>c=df.sqlGet("SELECT ClassNo FROM Class WHERE CampusNo='"+cno+"'AND SchoolType='"+tno+"'");
	
		for(int i=0; i<c.size(); i++){			
			df.exSql("INSERT INTO ScoreHist(student_no, school_year, school_" +
			"term, stdepart_class, evgr_type, cscode, opt, credit, score)SELECT j.student_no,"
			+ "'"+year+"','"+term+"', st.depart_class, '1', '99999', '1', '0', j.total_score FROM Just j, stmd st "
			+ "WHERE j.student_no=st.student_no AND st.depart_class='"+c.get(i).get("ClassNo")+"'");			
		}
		
	}
	
	/**
	 * 轉評語、體育、軍訓
	 * @param msg
	 * @return
	 */
	private void writComb1(String year, String term){
		df.exSql("INSERT INTO comb1(school_year,school_term,depart_class," +
		"student_no,com_name,physical_score,military_score)" +
		"SELECT '"+year+"', '"+term+"',st.depart_class, st.student_no, " +
		"CONCAT(IFNULL(j.com_code1,''), IFNULL(CONCAT(',',j.com_code2),''), IFNULL(CONCAT(',',j.com_code3),''))," +
		"(SELECT s.score FROM Seld s, Dtime d, Csno cs WHERE " +
		"s.student_no=st.student_no AND s.Dtime_oid=d.Oid AND cs.cscode=d.cscode AND d.Sterm='"+term+"' " +
		"AND cs.chi_name LIKE'%體育%' LIMIT 1)as physical_score," +
		"(SELECT score FROM Seld s, Dtime d, Csno cs WHERE " +
		"s.student_no=st.student_no AND s.Dtime_oid=d.Oid AND cs.cscode=d.cscode AND d.Sterm='"+term+"'" +
		"AND cs.chi_name LIKE'%軍訓%' LIMIT 1)as military_score " +
		"FROM Just j, Class c, stmd st WHERE j.student_no=st.student_no AND c.ClassNo=st.depart_class AND " +
		"c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"' AND c.graduate LIKE'"+grade+"%'");
	}
	
	/**
	 * 刪除範圍中evgr_type!=3,4,5,6的成績 (不包含暑修3,跨校4,待補5,抵免6)
	 * 刪除範圍中evgr_type IN('1', '2', '7')批次產生的成績
	 */
	private void delScoreHist(String year, String term){			
		df.exSql("DELETE FROM ScoreHist WHERE school_year='"+year+"' AND " +
		"school_term='"+term+"' AND evgr_type IN('1', '2', '7') AND " +
		"student_no IN(SELECT student_no FROM stmd s, Class c WHERE " +
		"s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"' AND " +
		"c.SchoolType='"+tno+"' AND c.graduate LIKE'"+grade+"%')");
	}
	
	/**
	 * 將批次轉入的成績再批次轉換隨班附讀標記
	 * @param year
	 * @param term
	 */
	private void chEvgrType(String year, String term){
		df.exSql("UPDATE ScoreHist SET evgr_type='2' WHERE evgr_type='1' AND " +
		"school_year='"+year+"' AND school_term='"+term+"' AND " +
		"stdepart_class!=(SELECT depart_class FROM stmd WHERE " +
		"stmd.student_no=ScoreHist.student_no)");
	}
	
	/**
	 * 轉全勤、操行cond
	 * @param year
	 * @param term
	 */
	private void writeCond(String year, String term){		
		df.exSql("INSERT INTO cond(student_no,school_year," +
		"school_term,depart_class,score,noabsent)SELECT s.student_no,'"+year+"', '"+term+"', s.depart_class, j.total_score," +
		"IF(j.dilg_score=3, 'Y', 'N') as noabsent FROM stmd s, Class c, Just j WHERE s.student_no=j.student_no AND " +
		"s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"' AND c.graduate LIKE'"+grade+"%'");
	}
	
	/**
	 * 獎懲
	 * @param year
	 * @param term
	 */
	private void writeComb2(String year, String term){
		df.exSql("INSERT INTO comb2(school_year,school_term," +
		"depart_class,student_no,ddate,no,reason,kind1,cnt1,kind2,cnt2)" +
		"SELECT '"+year+"','"+term+"',s.depart_class,d.student_no,d.ddate," +
		"d.no,d.reason,d.kind1,d.cnt1,d.kind2,d.cnt2 FROM " +
		"stmd s, Class c, desd d WHERE s.student_no=d.student_no AND " +
		"s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"' AND " +
		"c.SchoolType='"+tno+"' AND c.graduate LIKE'"+grade+"%'");
	}
	
	private void writeStavg(String year, String term){		
		df.exSql("INSERT INTO Stavg(depart_class,student_no,school_year,school_term,score,total_credit)"
		+ "SELECT st.depart_class,st.student_no,'"+year+"','"+term+"', ROUND(SUM(s.credit*s.score)/SUM(s.credit),2)as score,"
		+ "SUM(s.credit)as credit FROM stmd st, ScoreHist s, Class c WHERE s.evgr_type!='6'AND "
		+ "s.school_year='"+year+"'AND s.school_term='"+term+"'AND st.student_no=s.student_no AND "
		+ "c.ClassNo=st.depart_class AND c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"'"
		+ "AND c.graduate LIKE'"+grade+"%' GROUP BY s.student_no HAVING score IS NOT NULL");		
	}
	
	private void delData(String year, String term, String table){
		df.exSql("DELETE FROM "+table+" WHERE school_year='"+year+"' AND " +
		"school_term='"+term+"' AND student_no IN(" +
		"SELECT s.student_no FROM stmd s, Class c WHERE " +
		"s.depart_class=c.ClassNo AND " +
		"c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"' AND " +
		"c.graduate LIKE'"+grade+"%')");
	}
	
	private void writeSavedesd(String year, String term){
		df.exSql("INSERT INTO Savedesd(school_year,school_term," +
		"depart_class,student_no,ddate,no,reason," +
		"kind1,cnt1,kind2,cnt2)SELECT '"+year+"','"+term+"'," +
		"st.depart_class,st.student_no,d.ddate,d.no,d.reason,d.kind1," +
		"d.cnt1,d.kind2,d.cnt2 FROM desd d, stmd st, Class c WHERE " +
		"d.student_no=st.student_no AND c.ClassNo=st.depart_class AND " +
		"c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"'");
	}
}
