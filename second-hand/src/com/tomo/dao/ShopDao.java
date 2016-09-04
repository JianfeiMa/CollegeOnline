/**
 * 
 */
package com.tomo.dao;

import com.tomo.dao.common.BaseDao;
import com.tomo.entity.Shop;
import com.tomo.entity.common.PageModel;

/**
 * @author YHX
 * @date： 日期：2014-1-6 时间：下午3:04:04
 */
public interface ShopDao extends BaseDao<Shop> {
	public void delete(String username,int shopid);
	public PageModel<Shop> findByPager(String string1,String string2,int pageSize, int pageNo);
}
