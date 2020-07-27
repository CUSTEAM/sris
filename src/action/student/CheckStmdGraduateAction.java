package action.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.BaseAction;
import model.Message;
import model.gradresu;

public class CheckStmdGraduateAction extends BaseAction{
	public String cno, sno, dno, gno, zno, editStmd, student_no;
	public String[] updates, students_no, language, certificate, practice;
	
	public String edit() {		
		List<Map>stmd=search(editStmd);
		Map gradreq=df.sqlGetMap("SELECT*FROM gradreq WHERE enter_year='"+editStmd.substring(0, 3)+"'AND dept='"+df.sqlGetStr("SELECT c.Dept FROM Class c, stmd s WHERE c.ClassNo=s.depart_class AND s.student_no='"+editStmd+"'")+"'");
		String[] chi=gradreq.get("chi_name_1").toString().split(";");
		String[] name;
		List<Map>score1=new ArrayList();
		Map m;
		for(String s:chi) {
			m=new HashMap();
			try {
				name=s.split(",");
				m.put("year", name[0]);
				m.put("term", name[1]);
				m.put("credit", name[2]);
				m.put("chi_name", name[3]);
				score1.add(m);
			}catch(Exception e) {
				
			}
			
		}
		
		
		
		
		
		request.setAttribute("stmd", stmd);
		request.setAttribute("score1", score1);	
		request.setAttribute("score3", df.sqlGet("SELECT s.*, c.chi_name FROM ScoreHist s, Csno c WHERE s.cscode=c.cscode AND s.opt='2' AND s.student_no='"+editStmd+"'"));
		
		
		
		Map std=df.sqlGetMap("SELECT * FROM stmd WHERE student_no='"+editStmd+"'");		
		request.setAttribute("std", df.sqlGetMap("SELECT * FROM stmd WHERE student_no='"+editStmd+"'"));
		request.setAttribute("hist", df.sqlGet("SELECT * FROM gradresuHist WHERE student_no='"+editStmd+"'"));
		return "edit";
	}
	
	private List<Map>search(String stdNo){
		StringBuilder sql=new StringBuilder("SELECT s.student_no as stdNo, s.student_name, c.ClassName, c.ClassNo, g.*, (g.hist_credit1+hist_credit2+hist_credit3)as cnt FROM stmd s LEFT OUTER JOIN gradresu g ON g.student_no=s.student_no, Class c WHERE  s.depart_class=c.ClassNo ");
		if(stdNo==null) {
			if(!cno.equals(""))sql.append("AND c.CampusNo='"+cno+"'");
			//if(!tno.equals(""))sql.append("AND c.SchoolType='"+tno+"'");
			if(!sno.equals(""))sql.append("AND c.SchoolNo='"+sno+"'");
			if(!dno.equals(""))sql.append("AND c.DeptNo='"+dno+"'");
			if(!gno.equals(""))sql.append("AND c.Grade='"+gno+"'");
			if(!zno.equals(""))sql.append("AND c.SeqNo='"+zno+"'");		
		}else {
			sql.append("AND s.student_no='"+stdNo+"'");	
		}
		
		//審核狀況
		return  df.sqlGet(sql.toString());
	}
	
	public String execute() {
		
		return SUCCESS;
	}
	
	public String multSearch() {
		/*if(cno.equals("")|| sno.equals("") || dno.equals("")) {
			Message msg=new Message();
			msg.setError("請至少以系為審查範圍");
			this.savMessage(msg);
			return SUCCESS;
		}*/
		
		//審核狀況
		getSession().setAttribute("gradresu", search(null));		
		return SUCCESS;
	}
	
	public String justSearch() {
		if(student_no.indexOf(",")>0) {
			editStmd=student_no.substring(0, student_no.indexOf(","));
			return edit();
		}else {
			Message msg=new Message();
			msg.setError("請選擇學生");
			this.savMessage(msg);
			return SUCCESS;
		}
		
	}
	
	private void saveGradresu() {
		
		gradresu g;
		String empl=df.sqlGetStr("SELECT cname FROM empl WHERE idno='"+getSession().getAttribute("userid")+"'");
		for(int i=0; i<students_no.length; i++) {
			if(updates[i].equals(""))continue;			
			try {
				g=(gradresu)df.hqlGetListBy("FROM gradresu WHERE student_no='"+students_no[i]+"'").get(0);
			}catch(Exception e){
				g=new gradresu();
				g.setStudent_no(students_no[i]);
			}			
			g.setLanguage(language[i]);
			g.setPractice(practice[i]);
			g.setCertificate(certificate[i]);
			df.update(g);
			df.exSql("INSERT INTO gradresuHist(student_no,note)VALUES('"+students_no[i]+"','"+empl+"編輯畢業資格');");
						
		}
		
	}
	
	public String batchSave() {	
		saveGradresu();
		return multSearch();
	}
	
	
	public String justSave() {
		saveGradresu();
		return edit();
	}
	
	public String leave() {
		
		
		return SUCCESS;
	}
	
	public String save() {
		
		return edit();
	}
	
	public String next() {
		
		
		return edit();
	}
	
	public String previous() {
		
		
		return edit();
	}
	
}
