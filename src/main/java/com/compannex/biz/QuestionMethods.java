package com.compannex.biz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.compannex.dao.AnswerDao;
import com.compannex.dao.CompanyDao;
import com.compannex.dao.QuestionDao;
import com.compannex.mail.MailService;
import com.compannex.model.Answer;
import com.compannex.model.Answerable;
import com.compannex.model.Question;

public class QuestionMethods {

	private QuestionDao questionDao;
	
	private AnswerDao answerDao;
	
	private CompanyDao companyDao;
	
	private MailService mailService;

	public void addQuestion(final Integer companyID, final String person, final String email, final String subject, final String text, final Integer parentQuestionID) {
		
		Question question = new Question();
		if (companyID != null) {
			question.setCompanyID(companyID);
		} else {
			question.setPerson(person);
			question.setEmail(email);
		}
		question.setSubject(subject);
		question.setText(text);
		question.setDate(new Date());
		question.setIsNew(true);				
		
		if (parentQuestionID != null) {
			Question parent = getQuestionDao().getQuestion(parentQuestionID);
			
			question.setQuestionID(parentQuestionID);
			question.setSubject("Re:" + parent.getSubject());
			question.setCompanyID(parent.getCompanyID());
			question.setPerson(parent.getPerson());
			question.setEmail(parent.getEmail());
			
			parent.setIsNew(true);
			getQuestionDao().editQuestion(parent);
		}
		
		getQuestionDao().addQuestion(question);
	}
	
	public void addAnswer(final Integer questionID, final String text) {
		
		Answer answer = new Answer();
		answer.setQuestionID(questionID);
		answer.setText(text);
		answer.setDate(new Date());
		
		getAnswerDao().addAnswer(answer);
		
		Question question = getQuestionDao().getQuestion(questionID);
		question.setIsNew(false);
		getQuestionDao().editQuestion(question);
		
		String email = question.getEmail();
		if (email == null) {
			email = getCompanyDao().getCompanyById(question.getCompanyID()).getEmail();
		}
		
		getMailService().sendAnswer(email, questionID);
	}

	public List<Question> getAllOpenQuestions() {
		return getQuestionDao().getNewQuestions();
	}
	
	public List<Answerable> getQuestionThread(final Integer questionID) {
		List questions = getQuestionDao().getQuestionsThread(questionID);
		
		List answers = getAnswerDao().getAnswersByQuestionId(questionID);
		
		List<Answerable> result = new ArrayList<Answerable>();
		result.addAll(questions);
		result.addAll(answers);
		
		Collections.sort(result, new QuestionComparator());
		
		return result;
	}
	
	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public AnswerDao getAnswerDao() {
		return answerDao;
	}

	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
}

class QuestionComparator implements Comparator<Answerable> {
	public int compare(Answerable o1, Answerable o2) {
	    return o1.getDate().compareTo(o2.getDate());
    }	
}
