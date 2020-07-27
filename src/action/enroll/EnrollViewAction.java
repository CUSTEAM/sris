package action.enroll;

import java.util.List;
import java.util.Map;

import action.BaseAction;

public class EnrollViewAction extends BaseAction{
	
	public String Oid;
	
	public String execute(){
		
		List<Map>e=df.sqlGet("SELECT e.*,"
				+ "(SELECT COUNT(*)FROM Enroll_regist WHERE no IS NOT NULL AND Enroll_oid=e.Oid)as con,"
				+ "(SELECT COUNT(*)FROM Enroll_regist WHERE Enroll_oid=e.Oid)as cnt FROM Enroll e");
		for(int i=0; i<e.size(); i++){
			e.get(i).put("one", df.sqlGet("SELECT ed.dept_name, COUNT(*)as cnt FROM Enroll_regist_dept erd, "
			+ "Enroll_dept ed, Enroll e WHERE e.Oid=ed.Enroll_oid AND ed.Oid=erd.Enroll_dept_oid "
			+ "AND erd.choice=1 AND e.Oid="+e.get(i).get("Oid")+" GROUP BY ed.dept_name ORDER BY ed.Oid"));
			
			e.get(i).put("sec", df.sqlGet("SELECT ed.dept_name, COUNT(*)as cnt FROM Enroll_regist_dept erd, "
			+ "Enroll_dept ed, Enroll e WHERE e.Oid=ed.Enroll_oid AND ed.Oid=erd.Enroll_dept_oid "
			+ "AND erd.choice!=1 AND e.Oid="+e.get(i).get("Oid")+" GROUP BY ed.dept_name ORDER BY ed.Oid"));
			
		}
		request.setAttribute("enrolls", e);
				
				
		
		return SUCCESS;
	}
	
	public String acc(){
		
		
		return execute();
	}
	
	public String den(){
		
		
		return execute();
	}
}
