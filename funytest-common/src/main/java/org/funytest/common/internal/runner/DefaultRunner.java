package org.funytest.common.internal.runner;

import java.util.Map;

import org.funytest.common.internal.ITestRunner;
import org.funytest.common.internal.TestContextHolder;
import org.funytest.common.internal.handler.ITestStepHandlerFactory;
import org.funytest.common.internal.handler.TestStepHandlerFactory;
import org.funytest.common.model.TestContext;

/**
 * runner负责TestContext的具体实施,并且作为template模板的容器，负责模板的真正意义上的调度
 * 
 * @author xiuzhu.hp
 */
public class DefaultRunner implements ITestRunner {
	
	public Map<String, String> testStepHandlerMap;

	private AbstractTestTemplate template;
	
	public void run(TestContext context) throws Exception {
		
		try {
			/* 初始化上下文信息 */
			TestContextHolder.setTestContext(context);
			/* 开始执行用例 */
			getTemplate().run(context); 
		} finally {
			/* 清空上下文信息 */
			TestContextHolder.clean();
		}
	}

	/**
	 * 获取测试模板
	 * @return  获取测试模板
	 */
	public AbstractTestTemplate getTemplate(){
		
		/* 默认的测试模板 */
		if (template == null) {
			template = new DefaultTemplate();
			template.setStepHandlerFactory(new TestStepHandlerFactory());
		}
		
		return template;
	}

	@Override
	public void initStepHandlerFactory(ITestStepHandlerFactory handlerFactory) {
		
		this.template.setStepHandlerFactory(handlerFactory);
	}
	
	public void setTemplate(AbstractTestTemplate template){
		this.template = template;
	}
}
