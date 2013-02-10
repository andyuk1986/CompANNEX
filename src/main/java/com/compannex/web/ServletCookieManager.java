package com.compannex.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.compannex.constants.CompANNEXConstants;

public class ServletCookieManager implements CookieManager, CompANNEXConstants {
	private static final Logger log = Logger.getLogger(ServletCookieManager.class);

	private HttpServletRequest request;
	private HttpServletResponse response;

	private Map<String, String> cookies = new HashMap<String, String>();

	public ServletCookieManager(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		// Copy all existing cookies from the request into this map.
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				cookies.put(cookie.getName(), cookie.getValue());
			}
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setCookie(String value, int maxAge) {
		if (maxAge < 0) maxAge = REMOVE_ON_BROWSER_CLOSE;

		Cookie cookie = new Cookie(COMPANNEX_COOKIE_NAME, value);
		cookie.setMaxAge(maxAge);
		//cookie.setPath("/");
		//cookie.setSecure(true);

		response.addCookie(cookie);
		cookies.put(COMPANNEX_COOKIE_NAME, value);
	}
	
	public String parseSessionIDFromCookieValue() {
		if (getCookie() == null) return null;
		String[] parts = getCookie().split(COOKIE_DELIM, 3);
		return parts[0];
	}

	public String parseEmailFromCookieValue() {
		if (getCookie() == null) return null;
		String[] parts = getCookie().split(COOKIE_DELIM, 3);
		return parts.length >= 2 ? parts[1] : null;
	}

	public String parseTokenFromCookieValue() {
		if (getCookie() == null) return null;
		String[] parts = getCookie().split(COOKIE_DELIM, 3);
		return parts.length >= 3 ? parts[2] : null;
	}
	
	public void createCookieValue(String sessionID, String email, String token) {
		String value =  sessionID + (email != null ? COOKIE_DELIM + email : null) + (token != null ? COOKIE_DELIM + token : null);
		setCookie(value, COOKIE_EXPIRE);
	}

	public void removeCookie() {
		setCookie("", REMOVE);
		cookies.remove(COMPANNEX_COOKIE_NAME);
	}

	public String getCookie() {
		String value = cookies.get(COMPANNEX_COOKIE_NAME);
		return value;
	}

	public Collection<String> getCookieNames() {
		return new ArrayList<String>(cookies.keySet());
	}

}
