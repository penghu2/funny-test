package org.funytest.common.internal;

import javax.sql.DataSource;

import org.funytest.common.internal.dataprovider.IDataProvider;
import org.funytest.common.internal.method.IFunnyTestMethodFactory;

/**
 * 配置类，相关配置信息全部放在这个里面，主要的配置信息包括：
 * 1. IAnnotationMethodFinder 用于查找特定注解标识的方法
 * 2. ITestTemplate 测试模板，定义了该类的执行模板，模板定义了执行的手段
 * @author hupeng
 *
 */
public interface IConfiguration {
	
	/**
	 * 初始化配置信息
	 */
	public void init(FunyTestEngine testEngine);
	
	public DataSource getDataSource(String tableName, String ...groups);
	
	/**
	 * 注解方法查询类的 set 和 get
	 * @return
	 */
	IAnnotationMethodFinder getAnnotationFinder();
	void setAnnotationFinder(IAnnotationMethodFinder finder);
	
	/**
	 * 测试模板类的 set 和 get
	 * @return
	 */
	public ITestRunner getTestRunner();
	public void setTestRunner(ITestRunner runner);
	
	
	/**
	 * 数据驱动 set 和 get
	 */
	public void setDataProvider(IDataProvider dataProvider);
	public IDataProvider getDataProvider();
	
	public IFunnyTestMethodFactory getFunnyMethodFactory();
	
	/**
	 * 获取配置的值
	 * @param name
	 * @return
	 */
	public String getValue(String name);
	
	/**
	 * 根据beanName获取bean
	 * @param beanName
	 * @return
	 */
	public Object getBeanByName(String beanName);
}
