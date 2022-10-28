package com.blog.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.DTO.CommentDTO;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
			@PathVariable(value = "postId") Long postId) {

		CommentDTO savedCommentDTO = commentService.createComment(commentDTO, postId);

		return new ResponseEntity<>(savedCommentDTO, HttpStatus.CREATED);

	}

	// get all the comments associated with any perticular Post
	@GetMapping("/post/{postId}/comment")
	public List<CommentDTO> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId) {

		return commentService.getAllCommentsByPostId(postId);

	}

	@GetMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "commentId") Long commentId) {

		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);

	}

	@PutMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDTO> updateCommentById(@RequestBody CommentDTO commentDTO,
			@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId) {

		return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDTO), HttpStatus.CREATED);

	}

	@DeleteMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "commentId") Long commentId) {
		commentService.deleteCommentById(postId, commentId);
		return new ResponseEntity<>("Comment has been deleted Successfully.", HttpStatus.CREATED);

	}

}
