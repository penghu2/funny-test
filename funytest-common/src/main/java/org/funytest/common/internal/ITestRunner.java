package org.funytest.common.internal;

import org.funytest.common.internal.handler.ITestStepHandlerFactory;
import org.funytest.common.model.TestContext;

/**
 * 测试模板
 * @author xiuzhu.hp
 */
public interface ITestRunner {
	
	public void run(TestContext context) throws Exception;
	
	/**
	 * 初始化模板
	 */
	public void initStepHandlerFactory(ITestStepHandlerFactory handlerFactory);
}
