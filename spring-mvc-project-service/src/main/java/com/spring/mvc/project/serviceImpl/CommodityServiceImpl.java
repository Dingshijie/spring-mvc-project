package com.spring.mvc.project.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.CommodityInfo;
import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.repository.CommodityRepository;
import com.spring.mvc.project.service.CommodityService;

@Service
@Transactional(readOnly = true)
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	private CommodityRepository commodityRepository;

	@Override
	@Transactional(readOnly = false)
	public boolean add(CommodityInfo commodity) {
		return commodityRepository.add(commodity);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(CommodityInfo commodity) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		//添加权限，管理员可以更新，用户自己也可以更新
		commodityRepository.update(commodity);

		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(String paramter, String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		CommodityInfo CommodityInfo = find(id);
		//		if (userInfo.isManager() || userInfo.isAdministrator()) {
		StringBuffer sql = new StringBuffer();
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.parseObject(paramter);
		Set set = jsonObject.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			String value = jsonObject.get(key).toString();
			sql.append(",");
			sql.append(key + "='" + value + "'");
		}
		return commodityRepository.update(sql.toString().replaceFirst(",", ""), id);
		//		}
		//		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(CommodityInfo commodity) {
		commodityRepository.delete(commodity);

	}

	@Override
	public CommodityInfo find(String id) {
		return commodityRepository.find(id);
	}

	@Override
	public List<CommodityInfo> findList(String category, String areaCode, String schoolCode, int status, int recommend,
			int used, String keyword, int pageIndex, int pageSize) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager() || userInfo.isAdministrator()) {
			return commodityRepository.findList("", category, areaCode, schoolCode, status, recommend, used, keyword,
					pageIndex, pageSize);
		} else {
			return commodityRepository.findList(userInfo.getUsername(), category, areaCode, schoolCode, status,
					recommend, used, keyword, pageIndex, pageSize);
		}
	}

	@Override
	public int findCount(String category, String areaCode, String schoolCode, int status, int recommend, int used,
			String keyword) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager() || userInfo.isAdministrator()) {
			return commodityRepository.findCount("", category, areaCode, schoolCode, status, recommend, used, keyword);
		} else {
			return commodityRepository.findCount(userInfo.getUsername(), category, areaCode, schoolCode, status,
					recommend, used, keyword);
		}
	}
}
