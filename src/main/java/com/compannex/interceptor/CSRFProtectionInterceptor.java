package com.compannex.interceptor;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.compannex.constants.CompANNEXConstants;
import com.compannex.controller.annotations.Authenticate;
import com.compannex.util.SpringUtil;
import com.compannex.util.StringUtil;
import com.compannex.web.CookieManager;
import com.compannex.web.ServletCookieManager;

public class CSRFProtectionInterceptor extends HandlerInterceptorAdapter implements CompANNEXConstants {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (isProtectionRequired(request, handler)) {
			String token = request.getParameter("sessionID");
			CookieManager cm = new ServletCookieManager(request, response);
			//For security reasons we need to make sure the sessionID that was passed in matches the sessionID on the cookie. This will prevent
			//an intruder from create performing a csrf attack. The defense is simple, a sessionID is passed in as a query or post parameter. Verify the sessionID matc
			//the session id that is passed in from the session cookie. If the two session IDs don't match or if the sessionID(token) isn't passed in as a
			//parameter then redirect to login page.
			if (!StringUtil.equals(token, cm.parseSessionIDFromCookieValue())) {
				RequestDispatcher rd = request.getRequestDispatcher("loginnew.do");
				rd.forward(request, response);
				return false;
			}

		}
		return true;
	}

	private boolean isProtectionRequired(HttpServletRequest request, Object handler) {
		// Require CSRF protection in all cases where there exists a @Authentication annotation at either the
		// method or class level for the controller.
		Authenticate annotation = SpringUtil.findHandlerAnnotation(request, handler, Authenticate.class);
		return annotation != null;
	}


}
