package org.funny.ats.test.http.getUserTotalFlopNumber;

import java.util.Map;

import org.funytest.common.exception.CheckFailException;
import org.funytest.common.internal.FunyTestEngine;
import org.funytest.common.model.TestContext;
import org.funytest.common.utils.JsonPUtil;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import junit.framework.Assert;

@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class GetUserTotalFlopNumberTest extends FunyTestEngine {
	
	/**
	 * 测试步骤
	 * @param context
	 */
	@Test(dataProvider = "FunyTestDataProvider")
	public void test(String caseId, TestContext context){
		super.run(context);
	}
	
	/**
	 * 
	 * @param responseStr  
	 * @param expect
	 * @throws CheckFailException
	 */
	public void check(String responseStr, Integer expectFlopNumber, 
			String expectRetCode, Boolean expectSuccess) {
				
		Map resMap = JsonPUtil.praseToMap(responseStr);
		String actualRetCode = (String) resMap.get("returnCode");
		Boolean actualSuccess = (Boolean) resMap.get("success");
		Map data = (Map) resMap.get("data");
		Integer flopNumber = (Integer) data.get("leftFlopNumber");
		
		/* 比较剩余翻牌次数 */
		Assert.assertEquals(expectFlopNumber, flopNumber);
		Assert.assertEquals(expectRetCode, actualRetCode);
		Assert.assertEquals(expectSuccess, actualSuccess);
	}
}
