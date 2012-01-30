package com.compannex.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServiceController {

	private static Logger logger = Logger.getLogger(ServiceController.class);

	@RequestMapping("/findingpartner.do")
	public ModelAndView home() {
		ModelAndView result = new ModelAndView("findingpartner", "activeTab", "services");

		return result;
	}

	@RequestMapping("/offerproduct.do")
	public ModelAndView services() {
		ModelAndView result = new ModelAndView("offerproduct", "activeTab", "services");
		
		return result;
	}

	@RequestMapping("/communicate.do")
	public ModelAndView company() {
		ModelAndView result = new ModelAndView("communicate", "activeTab", "services");

		return result;
	}
}
