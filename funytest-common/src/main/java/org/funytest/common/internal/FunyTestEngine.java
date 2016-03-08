package org.funytest.common.internal;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.TestInstance;

/**
 * FunnyTest 测试核心引擎类
 * @author xiuzhu.hp
 */
public abstract class FunyTestEngine implements IFunyTestCase {

	/* 配置信息 */
	private IConfiguration config;
	
	/**
	 * 执行方法
	 */
	public void run(TestContext context) {
		this.config.getTestRunner().run(context);
	}
	
	/**
	 * 初始化
	 */
	@BeforeClass
	public void init(){
		
		//首先初始化配置信息
		this.config = this.initConfig();
		config.init(this.getConfigFiles());
		
		//扫描注解方法，并放置到配置中
	}

	/**
	 * 数据驱动
	 * @param m 测试方法
	 * @param instance 测试类的当前实例
	 * @return
	 */
	@DataProvider(name = "FunyTestDataProvider")
	public Iterator<?> getData(Method m, @TestInstance Object instance){
		return this.config.getDataProvider().getData(m, instance);
	}
	
	/*==========================[待实现区域]=============================*/
	
	public abstract Class<? extends IFunyTestCase> getTestClass();
	
	public abstract String getTestMethodName();
	
	public abstract IConfiguration initConfig();
	
	/**
	 * 获取配置文件
	 * @return
	 */
	public abstract List<String> getConfigFiles();
	
	/*==========================[配置相关区域]=============================*/
	public IConfiguration getIConfiguration(){
		return this.config;
	}
}
