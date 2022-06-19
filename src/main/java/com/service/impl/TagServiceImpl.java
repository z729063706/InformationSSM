package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beans.Tag;
import com.mappers.ArticleMapper;
import com.mappers.TagMapper;
import com.service.TagService;
@Service
public class TagServiceImpl implements TagService {
	@Resource
	TagMapper tagMapper;
	
	@Resource
	ArticleMapper articleMapper;
	
	@Override
	public List<Tag> listTag() {
		
		List<Tag> tagList = tagMapper.getAllTag();
		for(Tag tag : tagList) {
			tag.setArticleCount(articleMapper.getArticleCountByTagId(tag.getTagId()));
		}
		return tagList;
	}

	@Override
	public void addTag(Tag newTag) {
		tagMapper.addNewTag(newTag);
	}

	@Override
	public void deleteTag(Integer tagId) {
		tagMapper.deleteTagById(tagId);
		tagMapper.deleteArticleTag(tagId);
		
	}

	@Override
	public Tag getTag(Integer tagId) {
		return tagMapper.getTagById(tagId);
	}

	@Override
	public void updateTag(Tag tag) {
		tagMapper.updateTag(tag);
	}

}
