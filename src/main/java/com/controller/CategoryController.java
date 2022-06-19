package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.Category;
import com.service.ArticleService;
import com.service.CategoryService;

@Controller @RequestMapping("/Category")
public class CategoryController {
	@Resource
	CategoryService categoryService;
	
	@Resource
	ArticleService articleService;
	
	@RequestMapping("")
	public String allCategory(ModelMap m,HttpSession session) {
		m.put("loginuser",session.getAttribute("session_user"));
		m.put("categoryList",categoryService.allCategory());
		return "/category/category-list";
	}
	
	@RequestMapping(value="/delete/{categoryId}",method=RequestMethod.GET)
	public String deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return "forward:/Category";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCategory(HttpServletRequest request){
		Category category = new Category();
		category.setCategoryName(request.getParameter("categoryName"));
		category.setCategoryPid(Integer.parseInt(request.getParameter("categoryPid")));
		category.setCategoryDescription(request.getParameter("categoryDescription"));
		category.setCategoryOrder(1);
		category.setCategoryIcon(request.getParameter("categoryIcon"));
		categoryService.addCategory(category);
		return "forward:/Category";
	}
	
	@RequestMapping(value="/update/{categoryId}",method=RequestMethod.GET)
	public String updateCategory_get(@PathVariable("categoryId") Integer categoryId,ModelMap m,HttpSession session) {
		m.put("loginuser",session.getAttribute("session_user"));
		m.put("category",categoryService.getCategoryById(categoryId));
		m.put("categoryList",categoryService.allCategory());
		return "/category/category-update";
	}
	
	@RequestMapping(value="/update/{categoryId}",method=RequestMethod.POST)
	public String updateCategory_post(@PathVariable("categoryId") Integer categoryId,Category category) {
		category.setCategoryId(categoryId);
		categoryService.updateCategory(category);
		return "forward:/Category";
		
	} 
	
}
