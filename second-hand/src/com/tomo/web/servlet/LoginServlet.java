package com.tomo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tomo.common.DaoFactory;
import com.tomo.dao.UsersDao;
import com.tomo.entity.Users;

/**
 * 
 * @author YHX
 * @date： 日期：2014-1-9 时间：下午3:42:59
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDao usersDao = DaoFactory.getInstance("usersDao",
			UsersDao.class);
	private Users user;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String sql = "select * from users where username = ?";
		String paramValues = username;
		user = usersDao.findUnique(sql, paramValues);
		response.setContentType("text/html;charset=UTF-8");
		if (user == null) {
			response.getWriter().print("error1");

		} else {
			if (!user.getPassword().equalsIgnoreCase(pwd)) {
				response.getWriter().print("error2");
			} else {
				request.getSession().setAttribute("user", user);
				JSONObject obj = new JSONObject(user);
				String json = obj.toString();
				response.getWriter().print(json);
			}
		}
		response.getWriter().flush();
	}
}