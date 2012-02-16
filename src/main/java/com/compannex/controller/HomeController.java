package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.CompanyMethods;
import com.compannex.biz.FeedbackMethods;
import com.compannex.biz.NewsMethods;
import com.compannex.biz.PartnerMethods;
import com.compannex.constants.CompANNEXConstants;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/home.do")
	public ModelAndView home(HttpServletRequest request) {
		logger.info("Home");

		ModelAndView result = new ModelAndView("index", "activeTab", "home");
		
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		NewsMethods newsMethods = (NewsMethods)context.getBean("newsMethods");
		
		result.addObject("news", newsMethods.getLatestNews(CompANNEXConstants.DEFAULT_LANGUAGE));
		
		return result;
	}

	@RequestMapping("/services.do")
	public ModelAndView services() {
		ModelAndView result = new ModelAndView("services", "activeTab", "services");
		
		return result;
	}

	@RequestMapping("/company.do")
	public ModelAndView company(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("company", "activeTab", "company");
		
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		FeedbackMethods feedbackMethods = (FeedbackMethods)context.getBean("feedbackMethods");
		
		result.addObject("feedbacks", feedbackMethods.getLatestFeedbacks());
		return result;
	}

	@RequestMapping("/clients.do")
	public ModelAndView clients() {
		ModelAndView result = new ModelAndView("clients", "activeTab", "clients");
		result.addObject("clients", CompanyMethods.getInstance().getLatestClientCompanies());
		return result;
	}

	@RequestMapping("/contacts.do")
	public ModelAndView contacts() {
		ModelAndView result = new ModelAndView("contacts", "activeTab", "contacts");

		return result;
	}
	
	@RequestMapping("/news.do")
	public ModelAndView news(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("allnews", "activeTab", "home");
		
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		NewsMethods newsMethods = (NewsMethods)context.getBean("newsMethods");
		
		result.addObject("news", newsMethods.getLatestNews(CompANNEXConstants.DEFAULT_LANGUAGE));
		
		return result;
	}
}
