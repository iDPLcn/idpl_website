package com.bed.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class RecordDAOImpl implements RecordDAO{
	public long insert(Record record,String TableName) throws Exception{
		String sql="INSERT INTO "+TableName+"(id,testName,way,source,dataSize,destination,frequency,timeStart,timeEnd,Date,method,protocol,number,parallel,username) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		
		long NewId=System.currentTimeMillis()/1000;
		try{
			dbc=new DataBaseConnection(TableName);
		}catch(Exception e){
			throw new Exception("Connection Error!");
		}
		try{
			
			pstmt=dbc.getConnection().prepareStatement(sql);
			pstmt.setLong(1, NewId);
			pstmt.setString(2, record.getTestName());
			pstmt.setString(3, record.getWay());
			pstmt.setString(4, record.getDataSource());
			pstmt.setString(5, record.getDataSize());
			pstmt.setString(6, record.getDataDestination());
			pstmt.setString(7, record.getRepeat());
			pstmt.setString(8, record.getTime_Start());
			pstmt.setString(9, record.getTime_End());
			pstmt.setString(10, record.getDate());
			pstmt.setString(11, record.getMethod());
			pstmt.setString(12, record.getProtocol());
			pstmt.setString(13, record.getNumber());
			pstmt.setString(14, record.getParallel());
			pstmt.setString(15, record.getUsername());
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (Exception e){
			throw new Exception("Insert Error!");
		}
		finally{
			dbc.close();
		}
		return NewId;
	}
	public void update(Record record,String TableName) throws Exception{
		
	}
	public void delete(Record record,String TableName) throws Exception{
		
	}
	public Record queryById(long id,String TableName,String username) throws Exception{
		Record record=null;
		String sql="SELECT * FROM "+TableName+" WHERE id=? AND username='"+username+"'";		
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		if(TableName.equals("test"))
		{
			try{
				dbc=new DataBaseConnection(TableName);
				pstmt=dbc.getConnection().prepareStatement(sql);
				pstmt.setLong(1, id);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()){
					record=new Record();
					record.setRecordId(rs.getLong(1));
					record.setTestName(rs.getString(2));
					record.setWay(rs.getString(3));
					record.setDataSource(rs.getString(4));
					record.setDataSize(rs.getString(5));
					record.setDataDestination(rs.getString(6));
					record.setTime_Start(rs.getString(7));
					record.setTime_End(rs.getString(8));
					record.setDate(rs.getString(9));
					record.setMethod(rs.getString(10));
					record.setProtocol(rs.getString(11));
					record.setNumber(rs.getString(12));
					record.setParallel(rs.getString(13));
					record.setRepeat(rs.getString(14));
					record.setUsername(rs.getString(15));
				}
				rs.close();
				pstmt.close();
			}catch(Exception e){
				throw new Exception("Query By Id Fail!");
			}
			finally{
				dbc.close();
			}
		}
		else if(TableName.equals("condorjob"))
		{
			try{
				sql="SELECT * FROM "+TableName+" WHERE id=?";
				dbc=new DataBaseConnection(TableName);
				pstmt=dbc.getConnection().prepareStatement(sql);
				pstmt.setLong(1, id);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()){
					record=new Record();
					record.setRecordId(rs.getLong(1));
					record.setClusterId(rs.getInt(2));
					record.setJobId(rs.getInt(3));
					record.setState(rs.getString(4));
					record.setStartRunning(rs.getString(5));
					record.setCompletedTime(rs.getString(6));
				}
				rs.close();
				pstmt.close();
			}catch(Exception e){
				throw new Exception("Query By Id Fail!");
			}
			finally{
				dbc.close();
			}
		}
		return record;
	}
	public List<Record> queryAll(String TableName,String username) throws Exception{
		List<Record> all= new ArrayList<Record>();
		String sql="SELECT * FROM "+TableName+" WHERE username='"+username+"'";
		System.out.println(sql);
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			dbc=new DataBaseConnection(TableName);
			pstmt=dbc.getConnection().prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Record record=new Record();
				record.setRecordId(rs.getLong(1));
				record.setTestName(rs.getString(2));
				record.setWay(rs.getString(3));
				record.setDataSource(rs.getString(4));
				record.setDataSize(rs.getString(5));
				record.setDataDestination(rs.getString(6));
				record.setTime_Start(rs.getString(7));
				record.setTime_End(rs.getString(8));
				record.setDate(rs.getString(9));
				record.setMethod(rs.getString(10));
				record.setProtocol(rs.getString(11));
				record.setNumber(rs.getString(12));
				record.setParallel(rs.getString(13));
				record.setRepeat(rs.getString(14));
				record.setUsername(rs.getString(15));
				all.add(record);
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			throw new Exception("QueryAll Error!");
		}
		finally{
			dbc.close();
		}
		return all;
	}
}
