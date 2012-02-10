package com.compannex.dao;

import com.compannex.model.IndustryTranslation;


public interface IndustryTranslationDao {

	public IndustryTranslation getIndustryTranslation(final int industryId, final int languageID);

    public void saveIndustryTranslation(final IndustryTranslation industryTranslation);

    public void editIndustryTranslation(final IndustryTranslation industryTranslation);

    public void deleteIndustryTranslation(final IndustryTranslation industryTranslation);
}
