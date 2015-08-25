package com.fisher.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fisher.common.Constanse;
import com.fisher.entity.AdInfo;
import com.fisher.entity.QueryBeanAndList;
import com.fisher.entity.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HttpClientTest {
	private String url = "http://127.0.0.1:8080/HTTP/GetDataServlet";
	private HttpURLConnection connection = null;
	
	public String doPost(Map<String, String> map){
		try {
			URL u = new URL(url);
			connection = (HttpURLConnection) u.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			out.write(buildParameters(map));
			out.flush();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String strLine = "";
		String strResponse = "";
		InputStream in;
		try {
			in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			while ((strLine = reader.readLine()) != null)
			{
				strResponse += strLine + "\n";
			}
			System.out.print(strResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strResponse;
	}
	
	public void testLogin(){
		Map<String,String> map = new HashMap<String,String>();
		map.put(Constanse.MESSAGE_NAME, "login");
		map.put("username", "fisher");
		map.put("password", "123");
		String strResponse = doPost(map);
		Gson gson = new Gson();
		Result result = gson.fromJson(strResponse, Result.class);
		System.out.print(result.result);
		System.out.print(result.message);
	}
	
	public void testVpAd(){
		Map<String,String> map = new HashMap<String,String>();
		map.put(Constanse.MESSAGE_NAME, "getHomeVpAd");
		map.put("category", "home_vp_ad");
		String strResponse = doPost(map);
		Gson gson = new Gson();
		
		java.lang.reflect.Type type = new TypeToken<QueryBeanAndList<AdInfo,Result>>() {}.getType();  
		QueryBeanAndList<AdInfo,Result> result = gson.fromJson(strResponse, type);
		
		Result r = (Result)result.bean;
		System.out.println(result.list.size());
		System.out.println(r.result);
		System.out.println(r.message);
		
	}
	
	public String buildParameters(Map<String,String> map){
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> entry : map.entrySet()){
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println("parameters:"+sb.toString());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		HttpClientTest test = new HttpClientTest();
		test.testLogin();
		test.testVpAd();
	}

}
