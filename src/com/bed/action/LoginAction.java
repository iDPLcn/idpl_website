package com.bed.action;


import com.bed.dao.*;
import javax.servlet.http.*;
import org.apache.struts2.ServletActionContext; 

public class LoginAction extends BaseAction{
	private String username;
	private String password;
	HttpSession session = ServletActionContext.getRequest ().getSession(); 
	
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public String execute(){
		UserDAO userDAO=UserDAOFactory.getRecordDAOInstance();
		String result="false";
		try {
			result = userDAO.isUser(username, password, "user");
			session.setAttribute("username", username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
