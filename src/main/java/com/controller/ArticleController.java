package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.HtmlUtils;

import com.beans.Article;
import com.beans.Category;
import com.beans.Tag;
import com.beans.User;
import com.github.pagehelper.PageInfo;
import com.service.ArticleService;
import com.service.CategoryService;
import com.service.TagService;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONObject;

@Controller 
@RequestMapping("/Article")
public class ArticleController {
	
	@Resource
	ArticleService articleService;
	
	@Resource
	private CategoryService cateGoryService;  
	
	@Resource
	private TagService tagService; 
	
	@RequestMapping("/allArticles")
	public List<Article> getallArticles(ModelMap model) {
		List<Article> allArticles = articleService.getAllArticle();
		return allArticles;
	}
	
	@RequestMapping(value="")
	public String index(
				@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
				@RequestParam(required = false, defaultValue = "5") Integer pageSize,
				ModelMap m,HttpSession session ) {
		m.put("loginuser",session.getAttribute("session_user"));
		
		PageInfo <Article> pageInfo =articleService.getPageArticleList(pageIndex, pageSize);
		
		m.put("pageUrlPrefix","Article?pageIndex"); 

		m.put("pageInfo", pageInfo);
		return "/article/article-list" ;
	}
	
	@RequestMapping(value ="/add",method=RequestMethod.GET)
	public String add_get(ModelMap m,HttpSession session) {
		m.put("loginuser",session.getAttribute("session_user"));

		List<Category> categoryList= cateGoryService.listCategory();
		
		List<Tag> tagList= tagService.listTag();
		
		 m.put("categoryList", categoryList);
		 m.put("tagList", tagList);
			
		return "/article/article-add";		
	}
	
	@RequestMapping(value ="/add",method=RequestMethod.POST)
	public String add_post(HttpServletRequest request) {
		Article newArticle = new Article();
		User user = (User) request.getSession().getAttribute("session_user");
		newArticle.setArticleUserId(user.getUserId());
		newArticle.setArticleTitle(request.getParameter("articleTitle"));
		newArticle.setArticleContent(request.getParameter("articleContent"));
		String s = HtmlUtil.cleanHtmlTag(request.getParameter("articleContent"));
		newArticle.setArticleSummary(s.length()>150?s.substring(0,150):s);
		newArticle.setArticleCreateTime(new Date());
		newArticle.setArticleUpdateTime(new Date());
		newArticle.setArticleCommentCount(0);
		newArticle.setArticleLikeCount(0);
		newArticle.setArticleViewCount(0);
		newArticle.setArticleIsComment(0);
		newArticle.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));
		newArticle.setArticleOrder(1); 
		int articleParentCategoryId = Integer.parseInt(request.getParameter("articleParentCategoryId"));
		int articleChildCategoryId = Integer.parseInt(request.getParameter("articleChildCategoryId"));
		
		List<Category> categoryList = new ArrayList<Category> (2);
		categoryList.add(new Category(articleParentCategoryId));
		categoryList.add(new Category(articleChildCategoryId));
		newArticle.setCategoryList(categoryList);
		String [] tagIds = request.getParameterValues("articleTagIds");
		List<Tag> tagList =new ArrayList<>();
		for(String tagId:tagIds) {
			tagList.add(new Tag(Integer.parseInt(tagId)));
		}
		newArticle.setTagList(tagList);
		articleService.addArticle(newArticle);
		return "redirect:/Article";
	}
	
	@RequestMapping(value ="/update/{articleId}",method=RequestMethod.GET)
	public String update_get(@PathVariable("articleId") Integer articleId,ModelMap m,HttpSession session) {
		m.put("loginuser",session.getAttribute("session_user"));

		List<Category> categoryList= cateGoryService.listCategory();
		
		List<Tag> tagList= tagService.listTag();
		
		m.put("categoryList", categoryList);
		m.put("tagList", tagList);
		m.put("article",articleService.getArticleById(articleId));
		return "/article/article-update";
	}
	
	@RequestMapping(value ="/update/{articleId}",method=RequestMethod.POST)
	public String update_post(@PathVariable("articleId") Integer articleId,HttpServletRequest request) {
		Article newArticle = articleService.getArticleById(articleId);
		User user = (User) request.getSession().getAttribute("session_user");
		newArticle.setArticleUserId(user.getUserId());
		newArticle.setArticleTitle(request.getParameter("articleTitle"));
		newArticle.setArticleContent(request.getParameter("articleContent"));
		newArticle.setArticleUpdateTime(new Date());
		newArticle.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));
		int articleParentCategoryId = Integer.parseInt(request.getParameter("articleParentCategoryId"));
		int articleChildCategoryId = Integer.parseInt(request.getParameter("articleChildCategoryId"));
		
		List<Category> categoryList = new ArrayList<Category> (2);
		categoryList.add(new Category(articleParentCategoryId));
		categoryList.add(new Category(articleChildCategoryId));
		newArticle.setCategoryList(categoryList);
		String [] tagIds = request.getParameterValues("articleTagIds");
		List<Tag> tagList =new ArrayList<>();
		for(String tagId:tagIds) {
			tagList.add(new Tag(Integer.parseInt(tagId)));
		}
		newArticle.setTagList(tagList);
		articleService.updateArticle(newArticle);
		return "redirect:/Article";
	}
	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("articleId"));
		articleService.deleteArticle(id);
		return "redirect:/Article";
	}
	
	@RequestMapping(value ="/newdraft",method=RequestMethod.POST)
	public String addNewDrift(HttpServletRequest request) {
		Article article = new Article();
		User user = (User) request.getSession().getAttribute("session_user");
		article.setArticleUserId(user.getUserId());
		article.setArticleTitle(request.getParameter("articleTitle"));
		article.setArticleContent(request.getParameter("articleContent"));
		String s = HtmlUtil.cleanHtmlTag(request.getParameter("articleContent"));
		article.setArticleSummary(s.length()>150?s.substring(0,150):s);
		article.setArticleCreateTime(new Date());
		article.setArticleUpdateTime(new Date());
		article.setArticleCommentCount(0);
		article.setArticleLikeCount(0);
		article.setArticleViewCount(0);
		article.setArticleIsComment(0);
		article.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));
		article.setArticleOrder(1); 
		article.setCategoryList(null);
		article.setTagList(null);
		articleService.addArticle(article);
		return "redirect:/Article";
	}
	
	
	@ResponseBody @RequestMapping("/uploadImg")
	public String uploadArticleImg(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		//得到客户端传过来的图片 , imgFile 是一个固定名称
		MultipartFile file= request.getFile("imgFile");
		
		//随机生成一个文件名
		String fileName=UUID.randomUUID().toString()+".jpg";
		
		//定义一个存放文件的目标
		File destFile=new File("D:/spring_imgupload/"+fileName);
		
		//把文件存到某个目录下
		file.transferTo(destFile);

		String path="http://localhost:8080/upload/"+fileName;
		
		//注意,这个 JSONObject 是来源于 hutool 工具包
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", path);

		return  obj.toString();
	}
}
