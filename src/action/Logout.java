package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Message;

import org.apache.struts2.interceptor.ServletRequestAware;

import service.impl.AccountManager;
import service.impl.base.BaseAccessImpl;

public class Logout extends BaseAction implements ServletRequestAware{
	
	/**
	 * stis logout
	 * @return "public-access.xml"中"LoginAction"節點名稱"logout"定位頁面"/index.jsp"
	 */
	public String execute() throws Exception {		
		AccountManager am = (AccountManager) get("AccountManager");
		if(getSession()!=null){
			am.logOut(request, response);
		}
		Cookie cookie = new Cookie("userid", "deleted");
		cookie.setMaxAge(0);
    	cookie.setDomain(".cust.edu.tw");
    	cookie.setPath("/");
    	response.addCookie(cookie);
		response.sendRedirect("/ssos/Logout");
		return null;
	}

}
