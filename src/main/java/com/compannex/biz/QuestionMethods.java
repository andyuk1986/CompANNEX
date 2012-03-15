package com.compannex.biz;

import java.util.Date;
import java.util.List;

import com.compannex.dao.FeedbackDao;
import com.compannex.dao.QuestionDao;
import com.compannex.model.Feedback;
import com.compannex.model.Question;

public class QuestionMethods {

	private QuestionDao questionDao;

	public void addQuestion(final Integer companyID, final String person, final String email, final String subject, final String text) {
		
		Question question = new Question();
		if (companyID != null) {
			question.setCompanyId(companyID);
		} else {
			question.setPerson(person);
			question.setEmail(email);
		}
		question.setSubject(subject);
		question.setText(text);
		question.setDate(new Date());
		question.setIsNew(true);
		
		getQuestionDao().addQuestion(question);
	}
	
	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

}
