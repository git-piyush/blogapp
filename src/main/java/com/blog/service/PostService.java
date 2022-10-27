package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.DTO.PostDTO;

@Service
public interface PostService {

	public PostDTO createPost(PostDTO postDto);
	
	public List<PostDTO> getAllPosts();
	
}
