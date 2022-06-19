package com.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.beans.Comment;
@Component
public interface CommentMapper {
	@Select("select * from comment")
	List<Comment> getAllComment();
	
	@Select("select * from comment order by comment_id desc limit #{n}")
	List<Comment> getShowComment(int n);
	
	@Delete("delete from comment where comment_id=#{commentId}")
	void commentDelete(Integer commentId);
	
	@Insert("insert into comment(comment_pid,comment_pname,comment_article_id,comment_author_name,comment_author_email,comment_author_url,comment_content,comment_ip,comment_create_time,comment_role) values(#{commentPid},#{commentPname},#{commentArticleId},#{commentAuthorName},#{commentAuthorEmail},#{commentAuthorUrl},#{commentContent},#{commentIp},#{commentCreateTime},#{commentRole}) ")
	void commentInsert(Comment comment);
	
	@Select("select * from comment where comment_id=#{commentId}")
	Comment getCommentById(Integer commentId);
}
