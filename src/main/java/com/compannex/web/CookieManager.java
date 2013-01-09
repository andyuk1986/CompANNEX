package com.compannex.web;

import java.util.Collection;

/**
 * An interface for an object that manages cookies.
 */
public interface CookieManager {
	/**
	 * The max age value that indicates the cookie should be removed by the client.
	 */
	public static final int REMOVE = 0;

	/**
	 * The max age value that indicates the cookie should be removed as soon as the browser is closed.
	 */
	public static final int REMOVE_ON_BROWSER_CLOSE = -1;

	/**
	 * Adds a cookie to the client browser.
	 * @param value  the value of the cookie.
	 * @param maxAge  the max age for the cookie, in seconds.
	 */
	void setCookie(String value, int maxAge);

	/**
	 * Removes a cookie from the client browser.
	 */
	void removeCookie();

	public String parseSessionIDFromCookieValue();

	public String parseEmailFromCookieValue();

	public String parseTokenFromCookieValue();
	
	public void createCookieValue(String sessionID, String email, String token);
	
	/**
	 * Returns the value of the cookie with the given name, or null if the cookie does not exist.
	 */
	String getCookie();

	/**
	 * Returns a set of all the cookie names that were sent by the client browser.
	 */
	public Collection<String> getCookieNames();
}
