package utils;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoManager {
	
	private static MongoManager _instance = null;
	private static DB db = null;
	private static MongoClient mongo = null;
	
	private int port = 27017;
	private String host = "localhost";
	private String dbName = "trackinvest";
	
	private MongoManager() {
		try {
			mongo = new MongoClient(host, port);
			db = mongo.getDB(dbName);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MongoManager getInstance() {
		if(_instance == null) {
			synchronized (MongoManager.class) {
				_instance = new MongoManager();
			}
		}
		return _instance;
	}
	
	
	public DB getDB() {
		return db;
	}
	
	

}
