package com.spring.mvc.project.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mvc.project.service.EduPubCodeService;

@Controller
@RequestMapping("/edupubcode")
public class EduPubCodeController {

	@Autowired
	private EduPubCodeService eduPubCodeService;

	@RequestMapping(value = "zydl/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> findZYDLMap(String eduLevel, String keyword) {
		return eduPubCodeService.findZYDLMap(eduLevel, keyword);
	}

	@RequestMapping(value = "zyzl/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> findZYZLMap(String eduLevel, String firstCode, String keyword) {
		return eduPubCodeService.findZYZLMap(eduLevel, firstCode, keyword);
	}

	@RequestMapping(value = "zyxl/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> findZYXLMap(String eduLevel, String secondCode, String keyword) {
		return eduPubCodeService.findZYXLMap(eduLevel, secondCode, keyword);
	}

}
