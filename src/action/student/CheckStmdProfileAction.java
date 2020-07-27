package action.student;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import service.impl.DataFinder;
import action.BaseAction;
import action.student.CheckStmdProfile.CheckEditPrint;

public class CheckStmdProfileAction extends BaseAction{
	
	DataFinder df = (DataFinder) get("DataFinder");
	
	public String cno;
	public String tno;
	public String sno;
	public String dno;
	public String gno;
	public String zno;
	
	public String execute(){
		
		
		return SUCCESS;
	}
	
	public String search() throws IOException{
		
		
		List<Map>cls=df.sqlGet("SELECT c.ClassNo, c.ClassName, SUM(s.edited)as edited, COUNT(*)as cnt, " +
		"e.cname,e.CellPhone, e.Email FROM stmd s,Class c LEFT OUTER JOIN empl e ON " +
		"e.idno=c.tutor WHERE c.Type='P' AND c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"' AND " +
		"c.SchoolNo LIKE'"+sno+"%' AND c.DeptNo LIKE'"+dno+"%' AND c.Grade LIKE'"+gno+"%' AND c.SeqNo LIKE'"+zno+"%' AND " +
		"s.depart_class=c.ClassNo GROUP BY c.ClassNo ORDER BY c.DeptNo, c.SchoolNo");
		
		//List stds=new ArrayList();
		for(int i=0; i<cls.size(); i++){
			
			cls.get(i).put("stds", df.sqlGet("SELECT s.stdimage, s.edited,s.student_no,s.student_name,c.name," +
					"IFNULL(s.schl_name,'')as schl_name,IFNULL(s.grad_dept,'')as grad_dept, IFNULL(s.telephone,'')as telephone," +
					"IFNULL(s.CellPhone,'')as CellPhone,IFNULL(s.parent_name,'')as parent_name,IFNULL(s.Email,'')as " +
					"Email,IFNULL(s.curr_addr,'')as curr_addr,IFNULL(s.perm_addr,'')as perm_addr " +
					"FROM stmd s LEFT OUTER JOIN CODE_STMD_IDENT c ON s.ident=c.id " +
					"WHERE s.depart_class='"+cls.get(i).get("ClassNo")+"' ORDER BY s.student_no"));		
		}		
		
		CheckEditPrint p=new CheckEditPrint();
		p.print(response, cls);
		
		return null;
	}
	
	public String detail() throws IOException{
		
		
		request.setAttribute("stds", df.sqlGet("SELECT s.stdimage, s.edited,s.student_no,s.student_name,c.name," +
					"IFNULL(s.schl_name,'')as schl_name,IFNULL(s.grad_dept,'')as grad_dept, telephone," +
					"IFNULL(s.CellPhone,'')as CellPhone,IFNULL(s.parent_name,'')as parent_name,IFNULL(s.Email,'')as " +
					"Email,IFNULL(s.curr_addr,'')as curr_addr,IFNULL(s.perm_addr,'')as perm_addr " +
					"FROM (stmd s LEFT OUTER JOIN CODE_STMD_IDENT c ON s.ident=c.id),Class cs " +
					"WHERE cs.CampusNo='"+cno+"' AND cs.SchoolType='"+tno+"' AND " +
					"cs.SchoolNo LIKE'"+sno+"%' AND cs.DeptNo LIKE'"+dno+"%' AND cs.Grade LIKE'"+gno+"%' AND " +
					"cs.SeqNo LIKE'"+zno+"%' AND cs.ClassNo=s.depart_class ORDER BY s.student_no"));
		
		
		return SUCCESS;
	}
	
	

}
