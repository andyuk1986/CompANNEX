package com.compannex.dao;

import com.compannex.model.CompanyTranslation;

public interface CompanyTranslationDao {
	
    public CompanyTranslation getCompanyTranslationById(final int companyId, final int languageId);

    public void addCompanyTranslation(final CompanyTranslation companyTranslation);

    public void editCompanyTranslation(final CompanyTranslation companyTranslation);

    public void deleteCompanyTranslation(final CompanyTranslation companyTranslation);
}
