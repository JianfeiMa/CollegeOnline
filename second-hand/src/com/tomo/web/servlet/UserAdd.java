package com.tomo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomo.common.DaoFactory;
import com.tomo.dao.UsersDao;
import com.tomo.entity.Users;

/**
 * @author YHX
 * @date： 日期：2014-1-9 时间：下午3:50:50
 */
public class UserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String school = request.getParameter("school");
		String court = request.getParameter("court");
		String professional = request.getParameter("professional");
		UsersDao usersDao = DaoFactory.getInstance("usersDao", UsersDao.class);
		Users user = new Users();
		user = usersDao.findUnique(
				"select username from users where username = ?", username);
		response.setContentType("text/html;charset=UTF-8");
		if (user != null) {

			response.getWriter().print("用户名已被注册");
			response.getWriter().flush();
		} else {
			user = new Users();
			user.setEmail(email);
			user.setUserName(username);
			user.setPassword(pwd);
			user.setCourt(court);
			user.setSchool(school);
			user.setProfessional(professional);
			usersDao.save(user);
			response.getWriter().print("注册成功");
			response.getWriter().flush();
		}
	}
}
