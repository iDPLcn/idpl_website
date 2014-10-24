package com.bed.dao;
import java.util.*;
public interface RecordDAO {
	public long insert(Record record,String TableName) throws Exception;
	public void update(Record record,String TableName) throws Exception;
	public void delete(Record record,String TableName) throws Exception;
	public Record queryById(long id,String TableName,String username,long experimentId) throws Exception;
	public List<Record> queryAll(String TableName,String username,long experimentId) throws Exception;
}
