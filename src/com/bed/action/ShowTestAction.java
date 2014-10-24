package com.bed.action;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bed.dao.*;
public class ShowTestAction extends BaseAction{
	private long experimentId;
	private List<Record> experimentList;
	public void setExperimentId(long experimentId){
		this.experimentId=experimentId;
	}
	public long getExperimentId(){
		return this.experimentId;
	}
	public void setExperimentList(List<Record> experimentList){
		this.experimentList=experimentList;
	}
	public List<Record> getExperimentList(){
		return this.experimentList;
	}
	public String execute(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		String result="fail";
		RecordDAO recordDAO=RecordDAOFactory.getRecordDAOInstance();
		String username=(String)session.getAttribute("username");
		if(username!=null)
		{
			try {
			//	System.out.println(experimentId);
				experimentList=recordDAO.queryAll("test",username,experimentId);
			//	System.out.println("ShowTestAction Doing");
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
