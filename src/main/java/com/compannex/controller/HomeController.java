package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.FeedbackMethods;
import com.compannex.biz.NewsMethods;
import com.compannex.biz.QuestionMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.form.Question;
import com.compannex.model.Company;
import com.compannex.validator.QuestionValidation;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	private QuestionValidation questionValidation;

    @Autowired
    public HomeController(QuestionValidation questionValidation) {
        this.questionValidation = questionValidation;
    }
    
	@RequestMapping("/home.do")
	public ModelAndView home(HttpServletRequest request) {
		logger.info("Home");

		ModelAndView result = new ModelAndView("index", "activeTab", "home");

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		NewsMethods newsMethods = (NewsMethods) context.getBean("newsMethods");

		result.addObject("news",
				newsMethods.getLatestNews(CompANNEXConstants.DEFAULT_LANGUAGE));

		return result;
	}

	@RequestMapping("/services.do")
	public ModelAndView services() {
		ModelAndView result = new ModelAndView("services", "activeTab",
				"services");

		return result;
	}

	@RequestMapping("/company.do")
	public ModelAndView company(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("company", "activeTab",
				"company");

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		FeedbackMethods feedbackMethods = (FeedbackMethods) context
				.getBean("feedbackMethods");

		result.addObject("feedbacks", feedbackMethods.getLatestFeedbacks());
		return result;
	}

	@RequestMapping("/contacts.do")
	public ModelAndView contacts(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("contacts", "activeTab",
				"contacts");
		createQuestion(request, result);
		return result;
	}

	@RequestMapping("/news.do")
	public ModelAndView news(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("allnews", "activeTab", "home");

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		NewsMethods newsMethods = (NewsMethods) context.getBean("newsMethods");

		result.addObject("news",
				newsMethods.getLatestNews(CompANNEXConstants.DEFAULT_LANGUAGE));

		return result;
	}
	
	@RequestMapping("/questionadd.do")
	public ModelAndView addQuestion(
			HttpServletRequest request,
			@Valid Question question, BindingResult result) {

		ModelAndView success = new ModelAndView("contacts", "activeTab",
				"contacts");

		questionValidation.validate(question, result);
		if (result.hasErrors()) {
			createQuestion(request, success);
			return success;
		}

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		QuestionMethods questionMeth = (QuestionMethods) context
				.getBean("questionMethods");

		questionMeth.addQuestion(question.getCompanyID(), question.getPerson(), question.getEmail(), question.getSubject(), question.getText());
		
		success.addObject("success", "Thank You for Your question.");
		return success;
	}
	
	private void createQuestion(HttpServletRequest request, ModelAndView result) {
		
		Question question = new Question();
		Company loginCompany = (Company)request.getSession().getAttribute("loginCompany");
		if (loginCompany != null) {
			question.setCompanyID(loginCompany.getID());
		}
		
		result.addObject("question", question);
	}
}
