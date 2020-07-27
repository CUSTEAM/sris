package action.enroll;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import action.BaseAction;
import model.Enroll;
import model.EnrollAttach;
import model.EnrollDept;
import model.EnrollRegist;
import model.EnrollRegistDept;
import model.EnrollStmd;
import model.Message;
import model.Stmd;

public class EnrollManager extends BaseAction{
	
	public String enrollOid;
	private File brochure, envelope;
	private String brochureFileName, envelopeFileName;
	
	public String CampusNo[], SchoolNo[], DeptNo[], dept_name[], quota[];
	
	public String attach_name[], online[], att_note[];
	
	public String school_year, sign_begin, sign_end, open_score,
	open_match,	enroll_name,reg_fee,subsel,note;
	public String deptOid[], rank[];
	
	public String registOid[], score[], score1[], score2[], score3[], no[];
	public String idno[], student_no[], depart_class[];
	
	public File getBrochure() {
		return brochure;
	}

	public void setBrochure(File brochure) {
		this.brochure = brochure;
	}

	public File getEnvelope() {
		return envelope;
	}

	public void setEnvelope(File envelope) {
		this.envelope = envelope;
	}

	public String execute(){
		
		request.setAttribute("enrolls", df.sqlGet("SELECT e.*,"
		+ "(SELECT COUNT(*)FROM Enroll_regist WHERE no IS NOT NULL AND Enroll_oid=e.Oid)as con,"
		+ "(SELECT COUNT(*)FROM Enroll_regist WHERE Enroll_oid=e.Oid)as cnt FROM Enroll e"));
		
		
		return SUCCESS;
	}
	
	public String managEnroll(){
		
		
		//Map enroll=df.sqlGetMap("SELECT * FROM Enroll WHERE Oid="+enrollOid);
		//List depts=df.sqlGet("SELECT * FROM Enroll_dept WHERE Enroll_oid="+enrollOid);
		
		request.setAttribute("enrol", df.sqlGetMap("SELECT * FROM Enroll WHERE Oid="+enrollOid));
		
		request.setAttribute("depts", df.sqlGet("SELECT * FROM Enroll_dept WHERE Enroll_oid="+enrollOid));
		request.setAttribute("attach", df.sqlGet("SELECT * FROM Enroll_attach WHERE Enroll_oid="+enrollOid));
		return SUCCESS;
	}
	
	/**
	 * 建立集活動
	 * @return
	 */
	public String create(){
		
		Enroll e=new Enroll();
		e.setEnrollName("未命名的招生考試");
		df.update(e);
		enrollOid=e.getOid().toString();
		return managEnroll();
	}
	
	/**
	 * 對活動中的考生列表
	 * @return
	 */
	public String managStmd(){
		List<Map>stmds=df.sqlGet("SELECT es.student_no,c.ClassName,c.ClassNo, e.enroll_name, es.Oid as esOid, "
		+ "er.Oid as erOid, es.student_name,er.* FROM Enroll_stmd es LEFT "
		+ "OUTER JOIN Class c ON es.depart_class=c.ClassNo, Enroll_regist er, "
		+ "Enroll e WHERE e.Oid=er.Enroll_oid AND es.idno=er.idno AND "
		+ "er.Enroll_oid="+enrollOid);
		Map tmp;
		for(int i=0; i<stmds.size(); i++){
			/*在校生的問題
			 * tmp=df.sqlGetMap("SELECT s.student_no, c.ClassNo, c.ClassName FROM stmd s, Class c WHERE s.depart_class=c.ClassNo AND s.idno='"+stmds.get(i).get("idno")+"'");
			if(tmp!=null){
				stmds.get(i).putAll(tmp);
			}*/
			stmds.get(i).put("depts", df.sqlGet("SELECT d.quota, d.dept_name, erd.Oid, erd.choice, erd.rank FROM Enroll_regist_dept erd, Enroll_dept d WHERE erd.Enroll_dept_oid=d.Oid AND erd.idno='"+stmds.get(i).get("idno")+"'ORDER BY erd.choice"));
			stmds.get(i).put("files", df.sqlGet("SELECT ea.attach_name, era.path FROM Enroll_attach ea, Enroll_regist er, Enroll_regist_attach era WHERE ea.Oid=era.Enroll_attach_oid AND er.Oid=era.Enroll_regist_oid AND er.Oid="+stmds.get(i).get("Oid")+" ORDER BY era.Oid"));
			
		}		
		request.setAttribute("stmds", stmds);		
		return SUCCESS;
	}
	
	/**
	 * 刪除活動
	 * @return
	 */
	public String del(){
		EnrollDept d;
		Enroll e=(Enroll) df.hqlGetListBy("FROM Enroll WHERE Oid="+enrollOid).get(0);
		Message msg=new Message();
		if(e.getSignBegin().getTime()<new Date().getTime()){
			msg.setError("開始報名後不可修改考試資料");
			this.savMessage(msg);
			return managEnroll();
		}
		df.exSql("DELETE FROM Enroll WHERE Oid="+enrollOid);		
		msg.setError("已刪除");
		this.savMessage(msg);
		return execute();
	}
	
