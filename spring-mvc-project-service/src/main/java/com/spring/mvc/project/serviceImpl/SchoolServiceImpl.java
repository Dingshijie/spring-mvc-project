package com.spring.mvc.project.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.SchoolInfo;
import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.repository.SchoolRepository;
import com.spring.mvc.project.service.SchoolService;

@Service
@Transactional(readOnly = true)
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository schoolRepositry;

	@Override
	@Transactional(readOnly = false)
	public boolean add(SchoolInfo school) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return schoolRepositry.add(school);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(SchoolInfo school) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			schoolRepositry.update(school);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(String fieldName, String fieldValue, String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return schoolRepositry.update(fieldName, fieldValue, id);
		}
		return false;
	}

	@Override
	public SchoolInfo find(String id) {
		return schoolRepositry.find(id);
	}

	@Override
	public boolean isExist(String fieldName, String fieldValue) {
		return schoolRepositry.isExist(fieldName, fieldValue);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			schoolRepositry.delete(find(id));
			return true;
		}
		return false;
	}

	@Override
	public List<SchoolInfo> finList(String provinceCode) {
		return schoolRepositry.finList(provinceCode, "", 0, 0);
	}

	@Override
	public List<SchoolInfo> finList(String provinceCode, String keyword, int pageIndex, int pageSize) {
		return schoolRepositry.finList(provinceCode, keyword, pageIndex, pageSize);
	}

	@Override
	public int findCount(String provinceCode, String keyword) {
		return schoolRepositry.findCount(provinceCode, keyword);
	}

	@Override
	public List<JSONObject> findList(String provinceCode) {
		List<SchoolInfo> schoolList = schoolRepositry.finList(provinceCode, "", 0, 0);
		List<JSONObject> valueList = new ArrayList<JSONObject>();
		for (SchoolInfo school : schoolList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", school.getCode());
			jsonObject.put("name", school.getName());
			valueList.add(jsonObject);
		}
		return valueList;
	}
}
