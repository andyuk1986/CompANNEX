package com.compannex.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.LoginMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.web.CookieManager;
import com.compannex.web.ServletCookieManager;

public class RequestInitializeInterceptor extends HandlerInterceptorAdapter implements CompANNEXConstants {

	private LoginMethods loginMethods;
	private CompanyMethods companyMethods;

	public LoginMethods getLoginMethods() {
		return loginMethods;
	}

	public void setLoginMethods(LoginMethods loginMethods) {
		this.loginMethods = loginMethods;
	}

	public CompanyMethods getCompanyMethods() {
		return companyMethods;
	}

	public void setCompanyMethods(CompanyMethods companyMethods) {
		this.companyMethods = companyMethods;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
				    throws Exception {

		if (request.getSession().getAttribute("loginCompany") == null) {
			CookieManager cm = new ServletCookieManager(request, response);
			if (cm.getCookie() != null && cm.parseEmailFromCookieValue() != null && cm.parseTokenFromCookieValue() != null
					&& loginMethods.isTokenValid(cm.parseEmailFromCookieValue(), cm.parseTokenFromCookieValue())) {
				String newToken = loginMethods.regenerateToken(cm.parseEmailFromCookieValue());
				cm.createCookieValue(request.getSession().getId(), cm.parseEmailFromCookieValue(), newToken);
				request.getSession().setAttribute("loginCompany", companyMethods.getCompanyByEmail(cm.parseEmailFromCookieValue(),
								DEFAULT_LANGUAGE));
			}
		}

		return true;
	}

}
