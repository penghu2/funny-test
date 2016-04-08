package org.funytest.common.model;

import java.util.Map;

import org.funytest.common.internal.IFunyTestCase;

/**
 * 测试上下文类，存储测试的上下文数据
 * @author hupeng
 *
 */
public class TestContext {
	
	/* 测试实例 */
	public IFunyTestCase testInstance;
	
	/* 测试case信息 */
	private TestCase testcase;
	
	/* 用于存储上下文信息，处理过程中的任何参数均可以存储在这里 */
	private Map<String, Object> context;
	
	public TestContext(){
		
	}
	
	public TestContext(IFunyTestCase testInstance){
		this.testInstance = testInstance;
	}

	public IFunyTestCase getTestInstance() {
		return testInstance;
	}

	public void setTestInstance(IFunyTestCase testInstance) {
		this.testInstance = testInstance;
	}

	public TestCase getTestcase() {
		return testcase;
	}

	public void setTestcase(TestCase testcase) {
		this.testcase = testcase;
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public void setContext(Map<String, Object> context) {
		this.context = context;
	}
}
