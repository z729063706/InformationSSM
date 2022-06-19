package com.service;

import java.util.List;

import com.beans.Category;

public interface CategoryService {
	List<Category> listCategory();
	List<Category> allCategory();
	void deleteCategory(Integer categoryId);
	void addCategory(Category category);
	Category getCategoryById(Integer categoryId);
	void updateCategory(Category category);
}
