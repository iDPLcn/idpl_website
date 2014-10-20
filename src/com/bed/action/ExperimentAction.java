package com.bed.action;

import java.util.Map;
import javax.servlet.http.*;

public class ExperimentAction extends BaseAction {
	private String expName;
	private String expId;
	private String username;
	public String getExpName(){
		return expName;
	}
	
	public void setExpName(String expName){
		this.expName=expName;
	}
	
	public String getExpId(){
		return expId;
	}
	
	public void setExpId(String expId){
		this.expId=expId;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	public String execute(){
		return SUCCESS;
	}
}
	
