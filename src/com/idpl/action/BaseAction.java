package com.idpl.action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> session;
	
	//�洢��Session�е����ݼ���
    public static final String LOGIN_USER = "username";
    
	   /**
     * ��ȡ��ǰ��¼�û�
     */
    public String getLoginAccount() {
        String account = (String)session.get(LOGIN_USER);
        if (account == null) {
            return null;
        }
        return account;
    }

    /**
     * ����ǰ��¼�û��洢��Session��
     */
    public void setLoginAccount(String account) {
        if (account == null) {
            session.remove(LOGIN_USER);
        }
        else {
            session.put(LOGIN_USER, account);
        }
    }
    
    public  void cleanSession() {
        session.clear();
    }
    
	@Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

}
