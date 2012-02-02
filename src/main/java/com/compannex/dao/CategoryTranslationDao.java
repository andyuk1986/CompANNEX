package com.compannex.dao;

import com.compannex.model.Category;
import com.compannex.model.CategoryTranslation;
import com.compannex.model.Industry;
import com.compannex.model.Language;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 2/2/12
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryTranslationDao {
    public CategoryTranslation getCategoryTranslationById(final int categoryId);

    public CategoryTranslation getCategoryTranslationByName(final String categoryName);

    public CategoryTranslation getCategoryTranslationByLanguage(final Language language);

    public CategoryTranslation getCategoryTranslationByCategory(final Category language);

    public void saveCategoryTranslation(final CategoryTranslation categoryTranslation);

    public void editCategoryTranslation(final CategoryTranslation categoryTranslation);

    public void deleteCategoryTranslation(final CategoryTranslation categoryTranslation);
}
