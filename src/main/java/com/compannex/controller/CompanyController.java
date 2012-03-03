package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.IndustryMethods;
import com.compannex.util.StringUtil;

@Controller
public class CompanyController {

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
					industryMethods.getAllIndustries(1, false));
		} else if (industryID != null) {
			result.addObject(
					"categories",
					industryMethods.getAllCategories(
							industryID, 1));
			result.addObject("industry", industryMethods.getIndustry(industryID, 1));
		} else if (categoryID != null) {
			result.addObject("category", industryMethods.getCategory(categoryID, 1));
		}
		
		result.addObject("clients", companyMethods.getAllClientCompanies(industryID, categoryID, 1));

		return result;
	}
}
