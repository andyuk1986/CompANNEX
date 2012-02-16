package com.compannex.biz;

import java.util.List;

import com.compannex.dao.FeedbackDao;
import com.compannex.model.Feedback;

public class FeedbackMethods {

	private FeedbackDao feedbackDao;

	public List<Feedback> getLatestFeedbacks() {

		List<Feedback> feedbacks = getFeedbackDao().getLastFeedbacks();

		/**
		 * Feedback feedb1 = new Feedback(); feedb1.setPerson("John Smith");
		 * feedb1.setPosition("Manager"); feedb1.setText(
		 * "Lorem ipsum dolor sit amet, consectetur adipis icing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
		 * );
		 * 
		 * CompanyTranslation translation1 = new CompanyTranslation();
		 * translation1.setName("First Corporation");
		 * 
		 * Company comp1 = new Company(); comp1.setTranslation(translation1);
		 * feedb1.setCompany(comp1);
		 * 
		 * feedbacks.add(feedb1);
		 * 
		 * Feedback feedb2 = new Feedback(); feedb2.setPerson("Abraam Lincoln");
		 * feedb2.setPosition("CEO"); feedb2.setText(
		 * "Lorem ipsum dolor sit amet, consectetur adipis icing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
		 * );
		 * 
		 * CompanyTranslation translation2 = new CompanyTranslation();
		 * translation2.setName("Second Corporation");
		 * 
		 * Company comp2 = new Company(); comp2.setTranslation(translation2);
		 * feedb2.setCompany(comp2);
		 * 
		 * feedbacks.add(feedb2);
		 **/

		return feedbacks;
	}

	public List<Feedback> getAllFeedbacks() {
		List<Feedback> feedbacks = getFeedbackDao().getAllFeedbacks();

		/**
		 * Feedback feedb2 = new Feedback();
		 * feedb2.setPerson("Benjamin Franklin");
		 * feedb2.setPosition("President"); feedb2.setText(
		 * "Lorem ipsum dolor sit amet, consectetur adipis icing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
		 * );
		 * 
		 * CompanyTranslation translation2 = new CompanyTranslation();
		 * translation2.setName("America");
		 * 
		 * Company comp2 = new Company(); comp2.setTranslation(translation2);
		 * feedb2.setCompany(comp2);
		 * 
		 * feedbacks.add(feedb2);
		 **/

		return feedbacks;
	}

	public FeedbackDao getFeedbackDao() {
		return feedbackDao;
	}

	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}

}
