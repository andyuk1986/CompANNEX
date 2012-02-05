package com.compannex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.FeedbackMethods;
import com.compannex.biz.NewsMethods;

@Controller
public class FeedbackController {

	
	@RequestMapping("/feedbacks.do")
	public ModelAndView allFeedbacks() {
		ModelAndView result = new ModelAndView("allfeedbacks", "activeTab", "company");
		result.addObject("feedbacks", FeedbackMethods.getInstance().getAllFeedbacks());
		return result;
	}
	
}
