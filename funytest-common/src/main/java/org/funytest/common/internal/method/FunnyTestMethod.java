package org.funytest.common.internal.method;

public interface FunnyTestMethod {

	public int order = 0;
	
	/**
	 * 执行方法
	 */
	public void execute();
	
	/**
	 * 方法名称
	 * @return 方法名称
	 */
	public String getName();
}
