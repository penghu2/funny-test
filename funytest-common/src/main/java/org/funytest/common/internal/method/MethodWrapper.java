package org.funytest.common.internal.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xiuzhu
 */
public class MethodWrapper {
	
	private Object instance;
	
	private Class<?> cls;
	
	private Method method;
	
	private Object[] args;
	
	public Object invoke() throws InvocationTargetException{
	
		try {
			
			if (!method.isAccessible()) method.setAccessible(true);
			
			return this.method.invoke(instance, args);
			
		} catch (IllegalAccessException | IllegalArgumentException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}
