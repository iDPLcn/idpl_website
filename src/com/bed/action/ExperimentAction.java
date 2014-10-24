package com.bed.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.*;

import org.apache.struts2.ServletActionContext;

import com.bed.dao.*;
public class ExperimentAction extends BaseAction {
	private String experimentName;
	private long experimentId;
	private String username;
	private String timeStart_date;
	private String timeStart_time;
	private String timeEnd_date;
	private String timeEnd_time;
	public String getExperimentName(){
		return experimentName;
	}
	
	public void setExperimentName(String expName){
		this.experimentName=expName;
	}
	
	public long getExperimentId(){
		return experimentId;
	}
	
	public void setExperimentId(long experimentId){
		this.experimentId=experimentId;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public String getTimeStart_date(){
		return this.timeStart_date;
	}
	public String getTimeStart_time(){
		return this.timeStart_time;
	}
	public String getTimeEnd_date(){
		return this.timeEnd_date;
	}
	public String getTimeEnd_time(){
		return this.timeEnd_time;
	}
	public void setTimeStart_date(String timeStart_date){
		this.timeStart_date=timeStart_date;
	}
	public void setTimeStart_time(String timeStart_time){
		this.timeStart_time=timeStart_time;
	}
	public void setTimeEnd_date(String timeEnd_date){
		this.timeEnd_date=timeEnd_date;
	}
	public void setTimeEnd_time(String timeEnd_time){
		this.timeEnd_time=timeEnd_time;
	}
	public String execute(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		String Date;
		String timeStart=null;
		String timeEnd=null;
		String result="fail";
		String username=(String) session.getAttribute("username");
		
		Date nowTime=new Date();
		SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date=matter1.format(nowTime);
		
		if(!timeStart_date.equals("")&&!timeStart_time.equals(""))
			timeStart=timeStart_date+" "+timeStart_time;
		
		if(!timeEnd_date.equals("")&&!timeEnd_time.equals(""))
			timeEnd=timeEnd_date+" "+timeEnd_time;		

		if(username!=null)
		{
			ExperimentDAO experimentDAO=ExperimentDAOFactory.getExperimentDAOInstance();
			Experiment experiment=new Experiment();
			experiment.setExperimentName(experimentName);
			experiment.setTimeStart(timeStart);
			experiment.setTimeEnd(timeEnd);
			experiment.setDate(Date);
			experiment.setUsername(username);
			try {
				
				experimentId=experimentDAO.insert(experiment, "experiment");
				//session.setAttribute("experimentId", ExperimentId);
				result="success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
	
