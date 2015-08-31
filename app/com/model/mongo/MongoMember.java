package com.model.mongo;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoMember extends BasicDBObject implements com.model.Member, DBObject {
	
	public MongoMember(BasicDBObject doc) {
		super(doc);
	}
	
	public MongoMember(DBObject doc) {
		super(doc.toMap());
	}	

	public Long getId() {
		// TODO Auto-generated method stub
		try {
			return (Long) get("id");
		} catch (Exception e) {
	 		return null;			
		}
	}

	public void setId(Long id) {
		// TODO Auto-generated method stub
		put("id", id);
		
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		try {
			return getString("email");			
		} catch (Exception e) {
			return null;			
		}
	}

	public void SetEmail(String email) {
		// TODO Auto-generated method stub
		put("email", email);
	}

	public List getFollowers() {
		// TODO Auto-generated method stub
		try {
			return (List) get("followers");
		} catch (Exception e) {			
			return null;
		}
	}

	public void setFollowers(List followers) {
		// TODO Auto-generated method stub
		put("followers", followers);
	}

	public List getFollowing() {
		// TODO Auto-generated method stub
		try {
			return (List) get("following");
		} catch (Exception e) {			
			return null;
		}
	}

	public void setFollowing(List following) {
		// TODO Auto-generated method stub
		put("following", following);
	}

}
