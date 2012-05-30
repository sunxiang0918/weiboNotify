package com.cuit.sun.weibo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuit.sun.weibo.AppContext;

@SuppressWarnings("serial")
public class ChangeScheduledServlet extends HttpServlet {
	
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
		
		String string=req.getParameter("time");
		
		if (string==null||"".equals(string.trim())) {
			resp.getWriter().println("更改扫描时间失败,输入参数不正确");
			return;
		}
		
		int minuts=30;
		try {
			minuts=Integer.parseInt(string);
		} catch (Exception e) {
			resp.getWriter().println("更改扫描时间失败,输入参数不正确");
			return;
		}
		
		AppContext.getInstance().setMinuts(minuts);
		
		resp.getWriter().println("更改扫描时间:"+minuts+"成功!请重启!!");
	}
	
}

