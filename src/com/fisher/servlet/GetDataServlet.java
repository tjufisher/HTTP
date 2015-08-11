package com.fisher.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fisher.common.Constanse;
import com.fisher.data.UserInfoData;

public class GetDataServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		super.doGet(request, response);
		System.out.println("get");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Constanse c = new Constanse();
		System.out.println("post");
		String messageName = request.getParameter("messageName");
		System.out.println("messageName:" + messageName);
		Integer type = c.getMessageMap().get(messageName);
		
		String str = null;
		switch(type){
		case 0:
			str = UserInfoData.login(request);
			break;
		case 1:
			
			break;
		default:
			break;
		}
		
		PrintWriter out = response.getWriter();
        out.println(str);
        out.flush();
        out.close();
		
	}

}
