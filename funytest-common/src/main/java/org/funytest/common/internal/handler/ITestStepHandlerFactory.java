package org.funytest.common.internal.handler;

public interface ITestStepHandlerFactory {
	
	/**
	 * 获取handler
	 * @param stepType  测试步骤类型
	 * @param exephase     执行阶段
	 * @return
	 */
	public ITestStepHandler getHandler(String stepType, ExecutePhase exephase);

}
