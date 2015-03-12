package com.spring.mvc.project.serviceImpl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = false)
	public boolean add(Category category) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return categoryService.add(category);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(String fieldName, Object fieldValue, String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return categoryService.update(fieldName, fieldValue, id);
		}
		return false;
	}

	@Override
	public List<Category> findList(String categoryCode, String keyword, int pageIndex, int pageSize) {
		return categoryService.findList(categoryCode, keyword, pageIndex, pageSize);
	}

	@Override
	public int findCount(String categoryCode, String keyword) {
		return categoryService.findCount(categoryCode, keyword);
	}

}
