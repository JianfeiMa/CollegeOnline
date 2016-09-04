package com.tomo.dao;

import com.tomo.dao.common.BaseDao;
import com.tomo.entity.Collect;
import com.tomo.entity.common.PageModel;

public interface CollectionDao  extends BaseDao<Collect> {
	public void delete(String username,int shopid);
	public PageModel<Collect> findByPager(String string,int pageSize, int pageNo);
}
