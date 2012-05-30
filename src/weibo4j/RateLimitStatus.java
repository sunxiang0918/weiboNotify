/*
Copyright (c) 2007-2009, Yusuke Yamamoto
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the Yusuke Yamamoto nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package weibo4j;

import java.util.Date;

import org.w3c.dom.Element;

import weibo4j.http.Response;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

/**
 * A data class representing Weibo rate limit status
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class RateLimitStatus extends WeiboResponse {
    private int remainingHits;         //剩余调用次数
    private int hourlyLimit;           //每小时限制
    private int resetTimeInSeconds;    //距重置时间还有多少秒
    private Date resetTime;            //重置时间
    private static final long serialVersionUID = 933996804168952707L;

    /* package */ RateLimitStatus(Response res) throws WeiboException {
        super(res);
        Element elem = res.asDocument().getDocumentElement();
        remainingHits = getChildInt("remaining-hits", elem);
        hourlyLimit = getChildInt("hourly-limit", elem);
        resetTimeInSeconds = getChildInt("reset-time-in-seconds", elem);
        resetTime = getChildDate("reset-time", elem, "EEE MMM d HH:mm:ss z yyyy");
    }
    
    /*modify by sycheng add json call*/
    /* package */ RateLimitStatus(Response res,Weibo w) throws WeiboException {
        super(res);
        JSONObject json=	res.asJSONObject();
        try {
			remainingHits = json.getInt("remaining_hits");
			hourlyLimit = json.getInt("hourly_limit");
			resetTimeInSeconds = json.getInt("reset_time_in_seconds");
			resetTime = parseDate(json.getString("reset_time"), "EEE MMM dd HH:mm:ss z yyyy");
		} catch (JSONException jsone) {
			throw new WeiboException(jsone.getMessage() + ":" + json.toString(), jsone);
		}
    }

    public int getRemainingHits() {
        return remainingHits;
    }

    public int getHourlyLimit() {
        return hourlyLimit;
    }

    public int getResetTimeInSeconds() {
        return resetTimeInSeconds;
    }

    /**
     *
     * @deprecated use getResetTime() instead
     */
    public Date getDateTime() {
        return resetTime;
    }

    /**
     * @since  Weibo4J 1.2.1
     */
    public Date getResetTime() {
        return resetTime;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb=new StringBuilder();
    	sb.append("RateLimitStatus{remainingHits:");
    	sb.append(remainingHits);
    	sb.append(";hourlyLimit:");
    	sb.append(hourlyLimit);
    	sb.append(";resetTimeInSeconds:");
    	sb.append(resetTimeInSeconds);
    	sb.append(";resetTime:");
    	sb.append(resetTime);
    	sb.append("}");
    	return sb.toString();
    }
}
