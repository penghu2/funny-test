package org.funytest.common.internal.runner;

import java.util.Map;

import org.funytest.common.internal.ITestRunner;
import org.funytest.common.model.TestContext;

public class DefaultRunner implements ITestRunner {
	
	public Map<String, String> testStepHandlerMap;

	public void run(TestContext context) {
		
		/* 开始执行用例 */
		runWithTemplate(getTemplate(), context);
	}
	
	/**
	 * 使用模板进行奔跑~
	 */
	public void runWithTemplate(AbstractTestTemplate template, TestContext context) {
		
		try {
			template.clean(context);
			
			template.init(context);
			
			template.execute(context);
			
			template.check(context);
			
			template.clean(context);
			
		} catch (Exception e){
			
		} finally {
			
		}
	}

	public AbstractTestTemplate getTemplate(){
		return null;
	}
}
