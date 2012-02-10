package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

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
		
		result.addObject("industries", indMeth.getAllIndustries(1));
		
		return result;
	}
	
	@RequestMapping("/register.do")
	public ModelAndView register(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "repassword", required = true) String repassword,
			@RequestParam(value = "websiteurl", required = true) String websiteurl,
			@RequestParam(value = "contactperson", required = true) String contactperson,
			@RequestParam(value = "address", required = true) String address,
			@RequestParam(value = "country", required = true) String country,
			@RequestParam(value = "slogan", required = true) String slogan,
			@RequestParam(value = "employeecount", required = true) String employeecount,
			@RequestParam(value = "description", required = true) String description) {
		
		ModelAndView result = new ModelAndView("clients", "activeTab", "clients");
		
		
		PartnerMethods partnerMeth = PartnerMethods.getInstance();
		
		
		
		return result;
	}
}
