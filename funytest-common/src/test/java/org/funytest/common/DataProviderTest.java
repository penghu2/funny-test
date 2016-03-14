package org.funytest.common;

import org.funytest.common.internal.dataprovider.DefaultXmlDataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	
	
	@Test
	public void test() {
		String path = DefaultXmlDataProvider.class.getClassLoader().getResource("example/config.xml").getPath();
		System.out.println(path);
		
	}
}
