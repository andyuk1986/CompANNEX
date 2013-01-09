package com.compannex.biz;

import java.util.List;

import com.compannex.dao.NewsDao;
import com.compannex.dao.NewsTranslationDao;
import com.compannex.model.News;

public class NewsMethods {

	private NewsDao newsDao;

	private NewsTranslationDao newsTranslationDao;

	public List<News> getLatestNews(int languageID) {

		List<News> newsList = getNewsDao().getLastNews();
		
		for (News news : newsList) {
			news.setTranslation(getNewsTranslationDao().getNewsTranslation(
					news.getID(), languageID));
		}

		/**
		 * News news1 = new News(); news1.setDate(new Date()); NewsTranslation
		 * trans1 = new NewsTranslation(); trans1.setHeader("First Release");
		 * trans1
		 * .setText("CompANNEX first version is live, so you can register.");
		 * news1.setTranslation(trans1);
		 * 
		 * News news2 = new News(); news2.setDate(new Date()); NewsTranslation
		 * trans2 = new NewsTranslation(); trans2.setHeader("Secod Release");
		 * trans2
		 * .setText("CompANNEX second version is live, so you can register.");
		 * news2.setTranslation(trans2);
		 * 
		 * News news3 = new News(); news3.setDate(new Date()); NewsTranslation
		 * trans3 = new NewsTranslation(); trans3.setHeader("Third Release");
		 * trans3
		 * .setText("CompANNEX third version is live, so you can register.");
		 * news3.setTranslation(trans3);
		 * 
		 * news.add(news1); news.add(news2); news.add(news3);
		 **/

		return newsList;
	}

	public List<News> getAllNews(int languageID) {
		List<News> newsList = getNewsDao().getLastNews();

		for (News news : newsList) {
			news.setTranslation(getNewsTranslationDao().getNewsTranslation(
					news.getID(), languageID));
		}

		return newsList;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public NewsTranslationDao getNewsTranslationDao() {
		return newsTranslationDao;
	}

	public void setNewsTranslationDao(NewsTranslationDao newsTranslationDao) {
		this.newsTranslationDao = newsTranslationDao;
	}

}
