package action.score;

import java.util.List;
import java.util.Map;

import model.Message;
import action.BaseAction;

public class ScoreManagerAction extends BaseAction{
	
	int cnt;
	
	public String dOid;
	public String stNo;
	
	public String cno;
	public String sno;
	public String dno;
	public String gno;
	public String zno;
	
	public String Dtime_oid;
	public String student_no;
	
	public String sOid[];
	public String score[];
	public String score2[];
	public String score3[];
	
	public String school_year[];
	public String school_term[];
	public String stdepart_class[];
	public String evgr_type[];
	public String cscode[];
	public String opt[];
	public String credit[];
	public String graduate;
	
	public String execute(){
		
		
		return SUCCESS;
	}
	
	/**
	 * 以課程查詢
	 * @return
	 */
	public String searchDtime(){
		if(Dtime_oid.trim().length()<1){
			Message msg=new Message();
			msg.setError("未指定課程");
			this.savMessage(msg);
			return SUCCESS;
		}
		StringBuilder sql=new StringBuilder("SELECT d.Oid, o.name as opt, d.thour, d.credit, d.Select_Limit,cs.cscode,"
		+ "c.ClassName, e.CellPhone, cs.chi_name, IFNULL(e.cname,'')as cname, (SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt,"
		+ "(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid AND score2 IS NULL)as score2,(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid AND score IS NULL)as score FROM "
		+ "CODE_DTIME_OPT o,Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, Class c, Csno cs WHERE ");
		
		if(Dtime_oid.indexOf(",")>0){
			sql.append("d.Oid='"+Dtime_oid.substring(0, Dtime_oid.indexOf(","))+"'AND "
			+"cs.cscode=d.cscode AND o.id=d.opt AND d.depart_class=c.ClassNo");	
		}else{
			sql.append("cs.chi_name LIKE'%"+Dtime_oid+"%' AND "
			+"cs.cscode=d.cscode AND o.id=d.opt AND d.depart_class=c.ClassNo");	
		}
		request.setAttribute("css", df.sqlGet(sql.toString()));		
		return SUCCESS;
	}
	
	/**
	 * 查詢個人當期
	 * @return
	 */
	public String searchStudent(){
		if(student_no.indexOf(",")<0){
			Message msg=new Message();
			msg.setError("未指定學生");
			this.savMessage(msg);
			return SUCCESS;
		}
		stNo=student_no.substring(0, student_no.indexOf(","));	
		
		request.setAttribute("info", df.sqlGetMap("SELECT SUM(s.credit)as credit, SUM(IF(score <60, s.credit, 0))"
		+ "as dcredit, SUM(thour)as thour FROM Seld s, Dtime d WHERE s.Dtime_oid=d.Oid AND d.Sterm='"+getContext().getAttribute("school_term")+"' AND s.student_no='"+stNo+"'"));
		request.setAttribute("stselds", df.sqlGet("SELECT s.Oid, st.student_no, st.student_name, c.cscode, c.chi_name, s.score,s.score2, co.name as optName,d.thour,s.credit "
		+ "FROM CODE_DTIME_OPT co, Seld s, Dtime d, stmd st, Csno c WHERE st.student_no=s.student_no AND c.cscode=d.cscode AND d.Sterm='"+getContext().getAttribute("school_term")+"' AND "
		+ "co.id=d.opt AND s.Dtime_oid=d.Oid AND s.student_no='"+stNo+"' ORDER BY s.student_no"));
		return SUCCESS;
	}
	
	/**
	 * 以班級查詢
	 * @return
	 */
	public String searchClass(){
		
		if(sno.equals("")){
			Message msg=new Message();
			msg.setError("指定範圍過大");
			this.savMessage(msg);
			return SUCCESS;
			
		}
		
		StringBuilder sql=new StringBuilder("SELECT e.CellPhone, d.Oid, o.name as opt, d.thour, d.credit, d.Select_Limit,cs.cscode,"
		+ "c.ClassName, cs.chi_name, IFNULL(e.cname,'')as cname, (SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt,"
		+ "(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid AND score2 IS NULL)as score2,(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid AND score IS NULL)as score FROM "
		+ "CODE_DTIME_OPT o,Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, Class c, Csno cs WHERE "
		+ "cs.cscode=d.cscode AND o.id=d.opt AND d.depart_class=c.ClassNo AND d.cscode!='50000'AND d.Sterm='"+getContext().getAttribute("school_term")+"'");
		if(!cno.equals(""))sql.append("AND c.CampusNo='"+cno+"'");
		if(!sno.equals(""))sql.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sql.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sql.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sql.append("AND c.SeqNo='"+zno+"'");
		if(!graduate.equals(""))sql.append("AND c.graduate='"+graduate+"'");
		
		request.setAttribute("css", df.sqlGet(sql.toString()));
		return SUCCESS;
	}
	
