package com.service;

import java.util.List;


import com.beans.Article;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
	List<Article> getShowArticle();

	List<Article> getAllArticle();
	
	Article getArticleById(int id);
	
	PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize);
	
	void deleteArticle(int id);
	
	void addArticle(Article article);
	
	void updateArticle(Article article);
	
	int getUserArticleCount(Integer userId);
}
