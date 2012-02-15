package com.compannex.dao;

import java.io.Serializable;
import java.util.List;

import com.compannex.model.Country;

/**
 * The DAO interface which includes all necessary methods for working with DB.
 */
public interface CountryDao extends Serializable {
	/**
	 * Returns the Country by the given ID.
	 * 
	 * @param id
	 *            the ID of the Country in DB.
	 * @return the Country object representing the one in DB.
	 */
	public Country getCountryById(final int id);

	public List<Country> getAllCountries();

}
