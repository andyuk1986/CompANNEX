package com.compannex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeedbackController {

	
	@RequestMapping("/feedbacks.do")
	public ModelAndView allFeedbacks() {
		ModelAndView result = new ModelAndView("feedbacks", "activeTab", "company");
		
		return result;
	}
	
}
