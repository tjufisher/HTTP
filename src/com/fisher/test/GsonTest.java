package com.fisher.test;

import java.util.ArrayList;
import java.util.List;

import com.fisher.entity.Complex;
import com.fisher.entity.Result;
import com.google.gson.Gson;

public class GsonTest {

	public static void main(String[] args) {
		Gson gson = new Gson();
		Result result = new Result();
		String strResult = null;
		result.result = "100";
		result.message = "登录成功";
		
		
		Complex complex = new Complex();
		complex.cStr = "测试";
		complex.cReslut = result;
		List sList = new ArrayList<String>();
		List rList = new ArrayList<Result>();
		for(int i = 0; i < 3; i++){
			sList.add("string:"+i);
			rList.add(result);
		}
		complex.cStrList = sList;
		complex.cResultList = rList;
		strResult = gson.toJson(complex);
		System.out.println(strResult);
		
		Bean bean = gson.fromJson(strResult, Bean.class);
		System.out.println(bean.cStr);
		System.out.println(bean.cReslut.result);
		System.out.println(bean.cReslut.message);
		System.out.println(bean.cStrList.size());
		System.out.println(bean.cResultList.get(1).result);

	}
	
	public static class Bean{
		public A cReslut;
		public String cStr;
		public List<String> cStrList;
		public List<A> cResultList;
		
		public static class A{
			public String result;
			public String message;
		}
	}

}
