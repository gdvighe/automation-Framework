package com.Utility;

import org.apache.logging.log4j.Logger;

public class LoggerUtility {

	private LoggerUtility() {

	}

	public static Logger getLogger(Class<?> clazz) {
		Logger logger = null;
		if (logger == null) {
			logger = org.apache.logging.log4j.LogManager.getLogger(clazz);
		}
		return logger;
	}
}
 