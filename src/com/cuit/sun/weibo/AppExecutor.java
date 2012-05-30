package com.cuit.sun.weibo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuit.sun.weibo.vo.WeiBoVO;

public class AppExecutor implements Runnable{
	
	private Log log=LogFactory.getLog(AppExecutor.class);
	
	private String name;
	
	public AppExecutor(String name){
		this.name=name;
	}
	
	public void run() {
		
		if (log.isInfoEnabled()) {
			log.info("开始扫描用户:"+name+" 的新微博");
		}
		
		/*获取符合要求的微博*/
		List<WeiBoVO> weiBoVOs=AppWeiBoGetter.getWeiBoVOsByUserName(name);
		
		if (weiBoVOs==null) {
			if (log.isInfoEnabled()) {
				log.info("用户:"+name+" 没有新的微博");
			}
			return;
		}
		try {
			AppCalenderAdder calenderAdder=new AppCalenderAdder();
			for (WeiBoVO weiBoVO : weiBoVOs) {
				try {
					calenderAdder.addCalender(weiBoVO);
				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}

}
