package com.fisher.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
		String messageName = request.getParameter("messageName").trim();
		System.out.println("messageName:" + messageName);


		String classMethod = c.getMethodMap().get(messageName);
		String[] strs = classMethod.split("/");
		System.out.println("Strs 0:"+strs[0]);
		System.out.println("Strs 1:"+strs[1]);
		String result = null;
		try {
			Class clazz = Class.forName(strs[0]);
			Method method = clazz.getDeclaredMethod(strs[1],HttpServletRequest.class);
			result = (String)method.invoke(clazz, request);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
		
	}

}
