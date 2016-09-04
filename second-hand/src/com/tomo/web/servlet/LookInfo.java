package com.tomo.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tomo.common.DaoFactory;
import com.tomo.dao.LookDao;
import com.tomo.dao.ShopDao;
import com.tomo.dao.UsersDao;
import com.tomo.entity.ItemList;
import com.tomo.entity.Look;
import com.tomo.entity.Shop;
import com.tomo.entity.Users;
import com.tomo.entity.common.PageModel;

public class LookInfo extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pn = request.getParameter("pageNo");
		String value = request.getParameter("value");
		String condition = request.getParameter("condition");
		String school = request.getParameter("school");
		int pageSize = 10;
		int pageNo = 1;
		List<ItemList> itemLists = new ArrayList<ItemList>();
		if (pn != null && !"".equals(pn)) {
			pageNo = Integer.parseInt(pn);
		}

		LookDao lookDao = DaoFactory.getInstance("lookDao", LookDao.class);
		PageModel<Look> pm = lookDao.findByPager(condition,value, pageSize, pageNo);
		List<Look> list = pm.getData();
		if (list != null) {
			for (Look Look : list) {
				UsersDao usersDao = DaoFactory.getInstance("usersDao",
						UsersDao.class);
				String sql = "select * from users where username = ?";
				String paramValues = Look.getUsername();
				Users user = usersDao.findUnique(sql, paramValues);
				ItemList itemList = new ItemList();
				itemList.setCategory(Look.getCategory());
				itemList.setCourt(user.getCourt());
				itemList.setDescription(Look.getDescription());
				itemList.setPut_time(Look.getPut_time());
				itemList.setSchool(user.getSchool());
				itemList.setShopname(Look.getLookname());
				itemList.setShopId(Look.getLookid());
				itemList.setUserName(user.getUserName());
				itemList.setUserPhone(Look.getUserphone());
				if(school.equals("") || school ==null){
					itemLists.add(itemList);
				}else if(school.equals(itemList.getSchool())){
					itemLists.add(itemList);
				}else {
					
				}
			}
		}
		PageModel<ItemList> pm2 = new PageModel<ItemList>();
		pm2.setData(itemLists);
		pm2.setPageNo(pm.getPageNo());
		pm2.setPageSize(pm.getPageSize());
		pm2.setRecordCount(pm.getRecordCount());
		JSONObject jsonObject = new JSONObject(pm2);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toString());
		response.getWriter().flush();
	}
}
