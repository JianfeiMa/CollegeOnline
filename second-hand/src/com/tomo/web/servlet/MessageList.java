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
import com.tomo.dao.MessageDao;
import com.tomo.entity.ItemList;
import com.tomo.entity.Message;
import com.tomo.entity.common.PageModel;

public class MessageList extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pn = request.getParameter("pageNo");
		String receivename = request.getParameter("receivename");
		int pageSize = 10;
		int pageNo = 1;
		if (pn != null && !"".equals(pn)) {
			pageNo = Integer.parseInt(pn);
		}
		MessageDao msgDao = DaoFactory.getInstance("messageDao",
				MessageDao.class);
		PageModel<Message> pm = msgDao.findByPager(receivename, pageSize, pageNo);
		if(pm.getData()==null){
			pm.setData(new ArrayList<Message>());
		}
		JSONObject jsonObject = new JSONObject(pm);
		System.out.println(pm);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(jsonObject.toString());
		response.getWriter().flush();
	}

}
