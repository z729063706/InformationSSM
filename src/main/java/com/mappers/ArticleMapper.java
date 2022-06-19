package com.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.beans.Article;
import com.beans.Category;
@Component
public interface ArticleMapper {
	@Select("select * from article")
	List<Article> getAllArticle();
	
	@Select("select * from article where article_status=1 order by article_id desc limit #{n}")
	List<Article> getShowArticle(int n);
	
	@Select("select * from article where article_id=${id}")
	Article getArticleById(@Param("id") int id);
	
	@Select("select * from category where category_id in(select category_id from article_category_ref where article_id=#{articleId} )")
	List<Category> listCategoryByArticleId(Integer articleId);
	
	void addArticle(Article article);
	
	void updateArticle(Article article);
	
	@Insert("insert into article_category_ref (article_id,category_id)  values (#{articleId}, #{categoryId})")
	void addArticleCategory(@Param("articleId") Integer articleId, @Param("categoryId")  Integer categoryId);
	
	@Insert("insert into article_tag_ref (article_id,tag_id)  values (#{articleId}, #{tagId})")
	void addArticleTag(@Param("articleId")  Integer articleId, @Param("tagId")  Integer tagId);
	
	@Delete("delete from article where article_id=#{articleId}")
	int deleteArticle(int articleId);
	
	@Delete("delete from article_category_ref where article_id=#{articleId}")
	void deleteArticleCategory(Integer articleId);
	
	@Delete("delete from article_tag_ref where article_id=#{articleId}")
	void deleteArticleTag(Integer articleId);
	
	@Delete("delete from article_tag_ref where article_user_id_id=#{userId}")
	void deleteArticleByUser(Integer userId);
	
	@Select("select count(*) from article where article_user_id=#{userId}")
	int getArticleCountByUser(Integer userId);
	
	@Select("select count(*) from article_tag_ref where tag_id=#{tagId}")
	int getArticleCountByTagId(Integer tagId);
	
	@Select("select count(*) from article_category_ref where category_id=#{categoryId}")
	int getArticleCountByCategoryId(Integer categoryId);
	
	@Select("select * from article where article_user_id=#{userId}")
	List<Article> getArticleByUser(Integer userId);
}
