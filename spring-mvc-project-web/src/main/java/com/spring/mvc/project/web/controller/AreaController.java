package com.spring.mvc.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.service.AreaService;

@Controller
@RequestMapping(value = "/area")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "list/city", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> findCityList(String areaCode) {
		return areaService.findCityList(areaCode);
	}

	@RequestMapping(value = "list/area", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> findAreaList(String areaCode) {
		return areaService.findAreaList(areaCode);
	}

}
