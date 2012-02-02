package com.compannex.dao;

import com.compannex.model.Category;
import com.compannex.model.Industry;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Annushka
 * Date: 1/28/12
 * Time: 8:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryDao extends Serializable{

    public Category getCategoryById(final int categoryId);

    public void saveCategory(final Category category);

    public void editCategory(final Category category);

    public void deleteCategory(final Category category);

    public Category getCategoryByIndustry(final Industry industry);
}
