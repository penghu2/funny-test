package org.funny.ats.service.impl;

import org.funny.ats.dao.UserDao;
import org.funny.ats.model.User;
import org.funny.ats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public User queryUserByName(String name) {
		
		try {
			return userDao.get(name);
		} catch (Exception e) {
			
		}
		
		return null;
	}

	public void addUser(User user) {
		try {
			userDao.insert(user);
		} catch (Exception e) {
			
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
}
