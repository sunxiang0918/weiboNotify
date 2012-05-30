package com.cuit.sun.weibo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuit.sun.weibo.AppCore;

@SuppressWarnings("serial")
public class AddServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		
		String name=req.getParameter("name");
		
		boolean success=AppCore.getInstance().add(name);
		
		if (success) {
			resp.getWriter().println("添加要关注的微博:"+name+" 成功!");
		}else {
			resp.getWriter().println("添加要关注的微博:"+name+" 失败!请看日志!");
		}
	}
}