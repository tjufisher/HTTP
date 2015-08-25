package com.fisher.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constanse {
	public static final String MESSAGE_NAME = "messageName";
	
	private Map<String, String> methodMap;
	public Constanse(){
		methodMap = new HashMap<String,String>();
		String message = "login,"
				+ "logout,"
				+ "getHomeVpAd";
		String invokeClass = "com.fisher.data.UserInfoData/login,"
				+ "com.fisher.data.UserInfoData/logout,"
				+ "com.fisher.data.AdInfoData/getHomeVpAd";
		String[] str1 = message.split(",");
		String[] str2 = invokeClass.split(",");
		if( str1.length == str2.length){
			for( int i = 0 ; i < str1.length; i++){
				methodMap.put(str1[i], str2[i]);
			}
		}
		
	}
	
	public Map<String, String> getMethodMap() {
		return methodMap;
	}

	public void setMethodMap(Map<String, String> methodMap) {
		this.methodMap = methodMap;
	}
	
	public static void main(String[] args){
		Constanse c = new Constanse();
	}
}
