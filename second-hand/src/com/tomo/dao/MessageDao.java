/**
 * 
 */
package com.tomo.dao;

import com.tomo.dao.common.BaseDao;
import com.tomo.entity.Message;
import com.tomo.entity.common.PageModel;

/**
 * @author YHX
 * @date： 日期：2014-1-6 时间：下午3:04:59
 */
public interface MessageDao extends BaseDao<Message>{
	public void delete(String username);
	public PageModel<Message> findByPager(String string,int pageSize, int pageNo);
}
