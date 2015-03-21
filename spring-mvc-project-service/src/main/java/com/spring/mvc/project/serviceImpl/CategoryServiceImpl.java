package com.spring.mvc.project.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.Category;
import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.repository.CategoryRepository;
import com.spring.mvc.project.service.CategoryService;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryService;

	@Override
	public Category find(String id) {
		return categoryService.find(id);
	}

	@Override
	public boolean exist(String fieldName, String fieldValue) {
		return categoryService.exist(fieldName, fieldValue);
	}

	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = "appCache", allEntries = true)
	public boolean add(Category category) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		//		if (userInfo.isManager()) {
		return categoryService.add(category);
		//		}
		//		return false;
	}

	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = "appCache", allEntries = true)
	public boolean update(String fieldName, Object fieldValue, String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return categoryService.update(fieldName, fieldValue, id);
		}
		return false;
	}

	@Override
	public List<Category> findList(String categoryCode, int hot, int enable, String keyword, int pageIndex, int pageSize) {
		return categoryService.findList(categoryCode, hot, enable, keyword, pageIndex, pageSize);
	}

	@Override
	public int findCount(String categoryCode, int hot, int enable, String keyword) {
		return categoryService.findCount(categoryCode, hot, enable, keyword);
	}

	@Override
	@Cacheable(value = "appCache", key = "'list_'+#code")
	public List<JSONObject> findList(String code) {
		List<Category> list = categoryService.findList(code, 10, 1, "", 0, 0);
		List<JSONObject> objList = new ArrayList<JSONObject>();
		for (Category category : list) {
			JSONObject obj = new JSONObject();
			obj.put("code", category.getCode());
			obj.put("name", category.getName());
			objList.add(obj);
		}
		return objList;
	}

}
