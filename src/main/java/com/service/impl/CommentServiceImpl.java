package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beans.Article;
import com.beans.Comment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mappers.ArticleMapper;
import com.mappers.CommentMapper;
import com.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	@Resource
	CommentMapper commentMapper;
	
	@Resource
	ArticleMapper articleMapper;
	
	@Override
	public List<Comment> getAllComment() {		
		return commentMapper.getAllComment();
	}
	
	public PageInfo<Comment> getPageCommentList(Integer pageIndex, Integer pageSize) {		
		PageHelper.startPage(pageIndex,pageSize);		
		List<Comment> commentList = commentMapper.getAllComment(); 
		for(int i=0;i<commentList.size();i++) {
			
			//�õ������۵����µ�id
			int articleId=commentList.get(i).getCommentArticleId();
			
			//��������id�õ�������Ϣ
			Article article=articleMapper.getArticleById(articleId);
			
			//��������Ϣ��������
			commentList.get(i).setArticle(article);
			
		}
		return  new PageInfo<Comment>(commentList);
	}
	
	@Override
	public List<Comment> getShowComment() {
		List<Comment> showComments = commentMapper.getShowComment(5);
		for(int i=0;i<showComments.size();i++) {
			
			//�õ������۵����µ�id
			int commentArticleId=showComments.get(i).getCommentArticleId();
			
			//��������id�õ�������Ϣ
			Article article=articleMapper.getArticleById(commentArticleId);
			
			//��������Ϣ��������
			showComments.get(i).setArticle(article);
			
		}
		return showComments;
	}

	@Override
	public void addComment(Comment comment) {
		Comment c = commentMapper.getCommentById(comment.getCommentPid());
		comment.setCommentPname(c.getCommentAuthorName());
		comment.setCommentArticleId(c.getCommentArticleId());
		commentMapper.commentInsert(comment);
	}

	@Override
	public Comment getComment(Integer commentId) {
		return commentMapper.getCommentById(commentId);
	}

	@Override
	public void deleteComment(Integer commentId) {
		commentMapper.commentDelete(commentId);
		
	}

	
}
