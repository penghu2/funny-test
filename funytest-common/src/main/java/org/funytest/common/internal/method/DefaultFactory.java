package org.funytest.common.internal.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * method构造工厂类
 * @author hupeng
 */
public class DefaultFactory implements IFunnyTestMethodFactory {
	
	/**
	 * 
	 */
	public IFunnyTestMethod getTestMethod(Method m, Class<? extends Annotation> cls) {
		
		return null;
	}

}
