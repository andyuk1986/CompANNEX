package com.compannex.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/home.do")
	public ModelAndView home() {
		logger.info("Home");
		
		ModelAndView result = new ModelAndView("index");
		
		return result;
	}
}
