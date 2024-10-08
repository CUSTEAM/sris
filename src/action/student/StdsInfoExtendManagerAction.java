package action.student;

import action.BaseAction;
import model.Message;

public class StdsInfoExtendManagerAction extends BaseAction{
	
	public String cno, sno, dno, gno, zno, nameno;
	public String[]within, xsh_id, xsh_type1, grd_id,grd_name, StuEnterQual, stdNo;
	
	public String execute() {
		
		
		return SUCCESS;
	}
	
	
	public String search() {
		Message msg=new Message();
		//System.out.println(cno+", "+sno+", "+dno+", "+gno+", "+zno+", "+nameno);
		if(sno.equals("")&&dno.equals("")) {
			msg.setError("請至少輸入一個學制或科系");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		StringBuilder sql=new StringBuilder("SELECT s.student_no as stdNo, si.*, s.student_name, c.ClassName FROM stmd s LEFT OUTER JOIN stmd_info si ON s.student_no=si.student_no, Class c WHERE c.ClassNo=s.depart_class AND c.CampusNo='"+cno+"'");
		if(!sno.equals(""))sql.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sql.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sql.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sql.append("AND c.SeqNo='"+zno+"'");
		System.out.println(sql);
		request.setAttribute("stds", df.sqlGet(sql.toString()));
		
		this.nameno="";
		return SUCCESS;
	}
	
	public String namenoSearch() {
		Message msg=new Message();
		//System.out.println(cno+", "+sno+", "+dno+", "+gno+", "+zno+", "+nameno);
		if(nameno.indexOf(",")==-1) {
			msg.setError("請至少輸入一個學生");
			this.savMessage(msg);
			return SUCCESS;
		}
		StringBuilder sql=new StringBuilder("SELECT s.student_no as stdNo, si.*, s.student_name, c.ClassName FROM stmd s LEFT OUTER JOIN stmd_info si ON s.student_no=si.student_no, Class c WHERE c.ClassNo=s.depart_class AND s.student_no='"+nameno.substring(0, nameno.indexOf(","))+"'");
		sno="";
		dno="";
		gno="";
		zno="";
		//System.out.println(sql);
		request.setAttribute("stds", df.sqlGet(sql.toString()));
		
		
		
		return SUCCESS;
	}
	
	public String save() {
		
		
		for(int i=0; i<stdNo.length; i++) {				
			
			df.exSql("INSERT INTO stmd_info(student_no,within, xsh_id, xsh_type1, grd_id, grd_name, editor, StuEnterQual) VALUES('"+stdNo[i]+"','"+within[i]+"', '"+xsh_id[i]+"', '"+xsh_type1[i]+"', '"+grd_id[i]+"', '"+grd_name[i]+"', '"+getContext().getAttribute("userid")+"', '"+StuEnterQual[i]+"') ON DUPLICATE KEY UPDATE within='"+within[i]+"', xsh_id='"+xsh_id[i]+"', xsh_type1='"+xsh_type1[i]+"', grd_id='"+grd_id[i]+"', grd_name='"+grd_name[i]+"', editor='"+getSession().getServletContext().getAttribute("userid")+"', StuEnterQual='"+StuEnterQual[i]+"'");
		}
		
		if(nameno.indexOf(",")>0) {
			return namenoSearch();
		}
		return search();
	}

}
