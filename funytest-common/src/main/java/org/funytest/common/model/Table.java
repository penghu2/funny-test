package org.funytest.common.model;

import java.util.List;
import java.util.Map;

/**
 * 数据库表数据映射模型
 * @author hupeng
 *
 */
public class Table {
	
	public enum Type{
		insert,  //标明该表格为insert类型，构造insert语句
		update,  //标明该表格为update类型，构造update语句
		delete,  //标明该表格为delete类型，构造delete语句
		other;
	}
	
	/* 表格名 */
	private String name;
	
	/* 表格描述 */
	private String desc;
	
	/* 表格的执行类型 */
	private Type type;
	
	/* 
	 * 标识位，用于标识关键字
	 * C： where 条件关键字
	 * F： 标识着为函数条件
	 * Y： 标识着需要校验的位置
	 * N:  标识着不需要校验 
	 */
	private Map<String, String> Flag;
	
	/* 表格数据 */
	private List<Map<String, String>> datalist;
}

