package com.spring.mvc.project.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class PubCode {

	private static Map<String, String> province = new LinkedHashMap<String, String>();

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

}
