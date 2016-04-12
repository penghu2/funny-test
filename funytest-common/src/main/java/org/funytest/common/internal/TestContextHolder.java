package org.funytest.common.internal;

import org.funytest.common.model.TestContext;

/**
 * 测试上下文容器，用于存储过程中的上下文信息，在测试结束后清除
 * @author xiuzhu
 *
 */
public class TestContextHolder {
	
	/* testContext上下文时间 */
	public static ThreadLocal<TestContext> testContext = new ThreadLocal<TestContext>();
	
	public static void setTestContext(TestContext context){
		testContext.set(context);
	}
	
	public static TestContext getTestContext(){
		return testContext.get();
	}
	
	public static TestContext get(){
		return testContext.get();
	}
	
	public static void clean(){
		testContext.set(null);
	}
}
