package org.funny.ats.dao;

import org.funny.ats.model.User;

public interface UserDao {
	
	public User get(String username);
	
	public int insert(User user);
}
