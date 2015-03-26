package com.spring.mvc.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mvc.project.domain.CommodityInfo;
import com.spring.mvc.project.service.CommodityService;

@Controller(value = "/commodity")
public class CommodityController {

	@Autowired
	private CommodityService commodityService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<CommodityInfo> findList(String category, String areaCode, String schoolCode, int status, int recommend,
			int used, String keyword, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {
		return commodityService.findList(category, areaCode, schoolCode, status, recommend, used, keyword, pageIndex,
				pageSize);
	}

	@RequestMapping(value = "count", method = RequestMethod.GET)
	@ResponseBody
	public int findList(String category, String areaCode, String schoolCode, int status, int recommend, int used,
			String keyword) {
		return commodityService.findCount(category, areaCode, schoolCode, status, recommend, used, keyword);
	}

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommodityInfo find(@PathVariable(value = "id") String id) {
		return commodityService.find(id);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(CommodityInfo commodity) {
		return commodityService.add(commodity);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(CommodityInfo commodity) {
		return commodityService.update(commodity);
	}
}
