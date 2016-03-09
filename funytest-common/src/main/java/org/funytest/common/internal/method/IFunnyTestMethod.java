package org.funytest.common.internal.method;

import java.lang.reflect.Method;

public interface IFunnyTestMethod {

	public int order = 0;
	
	public void init(Method m);
	
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
