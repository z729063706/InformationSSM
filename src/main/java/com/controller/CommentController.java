package com.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beans.Article;
import com.beans.Comment;
import com.beans.User;
import com.github.pagehelper.PageInfo;
import com.service.CommentService;

@Controller
@RequestMapping("/Comment")
public class CommentController {
	@Resource
	CommentService commentService;
	
	@RequestMapping(value="")
	public String index(
				@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
				@RequestParam(required = false, defaultValue = "5") Integer pageSize,
				ModelMap m,HttpSession session ) {
		m.put("loginuser",session.getAttribute("session_user"));
		
		PageInfo <Comment> pageInfo =commentService.getPageCommentList(pageIndex, pageSize);
		
		m.put("pageUrlPrefix","Comment?pageIndex"); 

		m.put("pageInfo", pageInfo);
		return "/comment/comment-list" ;
	}
	
	@RequestMapping(value="/reply/{commentId}",method=RequestMethod.GET)
	public String replyComment_get(@PathVariable("commentId") Integer commentId,ModelMap m,HttpServletRequest request) {
		m.put("comment",commentService.getComment(commentId));
		return "/comment/comment-reply" ;
		
	}
	
	@RequestMapping(value="/reply/{commentId}",method=RequestMethod.POST)
	public String replyComment_post(@PathVariable("commentId") Integer commentId,ModelMap m,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("session_user");
		Comment comment = new Comment();
		comment.setCommentPid(commentId);
		comment.setCommentAuthorName(user.getUserName());
		comment.setCommentAuthorEmail(user.getUserEmail());
		comment.setCommentAuthorUrl(user.getUserUrl());
		comment.setCommentCreateTime(new Date());
		comment.setCommentContent(request.getParameter("commentContent"));
		comment.setCommentIp(request.getRemoteAddr());
		comment.setCommentRole(1);
		commentService.addComment(comment);
		return "forward:/Comment";
	}
	
	@RequestMapping("/delete/{commentId}")
	public String deleteComment(@PathVariable("commentId") Integer commentId) {
		commentService.deleteComment(commentId);
		return "forward:/Comment";
	}
}
