package com.tomo.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.tomo.common.DbHelper;
import com.tomo.dao.CollectionDao;
import com.tomo.dao.impl.common.BaseDaoImpl;
import com.tomo.entity.Collect;
import com.tomo.entity.common.PageModel;

public class CollectionDaoImpl extends BaseDaoImpl<Collect> implements CollectionDao{
	@Override
	protected String getPKName() {
		return "collectionid";
	}
	@Override
	public PageModel<Collect> findByPager(String string, int pageSize,
			int pageNo) {
        PageModel<Collect> pm = new PageModel<Collect>(pageSize, pageNo);
		
		String sql2 = "SELECT * FROM " + getTableName() + " where username = ? ORDER BY " + getPKName() + " DESC limit ?,?";
		Object[] paramValues = {string ,(pageNo - 1) * pageSize, pageSize};
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
	public void delete(String username,int shopid) {
		Connection conn = null;
		if(!username.equals("") && username != null){
			String sql = "DELETE FROM "+ getTableName() + " WHERE username=?";
			try {
				conn = DbHelper.getConn();
				qr.update(conn, sql, username);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DbHelper.close(conn);
			}
		}else {
			String sql = "DELETE FROM "+ getTableName() + " WHERE shopid=?";
			try {
				conn = DbHelper.getConn();
				qr.update(conn, sql,shopid);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DbHelper.close(conn);
			}
		}
	}
}