	/**
	 * 儲存活動
	 * @return
	 */
	public String saveEnroll() {
		
		EnrollDept d;
		Enroll e=(Enroll) df.hqlGetListBy("FROM Enroll WHERE Oid="+enrollOid).get(0);
		Message msg=new Message();
		if(e.getSignBegin().getTime()<new Date().getTime()){
			msg.setError("開始報名後不可修改考試資料");
			this.savMessage(msg);
			return managEnroll();
		}
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		try{
			e.setEnrollName(enroll_name);
			e.setNote(note);
			e.setOpenMatch(new Timestamp(sf.parse(open_match).getTime()));
			e.setOpenScore(new Timestamp(sf.parse(open_score).getTime()));
			e.setRegFee(Short.parseShort(reg_fee));
			e.setSchoolYear(Short.parseShort(school_year));
			e.setSignBegin(new Timestamp(sf.parse(sign_begin).getTime()));
			e.setSignEnd(new Timestamp(sf.parse(sign_end).getTime()));
			e.setSubsel(Byte.parseByte((subsel)));
			df.update(e);
			
			if(brochure!=null)
			saveFile("brochure", brochure, brochureFileName);//儲存附件
			if(envelope!=null)
			saveFile("envelope", envelope, envelopeFileName);//儲存附件
			
			msg.setError("儲存完成");
			this.savMessage(msg);
			return managEnroll();
		}catch(Exception ex){
			msg.setError("儲存發生錯誤, 請檢查欄位");
			this.savMessage(msg);
			return managEnroll();
		}
		
		
		
		
		
		
		//return managEnroll();
	}
	
	/**
	 * 儲存參與活動的系所
	 * @return
	 */
	public String saveDept(){
		
		Enroll e=(Enroll) df.hqlGetListBy("FROM Enroll WHERE Oid="+enrollOid).get(0);
		Message msg=new Message();
		if(e.getSignBegin().getTime()<new Date().getTime()){
			msg.setError("開始報名後不可修改考試資料");
			this.savMessage(msg);
			return managEnroll();
		}
		df.exSql("DELETE FROM Enroll_dept WHERE Enroll_oid="+enrollOid);
		EnrollDept d;
		for(int i=0; i<dept_name.length; i++){
			
			if(!CampusNo[i].equals("")&&!SchoolNo[i].equals("")&&!DeptNo[i].equals("")&&!dept_name[i].equals("")&&!quota[i].equals("")){
				d=new EnrollDept();
				d.setCampusNo(CampusNo[i]);
				d.setSchoolNo(SchoolNo[i]);
				d.setDeptNo(DeptNo[i]);
				d.setDeptName(dept_name[i]);
				d.setQuota(Short.parseShort(quota[i]));
				d.setEnrollOid(Integer.parseInt(enrollOid));
				df.update(d);
			}
				
			
			
		}
		
		msg.setError("儲存完成");
		this.savMessage(msg);
		return managEnroll();
	}
	
	/**
	 * 儲存活動的附件
	 * @return
	 */
	public String saveAttach(){
		
		
		EnrollDept d;
		Enroll e=(Enroll) df.hqlGetListBy("FROM Enroll WHERE Oid="+enrollOid).get(0);
		Message msg=new Message();
		if(e.getSignBegin().getTime()<new Date().getTime()){
			msg.setError("開始報名後不可修改考試資料");
			this.savMessage(msg);
			return managEnroll();
		}
		
		EnrollAttach a;
		df.exSql("DELETE FROM Enroll_attach WHERE Enroll_oid="+enrollOid);
		for(int i=0; i<attach_name.length; i++){
			
			if(!attach_name[i].equals("")&&!online[i].equals("")){
				a=new EnrollAttach();
				a.setAttachName(attach_name[i]);
				a.setEnrollOid(Integer.parseInt(enrollOid));
				a.setNote(att_note[i]);
				a.setOnline(online[i]);
				df.update(a);
			}
		}
		
		
		
		msg.setError("儲存完成");
		this.savMessage(msg);
		return managEnroll();
	}
	
	public String getBrochureFileName() {
		return brochureFileName;
	}

	public void setBrochureFileName(String brochureFileName) {
		this.brochureFileName = brochureFileName;
	}

	public String getEnvelopeFileName() {
		return envelopeFileName;
	}

	public void setEnvelopeFileName(String envelopeFileName) {
		this.envelopeFileName = envelopeFileName;
	}

