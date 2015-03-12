package com.spring.mvc.project.repository;

import java.util.List;

import com.spring.mvc.project.domain.SchoolInfo;

public interface SchoolRepository {

	/**
	 * 添加信息学校信息
	 * @param schoolInfo
	 * @return
	 */
	public boolean add(SchoolInfo schoolInfo);

	/**
	 * 更新学校的单个信息
	 * @param fieldName
	 * @param fieldValue
	 * @param code
	 * @return
	 */
	public boolean update(String fieldName, String fieldValue, String id);

	/**
	 * 根据学校代码查找学校
	 * @param id
	 * @return
	 */
	public SchoolInfo find(String id);

	/**
	 * 删除学校
	 * @param schoolInfo
	 * @return
	 */
	public void delete(SchoolInfo schoolInfo);

	/**
	 * 根据省代码、学校性质，关键字查询学校列表
	 * @param provinceCode
	 * @param tags
	 * @param keyword
	 * @return
	 */
	public List<SchoolInfo> finList(String provinceCode, String keyword, int pageIndex, int pageSize);

	/**
	 * 根据省代码、学校性质，关键字查询学校对应数目
	 * @param provinceCode
	 * @param tags
	 * @param keyword
	 * @return
	 */
	public int findCount(String provinceCode, String keyword);

}
