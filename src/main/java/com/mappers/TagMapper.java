package com.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.beans.Tag;

@Component
public interface TagMapper {
	@Select("select * from tag")
	List<Tag> getAllTag();
	
	@Insert("insert into tag(tag_name,tag_description) values(#{tagName},#{tagDescription})")
	void addNewTag(Tag tag);
	
	@Delete("delete from tag where tag_id=#{tagId}")
	void deleteTagById(Integer tagId);
	
	@Select("select * from tag where tag_id=#{tagId}")
	Tag getTagById(Integer tagId);
	
	@Update("update tag set tag_name=#{tagName}, tag_description=#{tagDescription} where tag_id=#{tagId}")
	void updateTag(Tag tag);
	
	@Delete("delete from article_tag_ref where tag_id=#{tagId}")
	void deleteArticleTag(Integer tagId);
}
