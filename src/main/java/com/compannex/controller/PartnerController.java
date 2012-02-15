package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CountryMethods;
import com.compannex.biz.IndustryMethods;
import com.compannex.biz.PartnerMethods;

@Controller
public class PartnerController {

	private static Logger logger = Logger.getLogger(PartnerController.class);
	
	@RequestMapping("/registernew.do")
	public ModelAndView registerNew(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("register", "activeTab", "clients");
		
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		IndustryMethods indMeth = (IndustryMethods)context.getBean("industryMethods");
		CountryMethods countrMeth = (CountryMethods)context.getBean("countryMethods");
		
		result.addObject("industries", indMeth.getAllIndustries(1));
		result.addObject("countries", countrMeth.getAllCountries(1));
		
		return result;
	}
	
	@RequestMapping("/register.do")
	public ModelAndView register(HttpServletRequest request,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "repassword", required = false) String repassword,
			@RequestParam(value = "industry", required = false) String industry,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "websiteurl", required = false) String websiteurl,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "fax", required = false) String fax,
			@RequestParam(value = "contactperson", required = false) String contactperson,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "slogan", required = false) String slogan,
			@RequestParam(value = "employeecount", required = false) String employeecount,
			@RequestParam(value = "description", required = false) String description) {
		
		ModelAndView result = new ModelAndView("clients", "activeTab", "clients");
		
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		PartnerMethods partnerMeth = (PartnerMethods)context.getBean("partnerMethods");
		
		partnerMeth.addNewPartner(name, email, repassword, category, websiteurl,
				telephone, fax, contactperson, address, country, slogan, employeecount, description);
		
		return result;
	}
}
