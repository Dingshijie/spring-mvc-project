package com.spring.mvc.project.serviceImpl;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.domain.UserInfo.Role;
import com.spring.mvc.project.repository.UserRepository;
import com.spring.mvc.project.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isExist(String fieldName, String fieldValue) {
		return userRepository.isExist(fieldName, fieldValue);
	}

	@Override
	public UserInfo findByUsername(String username) {
		if (StringUtils.hasText(username)) {
			return userRepository.findByUsername(username);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateLastLogin(String id, Date date, String ip) {
		userRepository.updateLastLogin(id, date, ip);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean add(UserInfo userInfo) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo user = (UserInfo) currentUser.getPrincipal();
		if ((user.isManager() && !userInfo.isManager()) || (user.isAdministrator() && !userInfo.isAdministrator())) {
			return userRepository.add(userInfo);
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	@Transactional(readOnly = false)
	public boolean update(String paramter, String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo user = (UserInfo) currentUser.getPrincipal();
		UserInfo userInfo = find(id);
		if ((user.isManager() && !userInfo.isManager()) || (user.isAdministrator() && !userInfo.isAdministrator())
				|| user.getId().equals(id)) {
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
			return userRepository.update(sql.toString().replaceFirst(",", ""), id);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(String id) {
		StringBuffer sql = new StringBuffer("isEnable=false");
		return userRepository.update(sql.toString(), id);
	}

	@Override
	public UserInfo find(String id) {
		return userRepository.find(id);
	}

	@Override
	public List<UserInfo> findList(Role role, String areaCode, String schoolCode, String keyword, int pageIndex,
			int pageSize) {
		return userRepository.findList(role, areaCode, schoolCode, keyword, pageIndex, pageSize);
	}

	@Override
	public int findCount(Role role, String areaCode, String schoolCode, String keyword) {
		return userRepository.findCount(role, areaCode, schoolCode, keyword);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean ModifyPassword(String password) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo != null) {
			return userRepository.ModifyPassword(userInfo.getId(), password);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean ResetPassword(String id) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
		if (userInfo.isManager()) {
			//密码重置设置为6个1
			String password = MD5("111111");
			return userRepository.ModifyPassword(id, password);
		}
		return false;
	}

	/**
	 * MD5加密
	 * @param input
	 * @return
	 */
	public String MD5(String input) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] btInput = input.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
