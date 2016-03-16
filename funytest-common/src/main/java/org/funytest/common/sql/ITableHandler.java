package org.funytest.common.sql;

import javax.sql.DataSource;

import org.funytest.common.exception.TableHandleException;
import org.funytest.common.model.Table;

/**
 * tableHandler类
 * @author hupeng
 *
 */
public interface ITableHandler {
	
	/**
	 * 
	 * @param table 对应的表数据
	 * @param datasource 对应的数据源
	 * @throws TableHandleException  异常
	 */
	public void execute(Table table, DataSource datasource) throws TableHandleException;
}
