package org.funytest.common.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.funytest.common.internal.FunyTestEngine;
import org.funytest.common.internal.IAnnotationMethodFinder;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.internal.ITestRunner;
import org.funytest.common.internal.dataprovider.DefaultXmlDataProvider;
import org.funytest.common.internal.dataprovider.IDataProvider;
import org.funytest.common.internal.finder.FunnyTestAnnotationFinder;
import org.funytest.common.internal.handler.ITestStepHandlerFactory;
import org.funytest.common.internal.handler.TestStepHandlerFactory;
import org.funytest.common.internal.method.IFunnyTestMethodFactory;
import org.funytest.common.internal.runner.DefaultRunner;
import org.funytest.common.utils.ObjectUtil;
import org.testng.internal.ClassHelper;

/**
 * 配置类，解析通用配置信息
 * @author hupeng
 *
 */
public class FunnyConfig implements IConfiguration {

	private ITestRunner runner;   						//测试runner
	private IAnnotationMethodFinder finder;				//注解finder
	//private IDataProvider dataProvider;					//数据驱动
	private IFunnyTestMethodFactory funnyMethodFactory; //funnymethod工厂类
	private Properties properties;						//配置属性，对应funny-test.config 文件
	private Map<String, DataSource> datasourceMap;		//数据源配置器
	private FunyTestEngine engine;						//测试引擎类，负责调度和上下文的维护
	private ITestStepHandlerFactory testStepHandlerFactory;	//test handler工厂类
	
