package com.spring.mvc.project.repository;

import java.util.List;

import com.spring.mvc.project.domain.EduPubCode;

public interface EduPubCodeRepository {

	/**
	 * 添加专业代码
	 * @param eduPubCode
	 * @return
	 */
	public boolean add(EduPubCode eduPubCode);

	/**
	 * 修改专业代码的单个字段信息
	 * @param fieldName
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	public boolean update(EduPubCode eduPubCode);

	/**
	 * 根据id查询专业代码
	 * @param id
	 * @return
	 */
	public EduPubCode find(String id);

	/**
	 * 查询数据库中fieldName字段是否有值为fieldValue
	 * @param eduLevel
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public boolean isExist(String eduLevel, String fieldName, String fieldValue);

	/**
	 * 删除专业代码
	 * @param id
	 * @return
	 */
	public void delete(EduPubCode eduPubCode);

	/**
	 * 根据查询条件查询专业代码的列表
	 * @param eduLevel
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<EduPubCode> findList(String eduLevel, String keyword, int pageIndex, int pageSize);

	/**
	 * 根据查询条件查询专业代码 
	 * @param eduLevel
	 * @param keyword
	 * @return
	 */
	public int findCount(String eduLevel, String keyword);

	/**
	 * 根据关键字查询专业大类(findZYDLMap：ZYDL=专业大类)
	 * @param keyword
	 * @return
	 */
	public List<Object[]> findZYDLMap(String eduLevel, String keyword);

	/**
	 * 根据专业大类和关键字查询专业中类(findZYZLMap：ZYZL=专业中类)
	 * @param keyword
	 * @return
	 */
	public List<Object[]> findZYZLMap(String eduLevel, String firstCode, String keyword);

	/**
	 * 根据专业大类、专业中类和关键字查询专业小类(findZYXLMap：ZYZL=专业小类)
	 * @param keyword
	 * @return
	 */
	public List<Object[]> findZYXLMap(String eduLevel, String secondCode, String keyword);

}
