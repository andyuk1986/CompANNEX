package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.PasswordMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.controller.annotations.Authenticate;
import com.compannex.exceptions.CompANNEXException;
import com.compannex.form.ChangePassword;
import com.compannex.model.Company;
import com.compannex.validator.ChangePasswordValidation;

@Controller
public class PasswordController {

	private static Logger logger = Logger.getLogger(PasswordController.class);

	private ChangePasswordValidation changePasswordValidation;
	private CompanyMethods companyMethods;
	private PasswordMethods passwordMethods;

	@Autowired
	public PasswordController(ChangePasswordValidation changePasswordValidation, CompanyMethods companyMethods, PasswordMethods passwordMethods) {
		this.changePasswordValidation = changePasswordValidation;
		this.companyMethods = companyMethods;
		this.passwordMethods = passwordMethods;
	}

	@RequestMapping("/changepasswordnew.do")
	public ModelAndView changePasswordNew(HttpServletRequest request) throws CompANNEXException {
		ModelAndView result = new ModelAndView("changepassword", "activeTab",
				"clients");

		ChangePassword changePassword = new ChangePassword();
		
		result.addObject("changepassword", changePassword);
		return result;
	}

	@RequestMapping("/changepassword.do")
	@Authenticate
	public ModelAndView changePassword(HttpServletRequest request,
			@Valid ChangePassword changepassword, BindingResult result) throws CompANNEXException {

		ModelAndView success = new ModelAndView("client", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("changepassword", "activeTab",
				"clients");

		Company loginCompany = (Company)request.getSession().getAttribute("loginCompany");
		if (loginCompany == null) return error;
		
		int companyID = loginCompany.getID();
		
		changePasswordValidation.validate(changepassword, result, loginCompany.getEmail());
		if (result.hasErrors()) {
			error.addObject("changepassword", changepassword);
			return error;
		}

		passwordMethods.changePassword(companyID, changepassword.getNewpassword());
			
		loginCompany = companyMethods.getCompanyByID(companyID, CompANNEXConstants.DEFAULT_LANGUAGE);
		success.addObject("client", loginCompany);
		request.getSession().setAttribute("loginCompany", loginCompany);
		
		return success;
	}
}
