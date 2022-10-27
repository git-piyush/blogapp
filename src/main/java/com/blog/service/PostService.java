package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.DTO.PostDTO;

@Service
public interface PostService {

	PostDTO createPost(PostDTO postDto);
	
	List<PostDTO> getAllPosts();
	
	PostDTO getPostById(Long postId);
	
}
