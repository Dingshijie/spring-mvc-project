package com.spring.mvc.project.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.project.domain.EduPubCode;
import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.repository.EduPubCodeRepository;
import com.spring.mvc.project.service.EduPubCodeService;

@Service
@Transactional(readOnly = true)
public class EduPubCodeServiceImpl implements EduPubCodeService {

	@Autowired
	private EduPubCodeRepository eduPubCodeRepository;

	@Override
	@Transactional(readOnly = false)
	public boolean add(EduPubCode eduPubCode) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			return eduPubCodeRepository.add(eduPubCode);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(EduPubCode eduPubCode) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			eduPubCodeRepository.update(eduPubCode);
			return true;
		}
		return false;
	}

	@Override
	public EduPubCode find(String id) {
		return eduPubCodeRepository.find(id);
	}

	@Override
	public boolean isExist(String fieldName, String fieldValue) {
		return eduPubCodeRepository.isExist(fieldName, fieldValue);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			eduPubCodeRepository.delete(find(id));
			return true;
		}
		return false;
	}

	@Override
	public List<EduPubCode> findList(String eduLevel, String keyword, int pageIndex, int pageSize) {
		return eduPubCodeRepository.findList(eduLevel, keyword, pageIndex, pageSize);
	}

	@Override
	public int findCount(String eduLevel, String keyword) {
		return eduPubCodeRepository.findCount(eduLevel, keyword);
	}

	@Override
	public List<Map<String, String>> findZYDLMap(String eduLevel, String keyword) {
		List<Object[]> list = eduPubCodeRepository.findZYDLMap(eduLevel, keyword);
		return changeObjectToMap(list);
	}

	@Override
	public List<Map<String, String>> findZYZLMap(String eduLevel, String firstCode, String keyword) {
		List<Object[]> list = eduPubCodeRepository.findZYZLMap(eduLevel, firstCode, keyword);
		return changeObjectToMap(list);
	}

	@Override
	public List<Map<String, String>> findZYXLMap(String eduLevel, String secondCode, String keyword) {
		List<Object[]> list = eduPubCodeRepository.findZYXLMap(eduLevel, secondCode, keyword);
		return changeObjectToMap(list);
	}

	public List<Map<String, String>> changeObjectToMap(List<Object[]> list) {
		List<Map<String, String>> value_list = new ArrayList<Map<String, String>>();
		for (Object[] obj : list) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("code", obj[0].toString());
			map.put("name", obj[1].toString());
			value_list.add(map);
		}
		return value_list;
	}

}
