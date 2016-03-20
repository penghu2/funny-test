package org.funytest.common.internal.runner;

import java.util.LinkedList;
import java.util.List;

import org.funytest.common.model.TestContext;
import org.funytest.common.model.teststep.ITestStep;

/**
 * 默认的执行器
 * @author xiuzhu.hp
 *
 */
public class DefaultTemplate extends AbstractTestTemplate {
	
	private static final int INIT = 1;
	private static final int EXECUTE = 2;
	private static final int CHECK = 3;
	private static final int CLEAN = 4;
	
	/**
	 * 初始化，取其中的初始化步骤进行执行
	 */
	@Override
	public void init(List<ITestStep> steps) {
		
		List<ITestStep> initSteps = new LinkedList<>();
		
		
	}
	
	@Override
	public void execute(List<ITestStep> steps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void check(List<ITestStep> steps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clean(List<ITestStep> steps) {
		// TODO Auto-generated method stub
		
	}
}
