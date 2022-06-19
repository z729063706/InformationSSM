package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.Tag;
import com.service.TagService;

@Controller 
@RequestMapping("/Tag")
public class TagController {
	@Resource
	TagService tagService;
	
	@RequestMapping(value="")
	public String getAllTag(ModelMap m,HttpSession session) {
		m.put("loginuser",session.getAttribute("session_user"));
		m.put("tagList",tagService.listTag());
		return "/tag/tag-list";
	}
	
	@RequestMapping(value="/addtag",method=RequestMethod.POST)
	public String addTag(Tag newTag) {
		tagService.addTag(newTag);
		return "forward:/Tag";		
	}
	
	@RequestMapping("/delete/{tagId}")
	public String deleteTag(@PathVariable("tagId") Integer tagId) {
		tagService.deleteTag(tagId);
		return "forward:/Tag";
	}
	
	@RequestMapping(value="/update/{tagId}",method=RequestMethod.GET)
	public String updateTag_get(@PathVariable("tagId") Integer tagId,ModelMap m,HttpSession session) {
		m.put("loginuser",session.getAttribute("session_user"));
		m.put("tagList",tagService.listTag());
		m.put("tag",tagService.getTag(tagId));
		return "/tag/tag-update";
	}
	
	@RequestMapping(value="/update/{tagId}",method=RequestMethod.POST)
	public String updateTag_post(@PathVariable("tagId") Integer tagId,ModelMap m,Tag tag) {
		tag.setTagId(tagId);
		tagService.updateTag(tag);
		return "forward:/Tag";
		
	}
}
