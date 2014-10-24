package com.bed.action;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bed.dao.*;
public class ShowExperimentAction extends BaseAction{
	private List<Experiment> experimentList;
	public List<Experiment> getExperimentList(){
		return this.experimentList;
	}
	public void setExperimentList(List<Experiment> experimentList){
		this.experimentList=experimentList;
	}
	public String execute(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		String result="fail";
		ExperimentDAO experimentDAO=ExperimentDAOFactory.getExperimentDAOInstance();
		String username=(String)session.getAttribute("username");
		System.out.println(username);
		if(username!=null)
		{
			try {
				experimentList=experimentDAO.queryAll("experiment", username);
				result="success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else result="login";
		return result;		
	}
}
