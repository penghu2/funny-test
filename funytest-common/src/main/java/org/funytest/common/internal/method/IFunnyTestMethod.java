package org.funytest.common.internal.method;

import java.lang.reflect.Method;

import org.funytest.common.model.TestContext;

public interface IFunnyTestMethod {

	public int order = 0;
	
	public void init(Method m, Class<?> cls, Object instance);
	
	/**
	 * 执行方法
	 */
	public void execute(TestContext context);
	
	/**
	 * 方法名称
	 * @return 方法名称
	 */
	public String getName();
}
