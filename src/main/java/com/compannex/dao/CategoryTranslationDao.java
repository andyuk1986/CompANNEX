package com.compannex.dao;

import com.compannex.model.CategoryTranslation;

public interface CategoryTranslationDao {
    public CategoryTranslation getCategoryTranslationById(final int categoryId, final int languageId);

    public void saveCategoryTranslation(final CategoryTranslation categoryTranslation);

    public void editCategoryTranslation(final CategoryTranslation categoryTranslation);

    public void deleteCategoryTranslation(final CategoryTranslation categoryTranslation);
}
