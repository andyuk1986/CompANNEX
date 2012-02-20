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
import com.compannex.biz.CountryMethods;
import com.compannex.biz.IndustryMethods;
import com.compannex.biz.PartnerMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.form.Registration;
import com.compannex.validator.RegistrationValidation;

@Controller
public class PartnerController {

	private static Logger logger = Logger.getLogger(PartnerController.class);

	private RegistrationValidation registrationValidation;

	/*public void setRegistrationValidation(
			RegistrationValidation registrationValidation) {
		this.registrationValidation = registrationValidation;
	}*/

    @Autowired
    public PartnerController(RegistrationValidation registrationValidation) {
        this.registrationValidation = registrationValidation;
    }

    @RequestMapping("/registernew.do")
	public ModelAndView registerNew(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("register", "activeTab",
				"clients");

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		IndustryMethods indMeth = (IndustryMethods) context
				.getBean("industryMethods");
		CountryMethods countrMeth = (CountryMethods) context
				.getBean("countryMethods");

		Registration registration = new Registration();
		result.addObject("registration", registration);
		result.addObject("industries",
				indMeth.getAllIndustries(CompANNEXConstants.DEFAULT_LANGUAGE));
		result.addObject("countries",
				countrMeth.getAllCountries(CompANNEXConstants.DEFAULT_LANGUAGE));

		return result;
	}

	@RequestMapping("/register.do")
	public ModelAndView register(
			HttpServletRequest request,
			@Valid Registration registration, BindingResult result) {

		ModelAndView success = new ModelAndView("clients", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("register", "activeTab",
				"clients");

		registrationValidation.validate(registration, result);
		if (result.hasErrors()) {
			return error;
		}

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		PartnerMethods partnerMeth = (PartnerMethods) context
				.getBean("partnerMethods");

		partnerMeth.addNewPartner(registration.getName(),
				registration.getEmail(), registration.getPassword(),
				registration.getCategory(), registration.getWebsiteurl(),
				registration.getTelephone(), registration.getFax(),
				registration.getContactperson(), registration.getAddress(),
				registration.getCountry(), registration.getSlogan(),
				registration.getEmployeecount(), registration.getDescription());

		CompanyMethods companyMethods = (CompanyMethods) context
				.getBean("companyMethods");
		success.addObject("clients", companyMethods.getLatestClientCompanies());
		return success;
	}
}
