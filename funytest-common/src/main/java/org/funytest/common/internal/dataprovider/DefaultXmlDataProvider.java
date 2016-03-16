package org.funytest.common.internal.dataprovider;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.internal.IFunyTestCase;
import org.funytest.common.model.TestCase;
import org.funytest.common.model.TestContext;
import org.funytest.common.model.teststep.TestStepFactory;
import org.funytest.common.utils.CollectionUtil;

/**
 * 默认的xml解析datarpovider
 * @author hupeng
 * qq号码 623158938
 */
public class DefaultXmlDataProvider implements IDataProvider, Iterator<Object> {

	private Document document;
	
	/* testcase节点集合 */
	private List<Element> testCaseElements;
	
	/* 解析索引 */
	private int readIndex=0;
	
	/* 通用属性 */
	private Map<String, String> swapMaps;
	
	private Object instance;
	
	/**
	 * 测试步骤工厂类
	 */
	private TestStepFactory testStepFactory;
	
	@SuppressWarnings("unchecked")
	public Iterator<?> getData(Method m, Class<? extends IFunyTestCase> cls, Object instance) throws DocumentException {
		
		this.instance = instance;
		
		/* 获取配置文件路径 */
		String configFilePath = getConfigFilePath(m, cls);
		
		/* 解析xml配置文件 */
		SAXReader reader = new SAXReader();
		
		try {
			document = reader.read(new File(configFilePath));
			
			//获取根节点元素对象  
	        Element root = document.getRootElement(); 
	        
	        testCaseElements = root.elements("test-case");
	        
	        initCommonProperties(swapMaps, root.element("properties"));
	        
		} catch (DocumentException e) {
			e.printStackTrace();
			throw e;
		}  
		
		return this;
	}
	
	/**
	 * 通用属性的替换与解析
	 * @param map 用于存储替换对象
	 * @param properties  xml 中的 "properties" 结点
	 */
	private void initCommonProperties(Map<String, String> map, Element properties){
		
		if(map==null) map = new HashMap<String, String>();
		
		//为空就返回
		if (properties == null) return;
		
		//遍历子节点
		for (Iterator<?> it = properties.elementIterator();it.hasNext();){
			Element element = (Element) it.next();
			String name = element.getName();
			String text = element.getText();
			
			map.put(name, text);
		}
	}
	
	public synchronized boolean hasNext() {
		if (testCaseElements != null && testCaseElements.size()>0){
			if (testCaseElements.size() - readIndex > 0){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * next 函数
	 */
	public synchronized Object[] next() {
		Element element = (Element)testCaseElements.get(readIndex);
		readIndex++;
		
		//将element解析成为TestCase对象，这里面会涉及到变量的替换
		TestContext context = new TestContext((IFunyTestCase) this.instance);
		convert(element, context);
		
		Object[] res = new Object[1];
		res[0] = context;
		return res;
	}
	
	/**
	 * 
	 * @param ele  "test-case" 结点
	 * @param rep  "里面会有需要替换的值"
	 */
	private void replace(Element ele, Map<String, String> rep){
		
	}
	
	/**
	 * 将xml解析成为测试上下文
	 * @param tkelement xml节点 "test-case"
	 * @param context  测试上下文
	 */
	@SuppressWarnings("unchecked")
	protected void convert(Element tkelement, TestContext context){
		TestCase testcase = new TestCase();
		
		testcase.setId(tkelement.attribute("id").getText());
		testcase.setDesc(tkelement.attribute("desc").getText());
		
		Element pro = tkelement.element("properties");
		Map<String, String> swap = new HashMap<String, String>();
		CollectionUtil.copyMap(swapMaps, swap);
		
		initCommonProperties(swap, pro);
		
		replace(tkelement, swap);
		
		List<Element> alignlist  = tkelement.elements("test-align");
		
		if (alignlist!=null){
			for (Element alignElement : alignlist){
				TestCase.TestAlign align =  testcase.new TestAlign();
				convert(alignElement, align);
				testcase.addAlign(align);
			}
		}
		
		context.setTestcase(testcase);
	}
	
	/**
	 * 将xml节点test-align 转换成TestCase.TestAlign对象
	 * @param aliginElement
	 * @param align
	 * @param swap
	 */
	protected void convert(Element aliginElement, TestCase.TestAlign align){
		if (aliginElement.attribute("index")!= null){
			align.setOrder(Integer.valueOf(aliginElement.attribute("index").getText()));
		}
		
		/* 遍历子节点 */
		for (Iterator<?> it=aliginElement.elementIterator();it.hasNext();){
			Element item = (Element) it.next();
			
			/* teststep是一个特殊的群体，因此这里会比较复杂 */
			String name=item.getName();
			
			IConfiguration config = ((IFunyTestCase)this.instance).getIConfiguration();
			if (testStepFactory==null) testStepFactory = new TestStepFactory();
			align.addTestStep(testStepFactory.buildStep(name, item, config));
		}
	}
	
	/**
	 * 获取配置文件
	 * @param m
	 * @param cls
	 * @return
	 */
	protected String getConfigFilePath(Method m, Class<? extends IFunyTestCase> cls){
		
		String filename = cls.getSimpleName() + "." + m.getName() + ".xml";
		
		String path = cls.getResource(filename).getPath();
			
		return path;
	}


	public void remove() {
		
	}
	
	public TestStepFactory getTestStepFactory() {
		return testStepFactory;
	}

	public void setTestStepFactory(TestStepFactory testStepFactory) {
		this.testStepFactory = testStepFactory;
	}
}
