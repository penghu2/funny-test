package org.funytest.common.internal.dataprovider;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.dom4j.DocumentException;
import org.funytest.common.internal.IFunyTestCase;
import org.funytest.common.model.TestContext;

/**
 * 数据驱动接口类
 * @author hupeng
 *
 */
public interface IDataProvider {
	
	public Iterator<?> getData(Method m, Class<? extends IFunyTestCase> cls, Object instance) throws DocumentException;
}