	/**
	 * 构造函数
	 * @param configfile
	 */
	public FunnyConfig(String configfile){
		try {
			InputStream in = getClass().getResourceAsStream(configfile);
			if (in==null){return;}
			this.properties = new Properties();
			properties.load(in);
			in.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化函数
	 */
	public void init(FunyTestEngine engine){
		/* 前后关系不可置换 */
		initTestStepHandlerFactory(properties);
		initRunner(properties);
		initFinder(properties);
		initMethodFactory(properties);
		initDataSource(properties, engine);
		
	}
	
	/**
	 * 初始化测试test step handler工厂类
	 */
	public void initTestStepHandlerFactory(Properties properties) {
		if (ObjectUtil.notNull(this.testStepHandlerFactory)) return;
		
		String className = properties.getProperty("testStepHandlerFactory");
		if (StringUtils.isBlank(className)){
			this.testStepHandlerFactory = new TestStepHandlerFactory();
			return;
		} else {
			@SuppressWarnings("unchecked")
			Class<IDataProvider> cls = (Class<IDataProvider>) ClassHelper.forName(className);
			this.testStepHandlerFactory = (ITestStepHandlerFactory) ClassHelper.newInstance(cls);
		}
	}
	
	/**
	 * 初始化数据datasource, 这个是需要从配置中获取到的
	 * @param properties
	 */
	public void initDataSource(Properties properties, FunyTestEngine engine){
		datasourceMap = new HashMap<>();
		
		String dataSourcesStr = properties.getProperty("ds.list").trim();
		if (StringUtils.isBlank(dataSourcesStr)) return;
		
		/* 按照逗号分隔 */
		String[] dataSourceStrList = dataSourcesStr.split(",");
		for (int i=0; i<dataSourceStrList.length; i++) {
			String dataSourceStr = dataSourceStrList[i];
			
			String key = "ds."+ dataSourceStr +".table";
			String tablesStr = properties.getProperty(key);
			if (StringUtils.isBlank(tablesStr)) continue;
			
			/*
			 * 这里可能会存在如下可能性 ：
			 * 1. 获取bean失败
			 * 2. 获取的bean不是DataSource，强转失败
			 */
			DataSource ds = (DataSource) engine.getBean(dataSourceStr);
			
			if (ds==null) continue;
			
			/*
			 * 分组的存在是为了支持多数据源，考虑分库分表的情况，使用group进行区分 
			 */
			String group = properties.getProperty("ds."+ dataSourceStr +".group");
			String tb_suffix="";
			if (StringUtils.isNotEmpty(group)) {
				tb_suffix="_"+group;
			}
			
			/* 按照逗号分隔 */
			String[] tables = tablesStr.split(",");
			for (int j=0; j<tables.length; j++) {
				
				datasourceMap.put(tables[j]+tb_suffix, ds);
			}
		}
	}
	
	/**
	 * 初始化数据驱动
	 * @param properties
	 */
	@SuppressWarnings("unchecked")
	protected IDataProvider initDataProvider(Properties properties){
		
		String className = properties.getProperty("DataProvider");
		if (StringUtils.isBlank(className)){
			return new DefaultXmlDataProvider();
		}
		
		Class<IDataProvider> cls = (Class<IDataProvider>) ClassHelper.forName(className);
		return ClassHelper.newInstance(cls);
	}
	
	/**
	 * 初始化runner
	 * @param properties
	 */
	@SuppressWarnings("unchecked")
	protected void initRunner(Properties properties) {
		if (ObjectUtil.notNull(this.runner)) return;
		
		String className = properties.getProperty("TestRunner");
		if (!StringUtils.isBlank(className)){
			Class<ITestRunner> cls = (Class<ITestRunner>) ClassHelper.forName(className);
			this.runner = ClassHelper.newInstance(cls);
			
			//TO_DO 抛异常
			if (this.runner == null){}
			
			//runner会涉及到handler的初始化，这个地方务必注意，不可遗漏，如果想扩展的话
			this.runner.initStepHandlerFactory(this.testStepHandlerFactory);
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
		if (ObjectUtil.notNull(this.finder)) return;
		
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
	protected void initMethodFactory(Properties properties){
		if (ObjectUtil.notNull(this.funnyMethodFactory)) return;
		
		String className = properties.getProperty("FunnyMethodFactory");
		if (!StringUtils.isBlank(className)){
			Class<IFunnyTestMethodFactory> cls = (Class<IFunnyTestMethodFactory>) ClassHelper.forName(className);
			this.funnyMethodFactory = ClassHelper.newInstance(cls);
			
			//TO_DO 抛异常
			if (this.funnyMethodFactory == null){}
		} else {
			
		}
	}

	public DataSource getDataSource(String tableName, String ...groups) {
		
		if (this.datasourceMap == null) return null;
		
		if (groups!=null) {
			return this.datasourceMap.get(tableName+"_"+groups[0]);
		}
		
		return this.datasourceMap.get(tableName);
	}
	
	/**
	 * TestStep handler 工厂类
	 */
	@Override
	public ITestStepHandlerFactory getTestStepHandlerFactory() {
		
		return this.testStepHandlerFactory;
	}
	
	@Override
	public Object getBeanByName(String beanName) {
		
		return null;
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

	/**
	 * 获取数据驱动对象，采用工厂模式，这里必须每次调用生产一个新的实例
	 * 以支持多线程程序
	 */
	public synchronized IDataProvider getDataProvider() {
		
		return initDataProvider(properties);
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

	public ITestRunner getRunner() {
		return runner;
	}

	public void setRunner(ITestRunner runner) {
		this.runner = runner;
	}

	public IAnnotationMethodFinder getFinder() {
		return finder;
	}

	public void setFinder(IAnnotationMethodFinder finder) {
		this.finder = finder;
	}

	public Map<String, DataSource> getDatasourceMap() {
		return datasourceMap;
	}

	public void setDatasourceMap(Map<String, DataSource> datasourceMap) {
		this.datasourceMap = datasourceMap;
	}

	public FunyTestEngine getEngine() {
		return engine;
	}

	public void setEngine(FunyTestEngine engine) {
		this.engine = engine;
	}

	public void setFunnyMethodFactory(IFunnyTestMethodFactory funnyMethodFactory) {
		this.funnyMethodFactory = funnyMethodFactory;
	}

	public ITestStepHandlerFactory getTestStepFactory() {
		return testStepHandlerFactory;
	}

	public void setTestStepFactory(ITestStepHandlerFactory testStepFactory) {
		this.testStepHandlerFactory = testStepFactory;
	}
}
