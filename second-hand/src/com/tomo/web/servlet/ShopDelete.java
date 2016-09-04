package com.tomo.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomo.common.DaoFactory;
import com.tomo.dao.ShopDao;

public class ShopDelete extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String shopId = request.getParameter("shopoid");
		System.out.println(username+shopId.toString());
		ShopDao shopDao = DaoFactory.getInstance("shopDao", ShopDao.class);
		shopDao.delete(username,Integer.valueOf(shopId));
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("删除成功");
	}
}
