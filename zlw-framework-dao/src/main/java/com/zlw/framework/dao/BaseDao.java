package com.zlw.framework.dao;

import java.util.List;


/**
 * 数据库操作基类，提供对数据库增、删、改、查、功能
 * @author fukui
 *
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 新增一个对象
	 */
	public int add(T t);

	/**
	 * 更新一个对象
	 */
	public int update(T t);

	/**
	 * 修改记录，只修改只不为空 (null) 的字段
	 */
	public int updateBySelective(T t);

	/**
	 * 删除一个对象
	 * 
	 * @param id
	 *            主键
	 */
	public int delete(Object id);

	/**
	 * 列表总数
	 */
	public int queryByCount(T t);

	/**
	 * 查询列表
	 */
	public List<T> queryByList(T t);

	/**
	 * 根据id查询一个对象
	 */
	public T queryById(Object id);


	/**
	 * 根据条件查询一个对象
	 */
	public T queryUniqueResult(T params);
}
