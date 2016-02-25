package action.student;

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

public class DumpNewStmdAction extends BaseAction{
	
	public File upload;
	
	//DataFinder df = (DataFinder) get("DataFinder");
	SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
	
	Date birthday;
	Calendar c=Calendar.getInstance();
	public String execute(){
		
		return SUCCESS;
	}
	
	
	public String upload() throws IOException{
		
		if(upload==null){
			Message msg=new Message();
			msg.setError("未指定檔案");
			this.savMessage(msg);
			return SUCCESS;
		}
		
		FileInputStream fis = new FileInputStream(upload);
		//FileReader fr = new FileReader(fis.getFD());
		//BufferedReader br = new BufferedReader(fr);
		
		XSSFWorkbook xwb = new XSSFWorkbook(fis);
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow row;
		
		
		//System.out.println(sheet.getPhysicalNumberOfRows());
		String ClassNo;
		String student_no;
		String student_name;
		String idno;
		Stmd stmd;
		
		Date now=new Date();
		int now_Year = now.getYear()-11;
		
		List complete=new ArrayList();
		List fail=new ArrayList();
		for (int i = sheet.getFirstRowNum()+1; i <= sheet.getPhysicalNumberOfRows(); i++) {				
			row = sheet.getRow(i);
			if(row!=null){				
				for(int j=0; j<5; j++){//檢查必要欄位null
					if(String.valueOf(row.getCell(j)).trim().equals("")){
						//fail.add(setMsg("第"+i+"列第"+j+"欄", "缺少必要欄位"));
						continue;
					}
				}
				
				//若為表頭
				ClassNo=readCellAsString(row.getCell(0));
				ClassNo=ClassNo.trim();
				student_no=readCellAsString(row.getCell(1));
				student_no=student_no.trim();
				student_name=readCellAsString(row.getCell(2));
				student_name=student_name.trim();
				idno=readCellAsString(row.getCell(3));
				idno=idno.trim();
				if(!checkClass(ClassNo)){
					fail.add(setMsg(student_no, "班級欄位格式有誤"));
					continue;
				}
				
				if(student_name==""){
					fail.add(setMsg(student_no, "姓名欄位格式有誤"));
					continue;
				}
				
				if(idno.length()<10){
					fail.add(setMsg(student_no, "身份證欄位格式有誤"));
					continue;
				}

				//生日檢核
				try{
					birthday=getBirthday(readCellAsString(row.getCell(4)));
				}catch(Exception e){
					fail.add(setMsg(student_no, "生日欄位格式有誤"));
					continue;
				}
				
				//試刪除原資料
				df.exSql("DELETE FROM stmd WHERE student_no='"+student_no+"'");
				
				try{
					df.exSql("DELETE FROM RegistrationCard WHERE StudentNo='"+student_no+"'");
				}catch(Exception e){
					
				}
				
				stmd=new Stmd();
				try{
					stmd.setDepartClass(ClassNo);
					stmd.setStudentNo(student_no);
					stmd.setStudentName(student_name);
					stmd.setIdno(idno);
					stmd.setBirthday(birthday);
					stmd.setSex(getSex(idno));
					stmd.setEntrance(Short.valueOf(now_Year+"08"));
					/*原本
					stmd.setDepartClass(ClassNo);
					stmd.setStudentNo(student_no);
					stmd.setStudentName(readCellAsString(row.getCell(2)));
					stmd.setIdno(readCellAsString(row.getCell(3)));
					stmd.setBirthday(birthday);
					stmd.setSex(getSex(readCellAsString(row.getCell(3))));
					*/
					df.update(stmd);
					df.exSql("INSERT INTO RegistrationCard (SchoolYear,StudentNo) VALUES ('"+now_Year+"','"+student_no+"')");
				}catch(Exception e){
					int k=i+1;
					fail.add(setMsg(student_no,"第"+k+"列 - 匯入失敗"));
					continue;
					
				}
				
				
				try{
					if(row.getCell(5)!=null)
					stmd.setIdent(readCellAsString(row.getCell(5)));
					if(row.getCell(6)!=null)
					stmd.setSchlName(readCellAsString(row.getCell(6)));
					if(row.getCell(7)!=null)
					stmd.setGradDept(readCellAsString(row.getCell(7)));
					
					//System.out.println(row.getCell(8));
					if(row.getCell(8)!=null) {
						if(!String.valueOf(row.getCell(8)).trim().equals(""))
							stmd.setGradyear(Short.parseShort(readCellAsString(row.getCell(8))));
					}

					if(row.getCell(9)!=null)
					stmd.setGraduStatus(readCellAsString(row.getCell(9)));
					if(row.getCell(10)!=null)
					stmd.setCurrPost(readCellAsString(row.getCell(10)));
					if(row.getCell(11)!=null)
					stmd.setCurrAddr(readCellAsString(row.getCell(11)));
					if(row.getCell(12)!=null)
					stmd.setTelephone(readCellAsString(row.getCell(12)));
					if(row.getCell(13)!=null)
					stmd.setCellPhone(readCellAsString(row.getCell(13)));
					if(row.getCell(14)!=null)
					stmd.setParentName(readCellAsString(row.getCell(14)));
					if(row.getCell(15)!=null)
					stmd.setEmail(readCellAsString(row.getCell(15)));
					if(row.getCell(16)!=null)
					stmd.setPermPost(readCellAsString(row.getCell(16)));
					if(row.getCell(17)!=null)
					stmd.setPermAddr(readCellAsString(row.getCell(17)));
					df.update(stmd);
					//System.out.println("INSERT INTO wwpass (username, password ,priority ,informixpass) VALUES ("+student_no+","+idno+",C,"+idno+");");
					complete.add(setMsg(student_no, "儲存完成"));
				}catch(Exception e){
					fail.add(setMsg(student_no, "欄位格式有誤"));
					continue;
				}							
			}			
		}
		
		fis.close();
		request.setAttribute("complete", complete);
		request.setAttribute("fail", fail);
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
