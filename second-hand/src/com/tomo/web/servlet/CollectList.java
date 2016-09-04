package com.tomo.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tomo.common.DaoFactory;
import com.tomo.dao.CollectionDao;
import com.tomo.dao.ShopDao;
import com.tomo.dao.UsersDao;
import com.tomo.entity.Collect;
import com.tomo.entity.ItemList;
import com.tomo.entity.Shop;
import com.tomo.entity.Users;
import com.tomo.entity.common.PageModel;

public class CollectList extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String pn = request.getParameter("pageNo");
		String username = request.getParameter("username");
		int pageSize = 10;
		int pageNo = 1;
		List<ItemList> itemLists = new ArrayList<ItemList>();
		if (pn != null && !"".equals(pn)) {
			pageNo = Integer.parseInt(pn);
		}
		CollectionDao  cDao = DaoFactory.getInstance("collectionDao",CollectionDao.class);
		PageModel<Collect> pm = cDao.findByPager(username,pageSize, pageNo);
		List<Collect> list = pm.getData();
		if(list != null){
			for(Collect collect : list){
				String sql = "select * from shop where shopid = ?";
				int paramValues = Integer.valueOf(collect.getShopId());
				ShopDao shopDao = DaoFactory.getInstance("shopDao", ShopDao.class);
				Shop shop = shopDao.findUnique(sql, paramValues);
				UsersDao usersDao = DaoFactory.getInstance("usersDao",
						UsersDao.class);
				String sql2 = "select * from users where username = ?";
				String paramValues2 = shop.getUserName();
				Users user = usersDao.findUnique(sql2, paramValues2);
				ItemList itemList = new ItemList();
				itemList.setCategory(shop.getCategory());
				itemList.setCourt(user.getCourt());
				itemList.setDescription(shop.getDescription());
				itemList.setPicture(shop.getPicture());
				itemList.setPrice(shop.getPrice());
				itemList.setPut_time(shop.getPut_time());
				itemList.setSchool(user.getSchool());
				itemList.setShopname(shop.getShopname());
				itemList.setShopId(shop.getShopId());
				itemList.setUserName(user.getUserName());
				itemList.setUserPhone(shop.getUserPhone());
				itemList.setUserPhone(shop.getUserPhone());
				itemLists.add(itemList);
			}
		}
		PageModel<ItemList> pm2 = new PageModel<ItemList>();
		pm2.setData(itemLists);
		pm2.setPageNo(pm.getPageNo());
		pm2.setPageSize(pm.getPageSize());
		pm2.setRecordCount(pm.getRecordCount());
		JSONObject jsonObject = new JSONObject(pm2);
		System.out.println(pm2);
		System.out.println(jsonObject);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toString());
		response.getWriter().flush();
	}

}
