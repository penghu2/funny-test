package org.funytest.common.internal.runner;

import java.util.List;

import org.funytest.common.model.TestCase;
import org.funytest.common.model.TestContext;
import org.funytest.common.model.teststep.ITestStep;

/**
 * 测试模板类
 * @author hupeng
 *
 */
public abstract class AbstractTestTemplate {
	
	/**
	 * run 驱动方法
	 * @param context
	 */
	public void run(TestContext context){
		
		TestCase testcase = context.getTestcase();
		
		List<TestCase.TestAlign> aligns = testcase.getTestAligns();
		
		for (TestCase.TestAlign align : aligns) {

			this.runSteps(align.getSteps());
		}
	}
	
	/**
	 * 采用职责链的设计模式
	 * @param steps
	 */
	protected void runSteps(List<ITestStep> steps){
		
		try {
			
			this.clean(steps);
			
			this.init(steps);
			
			this.execute(steps);
			
			this.check(steps);
			
		} catch (Exception e) {
			
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
	public abstract void init(List<ITestStep> steps);
	
	/**
	 * 执行接口调用和结果检查
	 * @param context
	 */
	public abstract void execute(List<ITestStep> steps);
	
	/**
	 * check校验，常用于DB校验
	 * @param context
	 */
	public abstract void check(List<ITestStep> steps);
	
	/**
	 * 清理数据，这里面的清理包含很多，包含数据库的清理，二期考虑做其它的事情
	 * @param context
	 */
	public abstract void clean(List<ITestStep> steps);
}
