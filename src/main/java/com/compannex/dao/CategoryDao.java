package com.compannex.dao;

import java.io.Serializable;
import java.util.List;

import com.compannex.model.Category;

public interface CategoryDao extends Serializable{

    public Category getCategoryById(final int categoryId);

    public void saveCategory(final Category category);

    public void editCategory(final Category category);

    public void deleteCategory(final Category category);

    public List<Category> getCategoriesByIndustryID(final int industryID);
}
