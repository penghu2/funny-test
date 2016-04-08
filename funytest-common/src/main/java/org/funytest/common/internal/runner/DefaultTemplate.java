package org.funytest.common.internal.runner;

import java.util.List;

import org.funytest.common.exception.TestStepException;
import org.funytest.common.internal.handler.ExecutePhase;
import org.funytest.common.internal.handler.ITestStepHandler;
import org.funytest.common.model.teststep.ITestStep;

/**
 * 默认的执行器
 * @author xiuzhu.hp
 *
 */
public class DefaultTemplate extends AbstractTestTemplate {
	
	/**
	 * 初始化，取其中的初始化步骤进行执行
	 */
	@Override
	public void init(List<ITestStep> steps) throws Exception {
		handle(steps, ExecutePhase.INIT);
	}
	
	@Override
	public void execute(List<ITestStep> steps) throws Exception {
		handle(steps, ExecutePhase.EXECUTE);
	}

	@Override
	public void check(List<ITestStep> steps) throws Exception {
		handle(steps, ExecutePhase.CHECK);
	}

	/**
	 * 先执行clean动作
	 * @throws TestStepException 
	 */
	@Override
	public void clean(List<ITestStep> steps) throws Exception {
		handle(steps, ExecutePhase.CLEAN);
	}
	
	/**
	 * 执行方法
	 * @param steps  测试step
	 * @param phase  执行阶段
	 * @throws Exception 
	 */
	protected void handle(List<ITestStep> steps, ExecutePhase phase) throws Exception {
		for (ITestStep step : steps) {
			String stepType = step.getType();
			ITestStepHandler handler = this.stepHandlerFactory.getHandler(stepType, phase);
			
			if (handler != null) {
				step.execute(handler);
			}
		}
	}
}
