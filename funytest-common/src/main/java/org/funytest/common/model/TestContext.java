package org.funytest.common.model;

import java.util.List;

import org.funytest.common.internal.IFunyTestCase;

/**
 * 测试上下文类，存储测试的上下文数据
 * @author hupeng
 *
 */
public class TestContext {
	
	/* 测试实例 */
	public IFunyTestCase testInstance;
	
	private List<TestCase> testcases;
	
	
}
