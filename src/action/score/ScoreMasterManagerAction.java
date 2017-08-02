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
		
		
		return search();
	}

}
