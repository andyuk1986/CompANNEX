package com.compannex.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/index.jsp")
	public void home(@RequestParam String defaultParam) {
		logger.debug("!!!defaultParam" + defaultParam);
	}
}
