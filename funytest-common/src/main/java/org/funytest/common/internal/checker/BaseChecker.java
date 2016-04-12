package org.funytest.common.internal.checker;

import org.funytest.common.exception.CheckFailException;
import org.funytest.common.internal.TestContextHolder;
import org.funytest.common.model.TestContext;

/**
 * checker基类，定义check 规范 和 通用方法
 * @author xiuzhu
 *
 */
public abstract class BaseChecker {
	
	public abstract String getName();
	
	/**
	 * check 公共函数
	 * @throws CheckFailException
	 */
	public void check() throws CheckFailException {
		/* 从上下文中 */
		TestContext context = TestContextHolder.getTestContext();
		
		try {
			check(context);
		} finally {
			cleanCheckInfo(context);
		}
	}
	
	/**
	 * 从测试上下文中获取待check的信息
	 * @param context
	 * @throws CheckFailException
	 */
	protected abstract void check(TestContext context) throws CheckFailException;
	
	/**
	 * check完成之后需要清理待check的信息为已check
	 * @param context
	 */
	protected abstract void cleanCheckInfo(TestContext context);
}
