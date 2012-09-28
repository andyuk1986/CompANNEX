package com.compannex.dao;

import java.util.List;

import com.compannex.model.CompanyCountry;

public interface CompanyCountryDao {

	public CompanyCountry getCompanyCountryByID(final int ID);

	public void addCompanyCountry(final CompanyCountry companyCountry);

	public void editCompanyCountry(final CompanyCountry companyCountry);

	public void deleteCompanyCountry(final CompanyCountry companyCountry);

	public List<CompanyCountry> getCompanyCountriesByCompany(final int companyID);

}
