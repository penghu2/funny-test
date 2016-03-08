package org.funytest.common.internal;

/**
 * FunnyTest 测试核心引擎类
 * @author xiuzhu.hp
 */
public abstract class FunyTestEngine implements IFunyTestCase {

	
	public void run() {
		
	}
	
	
	public void setConfiguration(IConfiguration config) {
		
	}
	
	/**
	 * 获取配置文件目录的路径
	 */
	public String getConfigFilePath() {
		Class<? extends IFunyTestCase> cls = this.getTestClass();
		String testedMethodName = this.getTestMethodName();
		String relativePath = "src/test/java/"
                 + this.getClass().getPackage().getName().replace(".", "/") + "/";
		String fileName = this.getClass().getSimpleName() + "." + testedMethodName + ".xml";
		String fileFullName = relativePath + fileName;
		 
		return fileFullName;
	}
	
	public abstract Class<? extends IFunyTestCase> getTestClass();
	
	public abstract String getTestMethodName();
}
