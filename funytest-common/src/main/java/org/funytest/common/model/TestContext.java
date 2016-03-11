package org.funytest.common.model;

import org.funytest.common.internal.IFunyTestCase;

/**
 * 测试上下文类，存储测试的上下文数据
 * @author hupeng
 *
 */
public class TestContext {
	
	/* 测试实例 */
	public IFunyTestCase testInstance;
	
	private TestCase testcase;
	
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
}
