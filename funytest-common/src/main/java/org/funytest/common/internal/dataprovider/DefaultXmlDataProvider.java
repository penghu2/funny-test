package org.funytest.common.internal.dataprovider;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.funytest.common.internal.IFunyTestCase;
import org.funytest.common.model.TestContext;

/**
 * 默认的xml解析datarpovider
 * @author hupeng
 * @qq 623158938
 */
public class DefaultXmlDataProvider implements IDataProvider, Iterator<TestContext> {

	public Iterator<TestContext> getData(Method m, Class<? extends IFunyTestCase> cls, Object instance) {
		
		/* 获取配置文件路径 */
		String configFilePath = getConfigFilePath(m, cls);
		
		/* 解析xml配置文件 */
		parseXml(configFilePath);
		
		return this;
	}
	
	
	public boolean hasNext() {
		
		return false;
	}

	public TestContext next() {
		
		return null;
	}
	
	/* 解析配置文件 */
	protected void parseXml(String path){
		
	}
	
	/**
	 * 获取配置文件
	 * @param m
	 * @param cls
	 * @return
	 */
	protected String getConfigFilePath(Method m, Class<? extends IFunyTestCase> cls){
		
		String relativePath = "src/test/java/"
                + this.getClass().getPackage().getName().replace(".", "/") + "/";
		
		String filename = this.getClass().getSimpleName() + "." + m.getName() + ".yaml";
		
		String fileFullName = relativePath + filename;
		
		//return fileFullName;
		
		return "";
	}
}
