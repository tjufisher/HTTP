package com.fisher.data;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fisher.common.StringUtils;
import com.fisher.db.H2Manager;
import com.fisher.entity.AdInfo;
import com.fisher.entity.Complex;
import com.fisher.entity.Content;
import com.fisher.entity.QueryBeanAndList;
import com.fisher.entity.Result;
import com.google.gson.Gson;

public class HomeContentInfoData {
	
	public static String getHomeList(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		int rows = 5;
		int times = 0;
		
		String tmp = request.getParameter("rows").trim();
		if( tmp != null && StringUtils.canParseInt(tmp)){
			rows = Integer.parseInt(tmp);
		}
		tmp = request.getParameter("times").trim();
		if( tmp != null && StringUtils.canParseInt(tmp)){
			times = Integer.parseInt(tmp);
		}
		
		
		H2Manager manager = new H2Manager();
		//逆序取得，rows * times 到  ( times + 1) * rows行的数据
		String sql = "select * from( select top "
				+ rows
				+ " * from ( select top "
				+ ( times + 1) * rows
				+ " * from content  order by id desc )  order by id asc) order by id desc;" ;
		System.out.println(sql);
		ResultSet rs = manager.executeQuery(sql);
		
		Gson gson = new Gson();
		QueryBeanAndList<Content,Result> query = new QueryBeanAndList<Content,Result>();
		List<Content> contentList = new ArrayList<Content>();
		Result result = new Result();
		
		try {
			while(rs.next()){//从1开始，不是0！！！
				Content content = new Content();
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				content.id = rs.getInt("id");
				content.title = rs.getString("title");
				content.latitude = rs.getDouble("latitude");
				content.longitude = rs.getDouble("longitude");
				content.description = rs.getString("description");
				content.imgUrl = rs.getString("imgUrl");
				content.rate = rs.getInt("rate");
				content.rateNum = rs.getInt("ratenum");
				content.sellPrice = rs.getDouble("sellprice");
				content.sellNum = rs.getInt("sellnum");
				content.tag = rs.getString("tag");
				content.adUrl = rs.getString("adurl");
				contentList.add(content);
			}
		} catch (SQLException e) {
			result.result = "101";
			result.message = "数据获取失败";
			e.printStackTrace();
		}
		manager.close();
		if( contentList.size() > 0){
			result.result = "100";
			result.message = "数据获取成功，共"+contentList.size()+"条";
		}
		query.list = contentList;
		query.bean = result;
		
		return gson.toJson(query);
	}

}
