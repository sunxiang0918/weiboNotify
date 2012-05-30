package com.cuit.sun.weibo.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuit.sun.weibo.AppContext;

@SuppressWarnings("serial")
public class ListServlet  extends HttpServlet {
	
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
		
		List<String> names=AppContext.getInstance().getNeedQueryFriendNames();
		
		resp.getWriter().println("目前已添加关注的有:");
		resp.getWriter().println("==================================");
		for (String string : names) {
			resp.getWriter().println(string);
		}
		resp.getWriter().println("==================================");
	}
}
