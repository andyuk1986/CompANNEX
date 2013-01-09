package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.FeedbackMethods;

@Controller
public class FeedbackController {

	
	@RequestMapping("/feedbacks.do")
	public ModelAndView allFeedbacks(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("allfeedbacks", "activeTab", "company");
		
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		FeedbackMethods feedbackMethods = (FeedbackMethods)context.getBean("feedbackMethods");
		
		result.addObject("feedbacks", feedbackMethods.getAllFeedbacks());
		
		return result;
	}
	
}
