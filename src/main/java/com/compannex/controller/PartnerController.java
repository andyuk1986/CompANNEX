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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.CountryMethods;
import com.compannex.biz.IndustryMethods;
import com.compannex.biz.LogoMethods;
import com.compannex.biz.PartnerMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.exceptions.CompANNEXException;
import com.compannex.form.Registration;
import com.compannex.model.Company;
import com.compannex.validator.RegistrationValidation;

@Controller
public class PartnerController {

	private static Logger logger = Logger.getLogger(PartnerController.class);

	private RegistrationValidation registrationValidation;

	@Autowired
	public PartnerController(RegistrationValidation registrationValidation) {
		this.registrationValidation = registrationValidation;
	}

	@RequestMapping("/registernew.do")
	public ModelAndView registerNew(HttpServletRequest request) throws CompANNEXException {
		ModelAndView result = new ModelAndView("register", "activeTab",
				"clients");

		loadIndustries(request, result);
		return result;
	}

	private void loadIndustries(HttpServletRequest request, ModelAndView result) throws CompANNEXException {
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		IndustryMethods indMeth = (IndustryMethods) context
				.getBean("industryMethods");
		CountryMethods countrMeth = (CountryMethods) context
				.getBean("countryMethods");
		PartnerMethods partnerMeth = (PartnerMethods) context
				.getBean("partnerMethods");
		
		Registration registration = new Registration();
		
		Company loginCompany = (Company)request.getSession().getAttribute("loginCompany");
		if (loginCompany != null) {
			registration.setAddress(loginCompany.getTranslation().getAddress());
			registration.setCategory(String.valueOf(loginCompany.getCategoryId()));
			registration.setIndustry(String.valueOf(indMeth.getCategory(loginCompany.getCategoryId(), CompANNEXConstants.DEFAULT_LANGUAGE).getID()));
			registration.setContactperson(loginCompany.getTranslation().getContacts());
			registration.setCountry(String.valueOf(partnerMeth.getCountryIDByCompanyID(loginCompany.getID())));
			registration.setDescription(loginCompany.getTranslation().getDescription());
			registration.setEmail(loginCompany.getEmail());
			registration.setEmployeecount(String.valueOf(loginCompany.getEmployeeCount()));
			registration.setFax(loginCompany.getFax());
			registration.setName(loginCompany.getTranslation().getName());
			registration.setSlogan(loginCompany.getTranslation().getSlogan());
			registration.setTelephone(loginCompany.getTelephone());
			registration.setWebsiteurl(loginCompany.getWebsite());
		}
		
		result.addObject("registration", registration);
		result.addObject("industries", indMeth.getAllIndustries(
				CompANNEXConstants.DEFAULT_LANGUAGE, true));
		result.addObject("countries",
				countrMeth.getAllCountries(CompANNEXConstants.DEFAULT_LANGUAGE));
	}

	@RequestMapping("/register.do")
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

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		PartnerMethods partnerMeth = (PartnerMethods) context
				.getBean("partnerMethods");
		LogoMethods logoMethods = (LogoMethods) context
				.getBean("logoMethods");

		int companyID = partnerMeth.addNewPartner(registration.getName(),
				registration.getEmail(), registration.getPassword(),
				registration.getCategory(), registration.getWebsiteurl(),
				registration.getTelephone(), registration.getFax(),
				registration.getContactperson(), registration.getAddress(),
				registration.getCountry(), registration.getSlogan(),
				registration.getEmployeecount(), registration.getDescription(), CompANNEXConstants.DEFAULT_LANGUAGE);

		if (registration.getLogo() != null) {
			logoMethods.addCompanyLogo(registration.getLogo(), companyID);
		}

		CompanyMethods companyMethods = (CompanyMethods) context
				.getBean("companyMethods");

		success.addObject("client", companyMethods.getCompanyByID(companyID, CompANNEXConstants.DEFAULT_LANGUAGE));

		return success;
	}

	/*** Trap Exceptions during the upload and show errors back in view form ***/
	/**public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {

		ModelAndView error = new ModelAndView("register", "activeTab",
				"clients");
		if (exception instanceof MaxUploadSizeExceededException) {
			error.addObject("logo",	"* Please choose the logo file which is less than 20KB.");
		} else {
			error.addObject("logo",	"Unexpected error: " + exception.getMessage());
		}
		loadIndustries(request, error);
		return error;
	}**/
	
	@RequestMapping("/editcompanynew.do")
	public ModelAndView editCompanyNew(HttpServletRequest request) throws CompANNEXException {
		ModelAndView result = new ModelAndView("editcompany", "activeTab",
				"clients");

		loadIndustries(request, result);
		
		return result;
	}

	@RequestMapping("/editcompany.do")
	public ModelAndView editCompany(HttpServletRequest request,
			@Valid Registration registration, BindingResult result) throws CompANNEXException {

		ModelAndView success = new ModelAndView("client", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("editcompany", "activeTab",
				"clients");

		registrationValidation.validate(registration, result, false	);
		if (result.hasErrors()) {
			loadIndustries(request, error);
			return error;
		}

		Company loginCompany = (Company)request.getSession().getAttribute("loginCompany");
		if (loginCompany != null) {
			int companyID = loginCompany.getID();
			WebApplicationContext context = WebApplicationContextUtils
					.getRequiredWebApplicationContext(request.getSession()
							.getServletContext());
			PartnerMethods partnerMeth = (PartnerMethods) context
					.getBean("partnerMethods");
			LogoMethods logoMethods = (LogoMethods) context
					.getBean("logoMethods");
	
			partnerMeth.editPartner(companyID, registration.getName(),
					registration.getCategory(), registration.getWebsiteurl(),
					registration.getTelephone(), registration.getFax(),
					registration.getContactperson(), registration.getAddress(),
					registration.getCountry(), registration.getSlogan(),
					registration.getEmployeecount(), registration.getDescription(), CompANNEXConstants.DEFAULT_LANGUAGE);
	
			if (registration.getLogo() != null) {
				logoMethods.addCompanyLogo(registration.getLogo(), companyID);
			}
	
			CompanyMethods companyMethods = (CompanyMethods) context
					.getBean("companyMethods");
	
			loginCompany = companyMethods.getCompanyByID(companyID, CompANNEXConstants.DEFAULT_LANGUAGE);
			success.addObject("client", loginCompany);
			request.getSession().setAttribute("loginCompany", loginCompany);
		}
		return success;
	}
}
