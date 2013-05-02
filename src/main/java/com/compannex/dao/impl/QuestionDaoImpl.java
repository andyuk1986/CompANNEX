package com.compannex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.compannex.dao.QuestionDao;
import com.compannex.model.Question;

public class QuestionDaoImpl extends HibernateDaoSupport implements QuestionDao {

	private static Logger logger = Logger.getLogger(QuestionDaoImpl.class);

	@Override
    public Question getQuestion(int questionId) {
		Session session = null;
		try {
			Question question = null;
			session = getSession();
			Object obj = session
					.createQuery("from Question as quest where quest.ID= ?").setCacheable(true)
					.setInteger(0, questionId).uniqueResult();
			if (obj != null) {
				question = (Question) obj;
			}

			return question;
		} finally {
			if (session != null)
				session.close();
		}
    }
	
	@Override
	public List<Question> getAllQuestions() {
		List<Question> questions = getHibernateTemplate().loadAll(
				Question.class);

		if (questions == null)
			questions = new ArrayList<Question>();

		return questions;
	}
	
	@Override
	public List<Question> getQuestionsThread(int questionId) {
		Session session = null;
		try {
			List<Question> questions = null;
			session = getSession();
			Object obj = session.createQuery(
					"from Question as qu where qu.ID =? OR qu.questionID =?").setCacheable(true)
					.setInteger(0, questionId).setInteger(1, questionId).list();
			if (obj != null) {
				questions = (List<Question>) obj;
			}

			return questions;
		} finally {
			if (session != null)
				session.close();
		}		
	}

	@Override
	public List<Question> getNewQuestions() {
		Session session = null;
		try {
			List<Question> questions = null;
			session = getSession();
			Object obj = session.createQuery(
					"from Question as qu where qu.isNew = true and qu.questionID IS NULL").list();
			if (obj != null) {
				questions = (List<Question>) obj;
			}

			return questions;
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
    public void addQuestion(Question question) {
        getHibernateTemplate().save(question);
    }
	
	@Override
    public void editQuestion(Question question) {
        getHibernateTemplate().update(question);
    }
}
