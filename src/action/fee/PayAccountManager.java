package action.fee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Message;
import model.Stmd;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import service.impl.DataFinder;
import action.BaseAction;

public class PayAccountManager extends BaseAction{
	
	public File upload;	
	public String occur_month, cno, tno, year, term, StudentNo, OfficeNo, AcctNo, Money, Kind, del[];
	SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");	
	Date birthday;
	Calendar c=Calendar.getInstance();
	public String execute(){
		
		return SUCCESS;
	}
	
	public String search(){
		List list=mainSearch("stmd");
		list.addAll(mainSearch("Gstmd"));
		
		request.setAttribute("dipost", list);
		return SUCCESS;
	}
	
	private List mainSearch(String target){
		List list;
		StringBuilder sql=new StringBuilder("SELECT d.Oid,s.student_no, s.student_name, "
		+ "c.ClassName, cd.name, d.OfficeNo, d.AcctNo,d.Money, d.LastModified, d.SchoolYear, d.SchoolTerm, d.occur_month FROM "
		+target+ " s, Dipost d, Class c, CODE_DIPOST cd WHERE cd.id=d.Kind AND "
		+ "s.student_no=d.StudentNo AND s.depart_class=c.ClassNo AND "
		+ "d.SchoolYear='"+year+"' AND c.CampusNo='"+cno+"'"
		+ "AND c.SchoolType='"+tno+"'AND d.Kind='"+Kind+"'");		
		if(StudentNo.indexOf(",")>0)sql.append("AND StudentNo ='"+StudentNo.substring(0, StudentNo.indexOf(","))+"'");
		//if(!occur_month.equals(""))sql.append("AND occur_month='"+occur_month+"'");
		if(Kind.equals("3"))sql.append("AND occur_month LIKE'"+occur_month+"%'");
		if(!term.equals(""))sql.append("AND d.SchoolTerm='"+term+"'");
		list=df.sqlGet(sql.toString());
		
		return list;
	}
	
	private List subSearch(String target){	
		SimpleDateFormat sf1=new SimpleDateFormat("yyyy-MM-dd");
		
		
		return df.sqlGet("SELECT d.Oid,s.student_no, s.student_name, c.ClassName, "
		+ "cd.name, d.OfficeNo, d.AcctNo,d.Money, d.LastModified, d.SchoolYear, d.SchoolTerm, "
		+ "d.occur_month FROM "+target+" s, Dipost d, Class c, CODE_DIPOST cd WHERE cd.id=d.Kind AND "
		+ "s.student_no=d.StudentNo AND s.depart_class=c.ClassNo AND d.LastModified LIKE'"+sf1.format(new Date())+"%' ORDER BY d.LastModified DESC");
	}
	
	public String del(){
		Message msg=new Message();
		if(del!=null){
			for(int i=0; i<del.length; i++){
				df.exSql("DELETE FROM Dipost WHERE Oid="+del[i]);				
			}
			msg.setSuccess("已刪除"+del.length+"筆資料");
		}else{
			msg.setSuccess("未勾選資料");
		}
		this.savMessage(msg);
		return search();
	}
	
	public String add(){
		Message msg=new Message();
		if(Money.equals("")|| AcctNo.equals("")|| OfficeNo.equals("")|| StudentNo.indexOf(",")<1){
			msg.setError("欄位不完整");
			this.savMessage(msg);
			return SUCCESS;
		}
		List list;
		try{
			if(Kind.equals("3")){
				df.exSql("INSERT INTO Dipost(StudentNo,OfficeNo,AcctNo,Money,Kind,Modifier,SchoolYear,SchoolTerm,occur_month)"
				+ "VALUES('"+StudentNo.substring(0, StudentNo.indexOf(","))+"', '"+OfficeNo+"', '"+AcctNo+"', '"
				+Money+"', '"+Kind+"', '"+getSession().getAttribute("userid")+"', '"+year+"', '"+term+"','"+occur_month+"');");
			}else{
				df.exSql("INSERT INTO Dipost(StudentNo,OfficeNo,AcctNo,Money,Kind,Modifier,SchoolYear,SchoolTerm)"
				+ "VALUES('"+StudentNo.substring(0, StudentNo.indexOf(","))+"', '"+OfficeNo+"', '"+AcctNo+"', '"
				+Money+"', '"+Kind+"', '"+getSession().getAttribute("userid")+"', '"+year+"', '"+term+"');");
			}			
			msg.setSuccess("新增完成");
			this.savMessage(msg);
			list=subSearch("stmd");
			list.addAll(subSearch("Gstmd"));
			request.setAttribute("dipost", list);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			msg.setError("請檢查是否為重複資料");
			this.savMessage(msg);	
			list=subSearch("stmd");
			list.addAll(subSearch("Gstmd"));
			request.setAttribute("dipost", list);
			return SUCCESS;
		}
	}
	
	
	
