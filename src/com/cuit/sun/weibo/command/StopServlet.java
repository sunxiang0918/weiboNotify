package com.cuit.sun.weibo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuit.sun.weibo.AppCore;

@SuppressWarnings("serial")
public class StopServlet extends HttpServlet {
	
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
		
		boolean success=AppCore.getInstance().stop();
		
		if (success) {
			resp.getWriter().println("关闭扫描器成功!如果要再打开请等待1分钟");
		}else {
			resp.getWriter().println("关闭扫描器失败,请看日志!");
		}
	}
	
}
