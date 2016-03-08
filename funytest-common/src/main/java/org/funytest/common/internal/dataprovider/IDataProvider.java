package org.funytest.common.internal.dataprovider;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * 数据驱动接口类
 * @author hupeng
 *
 */
public interface IDataProvider {
	
	public Iterator<?> getData(Method m, Object instance);
}
