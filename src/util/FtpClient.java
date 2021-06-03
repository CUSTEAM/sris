package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * 
 * @author JOHN
 *
 */
public class FtpClient {
	
	
	private static String FTPHost;	
	private static String username;
	private static String password;
	private String LocalDir;
	private String ServerDir;
	private FTPClient ftp;

	private boolean binaryTransfer = false;

	private final static Logger log = Logger.getLogger(FtpClient.class);

	/**
	 * @param server 操作FTP位置
	 * @param username 帳號
	 * @param password 密碼
	 */
	public FtpClient(String FTPHost, String username, String password, String LocalDir, String ServerDir) {

		this.FTPHost=FTPHost;
		this.username=username;
		this.password=password;
		this.LocalDir=LocalDir;
		this.ServerDir=ServerDir;
		ftp = new FTPClient();
		/*
		 * if(Configuration.PrintFTPCommandLog){ //印出命令字元
		 * ftp.addProtocolCommandListener(new PrintCommandListener()); }
		 */
	}
	 
	 /**
	  * 外部建構1個
	  */
	 public FtpClient() {
	 	this(FTPHost, username, password, FTPHost, FTPHost);
	 }
	 
	public boolean connect() {
		try {
			int reply;
			ftp.connect(FTPHost);

			//是否成功
			reply = ftp.getReplyCode();

			if (FTPReply.isPositiveCompletion(reply)) {
				if (ftp.login(username, password)) {
					// 設為passive模式
					ftp.enterLocalPassiveMode();
					return true;
				}
			} else {
				ftp.disconnect();
				log.error("FTP server refused connection.");
			}
		} catch (IOException e) {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
				}
			}
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 下載遠端檔案
	 * 
	 * @param fileName 檔案名稱不含路徑
	 * @param delFile 完成後是否刪除
	 * @return
	 */
	public boolean get(String fileName, boolean delFile) {
		String remote = ServerDir + fileName;
		String local = LocalDir + fileName;
		return get(remote, local, delFile);
	}

	/**
	 * 上傳檔案
	 * 
	 * @param fileName 檔案名稱不含路徑
	 * @param delFile 完成後是否刪除
	 * @return
	 */
	public boolean put(String fileName, boolean delFile) {
		
		String remote = ServerDir + fileName;
		String local = LocalDir + fileName;
		
		return put(remote, local, delFile);
	}
	
	/**
	 * 上傳檔案(會議/法規)
	 * 
	 * @param fileName 檔案名稱不含路徑
	 * @param delFile 完成後是否刪除
	 * @return
	 */
	public boolean putFile(String fileName, String fileName_N, boolean delFile) {
		
		String remote = ServerDir + fileName;
		String local = LocalDir + "/" + fileName;
		
		return put(remote, local, delFile);
	}
	
	/**
	 * 下載檔案(會議/法規)
	 * @param fileName 檔案名稱不含路徑
	 * @param delFile  完成後是否刪除
	 * @return
	 */
	public boolean getFile(String fileName, boolean delFile) {
		String remote = ServerDir + fileName;
		String local = LocalDir +"/"+ fileName;
		return get(remote, local, delFile);
	}
	

	/**
	 * 上傳多個檔案
	 */
	public boolean[] put(String[] fileNames, boolean delFile) {
		boolean[] result = new boolean[fileNames.length];
		for (int j = 0; j < result.length; j++) {
			result[j] = false;
		}
		String remoteFile;
		String localFile;
		for (int i = 0; i < fileNames.length; i++) {
			localFile = fileNames[i];
			result[i] = put(localFile, delFile);
		}
		return result;
	}

	
	
	
	/**
	 * 上傳檔案
	 */
	private boolean put(String remoteAbsoluteFile, String localAbsoluteFile,boolean delFile) {
		
		int reply;
        //ftp.connect(url, port);//連接FTP服務器
        //ftp.connect(url);
        //如果採用默認端口，可以使用ftp.connect(url)的方式直接連接FTP服務器
        //ftp.login(username, password);//登錄
        
		
		
		InputStream input = null;
		try {
			
			reply = ftp.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply)) {
	            ftp.disconnect();
	            return false;
	        }
			
			//System.out.println("建立目錄"+ftp.makeDirectory(ServerDir));
			
			ftp.makeDirectory(ServerDir);
			//設置檔案類型(2進制或文字)
			
			//if (binaryTransfer) {
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			//} else {
				//ftp.setFileType(FTPClient.ASCII_FILE_TYPE);
			//}
			
