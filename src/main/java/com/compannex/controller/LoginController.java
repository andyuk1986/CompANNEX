package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.LoginMethods;
import com.compannex.biz.NewsMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.form.Login;
import com.compannex.validator.LoginValidation;

@Controller
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	private LoginValidation loginValidation;

	@Autowired
    public LoginController(LoginValidation loginValidation) {
        this.loginValidation = loginValidation;
    }
    	
	@RequestMapping("/loginnew.do")
	public ModelAndView loginNew(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("login", "activeTab",
				"clients");

		Login login = new Login();
		result.addObject("login", login);
		
		return result;
	}

	@RequestMapping("/login.do")
	public ModelAndView login(
			HttpServletRequest request,
			@Valid Login login, BindingResult result) {

		ModelAndView success = new ModelAndView("index", "activeTab",
				"home");
		ModelAndView error = new ModelAndView("login", "activeTab",
				"clients");

		loginValidation.validate(login, result);
		if (result.hasErrors()) {
			return error;
		}

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		
		CompanyMethods companyMethods = (CompanyMethods) context
				.getBean("companyMethods");
		NewsMethods newsMethods = (NewsMethods) context.getBean("newsMethods");

		success.addObject("news",
				newsMethods.getLatestNews(CompANNEXConstants.DEFAULT_LANGUAGE));
		
		request.getSession().setAttribute("loginCompany", companyMethods.getCompanyByEmail(login.getEmail(), CompANNEXConstants.DEFAULT_LANGUAGE));
		
		return success;
	}
}
