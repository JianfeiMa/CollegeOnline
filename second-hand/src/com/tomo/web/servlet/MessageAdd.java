package com.tomo.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomo.common.DaoFactory;
import com.tomo.dao.MessageDao;
import com.tomo.entity.Message;

public class MessageAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String username = request.getParameter("username");
		String receivename = request.getParameter("receivename");
		Message message = new Message();
		message.setContent(content);
		message.setUsername(username);
		message.setReceivename(receivename);
		message.setLeave_time(new Date());
		MessageDao msgDao = DaoFactory.getInstance("messageDao",
				MessageDao.class);
		msgDao.save(message);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("留言成功");
	}

}
