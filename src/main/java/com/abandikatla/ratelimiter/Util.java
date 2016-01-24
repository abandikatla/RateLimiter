package com.abandikatla.ratelimiter;

import javax.servlet.http.HttpServletRequest;

public class Util {
	
	public static String getClientIP(HttpServletRequest request){
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}

	public static Integer getCurrentTimeSecs() {
		return (int) (System.currentTimeMillis()/1000);
	}

}
