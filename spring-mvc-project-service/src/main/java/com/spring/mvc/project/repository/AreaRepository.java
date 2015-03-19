package com.spring.mvc.project.repository;

import java.util.List;

import com.spring.mvc.project.domain.Area;

public interface AreaRepository {

	/**
	 * 添加新的地区信息
	 * @param area
	 * @return
	 */
	public boolean add(Area area);

	/**
	 * 更新地区信息
	 * @param area
	 * @return
	 */
	public void update(Area area);

	/**
	 * 删除地区信息
	 * @param id
	 * @return
	 */
	public void delete(Area area);

	/**
	 * 根据id,查询地区信息
	 * @param id
	 * @return
	 */
	public Area find(String id);

	/**
	 * 查询数据库中fieldName字段是否有值为fieldValue
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public boolean isExist(String fieldName, String fieldValue);

	/**
	 * 查询地区的列表
	 * @param provinceCode
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Area> findList(String areaCode, String keyword, int pageIndex, int pageSize);

	/**
	 * 查询列表数目
	 * @param provinceCode
	 * @param keyword
	 * @return
	 */
	public int findCount(String areaCode, String keyword);

	/**
	 * 查询市的列表
	 * @param areaCode
	 * @return
	 */
	public List<Area> findCityList(String areaCode);

	/**
	 * 查询区、县的列表
	 * @param areaCode
	 * @return
	 */
	public List<Area> findAreaList(String areaCode);

}
