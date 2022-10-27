package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.DTO.PostDTO;
import com.blog.entity.Post;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	PostRepository postRepository;

	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		//convert PostDTO to Post entity
		Post post = new Post();
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		
		
		Post newPost = postRepository.save(post);
		
		//convert Post Entity to PostDTO
		PostDTO newPostDto = new PostDTO();
		newPostDto.setId(newPost.getId());
		newPostDto.setTitle(newPost.getTitle());
		newPostDto.setDescription(newPost.getDescription());
		newPostDto.setContent(newPost.getContent());
		
		return newPostDto;
	}

}
