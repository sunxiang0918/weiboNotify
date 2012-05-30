package com.cuit.sun.weibo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.cuit.sun.weibo.util.AppConstant;

public class AppCore {
	
	private ScheduledThreadPoolExecutor poolExecutor=new ScheduledThreadPoolExecutor(1);
	
	private final Map<String, Runnable> executorCache=new HashMap<String, Runnable>();
	
	private AppCore(){}
	
	private static final AppCore instance=new AppCore();
	
	private boolean started=false;
	
	public static AppCore getInstance() {
		return instance;
	}
	
	public boolean start(){
		
		if (started) {
			return false;
		}
		
		/*启动*/
		for (String queryFriendName : AppContext.getInstance().getNeedQueryFriendNames()) {
			this.add(queryFriendName);
		}
		
		started=true;
		
		/*由于GAE不能随便启动线程,所以只能这样了*/
//		while (started) {
//			for (Map.Entry<String, Runnable> entry : executorCache.entrySet()) {
//				Runnable runnable=entry.getValue();
//				
//				runnable.run();
//				if (!started) {
//					break;
//				}
//			}
//			try {
//				Thread.sleep(AppContext.getInstance().getMinuts()*AppConstant.TIME_SPACE);
//			} catch (InterruptedException e) {
//			}
//		}
		
		return started;
	}
	
	public boolean add(String screenName){
		
		if (executorCache.containsKey(screenName)) {
			return false;
		}
		
		AppContext.getInstance().addNewQueryFriend(screenName, screenName);
		
		/*添加某一个关注的微博*/
		Runnable runnable=new AppExecutor(screenName);
		
		executorCache.put(screenName, runnable);
		
		/*开始扫描*/
		poolExecutor.scheduleAtFixedRate(runnable, 1000, AppContext.getInstance().getMinuts()*AppConstant.TIME_SPACE, TimeUnit.MILLISECONDS);
		
		return true;
	}
	
	public boolean stop(){
		if (!started) {
			return false;
		}
		
		/*关闭*/
		poolExecutor.shutdownNow();
		
		poolExecutor=new ScheduledThreadPoolExecutor(1);
		
		/**/
		executorCache.clear();
		
		started=false;
		
		return true;
	}
	
	public boolean remove(String name){
		if (!executorCache.containsKey(name)) {
			return false;
		}
		
		Runnable runnable=executorCache.remove(name);
		
		poolExecutor.remove(runnable);
		
		return true;
		
	}
	
}
