package com.bed.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ExperimentImpl implements ExperimentDAO {
	public long insert(Experiment experiment,String TableName) throws Exception{
		String sql="INSERT INTO "+TableName+"(id,experimentName,username,timeStart,timeEnd,Date) VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		
		long NewId=System.currentTimeMillis();
		Random rand =new Random(25);
		int RandNumber;
		RandNumber=rand.nextInt(1000);
		NewId = NewId+RandNumber;
		try{
			dbc=new DataBaseConnection(TableName);
		}catch(Exception e){
			throw new Exception("Connection Error!");
		}
		try{
			
			pstmt=dbc.getConnection().prepareStatement(sql);
			pstmt.setLong(1, NewId);
			pstmt.setString(2, experiment.getExperimentName());
			pstmt.setString(3, experiment.getUsername());
			pstmt.setString(4 ,experiment.getTimeStart());
			pstmt.setString(5 ,experiment.getTimeEnd());
			pstmt.setString(6 ,experiment.getDate());
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
	public void update(Experiment experiment,String TableName) throws Exception{
	
	}
	public void submit(String TableName,Long experimentId) throws Exception{
		String sql="UPDATE "+TableName+" SET submit='YES' WHERE id="+experimentId;
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			dbc=new DataBaseConnection(TableName);
		}catch(Exception e){
			throw new Exception("Connection Error!");
		}
		try{
			pstmt=dbc.getConnection().prepareStatement(sql);
			System.out.println(pstmt);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(Exception e){
			throw new Exception("Update Error!");
		}
		finally{
			dbc.close();
		}
	}
	public void delete(Experiment experiment,String TableName) throws Exception{
		
	}
	public Experiment queryById(long id,String TableName,String username) throws Exception{
		Experiment experiment=null;
		String sql="SELECT * FROM "+TableName+" WHERE id=? AND username='"+username+"'";		
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			dbc=new DataBaseConnection(TableName);
			pstmt=dbc.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				experiment=new Experiment();
				experiment.setExperimentId(rs.getLong(1));
				experiment.setExperimentName(rs.getString(2));
				experiment.setUsername(rs.getString(3));
				experiment.setTimeStart(rs.getString(4));
				experiment.setTimeEnd(rs.getString(5));
				experiment.setSubmit(rs.getString(6));
			}
			rs.close();
			pstmt.close();
		}catch(Exception e){
			throw new Exception("Query By Id Fail!");
		}
		finally{
			dbc.close();
		}
		return experiment;
	}
	public List<Experiment> queryAll(String TableName,String username) throws Exception{
		List<Experiment> all= new ArrayList<Experiment>();
		String sql="SELECT * FROM "+TableName+" WHERE username='"+username+"'";
		System.out.println(sql);
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			dbc=new DataBaseConnection(TableName);
			pstmt=dbc.getConnection().prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Experiment experiment=new Experiment();
				experiment.setExperimentId(rs.getLong(1));
				experiment.setExperimentName(rs.getString(2));
				experiment.setUsername(rs.getString(3));
				experiment.setTimeStart(rs.getString(4));
				experiment.setTimeEnd(rs.getString(5));
				experiment.setSubmit(rs.getString(6));
				experiment.setDate(rs.getString(7));
				all.add(experiment);
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
