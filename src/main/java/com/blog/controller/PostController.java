package com.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.DTO.PostDTO;
import com.blog.DTO.PostResponse;
import com.blog.service.PostService;
import com.blog.utils.AppConstants;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/post")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {

		PostDTO newPostDTO = postService.createPost(postDTO);

		return new ResponseEntity<PostDTO>(newPostDTO, HttpStatus.CREATED);

	}

	@GetMapping("/post")
	public PostResponse getAllPost(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {

		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDTO> getPostByPostId(@PathVariable Long postId) {

		PostDTO post = postService.getPostById(postId);

		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);

	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDTO> updatePostByPostId(@Valid @RequestBody PostDTO postDTO,
			@PathVariable(name = "postId") Long postId) {

		PostDTO post = postService.updatePostById(postDTO, postId);

		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);

	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<String> deletePostByPostId(@PathVariable(name = "postId") Long postId) {

		postService.deletePostById(postId);

		return new ResponseEntity<String>("Post has been deleted successfully", HttpStatus.OK);

	}

}
