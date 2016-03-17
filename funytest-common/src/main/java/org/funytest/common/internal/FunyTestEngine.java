package org.funytest.common.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.DocumentException;
import org.funytest.common.internal.method.IFunnyTestMethod;
import org.funytest.common.internal.method.IFunnyTestMethodFactory;
import org.funytest.common.model.FunnyConfig;
import org.funytest.common.model.TestContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.TestInstance;

/**
 * FunnyTest 测试核心引擎类, 基于spring 上下文进行
 * @author xiuzhu.hp
 */
public class FunyTestEngine extends AbstractTestNGSpringContextTests implements IFunyTestCase {

	public String configfile = "/config/funny-test.config";
	
	/* 配置信息类 */
	protected IConfiguration config;
	
	/* funnyTestMethod 工厂类 */
	protected IFunnyTestMethodFactory funyTestMethodFactory;
	
	/* funnyTestMethod map */
	protected Map<Class<? extends Annotation>, IFunnyTestMethod> funnyTestMethodMap;
	
	/* 存储类 */
	protected List<Class<? extends Annotation>> annotationClassList;
	
	/**
	 * 执行方法
	 */
	public void run(TestContext context) {
		this.config.getTestRunner().run(context);
	}
	
	protected void initConfig(){
		//首先初始化配置信息
		if (config!=null){
			return;
		}
		
		this.config = this.getConfig();
		config.init(this);
	}
	
	/**
	 * 初始化
	 */
	@BeforeClass
	public void init(){
		
		initConfig();
		/* 初始化变量 */
		annotationClassList = new LinkedList<Class<? extends Annotation>>();
		
		funyTestMethodFactory = this.config.getFunnyMethodFactory();
		
		//扫描注解方法，并放置到配置中
		initAnnotationMethods();
	}
		
	private void initAnnotationMethods(){
		
		IAnnotationMethodFinder finder = this.config.getAnnotationFinder();
		/* 初始化特定方法 */
		this.funnyTestMethodMap = finder.findFunnyTestMethodMap(this.getClass(), getAnnotationClassList(),funyTestMethodFactory);
	}

	/**
	 * 
	 * @return
	 */
	protected void register(Class<? extends Annotation> cls){
		this.annotationClassList.add(cls);
	} 
	
	/**
	 * 数据驱动
	 * @param m 测试方法
	 * @param instance 测试类的当前实例
	 * @return
	 */
	@DataProvider(name = "FunyTestDataProvider")
	public Iterator<?> getData(Method m, @TestInstance Object instance){
		try {
			initConfig();
			return this.config.getDataProvider().getData(m, this.getClass(), instance);
		} catch (DocumentException e) {
			// TODO 说明解析失败了
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 从spring上下文中获取指定的bean
	 * @param name
	 * @return 
	 */
	public Object getBean(String name){
		try {
			return applicationContext.getBean(name);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/*==========================[待实现区域]=============================*/

	
	public IConfiguration getConfig(){
		return new FunnyConfig(configfile);
	}
	
	
	
	/*==========================[配置相关区域]=============================*/
	public IConfiguration getIConfiguration(){
		return this.config;
	}

	public IFunnyTestMethodFactory getFunyTestMethodFactory() {
		return funyTestMethodFactory;
	}

	public void setFunyTestMethodFactory(IFunnyTestMethodFactory funyTestMethodFactory) {
		this.funyTestMethodFactory = funyTestMethodFactory;
	}

	public Map<Class<? extends Annotation>, IFunnyTestMethod> getFunnyTestMethodMap() {
		return funnyTestMethodMap;
	}

	public void setFunnyTestMethodMap(Map<Class<? extends Annotation>, IFunnyTestMethod> funnyTestMethodMap) {
		this.funnyTestMethodMap = funnyTestMethodMap;
	}

	public List<Class<? extends Annotation>> getAnnotationClassList() {
		return annotationClassList;
	}

	public void setAnnotationClassList(List<Class<? extends Annotation>> annotationClassList) {
		this.annotationClassList = annotationClassList;
	}

	public void setConfiguration(IConfiguration config) {
		
		this.config = config;
	}
	
	
}
