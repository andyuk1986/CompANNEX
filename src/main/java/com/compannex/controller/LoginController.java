package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.LoginMethods;
import com.compannex.biz.NewsMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.enums.LoginType;
import com.compannex.form.Login;
import com.compannex.validator.LoginValidation;
import com.compannex.web.CookieManager;
import com.compannex.web.ServletCookieManager;

@Controller
public class LoginController implements CompANNEXConstants {

	private static Logger logger = Logger.getLogger(LoginController.class);

	private LoginValidation loginValidation;
	private LoginMethods loginMethods;
	private CompanyMethods companyMethods;
	private NewsMethods newsMethods;

	@Autowired
	public LoginController(LoginValidation loginValidation,
			LoginMethods loginMethods, CompanyMethods companyMethods,
			NewsMethods newsMethods) {
		this.loginValidation = loginValidation;
		this.loginMethods = loginMethods;
		this.companyMethods = companyMethods;
		this.newsMethods = newsMethods;
	}

	@RequestMapping("/loginnew.do")
	public ModelAndView loginNew(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("login", "activeTab", "clients");

		Login login = new Login();
		result.addObject("login", login);

		return result;
	}

	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @Valid Login login,
			BindingResult result) {

		ModelAndView success = new ModelAndView("index", "activeTab", "home");
		ModelAndView error = new ModelAndView("login", "activeTab", "clients");

		loginValidation.validate(login, result, LoginType.COMPANY);
		if (result.hasErrors()) {
			return error;
		}
		
		CookieManager cm = new ServletCookieManager(request, response);
		cm.createCookieValue(request.getSession().getId(), login.isRememberme() ? login.getEmail() : null, login.isRememberme() ? loginMethods.regenerateToken(login.getEmail()) : null);
				
		request.getSession().setAttribute(
				"loginCompany",
				companyMethods.getCompanyByEmail(login.getEmail(),
						DEFAULT_LANGUAGE));
		
		success.addObject("news",
				newsMethods.getLatestNews(DEFAULT_LANGUAGE));

		return success;
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView success = new ModelAndView("index", "activeTab", "home");
				
		CookieManager cm = new ServletCookieManager(request, response);
		cm.removeCookie();
				
		request.getSession().removeAttribute(
				"loginCompany");
		
		success.addObject("news",
				newsMethods.getLatestNews(DEFAULT_LANGUAGE));

		return success;
	}
}
