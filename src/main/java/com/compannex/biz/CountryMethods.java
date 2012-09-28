package com.compannex.biz;

import java.util.List;

import com.compannex.dao.CountryDao;
import com.compannex.dao.CountryTranslationDao;
import com.compannex.model.Country;

public class CountryMethods {

	private CountryDao countryDao;

	private CountryTranslationDao countryTranslationDao;

	public List<Country> getAllCountries(int languageID) {
		List<Country> countries = getCountryDao().getAllCountries();

		for (Country country : countries) {
			country.setTranslation(getCountryTranslationDao()
					.getCountryTranslation(country.getID(), languageID));
		}
		return countries;
	}

	public CountryDao getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public CountryTranslationDao getCountryTranslationDao() {
		return countryTranslationDao;
	}

	public void setCountryTranslationDao(
			CountryTranslationDao countryTranslationDao) {
		this.countryTranslationDao = countryTranslationDao;
	}

}
