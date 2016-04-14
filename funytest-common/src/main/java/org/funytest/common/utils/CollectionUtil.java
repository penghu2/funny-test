package org.funytest.common.utils;

import java.util.HashMap;
import java.util.Map;

public class CollectionUtil {

	public static void copyMap(Map from, Map to){
		
		if (from==null || to ==null) return;
		
		for (Object key : from.keySet()){
			to.put(key, from.get(key));
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void convert(Map<String, Object> from, Map<String, T> to) {
		
		for (String key : from.keySet()) {
			to.put(key, (T)from.get(key));
		}
	}
	
	/**
	 * Map 合并
	 * @param A
	 * @param B
	 * @return
	 */
	public static <T,V> Map<T,V> combineMap(Map<T,V> A, Map<T,V> B) {
		Map<T,V> map = new HashMap<T,V>();
		
		for (T key : A.keySet()) {
			map.put(key, A.get(key));
		}
		
		for (T key : B.keySet()) {
			map.put(key, B.get(key));
		}
		
		return map;
	}
}
