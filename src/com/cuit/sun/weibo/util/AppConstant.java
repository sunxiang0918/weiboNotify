package com.cuit.sun.weibo.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;


public interface AppConstant {
	
	public static final String CONSUMER_KEY="2196720182";
	public static final String CONSUMER_SECRET="6e8fe46b02f649cc1bc69b6e5e641f26";
	
	public static final String TOKEN="7eb0735d849cc3fa289e4de45000fe6a";
	
	public static final String TOKEN_SECRET="e9ff7c8869555ce2c105e1963bcf7546";
	
	public static final String USER_ID="2682725811";
	
	public static final String GOOGLE_USER_NAME = "sunxiang0918@gmail.com";
	
	public static final String GOOGLE_ADD_CALENDAR_URL="https://www.google.com/calendar/feeds/"+GOOGLE_USER_NAME+"/private/full";
	
	public static final TimeZone DEFAULT_TIME_ZONE=TimeZone.getTimeZone("Asia/Shanghai");
	
	public static final long TIME_SPACE=1000*60;		//1分钟的毫秒
	
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
