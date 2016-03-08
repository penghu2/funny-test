package org.funytest.common.model;

/**
 * 测试上下文类，承载着整个测试的上下文信息
 * @author hupeng
 * 
 */
public class TestContext {
	
	public enum Status{
		parsing,
		executing,
		end;
	}
}
