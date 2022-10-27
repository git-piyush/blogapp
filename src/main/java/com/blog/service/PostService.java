package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.DTO.PostDTO;

@Service
public interface PostService {

	public PostDTO createPost(PostDTO postDto);
	
}
