package org.funytest.common.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.funytest.common.internal.IAnnotationMethodFinder;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.internal.ITestRunner;
import org.funytest.common.internal.dataprovider.DefaultXmlDataProvider;
import org.funytest.common.internal.dataprovider.IDataProvider;
import org.funytest.common.internal.finder.FunnyTestAnnotationFinder;
import org.funytest.common.internal.method.IFunnyTestMethodFactory;
import org.funytest.common.internal.runner.DefaultRunner;
import org.testng.internal.ClassHelper;

/**
 * 配置类，解析通用配置信息
 * @author hupeng
 *
 */
public class FunnyConfig implements IConfiguration {

	private ITestRunner runner;
	private IAnnotationMethodFinder finder;
	private IDataProvider dataProvider;
	private IFunnyTestMethodFactory funnyMethodFactory;
	private Properties properties;
	
	public FunnyConfig(String configfile){
		InputStream in = getClass().getResourceAsStream(configfile);
		this.properties = new Properties();
		try {
			properties.load(in);
			
		} catch (IOException e) {
			//TO_DO 需要解析异常，日志解析操作等
			e.printStackTrace();
		}
	}
	
	public void init(){
		initRunner(properties);
		initFinder(properties);
		initFactory(properties);
		initDataProvider(properties);
	}
	
	/**
	 * 初始化数据驱动
	 * @param properties
	 */
	@SuppressWarnings("unchecked")
	protected void initDataProvider(Properties properties){
		String className = properties.getProperty("DataProvider");
		if (StringUtils.isBlank(className)){
			this.dataProvider = new DefaultXmlDataProvider();
			return;
		}
		
		Class<IDataProvider> cls = (Class<IDataProvider>) ClassHelper.forName(className);
		this.dataProvider = ClassHelper.newInstance(cls);
	}
	
	/**
	 * 初始化runner
	 * @param properties
	 */
	@SuppressWarnings("unchecked")
	protected void initRunner(Properties properties) {
		String className = properties.getProperty("TestRunner");
		if (!StringUtils.isBlank(className)){
			Class<ITestRunner> cls = (Class<ITestRunner>) ClassHelper.forName(className);
			this.runner = ClassHelper.newInstance(cls);
			
			//TO_DO 抛异常
			if (this.runner == null){}
		} else {
			this.runner = new DefaultRunner();
		}
	}
	
	/**
	 * 初始化annotation finder
	 * @param properties
	 */
	@SuppressWarnings("unchecked")
	protected void initFinder(Properties properties) {
		String className = properties.getProperty("AnnotationFinder");
		if (!StringUtils.isBlank(className)){
			Class<IAnnotationMethodFinder> cls = (Class<IAnnotationMethodFinder>) ClassHelper.forName(className);
			this.finder = ClassHelper.newInstance(cls);
			
			//TO_DO 抛异常
			if (this.finder == null){}
		} else{
			this.finder = new FunnyTestAnnotationFinder();
		}
	}
	
	/**
	 * 初始化method factory
	 * @param properties
	 */
	@SuppressWarnings("unchecked")
	protected void initFactory(Properties properties){
		String className = properties.getProperty("FunnyMethodFactory");
		if (!StringUtils.isBlank(className)){
			Class<IFunnyTestMethodFactory> cls = (Class<IFunnyTestMethodFactory>) ClassHelper.forName(className);
			this.funnyMethodFactory = ClassHelper.newInstance(cls);
			
			//TO_DO 抛异常
			if (this.funnyMethodFactory == null){}
		} else {
			
		}
	}

	public IAnnotationMethodFinder getAnnotationFinder() {
		
		return finder;
	}

	public void setAnnotationFinder(IAnnotationMethodFinder finder) {
		this.finder = finder;
	}

	public ITestRunner getTestRunner() {
	
		return runner;
	}

	public void setTestRunner(ITestRunner runner) {
		this.runner = runner;
	}

	public void setDataProvider(IDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	public IDataProvider getDataProvider() {
	
		return dataProvider;
	}
	
	public IFunnyTestMethodFactory getFunnyMethodFactory(){
		
		return funnyMethodFactory;
	}

	public String getValue(String name) {
		return this.properties.getProperty(name);
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
