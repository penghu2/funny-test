package org.funny.ats.dao.impl;

import org.funny.ats.dao.UserDao;
import org.funny.ats.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	public User get(String username) {
		
		return sqlSessionTemplate.selectOne("MS-USER-SELECT-BY-USERNAME", username);
	}

	public int insert(User user) {
		
		return sqlSessionTemplate.insert("MS-USER-INSERT-ONE-ACCOUNT", user);
	}
	
}
