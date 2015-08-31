package com.model;

import java.util.List;

public interface Member {
	
	public Long getId();
	public void setId(Long id);
	
	public String getEmail();
	public void SetEmail(String email);
	
	public List getFollowers();
	public void setFollowers(List followers);
	
	public List getFollowing();
	public void setFollowing(List following);
	
}
