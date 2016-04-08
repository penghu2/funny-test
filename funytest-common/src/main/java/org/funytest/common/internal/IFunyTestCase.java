package org.funytest.common.internal;

import org.funytest.common.model.TestContext;

/**
 * 测试用例
 * @author xiuzhu.hp
 *
 */
public interface IFunyTestCase {
	
	/**
	 * run方法，为核心方法
	 * @throws Exception 
	 */
	public void run(TestContext context) throws Exception;

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
	
}
