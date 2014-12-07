package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.ConsultantMethods;
import com.compannex.biz.IndustryMethods;
import com.compannex.biz.LoginMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.enums.LoginType;
import com.compannex.exceptions.CompANNEXException;
import com.compannex.form.Login;
import com.compannex.form.Registration;
import com.compannex.util.StringUtil;
import com.compannex.validator.ConsultantRegistrationValidation;
import com.compannex.validator.LoginValidation;
import com.compannex.web.CookieManager;
import com.compannex.web.ServletCookieManager;

@Controller
public class ConsultantController implements CompANNEXConstants {

	private static Logger logger = Logger.getLogger(ConsultantController.class);

	private LoginValidation loginValidation;
	private LoginMethods loginMethods;
	private ConsultantMethods consultantMethods;
	private IndustryMethods industryMethods;
	private ConsultantRegistrationValidation registrationValidation;

	@Autowired
	public ConsultantController(LoginValidation loginValidation,
			LoginMethods loginMethods, ConsultantMethods consultantMethods,
			ConsultantRegistrationValidation registrationValidation, IndustryMethods industryMethods) {
		this.loginValidation = loginValidation;
		this.loginMethods = loginMethods;
		this.consultantMethods = consultantMethods;
		this.industryMethods = industryMethods;
		this.registrationValidation = registrationValidation;
	}

    @RequestMapping("/consultantregisternew.do")
    public ModelAndView registerNew(HttpServletRequest request) throws CompANNEXException {
        ModelAndView result = new ModelAndView("register", "activeTab",
                "clients");

        loadIndustries(request, result);

        Registration registration = new Registration();
        result.addObject("registration", registration);

        return result;
    }

    private void loadIndustries(HttpServletRequest request, ModelAndView result) throws CompANNEXException {
        result.addObject("industries", industryMethods.getAllIndustries(
                CompANNEXConstants.DEFAULT_LANGUAGE, true));
    }

    @RequestMapping("/consultantregister.do")
    @Transactional(rollbackFor=CompANNEXException.class)
    public ModelAndView register(HttpServletRequest request,
            @Valid Registration registration, BindingResult result) throws CompANNEXException {

        ModelAndView success = new ModelAndView("client", "activeTab",
                "clients");
        ModelAndView error = new ModelAndView("register", "activeTab",
                "clients");

        registrationValidation.validate(registration, result, true);
        if (result.hasErrors()) {
            loadIndustries(request, error);
            return error;
        }

        int consultantID = consultantMethods.addConsultant();

        success.addObject("consultant", consultantMethods.getConsultantByID(consultantID, CompANNEXConstants.DEFAULT_LANGUAGE));

        return success;
    }

	
	@RequestMapping("/consultantloginnew.do")
	public ModelAndView loginNew(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("login", "activeTab", "clients");

		Login login = new Login();
		result.addObject("login", login);

		return result;
	}

	@RequestMapping("/consultantlogin.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @Valid Login login,
			BindingResult result) {

		ModelAndView success = new ModelAndView("index", "activeTab", "home");
		ModelAndView error = new ModelAndView("login", "activeTab", "clients");

		loginValidation.validate(login, result, LoginType.CONSULTANT);
		if (result.hasErrors()) {
			return error;
		}
		
		CookieManager cm = new ServletCookieManager(request, response);
		cm.createCookieValue(request.getSession().getId(), login.isRememberme() ? login.getEmail() : null, login.isRememberme() ? loginMethods.regenerateToken(login.getEmail()) : null);
				
		request.getSession().setAttribute(
				"loginConsultant",
				consultantMethods.getConsultantByEmail(login.getEmail(),
						DEFAULT_LANGUAGE));
		
		return success;
	}
	
	@RequestMapping("/consultantlogout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView success = new ModelAndView("index", "activeTab", "home");
				
		CookieManager cm = new ServletCookieManager(request, response);
		cm.removeCookie();
				
		request.getSession().removeAttribute(
				"loginConsultant");

		return success;
	}
}
