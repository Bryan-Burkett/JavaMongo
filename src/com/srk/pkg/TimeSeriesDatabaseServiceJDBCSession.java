package com.srk.pkg;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import com.srk.pkg.TimeSeriesDatabaseServiceData;
import com.mongodb.*;


public class TimeSeriesDatabaseServiceJDBCSession implements Serializable{
	private static final long serialVersionUID = 959609439786983912L;
	
	//public static final String customerTable = "customer_ts_data";
	//public static final String baseTable = "ts_data";
	public static final String URL = "mongodb://baratheon.lenexa.ibm.com:27017/sysmaster";
	public static String host = "baratheon.lenexa.ibm.com";
	public static int port = 9088;
	public static int mongoPort = 27017;
	public static String database = "sysmaster";
	public static String user = "informix";
	public static String password = "Ibm4pass";


	private MongoClient getConnection() throws Exception {
				//Service service = Services.getInstance().getAllServiceInfos().get(0);
				Properties prop = new Properties();
				
				//if(!service.getHost().contains("localhost")) {
				//	prop.put("DB_LOCALE", "en_US.utf8");
				//	prop.put("CLIENT_LOCALE", "en_US.utf8");
				//}
				
				MongoClient conn = null;
				try {
					MongoClientURI uri = new MongoClientURI(URL);
					conn = new MongoClient(uri);
				} catch(Exception ex) {
					System.out.println("Execption in creating a Connection to: " + "localhost");
					System.out.println("Mongo Error: " + ex.getMessage());
					ex.printStackTrace();
					throw ex;
				}
				return conn;
	}
	
	public void testConnection() throws Exception {
		MongoClient conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected");
		} finally {
			if(conn != null)
				conn.close();
		}
	}
	
	public void createCollection(String collectionName) throws Exception {
		//List<Object> returnList = new ArrayList<Object>();
		MongoClient conn = null;
		//List<TimeSeriesDatabaseServiceData> data = new ArrayList<TimeSeriesDatabaseServiceData>();
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			DB db = conn.getDB(database);
			db.getCollection(collectionName);
			 
		} catch (MongoException ex){
			System.out.println(ex.getMessage());
		}
		finally {
			if(conn != null)
				conn.close();
		}
	}
	
	public String updateData(String id, int value, String collectionName) throws Exception {
		//List<Object> returnList = new ArrayList<Object>();
		MongoClient conn = null;
		String sql = null;
		//List<TimeSeriesDatabaseServiceData> data = new ArrayList<TimeSeriesDatabaseServiceData>();
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			DB db = conn.getDB(database);
			DBCollection coll = db.getCollection(collectionName);
			BasicDBObject doc = new BasicDBObject();
			doc.put("value", value);
			BasicDBObject update = new BasicDBObject();
			update.put("$set", doc);
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", id);
			coll.update(searchQuery, update);

		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			if(conn != null)
				conn.close();
		}
		return sql;
	}
	
	public String insertData(TimeSeriesDatabaseServiceData data) throws Exception {

		MongoClient conn = null;
	
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			DB db = conn.getDB(database);
			DBCollection coll = db.getCollection("test1");
			BasicDBObject doc = new BasicDBObject();
			doc.put("id", data.id);
			doc.put("value", data.value);
			coll.insert(doc);
			
		} finally {
			if(conn != null)
				conn.close();
		}
		return "Inserted";
	}
	
	public String insertMultiple(List<TimeSeriesDatabaseServiceData> data, String collectionName) throws Exception {

		MongoClient conn = null;
	
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			DB db = conn.getDB(database);
			DBCollection coll = db.getCollection(collectionName);
			List<BasicDBObject> docs = new ArrayList<BasicDBObject>();
			
			for (TimeSeriesDatabaseServiceData obj: data){
				BasicDBObject doc = new BasicDBObject();
				doc.put("id", obj.id);
				doc.put("value", obj.value);
				docs.add(doc);
			}
			coll.insert(docs);
			
		} finally {
			if(conn != null)
				conn.close();
		}
		return "Inserted";
	}
	
	
	public List<String> removeData(String id, String collectionName) throws Exception {
		List<String> returnList = new ArrayList<String>();
		MongoClient conn = null;
		try {
			conn = getConnection();
			DB db = conn.getDB(database);
			DBCollection coll = db.getCollection(collectionName);
			
			BasicDBObject query = new BasicDBObject();
			query.put("id", id);
			coll.remove(query);
			
			
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return returnList;
	}

	public String dropCollection(String collectionName) throws Exception {
		//List<String> returnList = new ArrayList<String>();
		String sql = "Collection Dropped";
		MongoClient conn = null;
		try {
			conn = getConnection();
			DB db = conn.getDB(database);
			DBCollection coll = db.getCollection(collectionName);
			coll.drop();
			
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return sql;
	}
	
	public void findAll(String id, String col) throws Exception {
		MongoClient conn = null;
		try {
			conn = getConnection();
			DB db = conn.getDB(database);
			DBCollection coll = db.getCollection(col);
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", id);
		 
			DBCursor cursor = coll.find(searchQuery);
			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}
		} finally {
			if(conn != null)
				conn.close();
		}
		return;
	}
	
	public void findOne(String id, String col) throws Exception {
		MongoClient conn = null;
		try {
			conn = getConnection();
			DB db = conn.getDB(database);
			DBCollection coll = db.getCollection(col);
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", id);
		 
			DBObject doc = coll.findOne(searchQuery);
	
			System.out.println(doc);
		} finally {
			if(conn != null)
				conn.close();
		}	
		return;
		
	}
	
	public void listAllDevices(String col) throws Exception {
		MongoClient conn = null;
		try {
			conn = getConnection();
			DB db = conn.getDB(database);
			DBCollection coll = db.getCollection(col);
			DBCursor cursor = coll.find();
			while (cursor.hasNext()){
				System.out.println(cursor.next());
			}
			cursor.close();
		} finally {
			if(conn != null)
				conn.close();
		}
		return;
	}
}
