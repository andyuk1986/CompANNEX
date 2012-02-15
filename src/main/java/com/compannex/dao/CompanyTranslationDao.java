package com.compannex.dao;

import com.compannex.model.CompanyTranslation;
import com.compannex.model.Language;

public interface CompanyTranslationDao {
    public CompanyTranslation getCompanyTranslationById(final int companyId);

    public CompanyTranslation getCompanyTranslationByName(final String companyName);

    public CompanyTranslation getCompanyTranslationByLanguage(final Language language);

    public void addCompanyTranslation(final CompanyTranslation companyTranslation);

    public void editCompanyTranslation(final CompanyTranslation companyTranslation);

    public void deleteCompanyTranslation(final CompanyTranslation companyTranslation);
}
