package org.funny.ats.test.service.userservice;

import org.testng.annotations.Test;
import org.funytest.common.internal.FunyTestEngine;
import org.funytest.common.model.TestContext;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class UserServiceTest extends FunyTestEngine {
	
	@Test(dataProvider = "FunyTestDataProvider")
	public void test(TestContext t){
		String caseId = t.getTestcase().getId();
		String caseDesc = t.getTestcase().getDesc();
		
		System.out.println(caseId);
	}
}
