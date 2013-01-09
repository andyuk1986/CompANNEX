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

	@RequestMapping("/registernew.do")
	public ModelAndView registerNew(HttpServletRequest request) throws CompANNEXException {
		ModelAndView result = new ModelAndView("register", "activeTab",
				"clients");

		loadIndustries(request, result);
		
		Registration registration = new Registration();
		result.addObject("registration", registration);
		
		return result;
	}

	private void loadIndustries(HttpServletRequest request, ModelAndView result) throws CompANNEXException {
		
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

		int companyID = partnerMeth.addNewPartner(registration.getName(),
				registration.getEmail(), registration.getPassword(),
				registration.getCategory(), registration.getWebsiteurl(),
				registration.getTelephone(), registration.getFax(),
				registration.getContactperson(), registration.getAddress(),
				registration.getCity(), registration.getRegion(), registration.getZipcode(),
				registration.getCountry(), registration.getSlogan(),
				registration.getEmployeecount(), registration.getDescription(), CompANNEXConstants.DEFAULT_LANGUAGE);

		if (registration.getLogo() != null && !StringUtil.isBlank(registration.getLogo().getOriginalFilename())) {
			logoMethods.addCompanyLogo(request.getSession().getServletContext(), registration.getLogo(), companyID);
		}

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

		EditCompany editcompany = new EditCompany();

		Company loginCompany = (Company)request.getSession().getAttribute("loginCompany");
		if (loginCompany != null) {
			editcompany.setAddress(loginCompany.getTranslation().getAddress());
			editcompany.setCity(loginCompany.getTranslation().getCity());
			editcompany.setRegion(loginCompany.getTranslation().getRegion());
			editcompany.setZipcode(loginCompany.getZipCode());
			editcompany.setCategory(String.valueOf(loginCompany.getCategoryId()));
			editcompany.setIndustry(String.valueOf(indMeth.getCategory(loginCompany.getCategoryId(), CompANNEXConstants.DEFAULT_LANGUAGE).getID()));
			editcompany.setContactperson(loginCompany.getTranslation().getContacts());
			editcompany.setCountry(String.valueOf(partnerMeth.getCountryIDByCompanyID(loginCompany.getID())));
			editcompany.setDescription(loginCompany.getTranslation().getDescription());
			editcompany.setEmail(loginCompany.getEmail());
			editcompany.setEmployeecount(String.valueOf(loginCompany.getEmployeeCount()));
			editcompany.setFax(loginCompany.getFax());
			editcompany.setName(loginCompany.getTranslation().getName());
			editcompany.setSlogan(loginCompany.getTranslation().getSlogan());
			editcompany.setTelephone(loginCompany.getTelephone());
			editcompany.setWebsiteurl(loginCompany.getWebsite());
		}
		
		result.addObject("editcompany", editcompany);
		return result;
	}

	@RequestMapping("/editcompany.do")
	@Authenticate
	public ModelAndView editCompany(HttpServletRequest request,
			@Valid EditCompany editcompany, BindingResult result) throws CompANNEXException {

		ModelAndView success = new ModelAndView("client", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("editcompany", "activeTab",
				"clients");

		registrationValidation.validate(editcompany, result, false);
		if (result.hasErrors()) {
			loadIndustries(request, error);
			error.addObject("editcompany", editcompany);
			return error;
		}

		Company loginCompany = (Company)request.getSession().getAttribute("loginCompany");
		if (loginCompany != null) {
			int companyID = loginCompany.getID();
				
			partnerMeth.editPartner(companyID, editcompany.getName(),
					editcompany.getCategory(), editcompany.getWebsiteurl(),
					editcompany.getTelephone(), editcompany.getFax(),
					editcompany.getContactperson(), editcompany.getAddress(),
					editcompany.getZipcode(), editcompany.getCity(), editcompany.getRegion(),
					editcompany.getCountry(), editcompany.getSlogan(),
					editcompany.getEmployeecount(), editcompany.getDescription(), CompANNEXConstants.DEFAULT_LANGUAGE);
	
			if (editcompany.getLogo() != null && !StringUtil.isBlank(editcompany.getLogo().getOriginalFilename())) {
				logoMethods.addCompanyLogo(request.getSession().getServletContext(), editcompany.getLogo(), companyID);
			}
	
			loginCompany = companyMethods.getCompanyByID(companyID, CompANNEXConstants.DEFAULT_LANGUAGE);
			success.addObject("client", loginCompany);
			request.getSession().setAttribute("loginCompany", loginCompany);
		}
		return success;
	}
}
