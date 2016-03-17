package org.funny.ats.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
	protected SqlSessionTemplate sqlSessionTemplate; 
	
	
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {  
       this.sqlSessionTemplate = sqlSessionTemplate;  
    }  
}
