package action.score;

import action.BaseAction;
import model.Message;

public class ScoreMasterManagerAction extends BaseAction{
	
	public String 
	Oid,
	school_year,
	school_term,
	student_no,
	theses_chiname,
	theses_engname,
	theses_score,
	evgr1_score,
	graduate_score,
	remark,
	onlineFileDate;
	
	public String execute(){
		
		
		return SUCCESS;
	}
	
	public String search(){
		Message msg=new Message();
		if(student_no.indexOf(",")<0){
			msg.setError("請指定1位學生");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		request.setAttribute("allScore", df.sqlGetMap("SELECT SUM((s.score)*(s.credit))as cnt, SUM(s.credit)as credit,"
				+ "SUM((s.score)*(s.credit))/SUM(s.credit) as score FROM ScoreHist s, Class c "
				+ "WHERE s.stdepart_class=c.ClassNo AND c.Schno='M' AND s.student_no='"+student_no.substring(0, student_no.indexOf(","))+"'"));
		System.out.println("SELECT SUM((s.score)*(s.credit))as cnt, SUM(s.credit)as credit, SUM((s.score)*(s.credit))/SUM(s.credit) as score FROM ScoreHist s WHERE s.student_no='"+student_no.substring(0, student_no.indexOf(","))+"'");
		
		
		
		request.setAttribute("allInfo", df.sqlGet("SELECT cl.SchNo, cl.ClassName, s.stdepart_class, s.school_year, s.school_term, c.chi_name, s.credit, s.score FROM ScoreHist s LEFT OUTER JOIN Class cl ON cl.ClassNo=s.stdepart_class, Csno c WHERE s.cscode=c.cscode AND s.student_no='"+student_no.substring(0, student_no.indexOf(","))+"' ORDER BY s.school_year DESC, s.school_term"));
		request.setAttribute("score", df.sqlGetMap("SELECT * FROM MasterData WHERE student_no='"+student_no.substring(0, student_no.indexOf(","))+"'"));
		return SUCCESS;
	}
	
	public String update(){
		Message msg=new Message();
		if(student_no.indexOf(",")<0){
			msg.setError("請指定1位學生");
			this.savMessage(msg);
			return SUCCESS;
	}		
		
	if(school_year.equals("")||
	
	theses_chiname.equals("")||
	theses_engname.equals("")||
	theses_score.equals("")||
	evgr1_score.equals("")||
	graduate_score.equals("")||
	onlineFileDate.equals("")){
		msg.setError("輸入不完整");
		this.savMessage(msg);
		return SUCCESS;
	}		
		
		try{
			if(Oid.equals("")){
				
				df.exSql("INSERT INTO MasterData "
						+ "(school_year,school_term,student_no,theses_chiname,theses_engname,theses_score,evgr1_score,graduate_score,remark,onlineFileDate)VALUES"
						+ "('"+school_year+"','"+school_term+"','"+student_no.substring(0, student_no.indexOf(","))+"','"+theses_chiname+"','"+theses_engname+"','"+theses_score+"','"+evgr1_score+"','"+graduate_score+"','"+remark+"','"+onlineFileDate+"')");
				msg.setError("已建立論文");
				this.savMessage(msg);
			}else{
					df.exSql("UPDATE MasterData SET school_year='"+school_year+"',school_term='"+school_term+"',theses_chiname='"+theses_chiname+"',theses_engname='"+theses_engname+"',theses_score='"+theses_score+"',evgr1_score='"+evgr1_score+"',graduate_score='"+graduate_score+"',remark='"+remark+"',onlineFileDate='"+onlineFileDate+"'WHERE Oid="+Oid);
					msg.setError("已修改論文");
					this.savMessage(msg);
			}
		}catch(Exception e){
			msg.setError("儲存中發現錯誤");
			this.savMessage(msg);
		}		
		return search();
	}
}