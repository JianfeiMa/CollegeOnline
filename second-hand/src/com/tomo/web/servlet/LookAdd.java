package com.tomo.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomo.common.DaoFactory;
import com.tomo.dao.LookDao;
import com.tomo.entity.Look;

/**
 * @author YHX
 * @date： 日期：2014-2-25 时间：下午1:25:11
 */
public class LookAdd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String lookname = request.getParameter("lookname");
		String username = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		Look look = new Look();
		look.setLookname(lookname);
		look.setUsername(username);
		look.setUserphone(userPhone);
		look.setDescription(description);
		look.setCategory(category);
		Date date = new Date();
		look.setPut_time(date);
		LookDao lookDao = DaoFactory.getInstance("lookDao",
				LookDao.class);
		lookDao.save(look);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("发布求购信息成功");
		
	

	}
}
