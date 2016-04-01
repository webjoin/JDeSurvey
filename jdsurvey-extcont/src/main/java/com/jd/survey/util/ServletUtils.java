package com.jd.survey.util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

	public static String getRemoteAddr(final HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

}