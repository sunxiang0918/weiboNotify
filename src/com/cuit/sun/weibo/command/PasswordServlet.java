package com.cuit.sun.weibo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuit.sun.weibo.AppContext;

@SuppressWarnings("serial")
public class PasswordServlet extends HttpServlet {
	
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
		
		String password=req.getParameter("password");
		if (password==null||password.trim().equals("")) {
			resp.getWriter().println("更改Google密码失败,输入的密码为空");
			return;
		}
		
		AppContext.getInstance().setGooglePassword(password);
		
		resp.getWriter().println("更改Google密码成功,请重启!");
	}
	
}
