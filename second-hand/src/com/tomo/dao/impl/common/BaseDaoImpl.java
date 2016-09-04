
package com.tomo.dao.impl.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.tomo.common.DbHelper;
import com.tomo.dao.common.BaseDao;
import com.tomo.entity.common.PageModel;
/**
 * 实现基本数据访问对象的抽象类
 * @author YHX
 * @date： 日期：2014-1-6 时间：下午3:06:16
 * @param <T>
 */
@SuppressWarnings({"unchecked","rawtypes"})
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	/** 当前操作的实体的类型信息 */
	protected Class clazz;
	/** 实体对应的表名 */
	protected String tableName;
	/** QueryRunner对象*/
	protected QueryRunner qr = new QueryRunner();
	protected BeanHandler<T> beanHandler; 
	protected BeanListHandler<T> beanListHandler;
	/**标量处理器，梯量处理器*/
	protected ScalarHandler<Long> scalarHandler;
	
	public BaseDaoImpl(){
		//通过反射机制获取子类传递过来的实体类的类型信息
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class)type.getActualTypeArguments()[0];
		
		beanHandler = new BeanHandler<T>(clazz);
		beanListHandler = new BeanListHandler<T>(clazz);
		scalarHandler = new ScalarHandler<Long>();
		
		this.tableName = clazz.getSimpleName().toLowerCase();
	}

	public void delete(Serializable id) {
		Connection conn = null;
		// delete from 表名 wher 主键列名=值
		String sql = "DELETE FROM "+ getTableName() + " WHERE " + getPKName() + "=?";
		try {
			conn = DbHelper.getConn();
			qr.update(conn, sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
	}
	
	public List<T> findAll() {
		List<T> list = null;
		
		Connection conn = null;
		//select * from 表名
		String sql = "SELECT * FROM "+ getTableName();
		try {
			conn = DbHelper.getConn();
			list = qr.query(conn, sql, beanListHandler);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
		return list;
	}

	public PageModel<T> findByPager(int pageSize, int pageNo) {
		PageModel<T> pm = new PageModel<T>(pageSize, pageNo);
		
		String sql2 = "SELECT * FROM " + getTableName() + " ORDER BY " + getPKName() + " DESC limit ?,?";
		Object[] paramValues = {(pageNo - 1) * pageSize, pageSize};
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			long count = total();
			if(count > 0){
				pm.setRecordCount(count);
				pm.setData(qr.query(conn,sql2, beanListHandler, paramValues));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
		return pm;
	}

	public T get(Serializable id) {
		T t = null;
		Connection conn = null;
		//select * from 表名 where 主键列名=?
		String sql = "SELECT * FROM "+ getTableName() + " WHERE " + getPKName() + "=?";
		try {
			conn = DbHelper.getConn();
			t = qr.query(conn, sql, beanHandler, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
		
		return t;
	}
	
	public long total() {
		long count = 0;
		Connection conn = null;
		String sql = "SELECT count(" + getPKName() + ") FROM "+ getTableName();
		try {
			conn = DbHelper.getConn();
			Long temp = (Long)qr.query(conn, sql, scalarHandler);
			if(temp != null){
				count = temp.longValue();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
		return count;
	}
	

	public void edit(T entity){
		Connection conn = null;
		List<Object> valueList = new ArrayList<Object>();
		//update 表名 set 列=?,列=? where 主键列名=?
		StringBuilder sql = new StringBuilder("UPDATE ");
		sql.append(getTableName());
		sql.append(" SET ");
		Map<String,Object> fieldValueMap = getFieldValueMap(entity);
		Object idValue = fieldValueMap.get(getPKName());
		fieldValueMap.remove(getPKName());
		int i = 0;
		for(Entry<String, Object> entry : fieldValueMap.entrySet()){
			if(i++ > 0){
				sql.append(",");
			}
			sql.append(entry.getKey() + "=?");
			valueList.add(entry.getValue());
		}
		sql.append(" WHERE ").append(getPKName()).append("=?");
		valueList.add(idValue);
		
		//System.out.println(sql.toString());
		//System.out.println(valueList);
		
		try {
			conn = DbHelper.getConn();
			qr.update(conn, sql.toString(), valueList.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
	}
	
	public Serializable save(T entity){
		Long id = Long.valueOf(0);
		Connection conn = null;
		//insert into 表名(列名,列名...) values(?,?...);
		StringBuilder sql = new StringBuilder("INSERT INTO ");
		sql.append(getTableName());
		sql.append("(");
		
		StringBuilder values = new StringBuilder("(");
		List<Object> valueList = new ArrayList<Object>();
		Map<String,Object> fieldValueMap = getFieldValueMap(entity);
		
		fieldValueMap.remove(getPKName());
		
		int i = 0;
		for(Entry<String, Object> entry : fieldValueMap.entrySet()){
			if(i++ > 0){
				sql.append(",");
				values.append(",");
			}
			sql.append(entry.getKey());
			values.append("?");
			valueList.add(entry.getValue());
		}
		sql.append(")");
		values.append(")");
		sql.append(" VALUES").append(values);
		
		//System.out.println(sql.toString());
		//System.out.println(valueList);
		try {
			conn = DbHelper.getConn();
			
			qr.update(conn, sql.toString(), valueList.toArray());
			
			//获取最新插入的这条数据的ID
			BigInteger bi = qr.query(conn, "select @@identity", new ScalarHandler<BigInteger>());
			
			id = Long.valueOf(bi.longValue());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
		
		return id;
	}

	
	/**
	 * 根据指定的SQL语句和参数值执行更新数据的操作
	 * @param sql SQL语句
	 * @param paramValues 参数值数组
	 */
	public void update(String sql, Object... paramValues){
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			qr.update(conn, sql, paramValues);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
	}
	
	/**
	 * 根据指定的SQL语句和参数值执行查询数据的操作
	 * @param sql SQL语句
	 * @param paramValues 参数值数组
	 * @return 符合条件的实体对象列表
	 */
	public List<T> find(String sql, Object... paramValues){
		List<T> list = null;
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			list = qr.query(conn, sql, beanListHandler, paramValues);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
		return list;
	}
	/**
	 * 根据指定的SQL语句和参数值执行单个对象的查询操作
	 * @param sql SQL语句
	 * @param paramValues 参数值数组
	 * @return 符合条件的实体对象
	 */
	public T findUnique(String sql, Object... paramValues){
		T t = null;
		
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			t = qr.query(conn, sql, beanHandler, paramValues);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
		return t;
	}
	
	/**
	 * 获取该实体类对应的表名，默认为全小写的实体类名。如果需要更改，请在子类中重写本方法
	 * @return 表名
	 */
	protected String getTableName(){
		return tableName;
	}
	
	/**
	 * 获取主键列名，默认值为“id”。如果需要更改，请在子类中重写本方法
	 * @return 主键列名
	 */
	protected String getPKName(){
		return "id";
	}
	
	/**
	 * 获取指定实例的所有属性名及对应值的Map实例
	 * @param entity 实例
	 * @return 字段名及对应值的Map实例
	 */
	protected Map<String, Object> getFieldValueMap(T entity){
		//key是属性名,value是对应值
		Map<String, Object> fieldValueMap = new HashMap<String, Object>();
		
		//获取当前加载的实体类中所有属性(字段)
		Field[] fields = this.clazz.getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++){
			Field f = fields[i];
			String name = f.getName(); //属性名
			if(!"serialVersionUID".equals(name)){
				//System.out.println("字段的名：" + name);
				
				f.setAccessible(true);//取消 Java 语言访问检查
				try {
					Object value = f.get(entity); //反射获取该属性的值
					
					fieldValueMap.put(name, value);
					
					//System.out.println("字段值：" + value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		return fieldValueMap;
	}
}