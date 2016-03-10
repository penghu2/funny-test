package org.funytest.common.internal.dataprovider;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.funytest.common.internal.IFunyTestCase;
import org.funytest.common.model.TestContext;

/**
 * 默认的xml解析datarpovider
 * @author hupeng
 * @qq 623158938
 */
public class DefaultXmlDataProvider implements IDataProvider, Iterator<TestContext> {

	private Document document;
	private List testCaseElements;
	
	public Iterator<TestContext> getData(Method m, Class<? extends IFunyTestCase> cls, Object instance) throws DocumentException {
		
		/* 获取配置文件路径 */
		String configFilePath = getConfigFilePath(m, cls);
		
		/* 解析xml配置文件 */
		SAXReader reader = new SAXReader();
		
		try {
			document = reader.read(new File(configFilePath));
			
			//获取根节点元素对象  
	        Element root = document.getRootElement(); 
	        
	        testCaseElements = root.elements("test-case");
		} catch (DocumentException e) {
			e.printStackTrace();
			throw e;
		}  
		
		return this;
	}
	
	
	public boolean hasNext() {
		if (testCaseElements != null && testCaseElements.size()>0)
			testCaseElements.iterator().hasNext();
		
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
		
		String filename = this.getClass().getSimpleName() + "." + m.getName() + ".xml";
		
		String fileFullName = relativePath + filename;
		
		//return fileFullName;
		
		return "";
	}
}
