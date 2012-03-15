package com.compannex.dao;

import java.io.Serializable;
import java.util.List;

import com.compannex.model.Feedback;
import com.compannex.model.News;
import com.compannex.model.Question;

public interface QuestionDao extends Serializable{

	
	public List<Question> getAllQuestions();

	public List<Question> getNewQuestions();
	
	public void addQuestion(Question question);
}
