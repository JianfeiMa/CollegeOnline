/**
 * 
 */
package com.tomo.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.tomo.common.DbHelper;
import com.tomo.dao.MessageDao;
import com.tomo.dao.impl.common.BaseDaoImpl;
import com.tomo.entity.Message;
import com.tomo.entity.common.PageModel;

/**
 * @author YHX
 * @date： 日期：2014-1-6 时间：下午3:12:22
 */
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {
	@Override
	protected String getPKName() {
		return "messageId";
	}

	@Override
	public PageModel<Message> findByPager(String string, int pageSize,
			int pageNo) {
		PageModel<Message> pm = new PageModel<Message>(pageSize, pageNo);

		String sql2 = "SELECT * FROM " + getTableName() + "  where  receivename = ? ORDER BY "
				+ getPKName() + " DESC limit ?,?";
		Object[] paramValues = {string, (pageNo - 1) * pageSize, pageSize };
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			long count = total();
			if (count > 0) {
				pm.setRecordCount(count);
				pm.setData(qr.query(conn, sql2, beanListHandler, paramValues));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbHelper.close(conn);
		}
		return pm;
	}

	@Override
	public void delete(String username) {
		Connection conn = null;
		// delete from 表名 wher 主键列名=值
		String sql = "DELETE FROM "+ getTableName() + " where  receivename =?";
		try {
			conn = DbHelper.getConn();
			qr.update(conn, sql, username);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbHelper.close(conn);
		}
	}

}
