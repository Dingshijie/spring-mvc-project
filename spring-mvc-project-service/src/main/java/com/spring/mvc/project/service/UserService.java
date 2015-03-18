package com.spring.mvc.project.service;

import java.util.Date;
import java.util.List;

import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.domain.UserInfo.Role;

public interface UserService {

	/**
	 * 检查用户名是否被占用
	 * @param 字段名
	 * @param 字段值
	 * @return
	 */
	public boolean isExist(String fieldName, String fieldValue);

	/**
	 * 根据用户名称查询用户
	 * @param username
	 * @return
	 */
	public UserInfo findByUsername(String username);

	/**
	 * 修改最后一次的登陆时间
	 * @param id
	 * @param date
	 */
	public void updateLastLogin(String id, Date date, String ip);

	/**
	 * 添加新的用户
	 * @param userInfo
	 * @return
	 */
	public boolean add(UserInfo userInfo);

	/**
	 * 更新用户的某个信息
	 * @param paramter
	 * @param id
	 * @return
	 */
	public boolean update(String paramter, String id);

	/**
	 * 根据id停用用户
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public UserInfo find(String id);

	/**
	 * 根据角色查询用户列表
	 * @param role
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<UserInfo> findList(Role role, String areaCode, String schoolCode, String keyword, int pageIndex,
			int pageSize);

	/**
	 * 根据角色查询用户列表的条目
	 * @param role
	 * @param keyword
	 * @return
	 */
	public int findCount(Role role, String areaCode, String schoolCode, String keyword);

}
