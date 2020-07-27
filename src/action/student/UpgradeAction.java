package action.student;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import model.Message;

import service.impl.DataFinder;

import action.BaseAction;

public class UpgradeAction extends BaseAction{
	
	public String cno;
	public String tno;
	DataFinder df = (DataFinder) get("DataFinder");
	
	public String execute(){		
		request.setAttribute("logs", df.sqlGet("SELECT * FROM SYS_STDUPGRADE_LOG ORDER BY up_time DESC"));		
		return SUCCESS;
	}
	
	public String confirm(){
		
		Message msg=new Message();
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		//System.out.println(c.get(Calendar.MONTH));
		if(c.get(Calendar.MONTH)!=7){
			msg.setError("非升級期間");
			savMessage(msg);
			return execute();
		}
		
		ServletContext servletContext = request.getServletContext();		
		String term=(String)servletContext.getAttribute("school_term");
		String year=(String)servletContext.getAttribute("school_year");	
		
		try{
			df.exSql("INSERT INTO SYS_STDUPGRADE_LOG(school_year,school_term," +
			"CampusNo,SchoolType,cname)VALUE('"+year+"','"+term+"','"+cno+"','"+tno+"','"+
			df.sqlGetStr("SELECT cname FROM empl WHERE idno='"+getSession().getAttribute("userid")+"'")+"');");
		}catch(Exception e){			
			msg.setError("重複升級");
			savMessage(msg);
			return execute();
		}
		/*
		List<Map<String, String>>cls=df.sqlGet("SELECT * FROM Class WHERE " +
		"CampusNo='"+cno+"' AND SchoolType='"+tno+"' AND Type='P' ORDER BY SchoolNo, Grade DESC");
		*/
		List<Map<String, String>>cls=df.sqlGet("SELECT c.*,cs.years FROM Class c, CODE_SCHOOL cs WHERE c.SchoolNo=cs.id AND "+
		"c.CampusNo='"+cno+"' AND c.SchoolType='"+tno+"' AND c.Type='P' ORDER BY c.SchoolNo, c.Grade DESC");
		String ClassNo="";		
		for(int i=0; i<cls.size(); i++){
			try{
				if(!cls.get(i).get("Grade").equals(String.valueOf(cls.get(i).get("years")))) {
					ClassNo=df.sqlGetStr("SELECT ClassNo FROM Class WHERE CampusNo='"+cls.get(i).get("CampusNo")+"' " +
					"AND SchoolNo='"+cls.get(i).get("SchoolNo")+"' AND DeptNo='"+cls.get(i).get("DeptNo")+"' AND " +
					"Grade='"+(Integer.parseInt(cls.get(i).get("Grade"))+1)+"' AND SeqNo='"+cls.get(i).get("SeqNo")+"'");
				}else{
					ClassNo=cls.get(i).get("CampusNo")+cls.get(i).get("SchoolNo")+cls.get(i).get("DeptNo")+(Integer.parseInt(cls.get(i).get("Grade"))+1)+"1";
				}
			}catch(Exception e){
				continue;
			}
			df.exSql("UPDATE stmd SET depart_class='"+ClassNo+"' WHERE depart_class='"+cls.get(i).get("ClassNo")+"'");
		}
		msg.setSuccess("已完成"+cls.size()+"個班級升級作業");
		savMessage(msg);		
		return execute();
	}

}
