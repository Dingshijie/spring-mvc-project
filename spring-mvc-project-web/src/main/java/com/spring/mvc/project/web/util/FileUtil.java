package com.spring.mvc.project.web.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.spring.mvc.project.domain.util.Constant;

/**
 * 文件工具
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public enum FileType {
		/**
		 * jpg
		 */
		JPG,
		/**
		 * jpeg
		 */
		JPEG,
		/**
		 * gif
		 */
		GIF,
		/**
		 * png
		 */
		PNG,
		/**
		 * bmp
		 */
		BMP,
		/**
		 * excel2007
		 */
		XLSX
	}

	/**
	 * 生成文件名
	 * @return 文件名
	 */
	public static String generateFileName() {
		return RandomStringUtils.random(10, 65, 90, true, false);
	}

	/**
	 * 获取文件类型（如果file==null或者file.isEmpty，则返回null）
	 * @param file 文件
	 * @return
	 */
	public static FileType getFileType(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return null;
		}
		String filename = file.getOriginalFilename();
		String fileType = filename.substring(filename.lastIndexOf(Constant.SEPARATOR_DOT) + 1);
		try {
			return FileType.valueOf(fileType.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public static long getFileSize(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return 0;
		}
		return file.getSize();
	}
}
