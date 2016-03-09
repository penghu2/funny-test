package org.funytest.common.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.funytest.common.internal.method.IFunnyTestMethod;
import org.funytest.common.internal.method.IFunnyTestMethodFactory;

public interface IAnnotationMethodFinder {
	
	/**
	 * 获取特定注解的methd方法
	 */
	public List<Method> findAnnotationedMethod(Class<? extends IFunyTestCase> cls,
			Class<? extends Annotation> annotationCls);
	
	
	public Map<Class<? extends Annotation>, IFunnyTestMethod> findFunnyTestMethodMap(Class<? extends IFunyTestCase> cls,
			List<Class<? extends Annotation>> annotationClsList, IFunnyTestMethodFactory methodFactory);

}
