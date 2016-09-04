/**
 * 
 */
package com.tomo.dao;

import com.tomo.dao.common.BaseDao;
import com.tomo.entity.Look;
import com.tomo.entity.Message;
import com.tomo.entity.Shop;
import com.tomo.entity.common.PageModel;

/**
 * @author YHX
 * @date： 日期：2014-1-6 时间：下午3:04:59
 */
public interface LookDao extends BaseDao<Look>{
	public void delete(String username,int shopid);
	public PageModel<Look> findByPager(String string1,String string2,int pageSize, int pageNo);
}
