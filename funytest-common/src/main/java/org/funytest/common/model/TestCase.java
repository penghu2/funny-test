package org.funytest.common.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.funytest.common.model.teststep.ITestStep;

/**
 * 测试场景类
 * @author hupeng
 */
public class TestCase {
	
	protected String id;
	
	protected String desc;
	
	private List<TestAlign> testAligns;
	
	private Map<String, String> swap;
	
	
	public TestCase(){
		this.testAligns = new LinkedList<TestAlign>();
	}
	
	public void addAlign(TestAlign align){
		this.testAligns.add(align);
	}
	
	/**
	 * 测试链
	 * @author hupeng
	 *
	 */
	public class TestAlign{
		
		List<ITestStep> steps;
		
		/* 排序序列号 */
		public int order;
		
		public TestAlign(){
			steps = new LinkedList<ITestStep>();
			this.order = 0;
		}
		
		public int getOrder(){
			return order;
		}
		
		public void setOrder(int order){
			this.order = order;
		}

		public List<ITestStep> getSteps() {
			return steps;
		}

		public void setSteps(List<ITestStep> steps) {
			this.steps = steps;
		}
		
		/* 添加测试步骤 */
		public void addTestStep(ITestStep step){
			this.steps.add(step);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<TestAlign> getTestAligns() {
		return testAligns;
	}

	public void setTestAligns(List<TestAlign> testAligns) {
		this.testAligns = testAligns;
	}

	public Map<String, String> getSwap() {
		return swap;
	}

	public void setSwap(Map<String, String> swap) {
		this.swap = swap;
	}
}
