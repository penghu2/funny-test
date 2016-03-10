package org.funytest.common.internal.dataprovider;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.funytest.common.internal.IFunyTestCase;
import org.funytest.common.model.TestContext;

/**
 * 数据驱动接口类
 * @author hupeng
 *
 */
public interface IDataProvider {
	
	public Iterator<TestContext> getData(Method m, Class<? extends IFunyTestCase> cls, Object instance);
}
