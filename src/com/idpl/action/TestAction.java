package com.idpl.action;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.idpl.dao.*;

import webcondor.*;
import javax.servlet.http.*;
import org.apache.struts2.ServletActionContext; 

public class TestAction extends BaseAction{
	private long recordId;       //ʵ���¼ID
	private String testName;     //ʵ������
	private String way;          //���ݴ��䷽��
	private String method;       //���ݴ��䷽ʽ
	private String protocol;     //���ݴ�����·Э��
	private String dataSource;   //����Դ
	private String dataSize;     //���ݴ�С
	private String dataNumber;
	private String dataDestination;  //����Ŀ�ĵ�
	private String repeat;        //���ݴ���Ƶ��
	private String number;
	private String parallel;
/*	private String timeStart_date;
	private String timeStart_time;
	private String timeEnd_date;
	private String timeEnd_time;*/
	private long experimentId;
	private String note;
	public long getRecordId(){     //�õ�ʵ���¼ID
		return this.recordId;
	}
	public String getTestName(){   //�õ�ʵ������
		return this.testName;
	}
	public String getWay(){        //�õ�ʵ�鴫�䷽��
		return this.way;
	}
	public String getMethod(){    //�õ�ʵ�鴫�䷽ʽ
		return this.method;
	}
	public String getProtocol(){   //�õ�ʵ�鴫����·Э��
		return this.protocol;
	}
	public String getDataSource(){ //�õ�����Դ
		return this.dataSource;
	}
	public String getDataSize(){  //�õ����ݴ�С
		return this.dataSize;
	}
	public String getDataNumber(){
		return this.dataNumber;
	}
	public String getDataDestination(){   //�õ�����Ŀ�ĵ�
		return this.dataDestination;
	}
	public String getRepeat(){        //�õ����ݴ���Ƶ��
		return this.repeat;
	}
	public String getNumber(){
		return this.number;
	}
	public String getParallel(){
		return this.parallel;
	}
/*	
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
	*/
	public long getExperimentId(){
		return this.experimentId;
	}
	public String getNote(){
		return this.note;
	}
	public void setTestName(String name){    //����ʵ������
		this.testName=name;
	}
	public void setWay(String way){        //���ô��䷽��
		this.way=way;
	}
	public void setMethod(String method){   //���ô��䷽ʽ
		this.method=method;
	}
	public void setProtocol(String protocol){    //�������ݴ�����·Э��
		this.protocol=protocol;
	}
	public void setDataSource(String dataSource){   //��������Դ
		this.dataSource=dataSource;
	}
	public void setDataSize(String dataSize){      //�������ݴ�С
		this.dataSize=dataSize;
	}
	public void setDataNumber(String dataNumber){
		this.dataNumber=dataNumber;
	}
	public void setRecordId(long id){           //����ʵ���¼ID
		this.recordId=id;
	}
	public void setDataDestination(String dataDestination){   //��������Ŀ�ĵ�
		this.dataDestination=dataDestination;
	}
	public void setRepeat(String repeat){      //�������ݴ���Ƶ��
		this.repeat=repeat;
	}
	public void setNumber(String number){
		this.number=number;
	}
	public void setParallel(String parallel){
		this.parallel=parallel;
	}
/*	public void setTimeStart_date(String timeStart_date){
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
	}*/
	
	public void setExperimentId(long experimentId){
		this.experimentId=experimentId;
	}
	public void setNote(String note){
		this.note=note;
	}
	public String execute(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		String Date;
//		String timeStart=null;
//		String timeEnd=null;
		String result="fail";
		String username=(String) session.getAttribute("username");
//		int repeat_number=Integer.parseInt(repeat);
		int number_number=Integer.parseInt(number);
		int parallel_number=Integer.parseInt(parallel);
		int dataNumber_number=Integer.parseInt(dataNumber.substring(0,dataNumber.length()-1));
		if(number_number<parallel_number) 
		{
			note="number is not less than parallel!";
			return result;
		}
		if(dataNumber_number>200)
		{
			note="data is too big! data size < 200G!";
			return result;
		}
		if(dataSource.equals(dataDestination))
		{
			note="date Source cannot be the same as data Destination!";
			return result;
		}
		Date nowTime=new Date();
		SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date=matter1.format(nowTime);
//		
//		if(!timeStart_date.equals("")&&!timeStart_time.equals(""))
//			timeStart=timeStart_date+" "+timeStart_time;
//		
//		if(!timeEnd_date.equals("")&&!timeEnd_time.equals(""))
//			timeEnd=timeEnd_date+" "+timeEnd_time;
//		
		RecordDAO recordDAO=RecordDAOFactory.getRecordDAOInstance();
		Record record=new Record();
		record.setTestName(testName);
		record.setWay(way);
		record.setMethod(method);
//		System.out.println(dataNumber);
		record.setDataSize(dataNumber);
		record.setDataDestination(dataDestination);
		record.setDataSource(dataSource);
		record.setRepeat(repeat);
		record.setNumber(number);
		record.setParallel(parallel);
		record.setExperimentId(experimentId);
		record.setProtocol(protocol);
		record.setDate(Date);
//		record.setTime_Start(timeStart);
//		record.setTime_End(timeEnd);
		if(username!=null)
		{
			record.setUsername(username);
			try {
				long NewId=recordDAO.insert(record,"test");
				note=null;
				result="success";
				//webcondor.Transfer.Transfer(long id, String user, String source, 
				//       String destination, String size, int number, int repeat, int parallel, 
				//       String protocol, String putorget, String v4orv6, String starttime, String stoptime)
				/*Transfer transfer=new Transfer(NewId, record.getUsername(), record.getDataSource(), 
						record.getDataDestination(), record.getDataSize(), number_number, repeat_number, parallel_number,
						record.getMethod(), record.getWay(), record.getProtocol(), timeStart, timeEnd);
				transfer.submit();*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			return "login";
		return result;
	}
}
