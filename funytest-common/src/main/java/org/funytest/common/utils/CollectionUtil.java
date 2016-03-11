package org.funytest.common.utils;

import java.util.Map;

public class CollectionUtil {

	public static void copyMap(Map from, Map to){
		
		if (from==null || to ==null) return;
		
		for (Object key : from.keySet()){
			to.put(key, from.get(key));
		}
	}
}
