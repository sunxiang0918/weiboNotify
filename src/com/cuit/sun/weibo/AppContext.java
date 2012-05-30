package com.cuit.sun.weibo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AppContext {

	/*key:screenName value:userID*/
	private final Map<String, String> needQueryFriendsMap=new HashMap<String, String>();
	
	private int minuts=30;		//默认30分钟扫描一次
	
	private String googlePassword=null;		//谷歌的密码
	
	private AppContext(){}
	
	private static final AppContext instance=new AppContext();
	
	public static AppContext getInstance() {
		return instance;
	}
	
	public void addNewQueryFriend(String screenName,String uid){
		needQueryFriendsMap.put(screenName, uid);
	}
	
	public List<String> getNeedQueryFriendNames(){
		return new ArrayList<String>(needQueryFriendsMap.keySet());
	}
	
	public String removeQueryFriendByName(String screenName){
		return needQueryFriendsMap.remove(screenName);
	}
	
	public void setMinuts(int minuts) {
		this.minuts = minuts;
	}
	
	public int getMinuts() {
		return minuts;
	}
	
	public void setGooglePassword(String googlePassword) {
		this.googlePassword = googlePassword;
	}
	
	public String getGooglePassword() {
		return googlePassword;
	}
}