	/**
	 * 清除選課
	 * @return
	 */
	public String clearSeld(){
		
		df.exSql("INSERT INTO SeldHist(StudentNo,depart_class,cscode,idno,type)"
		+ "SELECT s.student_no,d.depart_class, d.cscode,'"+getSession().getAttribute("userid")+"','D' FROM "
		+ "Seld s, Dtime d WHERE s.Dtime_oid=d.Oid AND d.Oid="+dOid);
		df.exSql("DELETE FROM Seld WHERE Dtime_oid="+dOid);
		Message msg=new Message();
		msg.setSuccess("已清除課程編號"+dOid);
		savMessage(msg);
		
		if(Dtime_oid.equals("")){
			return searchClass();
		}else{
			return searchDtime();
		}
		
	}
	
	/**
	 * 編輯單科成績
	 * @return
	 */
	public String editSeld(){
		request.setAttribute("selds", df.sqlGet("SELECT s.Oid, st.student_no, st.student_name, c.cscode, c.chi_name, "
		+ "s.score,s.score2, s.score3, co.name as optName,d.thour,s.credit FROM CODE_DTIME_OPT co, Seld s, Dtime d, stmd st, "
		+ "Csno c WHERE st.student_no=s.student_no AND c.cscode=d.cscode AND co.id=d.opt AND s.Dtime_oid=d.Oid AND "
		+ "d.Oid="+dOid+" ORDER BY s.student_no"));
		
		return SUCCESS;
	}
	
	/**
	 * 修改個人當期
	 * @return
	 */
	public String saveStSeld(){		
		Map Dtime;
		cnt=0;
		for(int i=0; i<sOid.length; i++){			
			if(!sOid[i].equals("")){
				if(score2[i].equals("")&&score[i].equals(""))continue;
				Dtime=df.sqlGetMap("SELECT depart_class,cscode FROM Dtime WHERE Oid="+df.sqlGetStr("SELECT Dtime_oid FROM Seld WHERE Oid="+sOid[i]));
				cnt++;
				StringBuilder sql=new StringBuilder("UPDATE Seld SET ");
				if(score2[i].equals(""))score2[i]=null;
				if(score[i].equals(""))score[i]=null;
				df.exSql("UPDATE Seld SET score2="+score2[i]+", score="+score[i]+" WHERE Oid="+sOid[i]);
			}
		}		
		Message msg=new Message();
		msg.setSuccess("已修改"+cnt+"筆成績");
		this.savMessage(msg);		
		return searchStudent();
	}
	
	/*
	 *刪除個人當期
	 
	public String delSeld(){		
		Message msg=new Message();
		msg.setSuccess("已刪除");
		this.savMessage(msg);		
		return searchStudent();
	}
	*/
	
	/**
	 * 儲存班級成績
	 * @return
	 */
	public String saveSeld(){
		Map Dtime=df.sqlGetMap("SELECT depart_class,cscode FROM Dtime WHERE Oid="+dOid);
		cnt=0;
		for(int i=0; i<sOid.length; i++){
			//if(score2[i].equals("")&&score[i].equals(""))continue;
			if(!sOid[i].equals("")){
				cnt++;
				if(score2[i].equals(""))score2[i]=null;
				if(score3[i].equals(""))score3[i]=null;
				if(score[i].equals(""))score[i]=null;				
				df.exSql("UPDATE Seld SET score3="+score3[i]+", score2="+score2[i]+", score="+score[i]+" WHERE Oid="+sOid[i]);				
				//df.exSql("INSERT INTO SeldHist(StudentNo,depart_class,cscode,idno,type)VALUES('"+df.sqlGetStr("SELECT student_no FROM Seld WHERE Oid="+sOid[i])+
				//"','"+Dtime.get("depart_class")+"','"+Dtime.get("cscode")+"','"+getSession().getAttribute("userid")+"','E');");
			}
		}		
		Message msg=new Message();
		msg.setSuccess("已修改"+cnt+"筆");
		this.savMessage(msg);
		return editSeld();
	}
	
	/**
	 * 查詢個人歷年
	 * @return
	 */
	public String searchScoreHist(){
		if(student_no.indexOf(",")<0){
			Message msg=new Message();
			msg.setError("未指定學生");
			this.savMessage(msg);
			return SUCCESS;
		}
		request.setAttribute("info", df.sqlGetMap("SELECT SUM(credit)as credit, SUM(IF(score <60, credit, 0))as dcredit FROM ScoreHist WHERE student_no='"+stNo+"'"));
		request.setAttribute("hist", true);
		stNo=new String(student_no.substring(0, student_no.indexOf(",")));
		request.setAttribute("scoreHist", df.sqlGet("SELECT cs.chi_name, s.*, c.ClassNo, c.ClassName FROM "
		+ "(ScoreHist s LEFT OUTER JOIN Class c ON c.ClassNo=s.stdepart_class)LEFT OUTER JOIN Csno cs ON s.cscode=cs.cscode WHERE "
		+ "s.student_no='"+stNo+"' ORDER BY s.school_year, s.school_term"));		
		request.setAttribute("ScoreHistEditLog", df.sqlGet("SELECT e.cname, s.* FROM empl e LEFT OUTER JOIN ScoreHistEditLog s ON e.idno=s.editor WHERE s.student_no='"+stNo+"'"));
		
		return SUCCESS;
	}
	
