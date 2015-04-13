package com.spring.mvc.project.web.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.domain.util.Constant;
import com.spring.mvc.project.web.util.FileUtil.FileType;

public class WebUtils {

	private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);

	public static Properties prop_constants = null;
	//加载配置文件
	static {
		try {
			prop_constants = PropertiesLoaderUtils.loadProperties(new ClassPathResource("constants.properties"));
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 上传路径
	 */
	public static String uploadPath = "/workspace/spring-mvc-project/spring-mvc-project-web/src/main/webapp/resources";

	/**
	 * 导出路径
	 */
	private static String exportPath = "/export";

	public static String getUploadPath() {
		return uploadPath;
	}

	public static String getExportPath() {
		return exportPath;
	}

	/**
	 * 生成上传路径
	 * @return 上传路径
	 */
	public static String generateUploadPath() {
		String dir = (uploadPath + "/upload" + getFolderRelativePath()).replace(Constant.SEPARATOR_BACKSLASH,
				Constant.SEPARATOR_SLASH);
		mkdirs(dir);
		return dir;
	}

	/**
	 * 获取项目的目录，设置上传路径
	 * @return
	 */
	public static String generatePath() {
		String dir = ("upload" + getFolderRelativePath()).replace(Constant.SEPARATOR_BACKSLASH,
				Constant.SEPARATOR_SLASH);
		mkdirs(dir);
		return dir;
	}

	/**
	 * 生成导出路径
	 * @return 导出路径
	 */
	public static String generateExportPath() {
		String dir = (exportPath + getFolderRelativePath()).replace(Constant.SEPARATOR_BACKSLASH,
				Constant.SEPARATOR_SLASH);
		mkdirs(dir);
		return dir;
	}

	/**
	 * 创建文件目录（存在dir则不创建，不存在则创建）
	 * @param dir 路径
	 * @return
	 */
	private static boolean mkdirs(String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			return file.mkdirs();
		}
		return false;
	}

	/**
	 * 生成文件上传路径
	 * @param suffix 文件类型
	 * @return 文件导出路径
	 */
	public static String generateFileUploadPath(FileType fileType) {
		return generateUploadPath() + Constant.SEPARATOR_SLASH + FileUtil.generateFileName() + Constant.SEPARATOR_DOT
				+ fileType.name();
	}

	/**
	 * 生成文件导出路径
	 * @param fileType 文件类型
	 * @return 文件导出路径
	 */
	public static String generateFileExportPath(FileType fileType) {
		return generateExportPath() + Constant.SEPARATOR_SLASH + FileUtil.generateFileName() + Constant.SEPARATOR_DOT
				+ fileType.name();
	}

	/**
	 * 获取上传或导出相对路径
	 * @return 相对路径
	 */
	private static String getFolderRelativePath() {
		UserInfo user = (UserInfo) SecurityUtils.getSubject().getPrincipal();
		return Constant.SEPARATOR_SLASH + new SimpleDateFormat("yyyyMMdd").format(new DateTime().toDate())
				+ Constant.SEPARATOR_SLASH + user.getAreaCode() + Constant.SEPARATOR_SLASH + user.getId();
	}

	/**
	 * 获取文件后缀名
	 * @param filePath 文件路径
	 * @return 文件后缀名
	 */
	public static String getFileTypeSuffix(String filePath) {
		return filePath.substring(filePath.lastIndexOf(Constant.SEPARATOR_DOT) + 1);
	}

	@Value("${path.upload}")
	public void setUploadPath(String uploadPath) {
		logger.info("uploadPath：" + uploadPath);
		WebUtils.uploadPath = uploadPath;
	}

	@Value("${path.export}")
	public void setExportPath(String exportPath) {
		logger.info("exportPath：" + exportPath);
		WebUtils.exportPath = exportPath;
	}

}