	public String upload() throws IOException{
		Message msg=new Message();
		if(upload==null){			
			msg.setError("未指定檔案");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		FileInputStream fis = new FileInputStream(upload);		
		XSSFWorkbook xwb = new XSSFWorkbook(fis);
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow row;	
		
		int check=0;
		boolean b=false;
		String y;
		String t;
		String k;
		String s;
		String o;
		String a;
		String m;
		String mm;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {				
			row = sheet.getRow(i);
			if(row.getCell(0)==null)break;	
				/*if(i==0)
				try{//檢查標題
					
					Integer.parseInt(row.getCell(0).toString());
				}catch(Exception e){
					msg.setError("發現標題欄位, 已跳過讀取<br>");
					continue;
				}*/
				
				/*for(int j=0; j<=6; j++){//檢查欄位null
					if(String.valueOf(row.getCell(j)).trim().equals("")||row.getCell(j)==null){	
						msg.addError("第"+i+"行發現空白");
						b=true;
					}
				}				
				if(b==true){
					msg.addError("<br>發現嚴重問題, 文件未匯入");
					this.savMessage(msg);
					return SUCCESS;
				}*/
				
				
				try{
					y=readCellAsString(row.getCell(0));
					t=readCellAsString(row.getCell(1));
					k=readCellAsString(row.getCell(2));
					s=readCellAsString(row.getCell(3));
					o=readCellAsString(row.getCell(4));
					a=readCellAsString(row.getCell(5));
					m=readCellAsString(row.getCell(6));	
					mm=readCellAsString(row.getCell(7));
					//試刪除原資料
					df.exSql("DELETE FROM Dipost WHERE Kind='"+k+"'AND StudentNo='"+s+"' AND SchoolYear='"+y+"' AND SchoolTerm='"+t+"'");
					//建立
					df.exSql("INSERT INTO Dipost(StudentNo,OfficeNo,AcctNo,Money,Kind,Modifier,SchoolYear,SchoolTerm,occur_month)"
					+ "VALUES('"+s+"', '"+o+"', '"+a+"', '"+m+"', '"+k+"', '"+getSession().getAttribute("userid")+"', '"+y+"', '"+t+"','"+mm+"');");
					check+=1;
				}catch(Exception e){
					msg.addError("第"+i+"行在儲存中發生錯誤");
					continue;
				}										
						
		}
		
		fis.close();
		//request.setAttribute("complete", complete);
		//request.setAttribute("fail", fail);
		msg.setSuccess("文件匯入完成, 共"+check+"筆資料");
		this.savMessage(msg);
		return SUCCESS;
	}
	
	
	private void saveTxtFile() throws IOException{
		
		FileInputStream fis = new FileInputStream(upload);
		FileReader fr = new FileReader(fis.getFD());
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine(); //讀第一行
		df.exSql("INSERT INTO LC_exam_stmds (student_no) VALUES ('"+line+"')ON DUPLICATE KEY UPDATE student_no=VALUES(student_no);");
		StringBuffer sb = new StringBuffer();
		while ((line=br.readLine())!=null){
			df.exSql("INSERT INTO LC_exam_stmds (student_no) VALUES ('"+line+"')ON DUPLICATE KEY UPDATE student_no=VALUES(student_no);");
		}	
		fis.close();
		fr.close();
		br.close();
		
		
		
		
		
		
		
		
		
	}
	
	/**
	 * excel欄位強制轉文字 2003-
	 * @param cell
	 * @return
	 */
	private String readCellAsString(HSSFCell cell) {
		if (cell == null) {
			return null;
		}
		final DecimalFormat df = new DecimalFormat("####################0.##########");

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return Boolean.valueOf(cell.getBooleanCellValue()).toString();
		case HSSFCell.CELL_TYPE_NUMERIC:
			return df.format(cell.getNumericCellValue());
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case HSSFCell.CELL_TYPE_ERROR:
			return Byte.toString(cell.getErrorCellValue());
		default:
			return "##POI## Unknown cell type";
		}
	}
	
	/**
	 * excel欄位強制轉文字 2003+
	 * @param cell
	 * @return
	 */
	private String readCellAsString(XSSFCell cell) {
		if (cell == null) {
			return null;
		}
		final DecimalFormat df = new DecimalFormat("####################0.##########");

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return Boolean.valueOf(cell.getBooleanCellValue()).toString().trim();
		case HSSFCell.CELL_TYPE_NUMERIC:
			return df.format(cell.getNumericCellValue()).trim();
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue().trim();
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula().trim();
		case HSSFCell.CELL_TYPE_ERROR:
			return Byte.toString(cell.getErrorCellValue());
		default:
			return "##POI## Unknown cell type";
		}
	}
	
	/**
	 * 班級是否存在
	 * @param ClassNo
	 * @return
	 */
	private boolean checkClass(String ClassNo){
		if(df.sqlGetInt("SELECT COUNT(*)FROM Class WHERE ClassNo='"+ClassNo+"'")<1){
			return false;
		}
		return true;
	}
	
	/**
	 * 取生日
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	private Date getBirthday(String str) throws ParseException{		
		if(str.length()==6){
			str="00"+str;
		}else{
			str="0"+str;
		}		
		
		birthday=sf.parse(str);
		c.setTime(birthday);
		c.add(Calendar.YEAR, 1911);
		return c.getTime();
	}
	
	/**
	 * 取性別
	 * @param idno
	 * @return
	 */
	private String getSex(String idno){	
		try{
			return idno.substring(1,2);
		}catch(Exception e){
			return "1";
		}
	}
	
	/**
	 * 訊息
	 * @param stdNo
	 * @param msg
	 * @return
	 */
	private Map setMsg(String stdNo, String msg){
		Map m=new HashMap();
		m.put("stdNo", stdNo);
		m.put("msg", msg);
		return m;
	}

}
