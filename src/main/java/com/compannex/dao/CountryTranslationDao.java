package com.compannex.dao;

import com.compannex.model.CountryTranslation;

public interface CountryTranslationDao {

	public CountryTranslation getCountryTranslation(final int countryId,
			final int languageID);

}
