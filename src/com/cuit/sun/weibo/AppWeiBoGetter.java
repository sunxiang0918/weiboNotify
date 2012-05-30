package com.cuit.sun.weibo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weibo4j.Paging;
import weibo4j.Status;
import weibo4j.User;
import weibo4j.Weibo;
import weibo4j.WeiboException;

import com.cuit.sun.weibo.util.AppConstant;
import com.cuit.sun.weibo.vo.WeiBoVO;

public class AppWeiBoGetter {
	
	private static Log log=LogFactory.getLog(AppWeiBoGetter.class);
	
	private static boolean isFailed=false;
	
	private static Weibo weibo = new Weibo();
	
	static {
		weibo.setOAuthConsumer(AppConstant.CONSUMER_KEY, AppConstant.CONSUMER_SECRET);
        weibo.setToken(AppConstant.TOKEN,AppConstant.TOKEN_SECRET);
	}
	
	public static List<WeiBoVO> getWeiBoVOsByUserName(String userName){
		
		Paging paging=new Paging(1, 2);
		List<WeiBoVO> result=new ArrayList<WeiBoVO>();
		
		if (isFailed) {
			return result;
		}
		
		List<Status> statuses=null;
		try {
			statuses=weibo.getUserTimeline(userName, paging);
		} catch (WeiboException e) {
			log.error(e);
			e.printStackTrace();
			
			WeiBoVO weiBoVO=new WeiBoVO();
			weiBoVO.setName("服务器");
			weiBoVO.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			weiBoVO.setContent("服务器获取微博信息失败:"+e.getMessage());
			result.add(weiBoVO);
			isFailed=true;
		}

    	if (statuses==null) {
			return result;
		}
    	
    	/*遍历*/
    	for (Status status : statuses) {
    		Date date=status.getCreatedAt();		//创建时间
    		String text=status.getText();			//内容
    		User user=status.getUser();				//发送用户
    		String screenName=user.getScreenName();
    		
    		/*如果微博的间隔时间小于扫描时间,表示是新的微博*/
    		if (new Date().getTime()-date.getTime()<AppContext.getInstance().getMinuts()*AppConstant.TIME_SPACE) {
				WeiBoVO weiBoVO=new WeiBoVO();
				weiBoVO.setName(screenName);
				weiBoVO.setContent(text);
				weiBoVO.setTime(AppConstant.SIMPLE_DATE_FORMAT.format(date));
				result.add(weiBoVO);
			}
    	}
    	
    	return result;
	}

}
