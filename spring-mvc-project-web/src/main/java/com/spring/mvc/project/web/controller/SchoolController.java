package com.spring.mvc.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.service.SchoolService;

@Controller
@RequestMapping(value = "/school")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@RequestMapping(value = "list/{provinceCode}", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> findList(@PathVariable(value = "provinceCode") String provinceCode) {
		return schoolService.findList(provinceCode);
	}

}
