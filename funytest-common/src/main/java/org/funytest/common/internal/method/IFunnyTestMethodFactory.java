package org.funytest.common.internal.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 测试method工厂类
 * @author hupeng
 */
public interface IFunnyTestMethodFactory {
	
	/**
	 * 获取指定方法
	 * @param m
	 * @param cls
	 * @return
	 */
	public IFunnyTestMethod getTestMethod(Method m, Class<? extends Annotation> cls);
}
