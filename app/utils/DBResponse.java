package utils;

import com.mongodb.DBObject;

public class DBResponse {
	
	public int retCode;
	public DBObject result;
	
	public DBResponse(int retCode) {
		this.retCode = retCode;
	}
	
	public DBResponse(int retCode, DBObject result) {
		this.retCode = retCode;
		this.result = result;
	}

}
