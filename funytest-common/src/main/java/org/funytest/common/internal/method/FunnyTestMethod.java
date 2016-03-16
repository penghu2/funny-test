package org.funytest.common.internal.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.funytest.common.model.TestContext;

public class FunnyTestMethod implements IFunnyTestMethod {

	private Method method;

	private Class<?> cls;

	private Object instance;

	@Override
	public void execute(TestContext context) {
		try {
			if (this.method.getParameterTypes().length == 0) {
				this.method.setAccessible(true);
				this.method.invoke(instance, new Object[] {});
				return;
			}

			if (this.method.getParameterTypes()[0].equals(TestContext.class)) {
				this.method.setAccessible(true);
				this.method.invoke(instance, new Object[] { context });
				return;
			}

		} catch (IllegalAccessException e) {

		} catch (IllegalArgumentException e) {

		} catch (InvocationTargetException e) {

		}
	}

	@Override
	public String getName() {

		return cls.getName() + "." + method.getName() + "()";
	}

	@Override
	public void init(Method m, Class<?> cls, Object instance) {
		this.method = m;
		this.cls = cls;
		this.instance = instance;
	}

	/**
	 * 
	 * @return
	 */
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
}
