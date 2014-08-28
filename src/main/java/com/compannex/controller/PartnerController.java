package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.CountryMethods;
import com.compannex.biz.IndustryMethods;
import com.compannex.biz.LogoMethods;
import com.compannex.biz.PartnerMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.controller.annotations.Authenticate;
import com.compannex.exceptions.CompANNEXException;
import com.compannex.form.EditCompany;
import com.compannex.form.Registration;
import com.compannex.model.Company;
import com.compannex.util.StringUtil;
import com.compannex.validator.RegistrationValidation;

@Controller
public class PartnerController {

	private static Logger logger = Logger.getLogger(PartnerController.class);

	private RegistrationValidation registrationValidation;
	private CompanyMethods companyMethods;
	private IndustryMethods indMeth;
	private CountryMethods countrMeth;
	private PartnerMethods partnerMeth;
	private LogoMethods logoMethods;

	@Autowired
	public PartnerController(RegistrationValidation registrationValidation, CompanyMethods companyMethods, IndustryMethods indMeth,
			CountryMethods countrMeth, PartnerMethods partnerMeth, LogoMethods logoMethods) {
		this.registrationValidation = registrationValidation;
		this.companyMethods = companyMethods;
		this.indMeth = indMeth;
		this.countrMeth = countrMeth;
		this.partnerMeth = partnerMeth;
		this.logoMethods = logoMethods;
	}


}
