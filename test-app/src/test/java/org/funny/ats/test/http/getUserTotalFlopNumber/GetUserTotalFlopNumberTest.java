package org.funny.ats.test.http.getUserTotalFlopNumber;

import java.util.Map;

import org.funytest.common.exception.CheckFailException;
import org.funytest.common.internal.FunyTestEngine;
import org.funytest.common.model.TestContext;
import org.funytest.common.utils.JsonPUtil;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class GetUserTotalFlopNumberTest extends FunyTestEngine {
	
	/**
	 * 测试步骤
	 * @param context
	 */
	@Test(dataProvider = "FunyTestDataProvider")
	public void test(TestContext context){
		super.run(context);
	}
	
	/**
	 * 
	 * @param responseStr  
	 * @param expect
	 * @throws CheckFailException
	 */
	public void check(String responseStr, int expectFlopNumber, 
			String expectRetCode, Boolean expectSuccess) {
				
		Map resMap = JsonPUtil.praseToMap(responseStr);
		String actualRetCode = (String) resMap.get("returnCode");
		Boolean actualSuccess = (Boolean) resMap.get("success");
		Map data = (Map) resMap.get("data");
		int flopNumber = (Integer) data.get("leftFlopNumber");
		
		/* 比较剩余翻牌次数 */
		Assert.assertEquals(expectFlopNumber, flopNumber);
		Assert.assertEquals(expectRetCode, actualRetCode);
		Assert.assertEquals(expectSuccess, actualSuccess);
		//Assert.assertEquals(2, flopNumber, "翻牌次数校验失败咯");
	}
}
