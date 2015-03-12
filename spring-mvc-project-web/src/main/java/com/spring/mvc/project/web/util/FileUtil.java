package com.spring.mvc.project.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public enum FileType {
		/**
		 * excel2003
		 */
		XLS,
		/**
		 * excel2007
		 */
		XLSX,
		/**
		 * CSV文件
		 */
		CSV,
		/**
		 * DBF文件
		 */
		DBF,
		/**
		 * ZIP压缩文件
		 */
		ZIP
	}
}
