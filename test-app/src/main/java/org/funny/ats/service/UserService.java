package org.funny.ats.service;

import org.funny.ats.model.User;

public interface UserService {
	
	public User queryUserByName(String name);
	
	
	public void addUser(User user);
}
