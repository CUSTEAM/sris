package action.student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import action.BaseAction;
import model.Message;
import util.*;


public class UploadStmdImageAction extends BaseAction{
	private File fileUpload[];
	private String fileUploadContentType[];
	private String fileUploadFileName[];
	
	public File[] getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File[] fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String[] getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String[] fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String[] getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String[] fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String execute() {
		
		
		return SUCCESS;
	}
	
	public String upload() throws IOException {
		String folder;
		String studentNo;		
		for(int i=0; i<fileUpload.length; i++) {
			
			System.out.println(fileUpload[i].getName());
			System.out.println(fileUploadContentType[i]);
			System.out.println(fileUploadFileName[i]);
			System.out.println("path: "+fileUpload[i].getPath());
			System.out.println("fileName: "+fileUploadFileName[i]);
			String path=fileUpload[i].getPath();
			path=path.replace("\\", "/");
			studentNo=fileUploadFileName[i].substring(0,fileUploadFileName[i].indexOf("."));
			if(df.sqlGet("SELECT COUNT(*)FROM stmd WHERE student_no='"+studentNo+"'").size()<1)return null;
			
			//改變大小
			resetImgSize4Stmd(fileUpload[i].getPath());				
			
			folder=studentNo.substring(0, 3);
			
			File f1 = new File(fileUpload[i].getPath());
			File f2 = new File(fileUpload[i].getParent()+"/"+studentNo+".jpg");
			boolean b = f1.renameTo(f2);
			f1.delete();
			
			try{
				String target="host_runtime";
				if(getContext().getAttribute("isServer").equals("0"))target="host_debug";			
				Map<String, String>ftpinfo=df.sqlGetMap("SELECT "+target+" as host, username, password, path FROM SYS_HOST WHERE useid='StdImage'");
				
				FtpClient ftp=new FtpClient(ftpinfo.get("host"), ftpinfo.get("username"), ftpinfo.get("password"), null, null);				
				ftp.connect();				
				ftp.setLocalDir(fileUpload[i].getParent()+"/");			
				ftp.setServerDir(ftpinfo.get("path")+"/"+folder+"/");	
				System.out.println("serverDIR: "+ftpinfo.get("path")+"/"+folder+"/");
				ftp.put(studentNo+".jpg", true);
						
			}catch(Exception e){
				e.printStackTrace();				
			}
			
			
			
		}
		
		Message msg=new Message();
		msg.setSuccess("已上傳");
		this.savMessage(msg);
		return SUCCESS;
	}
	
	private void resetImgSize4Stmd(String path) throws IOException {
		
		//改變大小
		ImageToolkit img=new ImageToolkit(path);		
		int height=img.getHeight();
		int width=img.getWidth();
		if(width>140){
			int ratio=img.getHeight()/140;
			img.reduceImg(path, height/ratio, width/ratio);			
		}				
		
		
	}
	

}
