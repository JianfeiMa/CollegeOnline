package com.tomo.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomo.common.DaoFactory;
import com.tomo.dao.CollectionDao;
import com.tomo.entity.Collect;

public class CollectionAdd extends HttpServlet {
	private Collect collect;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String shopId = request.getParameter("shopid");
		String userName = request.getParameter("username");
		System.out.println(userName+shopId);
		CollectionDao dao = DaoFactory.getInstance("collectionDao", CollectionDao.class);
		collect = dao.findUnique("select * from collect where shopid = ?", Integer.valueOf(shopId));
		if(collect == null){
			collect = new Collect();
			collect.setShopId(Integer.valueOf(shopId));
			collect.setUserName(userName);
			dao.save(collect);
			response.getWriter().print("收藏成功");
			response.getWriter().flush();
		}else {
			response.getWriter().print("也被收藏");
			response.getWriter().flush();
		}
		
	}
	

}
