package org.funytest.common.internal.finder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.funytest.common.internal.IAnnotationMethodFinder;
import org.funytest.common.internal.IFunyTestCase;
import org.funytest.common.internal.method.IFunnyTestMethod;
import org.funytest.common.internal.method.IFunnyTestMethodFactory;

/**
 * 
 * @author hupeng
 */
public class FunnyTestAnnotationFinder implements IAnnotationMethodFinder{

	public List<Method> findAnnotationedMethod(Class<? extends IFunyTestCase> cls,
			Class<? extends Annotation> annotationCls) {
		
		return null;
	}

	public Map<Class<? extends Annotation>, IFunnyTestMethod> findFunnyTestMethodMap(Class<? extends IFunyTestCase> cls,
			List<Class<? extends Annotation>> annotationClsList, IFunnyTestMethodFactory methodFactory) {
		
		return null;
	}
	
}
