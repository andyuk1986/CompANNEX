package com.compannex.dao;

import java.io.Serializable;
import java.util.List;

import com.compannex.model.Answer;

public interface AnswerDao extends Serializable{
	
	public List<Answer> getAnswersByQuestionId(int questionId);
	
	public void addAnswer(Answer answer);
}
