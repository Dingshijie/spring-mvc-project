package com.spring.mvc.project.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.Area;
import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.repository.AreaRepository;
import com.spring.mvc.project.service.AreaService;

@Service
@Transactional(readOnly = true)
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaRepository areaRepository;

	@Override
	@Transactional(readOnly = false)
	public boolean add(Area area) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return areaRepository.add(area);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(String fieldName, String fieldValue, String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return areaRepository.update(fieldName, fieldValue, id);
		}
		return false;
	}

	@Override
	public Area find(String id) {
		return areaRepository.find(id);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			areaRepository.delete(find(id));
			return true;
		}
		return false;
	}

	@Override
	public List<Area> findList(String areaCode, String keyword, int pageIndex, int pageSize) {
		return areaRepository.findList(areaCode, keyword, pageIndex, pageSize);
	}

	@Override
	public int findCount(String areaCode, String keyword) {
		return areaRepository.findCount(areaCode, keyword);
	}

	@Override
	public List<JSONObject> findCityList(String areaCode) {
		List<Area> list = areaRepository.findCityList(areaCode);
		List<JSONObject> valueList = new ArrayList<JSONObject>();
		for (Area area : list) {
			JSONObject object = new JSONObject();
			object.put("code", area.getCode().substring(0, 4));
			object.put("name", area.getName());
			valueList.add(object);
		}
		return valueList;
	}

	@Override
	public List<JSONObject> findAreaList(String areaCode) {
		List<Area> list = areaRepository.findAreaList(areaCode);
		List<JSONObject> valueList = new ArrayList<JSONObject>();
		for (Area area : list) {
			if (area.getCode().endsWith("00") || area.getName().equals("市辖区") || area.getName().equals("县")) {
				continue;
			}
			JSONObject object = new JSONObject();
			object.put("code", area.getCode());
			object.put("name", area.getName());
			valueList.add(object);
		}
		return valueList;
	}

}
