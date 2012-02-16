package com.compannex.dao;

import java.io.Serializable;

import com.compannex.model.IndustryTranslation;
import com.compannex.model.NewsTranslation;

public interface NewsTranslationDao extends Serializable {

	public NewsTranslation getNewsTranslation(final int newsId, final int languageID);
}
