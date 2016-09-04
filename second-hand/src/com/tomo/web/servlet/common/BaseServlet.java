package com.tomo.web.servlet.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = -7760748474281796208L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//先获取那个用于区分具体业务操作的参数“method”
		String method = request.getParameter("method");
		
		if(method != null){
			if("list".equals(method)){
				doList(request, response);
			}else if("toadd".equals(method)){
				doToAdd(request, response);
			}else if("add".equals(method)){
				doAdd(request, response);
			}else if("delete".equals(method)){
				doDelete(request, response);
			}else if("detail".equals(method)){
				doDetail(request, response);
			}else if("edit".equals(method)){
				doEdit(request, response);
			}else if("avatar".equals(method)){
				avatar(request, response);
			}else{
				//404
			}
		}else{
			//404
		}
	}
	
	public void doList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void doToAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void doAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void doDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void doEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void avatar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
