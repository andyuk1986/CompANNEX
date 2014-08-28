package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.PasswordMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.controller.annotations.Authenticate;
import com.compannex.exceptions.CompANNEXException;
import com.compannex.form.ChangePassword;
import com.compannex.form.ForgotPassword;
import com.compannex.form.Login;
import com.compannex.form.ResetPassword;
import com.compannex.model.Company;
import com.compannex.validator.ChangePasswordValidation;
import com.compannex.validator.ForgotPasswordValidation;
import com.compannex.validator.ResetPasswordValidation;

@Controller
public class PasswordController {

	private static Logger logger = Logger.getLogger(PasswordController.class);

	private ChangePasswordValidation changePasswordValidation;
	private ResetPasswordValidation resetPasswordValidation;
	private ForgotPasswordValidation forgotPasswordValidation;
	private CompanyMethods companyMethods;
	private PasswordMethods passwordMethods;

	@Autowired
	public PasswordController(ResetPasswordValidation resetPasswordValidation, ForgotPasswordValidation forgotPasswordValidation, ChangePasswordValidation changePasswordValidation, CompanyMethods companyMethods, PasswordMethods passwordMethods) {
		this.resetPasswordValidation = resetPasswordValidation;
		this.forgotPasswordValidation = forgotPasswordValidation;
		this.changePasswordValidation = changePasswordValidation;
		this.companyMethods = companyMethods;
		this.passwordMethods = passwordMethods;
	}

	@RequestMapping("/changepasswordnew.do")
	public ModelAndView changePasswordNew(HttpServletRequest request) throws CompANNEXException {
		ModelAndView result = new ModelAndView("changepassword", "activeTab",
				"clients");

		ChangePassword changePassword = new ChangePassword();
		changePassword.setSessionID(request.getSession().getId());

		result.addObject("changePassword", changePassword);
		return result;
	}

	@RequestMapping("/changepassword.do")
	@Authenticate
	public ModelAndView changePassword(HttpServletRequest request,
			@Valid ChangePassword changePassword, BindingResult result) throws CompANNEXException {

		ModelAndView success = new ModelAndView("client", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("changepassword", "activeTab",
				"clients");

		Company loginCompany = (Company)request.getSession().getAttribute("loginCompany");
		if (loginCompany == null) return error;

		int companyID = loginCompany.getID();

		changePasswordValidation.validate(changePassword, result, loginCompany.getLogin().getEmail());
		if (result.hasErrors()) {
			//error.addObject("changePassword", changePassword);
			return error;
		}

		passwordMethods.changePassword(loginCompany.getLogin().getEmail(), changePassword.getNewpassword());

		loginCompany = companyMethods.getCompanyByID(companyID, CompANNEXConstants.DEFAULT_LANGUAGE);
		success.addObject("client", loginCompany);
		request.getSession().setAttribute("loginCompany", loginCompany);

		return success;
	}

	@RequestMapping("/forgotpasswordnew.do")
	public ModelAndView forgotPasswordNew(HttpServletRequest request) throws CompANNEXException {
		ModelAndView result = new ModelAndView("forgotpassword", "activeTab",
				"clients");

		ForgotPassword forgotPassword = new ForgotPassword();
		forgotPassword.setSessionID(request.getSession().getId());

		result.addObject("forgotPassword", forgotPassword);
		return result;
	}

	@RequestMapping("/forgotpassword.do")
	public ModelAndView forgotPassword(HttpServletRequest request,
			@Valid ForgotPassword forgotPassword, BindingResult result) throws CompANNEXException {
		ModelAndView success = new ModelAndView("login", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("forgotpassword", "activeTab",
				"clients");


		forgotPasswordValidation.validate(forgotPassword, result);
		if (result.hasErrors()) {
			return error;
		}

		passwordMethods.resetPassword(forgotPassword.getEmail());

		Login login = new Login();
		success.addObject("login", login);
		return success;
	}

	@RequestMapping("/resetpasswordnew.do")
	public ModelAndView resetPasswordNew(HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "email", required = true) String email) throws CompANNEXException {
		ModelAndView result = new ModelAndView("resetpassword", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("login", "activeTab",
				"clients");

		if (!passwordMethods.isPasswordTokenValid(email, id, token)) {
			return error;
		}

		ResetPassword resetPassword = new ResetPassword();
		resetPassword.setToken(token);
		resetPassword.setEmail(email);
		resetPassword.setId(id);
		resetPassword.setSessionID(request.getSession().getId());

		result.addObject("resetPassword", resetPassword);
		return result;
	}

	@RequestMapping("/resetpassword.do")
	public ModelAndView resetPassword(HttpServletRequest request,
			@Valid ResetPassword resetPassword, BindingResult result) throws CompANNEXException {
		ModelAndView success = new ModelAndView("login", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("resetpassword", "activeTab",
				"clients");


		resetPasswordValidation.validate(resetPassword, result);
		if (!passwordMethods.isPasswordTokenValid(resetPassword.getEmail(), resetPassword.getId(), resetPassword.getToken()) || result.hasErrors()) {
			return error;
		}

		passwordMethods.changePassword(resetPassword.getEmail(), resetPassword.getNewpassword());

		passwordMethods.resetPasswordToken(resetPassword.getEmail());

		Login login = new Login();
		success.addObject("login", login);
		return success;
	}

}
