package com.bed.action;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.bed.dao.*;
import webcondor.*;
import javax.servlet.http.*;
import org.apache.struts2.ServletActionContext; 

public class TestAction extends BaseAction{
	private long recordId;       //实验记录ID
	private String testName;     //实验名称
	private String way;          //数据传输方法
	private String method;       //数据传输方式
	private String protocol;     //数据传输链路协议
	private String dataSource;   //数据源
	private String dataSize;     //数据大小
	private String dataNumber;
	private String dataDestination;  //数据目的地
	private String repeat;        //数据传输频率
	private String number;
	private String parallel;
/*	private String timeStart_date;
	private String timeStart_time;
	private String timeEnd_date;
	private String timeEnd_time;*/
	private long experimentId;
	
	public long getRecordId(){     //得到实验记录ID
		return this.recordId;
	}
	public String getTestName(){   //得到实验名称
		return this.testName;
	}
	public String getWay(){        //得到实验传输方法
		return this.way;
	}
	public String getMethod(){    //得到实验传输方式
		return this.method;
	}
	public String getProtocol(){   //得到实验传输链路协议
		return this.protocol;
	}
	public String getDataSource(){ //得到数据源
		return this.dataSource;
	}
	public String getDataSize(){  //得到数据大小
		return this.dataSize;
	}
	public String getDataNumber(){
		return this.dataNumber;
	}
	public String getDataDestination(){   //得到数据目的地
		return this.dataDestination;
	}
	public String getRepeat(){        //得到数据传输频率
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
	public void setTestName(String name){    //设置实验名称
		this.testName=name;
	}
	public void setWay(String way){        //设置传输方法
		this.way=way;
	}
	public void setMethod(String method){   //设置传输方式
		this.method=method;
	}
	public void setProtocol(String protocol){    //设置数据传输链路协议
		this.protocol=protocol;
	}
	public void setDataSource(String dataSource){   //设置数据源
		this.dataSource=dataSource;
	}
	public void setDataSize(String dataSize){      //设置数据大小
		this.dataSize=dataSize;
	}
	public void setDataNumber(String dataNumber){
		this.dataNumber=dataNumber;
	}
	public void setRecordId(long id){           //设置实验记录ID
		this.recordId=id;
	}
	public void setDataDestination(String dataDestination){   //设置数据目的地
		this.dataDestination=dataDestination;
	}
	public void setRepeat(String repeat){      //设置数据传输频率
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
	public String execute(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		String Date;
//		String timeStart=null;
//		String timeEnd=null;
		String result="fail";
		String username=(String) session.getAttribute("username");
//		int repeat_number=Integer.parseInt(repeat);
//		int number_number=Integer.parseInt(number);
//		int parallel_number=Integer.parseInt(parallel);
		
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
		record.setDataSize(dataNumber+dataSize);
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
		return result;
	}
}
