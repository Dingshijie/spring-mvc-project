package com.spring.mvc.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mvc.project.domain.Category;
import com.spring.mvc.project.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Category find(String id) {
		return categoryService.find(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(Category category) {
		return categoryService.add(category);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(String fieldName, Object fieldValue, String id) {
		return categoryService.update(fieldName, fieldValue, id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> findList(String categoryCode, @RequestParam(value = "hot", defaultValue = "10") int hot,
			@RequestParam(value = "enable", defaultValue = "10") int enable, String keyword,
			@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
		return categoryService.findList(categoryCode, hot, enable, keyword, pageIndex, pageSize);
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public int findList(String categoryCode, @RequestParam(value = "hot", defaultValue = "10") int hot,
			@RequestParam(value = "enable", defaultValue = "10") int enable, String keyword) {
		return categoryService.findCount(categoryCode, hot, enable, keyword);
	}
}
