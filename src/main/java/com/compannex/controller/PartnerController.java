package com.compannex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartnerController {

	
	@RequestMapping("/newpartner.do")
	public ModelAndView newPartner() {
		ModelAndView result = new ModelAndView("newpartner", "activeTab", "clients");
		
		return result;
	}
	
}
