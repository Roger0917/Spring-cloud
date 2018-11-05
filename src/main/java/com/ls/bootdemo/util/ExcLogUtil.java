package com.ls.bootdemo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExcLogUtil {
	private static Log logException = LogFactory.getLog("exception");

	public static void recordException(Throwable e) {
		StackTraceElement[] stackTraceElements = e.getStackTrace();// 拿到所有的异常堆栈信息
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement s : stackTraceElements) {
			sb.append(s.toString() + "\n");
		}
		logException.error("*******************************************");
		logException.error(ExcLogUtil.class.getName() + " 异常通用处理，异常内容如下：");
		logException.error("*******************************************");
		logException.error(e.toString());
		logException.error(sb);
	}
}
