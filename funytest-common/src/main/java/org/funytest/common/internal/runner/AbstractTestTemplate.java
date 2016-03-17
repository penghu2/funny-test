package org.funytest.common.internal.runner;

import org.funytest.common.model.TestContext;

/**
 * 测试模板类
 * @author hupeng
 *
 */
public abstract class AbstractTestTemplate {
	
	public abstract void init(TestContext context);
	
	public abstract void execute(TestContext context);
	
	public abstract void check(TestContext context);
	
	public abstract void clean(TestContext context);
}
