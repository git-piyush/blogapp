package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.DTO.CommentDTO;

@Service
public interface CommentService {

	CommentDTO createComment(CommentDTO commentDTO, Long postId);
	
	List<CommentDTO> getAllCommentsByPostId(Long postId);
	
	CommentDTO getCommentById(Long postId, Long commentId);
	
	CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO);
	
	void deleteCommentById(Long postId, Long commentId);
	
}
