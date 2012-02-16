package com.compannex.dao;

import java.io.Serializable;
import java.util.List;

import com.compannex.model.News;

public interface NewsDao extends Serializable {

	public List<News> getAllNews();

	public List<News> getLastNews();
}
