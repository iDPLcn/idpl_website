package com.bed.dao;
import java.sql.*;
public class DataBaseConnection {	
	private String url="jdbc:mysql://115.25.138.209:3306/cac";
	private static final String user="";
	private static final String password="";
	Connection conn=null;
	public DataBaseConnection(String TableName) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conn=DriverManager.getConnection(url, user, password);
		}
		catch (Exception e){
			System.out.println("Load Driver Fail!");
		}
	}
	public Connection getConnection(){
		return this.conn;
	}
	public void close() throws Exception{
		if(this.conn!=null)
		try{
			conn.close();
		}catch(Exception e){
			System.out.println("DataBase Close Fail!");
		}
	}
}
