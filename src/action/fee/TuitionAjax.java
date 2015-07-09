package action.fee;

import java.util.HashMap;
import java.util.Map;

import action.BaseAction;

public class TuitionAjax extends BaseAction{
	
	private Map msg;
	

	public Map getMsg() {
		return msg;
	}


	public void setMsg(Map msg) {
		this.msg = msg;
	}


	public String execute(){
		
		//String Oid=request.getParameter("Oid");
		String Money=request.getParameter("Money");
		String kind=request.getParameter("kind");
		String ClassNo=request.getParameter("ClassNo");
		String Fcode=request.getParameter("Fcode");
		System.out.println();
		
		Map m=new HashMap();
		try{
			if(Money.equals("")){
				df.exSql("DELETE FROM FeePay WHERE DepartClass='"+ClassNo+"' AND Fcode='"+Fcode+"'");				
				m.put("msg", "已刪除");				
			}else{				
				df.exSql("INSERT INTO FeePay(Kind,DepartClass,Fcode,Money)VALUES('"+kind+"','"+ClassNo+"','"+
				Fcode+"','"+Money+"') ON DUPLICATE KEY UPDATE Money='"+Money+"';");	
				
				m.put("msg", "已儲存"+Money);
			}			
			m.put("cnt", df.sqlGetStr("SELECT SUM(Money)FROM FeePay WHERE Kind='"+kind+"'AND DepartClass='"+ClassNo+"'"));
			this.setMsg(m);
		
		
		}catch(Exception e){
			e.printStackTrace();
			m.put("msg", "儲存失敗");
			m.put("cnt", df.sqlGetStr("SELECT SUM(Money)FROM FeePay WHERE Kind='"+kind+"'AND DepartClass='"+ClassNo+"'"));
			this.setMsg(m);
		}		
		
		
		
		return SUCCESS;
	}

}
