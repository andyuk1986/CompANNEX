package com.compannex.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.CountryMethods;
import com.compannex.biz.IndustryMethods;
import com.compannex.biz.PartnerMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.form.Registration;
import com.compannex.validator.RegistrationValidation;

@Controller
public class PartnerController implements HandlerExceptionResolver {

	private static Logger logger = Logger.getLogger(PartnerController.class);

	private RegistrationValidation registrationValidation;

	@Autowired
	public PartnerController(RegistrationValidation registrationValidation) {
		this.registrationValidation = registrationValidation;
	}

	@RequestMapping("/registernew.do")
	public ModelAndView registerNew(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("register", "activeTab",
				"clients");

		loadIndustries(request, result);
		return result;
	}

	private void loadIndustries(HttpServletRequest request, ModelAndView result) {
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		IndustryMethods indMeth = (IndustryMethods) context
				.getBean("industryMethods");
		CountryMethods countrMeth = (CountryMethods) context
				.getBean("countryMethods");

		Registration registration = new Registration();
		result.addObject("registration", registration);
		result.addObject("industries", indMeth.getAllIndustries(
				CompANNEXConstants.DEFAULT_LANGUAGE, true));
		result.addObject("countries",
				countrMeth.getAllCountries(CompANNEXConstants.DEFAULT_LANGUAGE));
	}

	@RequestMapping("/register.do")
	public ModelAndView register(HttpServletRequest request,
			@Valid Registration registration, BindingResult result) {

		ModelAndView success = new ModelAndView("clients", "activeTab",
				"clients");
		ModelAndView error = new ModelAndView("register", "activeTab",
				"clients");

		registrationValidation.validate(registration, result);
		if (result.hasErrors()) {
			loadIndustries(request, error);
			return error;
		}

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		PartnerMethods partnerMeth = (PartnerMethods) context
				.getBean("partnerMethods");

		int companyID = partnerMeth.addNewPartner(registration.getName(),
				registration.getEmail(), registration.getPassword(),
				registration.getCategory(), registration.getWebsiteurl(),
				registration.getTelephone(), registration.getFax(),
				registration.getContactperson(), registration.getAddress(),
				registration.getCountry(), registration.getSlogan(),
				registration.getEmployeecount(), registration.getDescription());

		if (registration.getLogo() != null) {
			try {
				String logoPath = "../images/logos/" + companyID + "_logo_" + registration.getLogo().getOriginalFilename();
				InputStream in = registration.getLogo().getInputStream();
				File file = new File(logoPath);
				file.createNewFile();
				FileOutputStream f = new FileOutputStream(file);
				int ch = 0;
				while ((ch = in.read()) != -1) {
					f.write(ch);
				}
				f.flush();
				f.close();
				partnerMeth.editPartnerLogo(companyID, logoPath);
			} catch (IOException e) {
				logger.error(e.getMessage());
				return error;
			}
		}

		CompanyMethods companyMethods = (CompanyMethods) context
				.getBean("companyMethods");
		success.addObject("clients", companyMethods.getLatestClientCompanies());
		return success;
	}

	/*** Trap Exceptions during the upload and show errors back in view form ***/
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {

		ModelAndView error = new ModelAndView("register", "activeTab",
				"clients");
		if (exception instanceof MaxUploadSizeExceededException) {
			error.addObject("logo",
					"* Please choose the logo file which is less than 20KB.");
		} else {
			error.addObject("logo",
					"Unexpected error: " + exception.getMessage());
		}
		loadIndustries(request, error);
		return error;
	}
}
