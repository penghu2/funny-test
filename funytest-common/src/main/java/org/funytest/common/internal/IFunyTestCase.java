package org.funytest.common.internal;

/**
 * 测试用例
 * @author xiuzhu.hp
 *
 */
public interface IFunyTestCase {
	
	/**
	 * run方法，为核心方法
	 */
	public void run(TestContext context);

	/**
	 * 设置配置信息
	 * @param config
	 */
	public void setConfiguration(IConfiguration config);
	
	/**
	 * 获取配置信息
	 * @return
	 */
	public IConfiguration getIConfiguration(); 
	
	public TestContext getTextContext();
	
}
