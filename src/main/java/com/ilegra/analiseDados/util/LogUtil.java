package com.ilegra.analiseDados.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogUtil {
	private static final Logger LOGGER = LogManager.getLogger(LogUtil.class.getName());

	public static StringBuilder log = new StringBuilder();

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

	private LogUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static void info(String mensagem) {
		LOGGER.info(mensagem);
		log.append(sdf.format(new Date()) + " [INFO] - " + mensagem + "\r\n");
	}

	public static void error(String mensagem) {
		LOGGER.error(mensagem);
		log.append(sdf.format(new Date()) + " [ERROR] - " + mensagem + "\r\n");
	}

	public static void error(Throwable e) {
		LOGGER.error(e);
		log.append(sdf.format(new Date()) + " [ERROR] - " + e.getMessage() + "\r\n");
	}

	public static void error(String mensagem, Throwable e) {
		LOGGER.error(mensagem, e);
		log.append(sdf.format(new Date()) + " [ERROR] - " + mensagem + "\r\n" + e.getMessage() + "\r\n");
	}

	public static void warn(String mensagem) {
		LOGGER.warn(mensagem);
		log.append(sdf.format(new Date()) + " [WARN] - " + mensagem + "\r\n");
	}

	public static void trace(String mensagem) {
		LOGGER.trace(mensagem);
		log.append(sdf.format(new Date()) + " [TRACE] - " + mensagem + "\r\n");
	}
}

