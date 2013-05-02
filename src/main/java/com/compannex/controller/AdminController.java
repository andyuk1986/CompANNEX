package com.compannex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.compannex.biz.FeedbackMethods;
import com.compannex.biz.NewsMethods;
import com.compannex.biz.QuestionMethods;
import com.compannex.constants.CompANNEXConstants;
import com.compannex.form.Answer;
import com.compannex.validator.AnswerValidation;

@Controller
public class AdminController {

	private static Logger logger = Logger.getLogger(AdminController.class);

	private AnswerValidation answerValidation;
	private QuestionMethods questionMethods;
	private FeedbackMethods feedbackMethods;
	private NewsMethods newsMethods;

    @Autowired
    public AdminController(AnswerValidation answerValidation, QuestionMethods questionMethods,
    		FeedbackMethods feedbackMethods, NewsMethods newsMethods) {
        this.answerValidation = answerValidation;
        this.questionMethods = questionMethods;
        this.feedbackMethods = feedbackMethods;
        this.newsMethods = newsMethods;
    }
    
	@RequestMapping("/adminhome.do")
	public ModelAndView home(HttpServletRequest request) {
		logger.info("Home");

		ModelAndView result = new ModelAndView("adminindex", "activeTab", "home");

		result.addObject("news",
				newsMethods.getLatestNews(CompANNEXConstants.DEFAULT_LANGUAGE));

		return result;
	}

	@RequestMapping("/adminquestions.do")
	public ModelAndView news(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("allquestions", "activeTab", "questions");

		result.addObject("questions",
				questionMethods.getAllOpenQuestions());

		return result;
	}

	@RequestMapping("/answernew.do")
	public ModelAndView newAnswer(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "questionID", required = true) Integer questionID) {

		ModelAndView success = new ModelAndView("answeradd", "activeTab",
				"questions");

		createAnswer(success, questionID);
		
		success.addObject("thread", questionMethods.getQuestionThread(questionID));

		return success;
	}
	
	@RequestMapping("/answeradd.do")
	public ModelAndView addAnswer(
			HttpServletRequest request,
			@Valid Answer answer, BindingResult result) {

		ModelAndView error = new ModelAndView("answeradd", "activeTab",
				"questions");
		
		ModelAndView success = new ModelAndView("allquestions", "activeTab",
				"questions");

		answerValidation.validate(answer, result);
		if (result.hasErrors()) {
			return error;
		}

		questionMethods.addAnswer(answer.getQuestionID(), answer.getText());
		
		success.addObject("questions",
				questionMethods.getAllOpenQuestions());
		
		return success;
	}
	
	private void createAnswer(ModelAndView result, Integer questionID) {
		
		Answer answer = new Answer();
		answer.setQuestionID(questionID);
		
		result.addObject("answer", answer);
	}
}
