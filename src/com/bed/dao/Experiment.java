package com.bed.dao;

public class Experiment {
	private long experimentId;
	private String experimentName;
	private String username;
	private String timeStart;    //��ʼʱ��
	private String timeEnd;      //����ʱ��
	private String date;          //�����趨ʱ��
	private String startRunning;  //����ʵ�ʿ�ʼ����ʱ��
	private String completedTime; //����ʵ�ʽ�������ʱ��
	private String state;         //���ݴ���״̬
	private String submit;
	public long getExperimentId(){
		return this.experimentId;
	}
	public void setExperimentId(long experimentId)
	{
		this.experimentId=experimentId;
	}
	public String getExperimentName(){
		return this.experimentName;
	}
	public void setExperimentName(String experimentName){
		this.experimentName=experimentName;
	}
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getTimeStart(){    //�õ������趨���俪ʼʱ��
		return this.timeStart;
	}
	public String getTimeEnd(){      //�õ������趨�������ʱ��
		return this.timeEnd;
	}
	public String getDate(){          //�õ������趨ʱ��
		return this.date;
	}
	public String getStartRunning(){  //�õ����ݴ���ʵ�ʿ�ʼʱ��
		return this.startRunning;
	}
	public String getCompletedTime(){ //�õ����ݴ���ʵ�ʽ���ʱ��
		return this.completedTime;
	}
	public String getState(){        //�õ����ݴ�����ҵ״̬
		return this.state;
	}
	public String getSubmit(){
		return this.submit;
	}
	public void setTimeStart(String timeStart){   //���������趨��ʼʱ��
		this.timeStart=timeStart;
	}
	public void setTimeEnd(String timeEnd){     //���������趨����ʱ��
		this.timeEnd=timeEnd;
	}
	public void setDate(String date){         //���������趨ʱ��
		this.date=date;
	}
	public void setStartRunning(String startRunning){     //�������ݴ���ʵ�ʿ�ʼʱ��
		this.startRunning=startRunning;
	}
	public void setCompletedTime(String completedTime){   //�������ݴ���ʵ�ʽ���ʱ��
		this.completedTime=completedTime;
	}
	public void setState(String state){         //�������ݴ���״̬
	
		this.state=state;
	}
	public void setSubmit(String submit){
		this.submit=submit;
	}
}