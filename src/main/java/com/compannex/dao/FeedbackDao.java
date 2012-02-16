package com.compannex.dao;

import java.io.Serializable;
import java.util.List;

import com.compannex.model.Feedback;
import com.compannex.model.News;

public interface FeedbackDao extends Serializable{

	
	public List<Feedback> getAllFeedbacks();

	public List<Feedback> getLastFeedbacks();
}
