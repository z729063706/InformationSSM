package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beans.Article;
import com.beans.Category;
import com.beans.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mappers.ArticleMapper;
import com.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Resource
	ArticleMapper articleMapper;
	
	@Override
	public List<Article> getAllArticle() {		
		return articleMapper.getAllArticle();
	}

	@Override
	public List<Article> getShowArticle() {
		List<Article> showArticles = articleMapper.getShowArticle(5);
		return showArticles;
	}

	@Override
	public Article getArticleById(int id) {
		return articleMapper.getArticleById(id);
	}
	
	public PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize) {		
		PageHelper.startPage(pageIndex,pageSize);		
		List<Article> articleList =articleMapper.getAllArticle(); 
		
		//对每个文章,都要把它对应的大分类,小分类信息查出来
		for(Article a:  articleList) {
			List<Category> categoryList=articleMapper.listCategoryByArticleId(a.getArticleId());
			a.setCategoryList(categoryList   );
		}	
		return  new PageInfo<Article>(articleList);
	}

	@Override
	public void deleteArticle(int id) {
		articleMapper.deleteArticle(id);
		articleMapper.deleteArticleCategory(id);
		articleMapper.deleteArticleTag(id);
	}

	@Override
	public void addArticle(Article article) {
		
		articleMapper.addArticle(article);
		List<Category> categorylist = article.getCategoryList();
		for(Category c:categorylist ) {
			articleMapper.addArticleCategory(article.getArticleId(), c.getCategoryId());
		}
		
		List<Tag> tagList=article.getTagList();
		for(Tag t: tagList) {
			articleMapper.addArticleTag(article.getArticleId(),t.getTagId());
		}
	}

	@Override
	public int getUserArticleCount(Integer userId) {
		return articleMapper.getArticleCountByUser(userId);
	}

	@Override
	public void updateArticle(Article article) {
		articleMapper.updateArticle(article);
		articleMapper.deleteArticleCategory(article.getArticleId());
		articleMapper.deleteArticleTag(article.getArticleId());
		List<Category> categorylist = article.getCategoryList();
		for(Category c:categorylist ) {
			articleMapper.addArticleCategory(article.getArticleId(), c.getCategoryId());
		}
		
		List<Tag> tagList=article.getTagList();
		for(Tag t: tagList) {
			articleMapper.addArticleTag(article.getArticleId(),t.getTagId());
		}
		
	}

}
