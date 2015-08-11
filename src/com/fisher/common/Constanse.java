package com.fisher.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constanse {
	private List<String> messageList;
	private Map<String, Integer> messageMap;
	
	public Constanse(){
		messageList = new ArrayList<String>();
		messageMap = new HashMap<String, Integer>();
		String message = "login,logout";
		String[] strs = message.split(",");
		for(String str : strs){
			messageList.add(str);
			messageMap.put(str, messageList.indexOf(str));
		}
	}
	
	public static void main(String[] args){
		Constanse c = new Constanse();
		System.out.println(c.getMessageMap().get("login"));
		System.out.println(c.getMessageMap().get("logout"));
//		c.getMessageMap().get("login");
//		c.getMessageMap().get("logout");
	}
	
	public List<String> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}

	public Map<String, Integer> getMessageMap() {
		return messageMap;
	}

	public void setMessageMap(Map<String, Integer> messageMap) {
		this.messageMap = messageMap;
	}
}
