package com.spring.mvc.project.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class PubCode {

	private static Map<String, String> province = new LinkedHashMap<String, String>();

	private static Map<String, String> category = new LinkedHashMap<String, String>();

	//学校性质
	private static Map<String, String> schoolType = new LinkedHashMap<String, String>();

	//办学类型
	private static Map<String, String> buildType = new LinkedHashMap<String, String>();

	//jsp页面取值需要此方法
	public Map<String, String> getProvince() {
		return province;
	}

	public static Map<String, String> getProvinceMap() {
		return province;
	}

	public void setProvince(Map<String, String> province) {
		PubCode.province = province;
	}

	//jsp页面取值需要此方法
	public Map<String, String> getCategory() {
		return category;
	}

	public static Map<String, String> getCategoryMap() {
		return category;
	}

	public void setCategory(Map<String, String> category) {
		PubCode.category = category;
	}

	//jsp页面取值需要此方法
	public Map<String, String> getSchoolType() {
		return schoolType;
	}

	public static Map<String, String> getSchoolTypeMap() {
		return schoolType;
	}

	public void setSchoolType(Map<String, String> schoolType) {
		PubCode.schoolType = schoolType;
	}

	//jsp页面取值需要此方法
	public Map<String, String> getBuildType() {
		return buildType;
	}

	public static Map<String, String> getBuildTypeMap() {
		return buildType;
	}

	public void setBuildType(Map<String, String> buildType) {
		PubCode.buildType = buildType;
	}

}
