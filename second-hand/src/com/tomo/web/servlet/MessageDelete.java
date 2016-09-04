package com.tomo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomo.common.DaoFactory;
import com.tomo.dao.MessageDao;

public class MessageDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		MessageDao msgDao = DaoFactory.getInstance("messageDao",
				MessageDao.class);
		msgDao.delete(username);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("删除成功");
	}

}
