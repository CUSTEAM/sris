package action.score;

import java.util.List;
import java.util.Map;

import action.BaseAction;
import model.Message;

public class ScoreViewManagerAction extends BaseAction{
	
	public String cno;
	public String tno;
	public String dno;
	public String gno;
	public String only;
	
	
	public String[] DtimeOid, mid, mid_view, fin, fin_view;
	
	public String execute(){
		
		return SUCCESS;
	}
	
	public String search(){
		Message msg=new Message();
		if(cno.equals("")||gno.equals("")||tno.equals("")){			
			msg.setError("請選擇校區、部制、年級，縮小顯示結果以利手動作業");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		
		StringBuilder sql=new StringBuilder("SELECT s.*,d.Oid,cs.chi_name, o.name as opt, c.ClassName,"
		+ "(SELECT COUNT(*)FROM Seld s1, stmd st, Class c1 WHERE "
		+ "s1.student_no=st.student_no AND st.depart_class=c1.ClassNo AND "
		+ "c1.graduate='1'AND s1.Dtime_oid=d.Oid)as cnt FROM Dtime d LEFT "
		+ "OUTER JOIN ScoreOdDate s ON d.Oid=s.DtimeOid, Class c,Csno cs,"
		//+ "CODE_DTIME_OPT o WHERE d.stu_select>0 AND o.id=d.opt AND cs.cscode=d.cscode AND "
		+ "CODE_DTIME_OPT o WHERE o.id=d.opt AND cs.cscode=d.cscode AND "
		+ "c.ClassNo=d.depart_class ");
		if(!cno.equals(""))sql.append("AND c.CampusNo='"+cno+"'");
		if(!tno.equals(""))sql.append("AND c.SchoolType='"+tno+"'");
		//if(!sno.equals(""))sql.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sql.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sql.append("AND c.Grade='"+gno+"'");
		//if(!zno.equals(""))sql.append("AND c.SeqNo='"+zno+"'");
		if(only.equals("1")){
			sql.append("HAVING cnt>0");
		}
		request.setAttribute("cls",df.sqlGet(sql.toString()));
		
		
		return SUCCESS;
	}
	
	public String save(){
		
		for(int i=0; i<DtimeOid.length; i++){
			
			if(!mid[i].equals("")||!mid_view[i].equals("")||!fin[i].equals("")||!fin_view[i].equals("")){
				if(mid[i].equals(""))mid[i]=getContext().getAttribute("exam_mid").toString();
				if(mid_view[i].equals(""))mid_view[i]=getContext().getAttribute("exam_mid_view").toString();
				if(fin[i].equals(""))fin[i]=getContext().getAttribute("exam_fin").toString();
				if(fin_view[i].equals(""))fin_view[i]=getContext().getAttribute("exam_fin_view").toString();
				
				df.exSql("INSERT INTO ScoreOdDate(DtimeOid,exam_mid,exam_mid_view,exam_fin,exam_fin_view)VALUES"
				+ "('"+DtimeOid[i]+"','"+mid[i]+"','"+mid_view[i]+"','"+fin[i]+"','"+fin_view[i]+"')ON DUPLICATE KEY UPDATE DtimeOid="+DtimeOid[i]+",exam_mid='"+mid[i]+"',exam_mid_view='"+mid_view[i]+"',exam_fin='"+fin[i]+"',exam_fin_view='"+fin_view[i]+"';");
			}else{
				df.exSql("DELETE FROM ScoreOdDate WHERE DtimeOid="+DtimeOid[i]);
			}			
		}
		
		return search();
	}

}
