package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.DTO.PostDTO;
import com.blog.DTO.PostResponse;

@Service
public interface PostService {

	PostDTO createPost(PostDTO postDTO);

	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDTO getPostById(Long postId);

	PostDTO updatePostById(PostDTO postDTO, Long postId);

	void deletePostById(Long postId);

}
