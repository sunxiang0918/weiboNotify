package com.cuit.sun.weibo;

import java.net.URL;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuit.sun.weibo.util.AppConstant;
import com.cuit.sun.weibo.vo.WeiBoVO;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.Reminder;
import com.google.gdata.data.extensions.Reminder.Method;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.AuthenticationException;

public class AppCalenderAdder {
	
	private Log log=LogFactory.getLog(AppCalenderAdder.class);
	
	private CalendarService calendarService = new CalendarService("CalendarTestApp");
	
	public AppCalenderAdder() throws AuthenticationException{
		calendarService.setUserCredentials(AppConstant.GOOGLE_USER_NAME, AppContext.getInstance().getGooglePassword());
	}
	
	public void addCalender(WeiBoVO weiBoVO) throws Exception{
		URL postUrl =new URL(AppConstant.GOOGLE_ADD_CALENDAR_URL);
		
		if (log.isInfoEnabled()) {
			log.info("添加用户"+weiBoVO.getName()+"的新微博提醒:"+weiBoVO.getContent());
		}
		
		/*添加日历事件*/
		CalendarEventEntry myEntry = new CalendarEventEntry();
		myEntry.setTitle(new PlainTextConstruct("用户@"+weiBoVO.getName()+"于"+weiBoVO.getTime()+"发布新微博:"+weiBoVO.getContent()));
		myEntry.setContent(new PlainTextConstruct(weiBoVO.getContent()));

		/*添加日历的起止时间*/
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 2);
		DateTime startTime = new DateTime(calendar.getTime(),AppConstant.DEFAULT_TIME_ZONE);
		calendar.add(Calendar.MINUTE, 2);
		DateTime endTime = new DateTime(calendar.getTime(),AppConstant.DEFAULT_TIME_ZONE);
		When eventTimes = new When();
		eventTimes.setStartTime(startTime);
		eventTimes.setEndTime(endTime);
		myEntry.addTime(eventTimes);
		
		/*短信*/
		Reminder reminder=new Reminder();
		reminder.setMinutes(1);
		reminder.setMethod(Method.SMS);
		myEntry.getReminder().add(reminder);
		
		/*邮件*/
		reminder=new Reminder();
		reminder.setMinutes(1);
		reminder.setMethod(Method.EMAIL);
		myEntry.getReminder().add(reminder);

		/*添加一个日历*/
		calendarService.insert(postUrl, myEntry);
		
		if (log.isInfoEnabled()) {
			log.info("添加用户"+weiBoVO.getName()+"的新微博提醒:"+weiBoVO.getContent()+" 完成");
		}
	}

}
