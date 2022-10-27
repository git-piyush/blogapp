package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.DTO.PostDTO;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/post")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {

		PostDTO newPostDTO = postService.createPost(postDTO);

		return new ResponseEntity<PostDTO>(newPostDTO, HttpStatus.CREATED);

	}

	@GetMapping("/post")
	public List<PostDTO> createPost() {

		return postService.getAllPosts();

	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDTO> getPostByPostId(@PathVariable Long postId) {

		PostDTO post = postService.getPostById(postId);
		
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);

	}

}
