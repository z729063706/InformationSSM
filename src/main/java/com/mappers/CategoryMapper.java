package com.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.beans.Category;

@Component
public interface CategoryMapper {
	@Select("select * from category")
	List<Category> getAllCategory();
	
	@Select("select * from category where category_pid=#{categoryId}")
	List<Category> getCategoryByPid(Integer categoryPid);
	
	@Delete("delete from category where category_id=#{categoryId}")
	void deleteCategory(Integer categoryId);
	
	@Insert("insert into category(category_pid,category_name,category_description,category_order,category_icon) values(#{categoryPid},#{categoryName},#{categoryDescription},#{categoryOrder},#{categoryIcon})")
	void addCategory(Category category);
	
	@Delete("delete from article_category_ref where category_id=#{categoryId}")
	void deleteArticleCategory(Integer categoryId);
	
	@Select("select * from category where category_id=#{categoryId}")
	Category getCategoryById(Integer categoryId);
	
	@Update("update category set category_pid=#{categoryPid},category_name=#{categoryName},category_description=#{categoryDescription},category_description=#{categoryIcon} where category_id=#{categoryId}")
	void updateCategory(Category category);
}
