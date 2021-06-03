package action.student;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import action.BaseAction;
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
		
		
		for(int i=0; i<fileUpload.length; i++) {
			
			System.out.println(fileUpload[i].getName());
			System.out.println(fileUploadContentType[i]);
			System.out.println(fileUploadFileName[i]);
			System.out.println("path: "+fileUpload[i].getPath());
			System.out.println("fileName: "+fileUploadFileName[i]);
			String path=fileUpload[i].getPath();
			path=path.replace("\\", "/");
			resetImgSize4Stmd(path.substring(0, path.lastIndexOf("/")), fileUploadFileName[i]);
			
		}
		
		
		return SUCCESS;
	}
	
	private void resetImgSize4Stmd(String path, String fileName) throws IOException {
		
		//改變大小
		ImageToolkit img=new ImageToolkit(path+"/"+fileName);		
		int height=img.getHeight();
		int width=img.getWidth();
		if(width>140){
			int ratio=img.getHeight()/140;
			img.reduceImg(path+"/"+fileName, height/ratio, width/ratio);			
		}				
		String studentNo=fileName.substring(0, fileName.indexOf("."));
		String folder=studentNo.substring(0, 3);
		System.out.println("student_no:"+studentNo);
		System.out.println("folder:"+folder);	
		try{
			String target="host_runtime";
			//if(!manager.testOnlineServer())target="host_debug";			
			
			Map<String, String>ftpinfo=df.sqlGetMap("SELECT "+target+" as host, username, password, path FROM SYS_HOST WHERE useid='StdImage'");
			
			FtpClient ftp=new FtpClient(ftpinfo.get("host"), ftpinfo.get("username"), ftpinfo.get("password"), null, null);				
			ftp.connect();				
			ftp.setLocalDir(path+"/");			
			ftp.setServerDir(ftpinfo.get("path")+"/"+folder+"/");			
			ftp.put(fileName, true);
						
		}catch(Exception e){
			e.printStackTrace();				
		}
		
		
	}
	

}
