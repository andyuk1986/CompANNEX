package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.IndustryMethods;
import com.compannex.constants.CompANNEXConstants;

@Controller
public class CompanyController extends BaseController {

	@RequestMapping("/clients.do")
	public ModelAndView clients(
			HttpServletRequest request,
			@RequestParam(value = "industryID", required = false) Integer industryID,
			@RequestParam(value = "categoryID", required = false) Integer categoryID) {

		ModelAndView result = new ModelAndView("clients", "activeTab",
				"clients");

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		CompanyMethods companyMethods = (CompanyMethods) context
				.getBean("companyMethods");
		IndustryMethods industryMethods = (IndustryMethods) context
				.getBean("industryMethods");

		if (industryID == null && categoryID == null) {
			result.addObject("industries",
					industryMethods.getAllIndustries(CompANNEXConstants.DEFAULT_LANGUAGE, false));
		} else if (industryID != null) {
			result.addObject(
					"categories",
					industryMethods.getAllCategories(
							industryID, CompANNEXConstants.DEFAULT_LANGUAGE));
			result.addObject("industry", industryMethods.getIndustry(industryID, CompANNEXConstants.DEFAULT_LANGUAGE));
		} else if (categoryID != null) {
			result.addObject("category", industryMethods.getCategory(categoryID, CompANNEXConstants.DEFAULT_LANGUAGE));
		}
		
		result.addObject("clients", companyMethods.getAllClientCompanies(industryID, categoryID, CompANNEXConstants.DEFAULT_LANGUAGE));

		return result;
	}

	@RequestMapping("/client.do")
	@Before("com.compannex.interceptor.RequestInitializeInterceptor.preHandle(request, response)")
	public ModelAndView client(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "companyID", required = false) Integer companyID) {

		ModelAndView result = new ModelAndView("client", "activeTab",
				"clients");

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		CompanyMethods companyMethods = (CompanyMethods) context
				.getBean("companyMethods");

		if (companyID != null) {
			result.addObject("client", companyMethods.getCompanyByID(companyID, CompANNEXConstants.DEFAULT_LANGUAGE));
		}

		return result;
	}
	
}