			// 處理傳輸
			input = new FileInputStream(localAbsoluteFile);
			ftp.storeFile(remoteAbsoluteFile, input);
			//log.debug("put " + localAbsoluteFile);
			input.close();
			if (delFile) {
				(new File(localAbsoluteFile)).delete();
			}
			//log.debug("delete " + localAbsoluteFile);
			//System.out.println(localAbsoluteFile);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				ftp.disconnect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * 下載遠端FTP檔案
	 */
	public boolean get(String remoteAbsoluteFile, String localAbsoluteFile, boolean delFile) {
		
		//System.out.println("D_ServerDir="+ServerDir);
		//System.out.println("D_localAbsoluteFile="+localAbsoluteFile);
		//System.out.println("D_remoteAbsoluteFile="+remoteAbsoluteFile);
		
		OutputStream output = null;
		try {
			// 設置檔案類型
			if (binaryTransfer) {
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			} else {
				ftp.setFileType(FTPClient.ASCII_FILE_TYPE);
			}
			// 處理傳輸
			output = new FileOutputStream(localAbsoluteFile);
			ftp.retrieveFile(remoteAbsoluteFile, output);
			output.close();
			if (delFile) { // 刪除遠端檔案
				ftp.deleteFile(remoteAbsoluteFile);
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e2) {
			}
		}
		return false;
	}

	/**
	 * 列出遠端所有檔案
	 */
	public String[] listNames(String remotePath) {
		String[] fileNames = null;
		try {
			FTPFile[] remotefiles = ftp.listFiles(remotePath);
			fileNames = new String[remotefiles.length];
			for (int i = 0; i < remotefiles.length; i++) {
				fileNames[i] = remotefiles[i].getName();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileNames;
	}

	/**
	 * 結束FTP
	 */
	public void disconnect() {
		try {
			ftp.logout();
			if (ftp.isConnected()) {
				ftp.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Returns the binaryTransfer.
	 */
	public boolean isBinaryTransfer() {
		return binaryTransfer;
	}

	/**
	 * @param binaryTransfer
	 *            The binaryTransfer to set.
	 */
	public void setBinaryTransfer(boolean binaryTransfer) {
		this.binaryTransfer = binaryTransfer;
	}




	public static String getFTPHost() {
		return FTPHost;
	}




	public static void setFTPHost(String host) {
		FTPHost = host;
	}




	public String getLocalDir() {
		return LocalDir;
	}




	public void setLocalDir(String localDir) {
		LocalDir = localDir;
	}




	public String getServerDir() {
		return ServerDir;
	}




	public void setServerDir(String servdrDir) {
		ServerDir = servdrDir;
	}




	public static String getUsername() {
		return username;
	}




	public static void setUsername(String username) {
		FtpClient.username = username;
	}




	public static String getPassword() {
		return password;
	}




	public static void setPassword(String password) {
		FtpClient.password = password;
	}




	public FTPClient getFtp() {
		return ftp;
	}




	public void setFtp(FTPClient ftp) {
		this.ftp = ftp;
	}




	public static Logger getLog() {
		return log;
	}
	
	/**
     * Description: 向FTP服務器上傳文件
     * @Version1.0
     * @param url FTP服務器hostname
     * @param port FTP服務器端口
     * @param username FTP登錄賬號
     * @param password FTP登錄密碼
     * @param path FTP服務器保存目錄
     * @param filename 上傳到FTP服務器上的文件名
     * @param input 輸入流
     * @return 成功返回true，否則返回false
     */
    public boolean uploadFile(
            //String url,//FTP服務器hostname
            //int port,//FTP服務器端口
            //String username, // FTP登錄賬號
            //String password, //FTP登錄密碼
            //String path, //FTP服務器保存目錄
            String localAbsoluteFile, //上傳到FTP服務器上的文件名
            String fileName // 檔名
            ) {
        boolean success = false;
        //FTPClient ftp = new FTPClient();
        try {
            int reply;
            //ftp.connect(url, port);//連接FTP服務器
            //ftp.connect(url);
            //如果採用默認端口，可以使用ftp.connect(url)的方式直接連接FTP服務器
            //ftp.login(username, password);//登錄
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            if (binaryTransfer) {
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			} else {
				ftp.setFileType(FTPClient.ASCII_FILE_TYPE);
			}
            //ftp.changeWorkingDirectory(path);
            FileInputStream input = new FileInputStream(localAbsoluteFile);
            ftp.storeFile(fileName, new FileInputStream(localAbsoluteFile));
            input.close();            
            ftp.logout();
            
            new File(localAbsoluteFile).delete();
            
            
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
	
}
