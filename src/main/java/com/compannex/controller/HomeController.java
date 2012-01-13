package com.compannex.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/home.do")
	public ModelAndView home() {
		logger.info("Home");

		ModelAndView result = new ModelAndView("index", "activeTab", "home");

		return result;
	}

	@RequestMapping("/services.do")
	public ModelAndView services() {
		ModelAndView result = new ModelAndView("services", "activeTab", "services");
		
		return result;
	}

	@RequestMapping("/company.do")
	public ModelAndView company() {
		ModelAndView result = new ModelAndView("company", "activeTab", "company");

		return result;
	}

	@RequestMapping("/clients.do")
	public ModelAndView clients() {
		ModelAndView result = new ModelAndView("clients", "activeTab", "clients");

		return result;
	}

	@RequestMapping("/contacts.do")
	public ModelAndView contacts() {
		ModelAndView result = new ModelAndView("contacts", "activeTab", "contacts");

		return result;
	}
}
