package com.beans;

import java.util.List;

public class Category {
	private Integer categoryId;
	private Integer categoryPid;
	private String categoryName;
	private String categoryDescription;
	private String categoryIcon;
	private Integer categoryOrder;
	private Integer articleCount; 
	private List<Category> clidrenCategory;
	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public List<Category> getClidrenCategory() {
		return clidrenCategory;
	}
	public void setClidrenCategory(List<Category> clidrenCategory) {
		this.clidrenCategory = clidrenCategory;
	}
	public Integer getCategoryPid() {
		return categoryPid;
	}
	public void setCategoryPid(Integer categoryPid) {
		this.categoryPid = categoryPid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public String getCategoryIcon() {
		return categoryIcon;
	}
	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}
	public Integer getCategoryOrder() {
		return categoryOrder;
	}
	public void setCategoryOrder(Integer categoryOrder) {
		this.categoryOrder = categoryOrder;
	}
	public Category(){}
	public Category(Integer categoryId){
		this.categoryId=categoryId;
	}
}
