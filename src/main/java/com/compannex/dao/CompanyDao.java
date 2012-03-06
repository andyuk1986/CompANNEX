package com.compannex.dao;

import java.util.List;

import com.compannex.model.Company;

public interface CompanyDao {

	public Company getCompanyById(final int companyId);
	
	public Company getCompanyByEmail(final String email);

	public List<Company> getCompaniesByIndustryId(final int industryId);

	public List<Company> getCompaniesByCategoryId(final int industryId);

	public List<Company> getAllCompanies();

	public void addCompany(final Company company);

	public void editCompany(final Company company);

	public void deleteCompany(final Company company);

}
