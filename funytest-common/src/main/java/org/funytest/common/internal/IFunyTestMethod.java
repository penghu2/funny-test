package org.funytest.common.internal;

/**
 * 框架方法，用于注入框架流程中的
 * @author xiuzhu.hp
 *
 */
public interface IFunyTestMethod {
	
	/**
	 * 执行入口函数
	 */
	public void execute();
	
	/**
	 * 获取执行信息
	 * @return
	 */
	public String getExecuteInfo();
	
	/**
	 * 获取名称
	 * @return
	 */
	public String getName();
}
