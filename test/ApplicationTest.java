import static org.junit.Assert.*;

import org.junit.Test;

import utils.Constants;

import com.dao.MemberDAO;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

	MemberDAO memberDao = new MemberDAO();
	String randomHash = String.valueOf(System.currentTimeMillis());
	
    @Test
    public void validateUserEntryTest() {
    	assertFalse(memberDao.validateUserEntry("email", "password"));
    	assertFalse(memberDao.validateUserEntry(null, null));
    	assertFalse(memberDao.validateUserEntry(null, ""));
    	assertFalse(memberDao.validateUserEntry(" ", ""));
    	assertTrue(memberDao.validateUserEntry("email@email.com", "password"));
    }
    
    @Test
    public void memberRegistrationTest() {
    	
    	assertEquals(Constants.FAILURE, memberDao.memberRegistration("email.com", "password").retCode);
    	assertEquals(Constants.SUCCESS, memberDao.memberRegistration(randomHash + "@email.com", "password").retCode);
    	assertEquals(Constants.SUCCESS, memberDao.memberRegistration(randomHash + "@email.com", "password").retCode);
    	assertEquals(Constants.SUCCESS, memberDao.memberRegistration(randomHash + "@email.com", "password").retCode);
    	assertEquals(Constants.SUCCESS, memberDao.memberRegistration(randomHash + "@email.com", "password").retCode);
    }
    
    @Test
    public void followUserTest() {
    	assertEquals(Constants.SUCCESS, memberDao.followMember(1, 2).retCode);
    }
    
    @Test
    public void verifyFollowingTest() {
    	assertEquals(Constants.SUCCESS, memberDao.verifyFollowing(1, 2).retCode);
    }        
    
    @Test
    public void verifyFollowersTest() {
    	assertEquals(Constants.SUCCESS, memberDao.verifyFollowers(2, 1).retCode);
    }

}
