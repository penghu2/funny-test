package org.funytest.common.internal.handler;

/**
 * 执行阶段
 * @author xiuzhu
 */
public enum ExecutePhase {
	INIT("init"),
	CLEAN("clean"),
	EXECUTE("execute"),
	CHECK("check"),
	EXTENSION("extension");
	
	String value;
	
	private ExecutePhase(String phase){
    	 this.value = phase;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public ExecutePhase getPhaseByValue(String value){
		for (ExecutePhase ep : ExecutePhase.values()){
			if (ep.value.equals(value)){
				return ep;
			}
		}
		return null;
	}
}