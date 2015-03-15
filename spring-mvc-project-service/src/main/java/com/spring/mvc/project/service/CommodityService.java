package com.spring.mvc.project.service;

import java.util.List;

import javax.activation.CommandInfo;

import com.spring.mvc.project.domain.Category;

public interface CommodityService {

	/**
	 * 添加商品
	 * @param commandInfo
	 * @return
	 */
	public boolean add(CommandInfo commandInfo);

	/**
	 * 更新商品信息
	 * @param commandInfo
	 */
	public void update(CommandInfo commandInfo);

	/**
	 * 更新商品的某些字段的信息
	 * @param id
	 * @param fieldName
	 * @param fieldValue
	 */
	public boolean update(String paramter, String id);

	/**
	 * 删除商品
	 * @param commandInfo
	 */
	public void delete(CommandInfo commandInfo);

	/**
	 * 根据条件查询商品列表
	 * @param category 类别
	 * @param status 状态，在售或下架
	 * @param recommend 是否在推广
	 * @param used 是否是二手
	 * @param pageIndex 页码数
	 * @param pageSize 每页显示的条目数
	 * @return
	 */
	public List<CommandInfo> findList(Category category, int status, int recommend, int used, int pageIndex,
			int pageSize);

	/**
	 * 根据条件查询商品数目
	 * @param category 类别
	 * @param status 状态，在售或下架
	 * @param recommend 是否在推广
	 * @param used 是否是二手
	 * @param pageSize 每页显示的条目数
	 * @return
	 */
	public List<CommandInfo> findCount(Category category, int status, int recommend, int used);

}
