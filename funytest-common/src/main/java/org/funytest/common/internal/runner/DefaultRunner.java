package org.funytest.common.internal.runner;

import java.util.Map;

import org.funytest.common.internal.ITestRunner;
import org.funytest.common.model.TestContext;

/**
 * runner负责TestContext的具体实施,并且作为template模板的容器，负责模板的真正意义上的调度
 * 
 * @author xiuzhu.hp
 */
public class DefaultRunner implements ITestRunner {
	
	public Map<String, String> testStepHandlerMap;

	private AbstractTestTemplate template;
	
	public void run(TestContext context) {
		
		/* 开始执行用例 */
		getTemplate().run(context); 
	}
		
	public void setTemplate(AbstractTestTemplate template){
		this.template = template;
	}
	
	public AbstractTestTemplate getTemplate(){
		
		if (template == null) return null;
		
		return template;
	}
}
