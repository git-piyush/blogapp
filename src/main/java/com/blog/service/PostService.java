package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.DTO.PostDTO;

@Service
public interface PostService {

	PostDTO createPost(PostDTO postDTO);
	
	List<PostDTO> getAllPosts(int pageNo, int pageSize);
	
	PostDTO getPostById(Long postId);
	
	PostDTO updatePostById(PostDTO postDTO, Long postId);
	
	void deletePostById(Long postId);
	
}