	/**
	 * 儲存成績
	 * @return
	 */
	public String saveScore(){
		EnrollRegist er;
		EnrollRegistDept erd;
		EnrollStmd es;
		Message msg=new Message();
		msg.setMsg("");
		
		//准考證和成績
		for(int i=0; i<registOid.length; i++){			
			if(!registOid[i].equals("")){
				try{
					er=(EnrollRegist)df.hqlGetListBy("FROM EnrollRegist WHERE Oid="+registOid[i]).get(0);
					if(!no[i].equals(""))er.setNo(no[i]);
					if(!score[i].equals(""))er.setScore(Short.parseShort(score[i]));
					if(!score1[i].equals(""))er.setScore1(Short.parseShort(score1[i]));
					if(!score2[i].equals(""))er.setScore2(Short.parseShort(score2[i]));
					if(!score3[i].equals(""))er.setScore3(Short.parseShort(score3[i]));
					df.update(er);
				}catch(Exception e){
					msg.addMsg("但<br>"+e);
				}
			}
		}
		
		//名次
		for(int i=0; i<deptOid.length; i++){
			if(!deptOid[i].equals("")){
				erd=(EnrollRegistDept)df.hqlGetListBy("FROM EnrollRegistDept WHERE Oid="+deptOid[i]).get(0);
				if(!rank[i].equals("")){					
					erd.setRank(Short.parseShort(rank[i]));
				}else{					
					erd.setRank(null);
				}
				df.update(erd);
			}			
		}	
		
		//分班
		List<Stmd>stmds;
		Stmd s;
		for(int i=0; i<idno.length; i++){
			
			if(!student_no[i].equals("")&& depart_class[i].indexOf(",")>0){
				try{
					es=(EnrollStmd) df.hqlGetListBy("FROM EnrollStmd WHERE idno='"+idno[i]+"'").get(0);
					es.setDepartClass(depart_class[i].substring(0, depart_class[i].indexOf(",")));
					es.setStudentNo(student_no[i]);
					df.update(es);
					
					stmds=df.hqlGetListBy("FROM Stmd WHERE studentNo='"+es.getStudentNo()+"'");
					if(stmds.size()<1){
						s=new Stmd();
					}else{
						s=stmds.get(0);
					}					
					s.setDepartClass(es.getDepartClass());
					s.setStudentNo(es.getStudentNo());
					s.setStudentName(es.getStudentName());
					s.setBirthday(es.getBirthday());
					s.setIdno(es.getIdno());
					s.setSex(es.getSex());
					s.setEntrance(Short.parseShort(getContext().getAttribute("school_year")+"09"));
					s.setSchlName(es.getSchlName());
					s.setGradDept(es.getGradDept());
					s.setParentName(es.getParentName());
					if(es.getParentPhone()!=null)
					s.setIdentRemark("家長連絡電話"+es.getParentPhone());
					s.setPermPost(es.getPermPost());
					s.setPermAddr(es.getPermAddr());
					s.setCurrAddr(es.getCurrAddr());
					s.setCurrPost(es.getCurrPost());
					s.setEmail(es.getEmail());
					s.setTelephone(es.getTelephone());
					s.setCellPhone(es.getCellPhone());
					df.update(s);
				}catch(Exception e){
					e.printStackTrace();
					msg.addMsg("<br>學號"+student_no[i]+"學生基本資料請手動建立或更新");
				}
				
			}
		}
		
		msg.addMsg("<br>已儲存考生資料");
		this.savMessage(msg);
		
		return managStmd();
	}	
	
	/**
	 * 上傳文件
	 * @param type
	 * @param fileUpload
	 * @param fileUploadFileName
	 */
	private void saveFile(String type, File fileUpload, String fileUploadFileName){
		
		if(fileUpload!=null){
			long now=new Date().getTime();
			String fileName;		
			String filePath;
			String tmp_path=getContext().getRealPath("/tmp");//本機目錄
			String target="host_runtime";
			File dst;
			Map<String, String>ftpinfo;
			File uploadedFile;
			//for (int i = 0; i < fileUpload.length; i++) {			
            uploadedFile = fileUpload;            
            fileName=now+bio.getExtention(fileUploadFileName);//置換檔名            
            filePath=getContext().getRealPath("/tmp" )+"/"+fileName;            
            if(getContext().getAttribute("isServer").equals("0")){//測試的情況
    			target="host_debug";
    			filePath=filePath.replace("\\", "/");
    			tmp_path=tmp_path.replace("\\", "/");
    		}
            dst=new File(tmp_path);//暫存資料夾	TBTB		
			if(!dst.exists())dst.mkdir();
			bio.copyFile(fileUpload, new File(filePath));
			ftpinfo=df.sqlGetMap("SELECT "+target+" as host, username, password, path FROM SYS_HOST WHERE useid='enroll'");
			
			//存放於TaskOid資料夾內
			bio.putFTPFile(ftpinfo.get("host"), ftpinfo.get("username"), ftpinfo.get("password"), tmp_path+"/", "enroll/", fileName);

	        //}
			df.exSql("UPDATE Enroll SET "+type+"='"+fileName+"'WHERE Oid="+enrollOid);
			
		}		
	}

}
