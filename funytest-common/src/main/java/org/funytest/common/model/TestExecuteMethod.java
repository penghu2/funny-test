package org.funytest.common.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.funytest.common.exception.TestStepException;

public class TestExecuteMethod {
	
	private Method method;

	private Class<?> cls;

	private Object instance;
	
	private Object[] args;
	
	private Object expectObj;
	
	public void execute() throws TestStepException {
		try {
			if (this.method.getParameterTypes().length != args.length){
				throw new TestStepException("args's length is not as expected");
			}

			if (instance != null) {
				this.method.setAccessible(true);
				this.method.invoke(instance, args);
			} else {
				this.method.invoke(null, args);
			}

		} catch (IllegalAccessException e) {

		} catch (IllegalArgumentException e) {

		} catch (InvocationTargetException e) {

		}
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Object getExpectObj() {
		return expectObj;
	}

	public void setExpectObj(Object expectObj) {
		this.expectObj = expectObj;
	}
}
