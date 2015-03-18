package com.spring.mvc.project.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class PubCode {

	private static Map<String, String> province = new LinkedHashMap<String, String>();

	private static Map<String, String> category = new LinkedHashMap<String, String>();

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

}
