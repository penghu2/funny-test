package org.funytest.common.internal;

/**
 * 配置类，相关配置信息全部放在这个里面，主要的配置信息包括：
 * 1. IAnnotationMethodFinder 用于查找特定注解标识的方法
 * 2. ITestTemplate 测试模板，定义了该类的执行模板，模板定义了执行的手段
 * @author hupeng
 *
 */
public interface IConfiguration {
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
	public ITestRunner getTestTemplate();
	public void setTestTemplate(ITestRunner template);
}
