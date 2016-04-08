package org.funytest.common;

import org.funytest.common.internal.FunyTestEngine;
import org.funytest.common.internal.IConfiguration;
import org.testng.annotations.Test;

public class DataProviderTest {

	@Test
	public void test() {
		
		FunyTestEngine ft = new FunyTestEngine();
		ft.configfile = "/example/funny-test.config";
		IConfiguration config = ft.getConfig();
		//config.init(ft);
	}
}
