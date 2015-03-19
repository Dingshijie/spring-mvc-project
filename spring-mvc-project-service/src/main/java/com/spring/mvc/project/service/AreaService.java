package com.spring.mvc.project.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.spring.mvc.project.domain.Area;

public interface AreaService {

	/**
	 * 添加新的地区信息
	 * @param area
	 * @return
	 */
	public boolean add(Area area);

	/**
	 * 更新地区信息
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	public boolean update(Area area);

	/**
	 * 删除地区信息
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * 根据id查询地区信息
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
	 * 查询列表数据
	 * @param provinceCode
	 * @param keyword
	 * @return
	 */
	public int findCount(String areaCode, String keyword);

	/**
	 * 查询某省份下的市列表
	 * @param provinceCode
	 * @return
	 */
	public List<JSONObject> findCityList(String areaCode);

	/**
	 * 查询某省份下的区、县列表
	 * @param provinceCode
	 * @return
	 */
	public List<JSONObject> findAreaList(String areaCode);

}
