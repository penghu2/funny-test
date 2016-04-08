package org.funytest.common.model.teststep;

import org.funytest.common.exception.TestStepException;
import org.funytest.common.internal.handler.ITestStepHandler;

/**
 * 测试步骤
 * @author Administrator
 *
 */
public interface ITestStep {
	
	/**
	 * 获取类型
	 * @return
	 */
	public String getType();
	
	/**
	 * 执行方法
	 * @throws TestStepException
	 */
	public void execute(ITestStepHandler handler) throws Exception;
}
