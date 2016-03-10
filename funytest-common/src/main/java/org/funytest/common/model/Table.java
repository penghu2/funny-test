package org.funytest.common.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.funytest.common.sql.TableHandlerFactory;

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
		compare; //compare 类型，这个比较特殊，非常难搞定
	}
	
	private int id;
	
	/* 表格名 */
	private String name;
	
	/* 表格描述 */
	private String desc;
	
	/* 表格的执行类型 */
	private Type type;
	
	/* 数据源 */
	private DataSource datasource;
	
	private TableHandlerFactory handlerFactory = new TableHandlerFactory();
	
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
	
	
	public void execute(){
		
	}
	
	/*====================[配置相关区域]=========================*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public Map<String, String> getFlag() {
		return Flag;
	}

	public void setFlag(Map<String, String> flag) {
		Flag = flag;
	}

	public List<Map<String, String>> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<Map<String, String>> datalist) {
		this.datalist = datalist;
	}
}

