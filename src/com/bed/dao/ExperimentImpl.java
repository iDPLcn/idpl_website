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
//			System.out.println(pstmt);
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
	public void delete(long experimentId,String TableName) throws Exception{
		String sql="DELETE FROM test WHERE experimentId="+experimentId;
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		/*delete test*/
		try{
			dbc=new DataBaseConnection("test");
			pstmt=dbc.getConnection().prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
		}catch (Exception e){
			throw new Exception("Delete From test ERROR!");
		}
		finally{
			dbc.close();
		}
		/*delete experiment*/
		sql="DELETE FROM "+TableName+" WHERE id="+experimentId;
		try{
			dbc=new DataBaseConnection(TableName);
			pstmt=dbc.getConnection().prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
		}catch (Exception e){
			throw new Exception("Delete From experiment ERROR!");
		}
		finally{
			dbc.close();
		}
		/*delete idplExperiment*/
		sql="DELETE FROM idplExperiment WHERE id="+experimentId;
		try{
			dbc=new DataBaseConnection("idplExperiment");
			pstmt=dbc.getConnection().prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
		}catch (Exception e){
			throw new Exception("Delete From idplExperiment ERROR!");
		}
		finally{
			dbc.close();
		}
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
				experiment.setStageNumber(getExperimentStageNumber("test",username,rs.getLong(1)));
				if(rs.getString(6)==null)
					experiment.setSubmit("No");
				else
					experiment.setSubmit(rs.getString(6));
//				experiment.setDate(rs.getString(7));
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
//		System.out.println(sql);
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			int allNumber;
			int completeNumber;
			int percentage;
			dbc=new DataBaseConnection(TableName);
			pstmt=dbc.getConnection().prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Experiment idplExperiment=null;
				Experiment experiment=new Experiment();
				experiment.setExperimentId(rs.getLong(1));
				experiment.setExperimentName(rs.getString(2));
				experiment.setUsername(rs.getString(3));
				experiment.setTimeStart(rs.getString(4));
				experiment.setTimeEnd(rs.getString(5));
				experiment.setStageNumber(getExperimentStageNumber("test",username,rs.getLong(1)));
				
				allNumber=getExperimentAllNumber("idplExperiment",rs.getLong(1));
				experiment.setAllNumber(allNumber);
				completeNumber=getExperimentCompleteNumber("idplExperiment",rs.getLong(1));
				experiment.setCompleteNumber(completeNumber);
				if(allNumber!=0)
					percentage=completeNumber*100/allNumber;
				else
					percentage=0;
				experiment.setPercentage(percentage);
				if(rs.getString(6)==null)
					experiment.setSubmit("NO");
				else
					experiment.setSubmit(rs.getString(6));
				experiment.setDate(rs.getString(7));
				
				idplExperiment=getIdplExperiment(rs.getLong(1));

				if(idplExperiment.getState()!=null)
					experiment.setState(idplExperiment.getState());
				else
					experiment.setState("Not Submit");
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
	public int getExperimentStageNumber(String TableName,String username,long experimentId) throws Exception{
		int result=0;
		String sql="SELECT * FROM "+TableName+" WHERE username='"+username+"' AND experimentId="+experimentId;

		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			dbc=new DataBaseConnection(TableName);
			pstmt=dbc.getConnection().prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				result++;
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			throw new Exception("Get Stage Number Error!");
		}
		finally{
			dbc.close();
		}
		return result;
	}
	public int getExperimentAllNumber(String TableName,long experimentId) throws Exception{
		int result=0;
		String sql="SELECT * FROM "+TableName+" WHERE id="+experimentId;
//		System.out.println(sql);
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			dbc=new DataBaseConnection(TableName);
			pstmt=dbc.getConnection().prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt(3)!=0)
					result++;
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			throw new Exception("Get AllNumber Error!");
		}
		finally{
			dbc.close();
		}
		return result;
	}
	public int getExperimentCompleteNumber(String TableName,long experimentId) throws Exception{
		int result=0;
		String sql="SELECT * FROM "+TableName+" WHERE id="+experimentId;
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			dbc=new DataBaseConnection(TableName);
			pstmt=dbc.getConnection().prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt(3)!=0&&rs.getString(5).equals("Completed"))
					result++;
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			throw new Exception("Get CompleteNumber Error!");
		}
		finally{
			dbc.close();
		}
		return result;
	}
	public Experiment getIdplExperiment(long experimentId) throws Exception{
		Experiment experiment=new Experiment();
		String sql="SELECT * FROM idplExperiment WHERE id="+experimentId+" AND repeat_id=0";	
		System.out.println(sql);
		PreparedStatement pstmt=null;
		DataBaseConnection dbc=null;
		try{
			dbc=new DataBaseConnection("idplExperiment");
			pstmt=dbc.getConnection().prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			System.out.println("try!!");
			if(rs.next()){
				experiment.setExperimentId(rs.getLong(1));
				experiment.setState(rs.getString(5));
				experiment.setStartRunning(rs.getString(7));
				experiment.setCompletedTime(rs.getString(8));
				System.out.println(experimentId);
			}
			rs.close();
			pstmt.close();
		}catch(Exception e){
			throw new Exception("Query idplExperiment By Id Fail!");
		}
		finally{
			dbc.close();
		}
		return experiment;
	}
}
