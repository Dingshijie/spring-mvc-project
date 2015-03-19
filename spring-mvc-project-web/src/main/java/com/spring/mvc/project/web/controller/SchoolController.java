package com.spring.mvc.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.SchoolInfo;
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

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SchoolInfo find(String id) {
		return schoolService.find(id);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(SchoolInfo schoolInfo) {
		return schoolService.update(schoolInfo);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<SchoolInfo> finList(String provinceCode, String keyword,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		return schoolService.finList(provinceCode, keyword, pageIndex, pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findCount(String provinceCode, String keyword) {
		return schoolService.findCount(provinceCode, keyword);
	}

}
