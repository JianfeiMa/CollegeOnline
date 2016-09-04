/**
 *  ClassName: BaseDao.java
 *  created on 2011-6-28 上午09:48:58
 *  Copyrights 2011-2012 022tomo.com All rights reserved.
 *  site: http://www.022tomo.com
 *  email: qjyong@gmail.com
 */
package com.tomo.dao.common;

import java.io.Serializable;
import java.util.List;

import com.tomo.entity.common.PageModel;

/**
 * 泛型的通用Dao接口
 * @author yhx
 * DAO=data access object数据库访问对象
 */
public interface BaseDao<T> {

	/**
	 * 新增一个实例
	 * @param entity 要新增的实例 
	 */
	public Serializable save(T entity);
//-----------------------------------------------	
	/**
	 * 根据主键删除一个实例 
	 * @param id 主键
	 */
	public void delete(Serializable id);
//----------------------------------------------
	/**
	 * 编辑指定实例的详细信息
	 * @param entity 实例 
	 */
	public void edit(T entity);
//-----------------------------------------------	
	/**
	 * 根据主键获取对应的实例 
	 * @param id 主键值
	 * @return 如果查询成功，返回符合条件的实例;如果查询失败，返回null
	 */
	public T get(Serializable id);
//------------------------------------------------	
	/**
	 * 获取所有实体实例列表
	 * @return 符合条件的实例列表
	 */
	public List<T> findAll();
//-----------------------------------------------------------	
	/**
	 * 统计总实体实例的数量
	 * @return 总数量
	 */
	public long total();
//------------------------------------------------------	
	/**
	 * 获取分页列表
	 * @param pageNo 当前页号
	 * @param pageSize 每页要显示的记录数
	 * @return 符合分页条件的分页模型实例
	 */
	public PageModel<T> findByPager(int pageSize, int pageNo);
//--------------------------------------------------------------
	/**
	 * 根据指定的SQL语句和参数值执行更新数据的操作
	 * @param sql SQL语句
	 * @param paramValues 参数值数组
	 */
	public void update(String sql, Object... paramValues);
//------------------------------------------------------------	
	/**
	 * 根据指定的SQL语句和参数值执行查询数据的操作
	 * @param sql SQL语句
	 * @param paramValues 参数值
	 * @return 符合条件的实体对象列表
	 */
	public List<T> find(String sql, Object... paramValues);
//--------------------------------------------------------------	
	/**
	 * 根据指定的SQL语句和参数值执行单个对象的查询操作
	 * @param sql SQL语句
	 * @param paramValues 参数值
	 * @return 符合条件的实体对象
	 */
	public T findUnique(String sql, Object... paramValues);
}