	/**
	 * 修改個人歷年
	 * @return
	 */
	public String saveScoreHist(){
		cnt=0;
		int err=0;
		String ClassNo;
		for(int i=0; i<sOid.length; i++){
			
			if(!sOid[i].equals("")){
				//無年度、課程、成績不予修改
				if(school_year[i].equals("")||school_term[i].equals("")||cscode[i].indexOf(",")<1){
					err+=1;
					continue;
				}
				
				//抵免班級為空
				if(stdepart_class[i].indexOf(",")<1){
					ClassNo=null;
				}else{
					ClassNo="'"+stdepart_class[i].substring(0, stdepart_class[i].indexOf(","))+"'";
				}
				
				if(score[i].equals("")){
					score[i]=null;
				}else{
					score[i]="'"+score[i]+"'";
				}
				
				
				cnt++;
				//備份
				df.exSql("INSERT INTO ScoreHistEditLog(student_no,school_year,school_term,stdepart_class,evgr_type,cscode,opt,credit,score,type,editor)"
				+ "SELECT student_no,school_year,school_term,stdepart_class,evgr_type,cscode,opt,credit,score,'E','"+getSession().getAttribute("userid")+"' FROM ScoreHist WHERE Oid="+sOid[i]);
				//修改				
				df.exSql("UPDATE ScoreHist SET school_year="+school_year[i]+",school_term="+school_term[i]+",stdepart_class="+ClassNo+",evgr_type='"+evgr_type[i]+"',cscode='"+
				cscode[i].substring(0, cscode[i].indexOf(","))+"',opt='"+opt[i]+"',credit="+credit[i]+",score="+score[i]+" WHERE Oid="+sOid[i]);
				
			}
			
			Message msg=new Message();
			msg.setSuccess("已修改 "+cnt+"筆成績");
			if(err>0)msg.addSuccess(", 失敗 "+err+"筆");
			savMessage(msg);
		}		
		return searchScoreHist();
	}
	
	/**
	 * 刪除個人歷年
	 * @return
	 */
	public String deleteScoreHist(){
		//備份
		df.exSql("INSERT INTO ScoreHistEditLog(student_no,school_year,school_term,stdepart_class,evgr_type,cscode,opt,credit,score,type,editor)"
		+ "SELECT student_no,school_year,school_term,stdepart_class,evgr_type,cscode,opt,credit,score,'D','"+getSession().getAttribute("userid")+"' FROM ScoreHist WHERE Oid="+dOid);
		//刪除
		df.exSql("DELETE FROM ScoreHist WHERE Oid="+dOid);
		Message msg=new Message();
		msg.setSuccess("已刪除");
		savMessage(msg);
		return searchScoreHist();
	}
	
	public String addScoreHist(){
		
		//無年度、課程、成績、學分不予修改
		if(school_year[0].equals("")||school_term[0].equals("")||cscode[0].indexOf(",")<1||credit[0].equals("")){
			Message msg=new Message();
			msg.setError("學年學期、課程編號為必要資料");
			savMessage(msg);
			return searchScoreHist();
		}
		
		String ClassNo;
		//抵免班級為空
		if(stdepart_class[0].indexOf(",")<1){
			ClassNo=null;
		}else{
			ClassNo="'"+stdepart_class[0].substring(0, stdepart_class[0].indexOf(","))+"'";
		}
		
		if(score[0].equals(""))score[0]=null;
		//建立log
		df.exSql("INSERT INTO ScoreHistEditLog(student_no,school_year,school_term,stdepart_class,evgr_type,cscode,opt,credit,score,type,editor)"
		+ "VALUES('"+stNo+"','"+school_year[0]+"','"+school_term[0]+"',"+ClassNo+",'"+evgr_type[0]+"','"+cscode[0]+"','"+opt[0]+"','"+credit[0]+"',"+score[0]+",'A','"+getSession().getAttribute("userid")+"');");
		
		//建立成績
		df.exSql("INSERT INTO ScoreHist(student_no,school_year,school_term,stdepart_class,evgr_type,cscode,opt,credit,score)VALUES"
		+ "('"+stNo+"','"+school_year[0]+"','"+school_term[0]+"',"+ClassNo+",'"+evgr_type[0]+"','"+cscode[0].substring(0, cscode[0].indexOf(","))+"','"+opt[0]+"','"+credit[0]+"',"+score[0]+")");
		
		Message msg=new Message();
		msg.setSuccess("已新增");
		savMessage(msg);
		return searchScoreHist();
	}

}
