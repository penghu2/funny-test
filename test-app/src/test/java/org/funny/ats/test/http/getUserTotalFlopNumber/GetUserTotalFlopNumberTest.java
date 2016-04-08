package org.funny.ats.test.http.getUserTotalFlopNumber;

import org.funytest.common.internal.FunyTestEngine;
import org.funytest.common.model.TestContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class GetUserTotalFlopNumberTest extends FunyTestEngine {

	@Test(dataProvider = "FunyTestDataProvider")
	public void test(TestContext context){
		super.run(context);
	}
	
	
	public boolean check(){
		return false;
	}
}
