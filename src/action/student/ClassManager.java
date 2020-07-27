package action.student;

import java.text.SimpleDateFormat;
import java.util.Date;

import action.BaseAction;
import model.Classes;
import model.Message;

/**
 * 班級管理
 * @author shawn
 *
 */
public class ClassManager extends BaseAction{
	public String Oid;
	//public String nobody[], Type[], CampusNo[], InstNo[], graduate[], SchoolType[], SchoolNo[], DeptNo[], Grade[], SeqNo[];	
	public String nobody, Type, CampusNo, InstNo, graduate, SchoolType, SchoolNo, DeptNo, Grade, SeqNo;	
	public String nobody1, Type1, CampusNo1, InstNo1, graduate1, SchoolType1, SchoolNo1, DeptNo1, Grade1, SeqNo1;
	public String ClassNo, ClassName, ShortName;	
	
	public String execute(){
		
		
		return SUCCESS;
	}
	
	public String search(){		
		
		StringBuilder sb=new StringBuilder("SELECT e1.cname as editname, e.cname, e.CellPhone, cct.name as typeName, c.* FROM (Class c LEFT OUTER JOIN empl e ON c.tutor=e.idno)LEFT OUTER JOIN empl e1 ON e1.idno=c.editor, CODE_CLASS_TYPE cct WHERE c.Type=cct.id ");
		if(!Type.equals(""))sb.append("AND c.Type='"+Type+"'");
		if(!CampusNo.equals(""))sb.append("AND c.CampusNo='"+CampusNo+"'");
		if(!InstNo.equals(""))sb.append("AND c.InstNo='"+InstNo+"'");
		if(!graduate.equals(""))sb.append("AND c.graduate='"+graduate+"'");
		if(!SchoolType.equals(""))sb.append("AND c.SchoolType='"+SchoolType+"'");
		if(!SchoolNo.equals(""))sb.append("AND c.SchoolNo='"+SchoolNo+"'");
		if(!DeptNo.equals(""))sb.append("AND c.DeptNo='"+DeptNo+"'");
		if(!Grade.equals(""))sb.append("AND c.Grade='"+Grade+"'");
		if(!SeqNo.equals(""))sb.append("AND c.SeqNo='"+SeqNo+"'");
		
		if(nobody!=null){
			if(nobody.equals("1")){sb.append(" HAVING c.stds>0");}
			if(nobody.equals("0")){sb.append(" HAVING c.stds<1");}
		}
		
		sb.append(" ORDER BY c.CampusNo, c.DeptNo, c.SchoolNo");
		
		request.setAttribute("cls", df.sqlGet(sb.toString()));
		return SUCCESS;
	}
	
	
	
	public String save(){
		Message msg=new Message();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(Type1.equals("")||CampusNo1.equals("")||InstNo1.equals("")||graduate1.equals("")||
		SchoolType1.equals("")||SchoolNo1.equals("")||DeptNo1.equals("")||Grade1.equals("")||	
		SeqNo1.equals("")||ClassNo.equals("")||ClassName.equals("")||ShortName.equals("")){
			msg.setError("欄位不得空白");
			this.savMessage(msg);
			return edit();
		}
		
		try{			
			df.exSql("UPDATE Class SET editime='"+sf.format(new Date())+"', Type='"+Type1+"', CampusNo='"+CampusNo1+"', InstNo='"+InstNo1+"', graduate='"+
			graduate1+"', SchoolType='"+SchoolType1+"', SchoolNo='"+SchoolNo1+"', DeptNo='"+DeptNo1+"', Grade='"+Grade1+"', SeqNo='"+SeqNo1+"',"
			+ "ClassNo='"+ClassNo+"', ClassName='"+ClassName+"', ShortName='"+ShortName+"',editor='"+getSession().getAttribute("userid")+"'WHERE Oid="+Oid);
		}catch(Exception e){
			msg.setError("請檢查重複開班");
			this.savMessage(msg);
			return edit();
		}
		
		msg.setError("已儲存, 所有系統於15分鐘後生效");
		this.savMessage(msg);
		
		return edit();
	}
	
	public String edit(){
		request.setAttribute("cl", df.sqlGetMap("SELECT * FROM Class WHERE Oid="+Oid));
		return SUCCESS;
	}
	
	public String add(){
		Message msg=new Message();
		if(Type.equals("")||CampusNo.equals("")||InstNo.equals("")||graduate.equals("")||
		SchoolType.equals("")||SchoolNo.equals("")||DeptNo.equals("")||Grade.equals("")){
			msg.setError("欄位不得空白");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		try{			
			Classes c=new Classes();
			c.setType(Type);			
			c.setCampusNo(CampusNo);
			c.setClassName("名稱未設定");
			c.setClassNo(CampusNo+SchoolNo+DeptNo+Grade+SeqNo);
			c.setDept(CampusNo+SchoolNo+DeptNo);
			c.setDeptNo(DeptNo);
			c.setEditime(new Date());			
			c.setEditor(getSession().getAttribute("userid").toString());			
			c.setGrade(Integer.parseInt(Grade));
			c.setGraduate(graduate);
			c.setInstNo(InstNo);
			c.setSchNo(df.sqlGetStr("SELECT schNo FROM CODE_SCHOOL WHERE id='"+SchoolNo+"'"));
			c.setSchoolNo(SchoolNo);
			c.setSchoolType(SchoolType);
			c.setSeqNo(SeqNo);
			c.setShortName("未定");			
			df.update(c);
			Oid=String.valueOf(c.getOid());
			nobody="";
			return edit();
			
			
		}catch(Exception e){
			//e.printStackTrace();
			msg.setError("重複開班或欄位格式有誤");
			this.savMessage(msg);
			return SUCCESS;
		}
		
	}

}
