package org.funny.ats.test.service.userservice;

import org.testng.annotations.Test;
import org.funny.ats.service.UserService;
import org.funytest.common.internal.FunyTestEngine;
import org.funytest.common.model.TestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class UserServiceTest extends FunyTestEngine {
	
	@Autowired
	private UserService userService;
	
	@Test(dataProvider = "FunyTestDataProvider")
	public void test(String caseId, TestContext context){
		String caseDesc = context.getTestcase().getDesc();
		
		Object bean = this.getBean("userDaoImpl");
		Object dataSource = this.getBean("dataSource");
		
		System.out.println(caseId);
		
		super.run(context);
	}
}
