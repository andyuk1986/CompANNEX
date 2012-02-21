package com.compannex.biz;

import java.util.List;

import com.compannex.dao.CategoryDao;
import com.compannex.dao.CategoryTranslationDao;
import com.compannex.dao.IndustryDao;
import com.compannex.dao.IndustryTranslationDao;
import com.compannex.model.Category;
import com.compannex.model.Industry;

public class IndustryMethods {

	private static IndustryMethods instance = null;

	private CategoryDao categoryDao;

	private CategoryTranslationDao categoryTranslationDao;

	private IndustryDao industryDao;

	private IndustryTranslationDao industryTranslationDao;

	public void addNewPartner() {

	}

	public List<Industry> getAllIndustries(int languageID,
			boolean loadCategories) {
		List<Industry> industries = getIndustryDao().getAllIndustries();

		for (Industry ind : industries) {
			ind.setTranslation(getIndustryTranslationDao()
					.getIndustryTranslation(ind.getID(), languageID));

			if (loadCategories) {
				List<Category> categories = getCategoryDao()
						.getCategoriesByIndustryID(ind.getID());
				ind.setCategories(categories);
				for (Category cat : categories) {
					cat.setTranslation(getCategoryTranslationDao()
							.getCategoryTranslationById(cat.getID(), languageID));
				}
			}
		}

		return industries;
	}

	public List<Category> getAllCategories(int industryID, int languageID) {
		List<Category> categories = getCategoryDao().getCategoriesByIndustryID(
				industryID);
		for (Category cat : categories) {
			cat.setTranslation(getCategoryTranslationDao()
					.getCategoryTranslationById(cat.getID(), languageID));
		}

		return categories;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public CategoryTranslationDao getCategoryTranslationDao() {
		return categoryTranslationDao;
	}

	public void setCategoryTranslationDao(
			CategoryTranslationDao categoryTranslationDao) {
		this.categoryTranslationDao = categoryTranslationDao;
	}

	public IndustryDao getIndustryDao() {
		return industryDao;
	}

	public void setIndustryDao(IndustryDao industryDao) {
		this.industryDao = industryDao;
	}

	public IndustryTranslationDao getIndustryTranslationDao() {
		return industryTranslationDao;
	}

	public void setIndustryTranslationDao(
			IndustryTranslationDao industryTranslationDao) {
		this.industryTranslationDao = industryTranslationDao;
	}
}
