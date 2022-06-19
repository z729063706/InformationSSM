package com.service;

import java.util.List;

import com.beans.Comment;
import com.github.pagehelper.PageInfo;

public interface CommentService {
	List<Comment> getShowComment();

	List<Comment> getAllComment();
	
	PageInfo<Comment> getPageCommentList(Integer pageIndex, Integer pageSize);
	
	void addComment(Comment comment);
	
	Comment getComment(Integer commentId);
	
	void deleteComment(Integer commentId);
}
