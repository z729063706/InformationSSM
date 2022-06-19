package com.service;

import java.util.List;

import com.beans.Tag;


public interface TagService {
	List<Tag> listTag();
	void addTag(Tag newTag);
	void deleteTag(Integer tagId);
	Tag getTag(Integer tagId);
	void updateTag(Tag tag);
}
