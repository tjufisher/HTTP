package com.fisher.data;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.fisher.db.H2Manager;
import com.fisher.entity.Complex;
import com.fisher.entity.Result;
import com.google.gson.Gson;

public class UserInfoData {
	public static String login(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		H2Manager manager = new H2Manager();
		String sql = "select * from userinfo where username='"
				+ username + "' and password='" + password + "'";
		System.out.println(sql);
		ResultSet rs = manager.executeQuery(sql);
		Gson gson = new Gson();
		Result result = new Result();
		String strResult = null;
		
		try {
			if(rs.next()){
				manager.close();
				result.result = "100";
				result.message = "登录成功";
			}else{
				manager.close();
				result.result = "101";
				result.message = "登录失败";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		strResult = gson.toJson(result);
		
//		Complex complex = new Complex();
//		complex.cStr = "测试";
//		complex.cReslut = result;
//		for(int i = 0; i < 3; i++){
//			complex.cStrList.add("string:"+i);
//			complex.cResultList.add(result);
//		}
//		strResult = gson.toJson(complex.cStr);
//		System.out.println(strResult);
		return strResult;
	}

}
