package org.funytest.common.model;

import java.util.List;
import java.util.Map;

/**
 * 测试场景类
 * @author hupeng
 */
public class TestCase {
	
	public List<TestAlign> testAligns;
	
	public Map<String, Object> swap;
	
	/**
	 * 测试链
	 * @author hupeng
	 *
	 */
	public class TestAlign{
		
		List<TestStep> steps;
	}
}
