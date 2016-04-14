package org.funytest.common.internal.runner;

import java.util.List;

import org.funytest.common.internal.handler.ITestStepHandlerFactory;
import org.funytest.common.model.TestCase;
import org.funytest.common.model.TestContext;
import org.funytest.common.model.teststep.ITestStep;
import org.funytest.common.utils.LogUtils;

/**
 * 测试模板类
 * @author hupeng
 *
 */
public abstract class AbstractTestTemplate {
	
	/**
	 * 测试handler工厂类
	 */
	protected ITestStepHandlerFactory stepHandlerFactory;
	
	/**
	 * run 驱动方法
	 * @param context
	 * @throws Exception 
	 */
	public void run(TestContext context) throws Exception{
		LogUtils.printFlag();
		LogUtils.info("begin test context : [" + context.toString() + "]");
		/**
		 *  设置testStep工厂类
		 */
		this.setStepHandlerFactory(context.testInstance.
				getIConfiguration().getTestStepHandlerFactory());
		
		TestCase testcase = context.getTestcase();
		
		List<TestCase.TestAlign> aligns = testcase.getTestAligns();
		
		for (TestCase.TestAlign align : aligns) {
			LogUtils.info("begin test align : " + align.getOrder());
			
			this.runSteps(align.getSteps());
			
			LogUtils.info("test align success : " + align.getOrder());
		}
		
		LogUtils.info("test context test success : [" + context.toString() + "]");
		LogUtils.printFlag();
	}
	
	/**
	 * 采用职责链的设计模式
	 * @param steps
	 * @throws Exception 
	 */
	protected void runSteps(List<ITestStep> steps) throws Exception{
		
		try {
			
			this.clean(steps);
			
			this.init(steps);
			
			this.execute(steps);
			
			this.check(steps);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				this.clean(steps);
			} catch (Exception e) {
				
			}
		}
	}
		
	/**
	 * 初始化
	 * @param context
	 */
	public abstract void init(List<ITestStep> steps) throws Exception;
	
	/**
	 * 执行接口调用和结果检查
	 * @param context
	 */
	public abstract void execute(List<ITestStep> steps) throws Exception;
	
	/**
	 * check校验，常用于DB校验
	 * @param context
	 */
	public abstract void check(List<ITestStep> steps) throws Exception;
	
	/**
	 * 清理数据，这里面的清理包含很多，包含数据库的清理，二期考虑做其它的事情
	 * @param context
	 */
	public abstract void clean(List<ITestStep> steps) throws Exception;
	
	
	/*=========================[getter setter]==========================*/
	public ITestStepHandlerFactory getStepHandlerFactory() {
		return stepHandlerFactory;
	}

	public void setStepHandlerFactory(ITestStepHandlerFactory stepHandlerFactory) {
		this.stepHandlerFactory = stepHandlerFactory;
	}	
}
