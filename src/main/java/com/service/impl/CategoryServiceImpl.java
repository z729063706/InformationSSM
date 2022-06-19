package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beans.Category;
import com.mappers.ArticleMapper;
import com.mappers.CategoryMapper;
import com.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	CategoryMapper categoryMapper;
	
	@Resource
	ArticleMapper articleMapper;
	
	@Override
	public List<Category> listCategory() {
		return categoryMapper.getAllCategory();
	}

	@Override
	public List<Category> allCategory() {
		List<Category> categoryList = categoryMapper.getCategoryByPid(0);
		for(Category category : categoryList) {
			category.setArticleCount(articleMapper.getArticleCountByCategoryId(category.getCategoryId()));
			category.setClidrenCategory(categoryMapper.getCategoryByPid(category.getCategoryId()));
			for(Category children : category.getClidrenCategory()) {
				children.setArticleCount(articleMapper.getArticleCountByCategoryId(children.getCategoryId()));
			}
		}
		return categoryList;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryMapper.deleteCategory(categoryId);
		categoryMapper.deleteArticleCategory(categoryId);
	}

	@Override
	public void addCategory(Category category) {
		categoryMapper.addCategory(category);
	}

	@Override
	public Category getCategoryById(Integer categoryId) {
		return categoryMapper.getCategoryById(categoryId);
	}

	@Override
	public void updateCategory(Category category) {
		categoryMapper.updateCategory(category);
		
	}

}
