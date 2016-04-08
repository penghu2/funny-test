package org.funytest.common.model.teststep;

import java.util.List;

import org.funytest.common.internal.handler.ITestStepHandler;
import org.funytest.common.model.TestExecuteMethod;
import org.funytest.common.utils.Constant;

/**
 * method执行步骤
 * @author hupeng
 *
 */
public class MethodTestStep implements ITestStep {
	
	private List<TestExecuteMethod> methods;
	
	@Override
	public String getType() {
		
		return Constant.STEP_TYPE_METHOD;
	}

	/**
	 * 代理设计模式，将handler交给method，代理执行
	 */
	@Override
	public void execute(ITestStepHandler handler) throws Exception {
		handler.handle(this);
	}

	public List<TestExecuteMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<TestExecuteMethod> methods) {
		this.methods = methods;
	}
}
