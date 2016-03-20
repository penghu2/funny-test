package org.funytest.common.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class LogUtils {
	
	/* 流水日志 */
	public static Logger digest_logger = Logger.getLogger("funny-test-digest-log");
	 
	/* info 日志 */
	private static Logger info_logger = Logger.getLogger("funny-test-info-log");
	
	/* err 日志 */
	private static Logger error_logger = Logger.getLogger("funny-test-error-log");
	
	
	public void info(String message){
		if (info_logger.isInfoEnabled()){
			info_logger.info(message);
		}
	}
	
	public void warn(String message){
		if (info_logger.isEnabledFor(Priority.WARN)){
			info_logger.warn(message);
		}
	}
	
	public void error(String message){
		if (info_logger.isEnabledFor(Priority.ERROR)){
			info_logger.error(message);
		}
		
		if (error_logger.isInfoEnabled()){
			error_logger.info(message);
		}
	}
}
