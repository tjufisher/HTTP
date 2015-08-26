package com.fisher.data;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fisher.db.H2Manager;
import com.fisher.entity.AdInfo;
import com.fisher.entity.Complex;
import com.fisher.entity.QueryBeanAndList;
import com.fisher.entity.Result;
import com.google.gson.Gson;

public class AdInfoData {
	public static String getHomeVpAd(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category").trim();
		
		H2Manager manager = new H2Manager();
		String sql = "select * from adinfo where category='"
				+ category + "'" ;
		System.out.println(sql);
		ResultSet rs = manager.executeQuery(sql);
		
		Gson gson = new Gson();
		QueryBeanAndList<AdInfo,Result> query = new QueryBeanAndList<AdInfo,Result>();
		List<AdInfo> adInfoList = new ArrayList<AdInfo>();
		Result result = new Result();
		
		try {
			while(rs.next()){//从1开始，不是0！！！
				AdInfo adInfo = new AdInfo();
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				adInfo.id = rs.getInt(1);
				adInfo.category = rs.getString(2);
				adInfo.imgUrl = rs.getString(3);
				adInfo.adUrl = rs.getString(4);
				adInfoList.add(adInfo);
			}
		} catch (SQLException e) {
			result.result = "101";
			result.message = "数据获取失败";
			e.printStackTrace();
		}
		manager.close();
		if( adInfoList.size() > 0){
			result.result = "100";
			result.message = "数据获取成功，共"+adInfoList.size()+"条";
		}
		query.list = adInfoList;
		query.bean = result;
		
		return gson.toJson(query);
	}

}
