/**
 * 
 */
package com.tomo.dao.impl;

import com.tomo.dao.UsersDao;
import com.tomo.dao.impl.common.BaseDaoImpl;
import com.tomo.entity.Users;
/**
 * @author YHX
 * @date： 日期：2014-1-6 时间：下午3:03:18
 */
//                        继承        BaseDaoImpl(baseDao)          实现UsersDao(baseDao)接口
public class UsersDaoImpl extends BaseDaoImpl<Users> implements UsersDao{
	
	@Override
	protected String getPKName() {
		return "userid";
	}
}
