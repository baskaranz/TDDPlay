package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import play.Logger;
import play.data.validation.Constraints.EmailValidator;
import utils.Constants;
import utils.DBResponse;
import utils.MongoManager;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MemberDAO {
	
	public DBCollection memberCollection = null;
	public DBCollection counterCollection = null;
	
	public MemberDAO() {
		memberCollection = MongoManager.getInstance().getDB().getCollection("members");
		counterCollection = MongoManager.getInstance().getDB().getCollection("counter");			
	}

	public boolean validateUserEntry(String email, String password) {
		// TODO Auto-generated method stub
		EmailValidator validator = new EmailValidator();
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			return false;
		} else {
			return validator.isValid(email) == false ? false : true;
		}	
	}

	public DBResponse memberRegistration(String email, String password) {
		// TODO Auto-generated method stub
		DBResponse dbr = new DBResponse(Constants.FAILURE);
		if(validateUserEntry(email, password) == false) return dbr;
		DBObject member = new BasicDBObject();
		List followers = new ArrayList();
		try {
			member.put("email", email);
			//TODO need to hash the password
			member.put("password", password);
			member.put("followers", followers);
			member.put("following", followers);
			Long memberId = getNextMemberId("members");
			member.put("id", memberId);
			memberCollection.insert(member);
			dbr = new DBResponse(Constants.SUCCESS);
		} catch (Exception e) {
			Logger.info("exception while creating member ", e);
		}
		return dbr;
	}
	
    private Long getNextMemberId(String type) {
        DBObject counter = counterCollection.findAndModify(new BasicDBObject("collection", type),
                new BasicDBObject("$inc", new BasicDBObject("c", 1)) );
        return ((Number) counter.get("c")).longValue();
    }

	public DBResponse followMember(long memberId, long followerId) {
		// TODO Auto-generated method stub
		DBResponse dbr = new DBResponse(Constants.FAILURE);
		try {
			DBObject member = new BasicDBObject("id" , memberId);
			DBObject followers = new BasicDBObject("$addToSet",  new BasicDBObject("following", followerId));
			memberCollection.update(member, followers);
			dbr = followingMember(followerId, memberId);
		} catch (Exception e) {
			Logger.info("exception while updating member follower", e);
		}
		return dbr;
	}
	
	public DBResponse followingMember(long memberId, long followingId) {
		// TODO Auto-generated method stub
		DBResponse dbr = new DBResponse(Constants.FAILURE);
		try {
			DBObject member = new BasicDBObject("id" , memberId);
			DBObject followers = new BasicDBObject("$addToSet",  new BasicDBObject("followers", followingId));
			memberCollection.update(member, followers);
			dbr = new DBResponse(Constants.SUCCESS);
		} catch (Exception e) {
			Logger.info("exception while updating member following", e);
		}
		return dbr;
	}

	public DBResponse verifyFollowers(long memberId, long followerId) {
		// TODO Auto-generated method stub
		DBResponse dbr = new DBResponse(Constants.FAILURE);
		try {
			//db.inventory.find( { qty: { $in: [ 5, 15 ] } } )
			DBObject member = new BasicDBObject("id" , memberId);
			DBObject result = memberCollection.findOne(member);
			List followersList = (List) result.get("followers");
			Logger.info("verifyFollowers result : " + result) ;
			if (followersList.contains(followerId)) {
				dbr = new DBResponse(Constants.SUCCESS);
			}
		} catch (Exception e) {
			Logger.info("exception while verifying followers", e);
		}
		return dbr;
	}
	
	public DBResponse verifyFollowing(long memberId, long followingId) {
		// TODO Auto-generated method stub
		DBResponse dbr = new DBResponse(Constants.FAILURE);
		Logger.info("id : " + memberId);
		Logger.info("followingId : " + followingId);
		try {
			DBObject member = new BasicDBObject("id" , memberId);
			DBObject result = memberCollection.findOne(member);
			Logger.info("verifyFollowing result : " + result) ;
			List followingList = (List) result.get("following");
			if (followingList.contains(followingId)) {
				dbr = new DBResponse(Constants.SUCCESS);
			}
		} catch (Exception e) {
			Logger.info("exception while verifying following", e);
		}
		return dbr;
	}	

}
