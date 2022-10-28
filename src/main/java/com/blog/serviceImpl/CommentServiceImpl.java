package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.DTO.CommentDTO;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.BlogApiException;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Long postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		Comment comment = commentDTOToCommentEntity(commentDTO);

		comment.setPost(post);

		Comment savedComment = commentRepository.save(comment);

		return commentEntityToCommentDTO(savedComment);
	}

	private CommentDTO commentEntityToCommentDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setName(comment.getName());
		commentDTO.setEmail(comment.getEmail());
		commentDTO.setBody(comment.getBody());
		return commentDTO;

	}

	private Comment commentDTOToCommentEntity(CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setId(commentDTO.getId());
		comment.setName(commentDTO.getName());
		comment.setEmail(commentDTO.getEmail());
		comment.setBody(commentDTO.getBody());
		return comment;

	}

	@Override
	public List<CommentDTO> getAllCommentsByPostId(Long postId) {

		List<Comment> comments = commentRepository.findByPostId(postId);

		List<CommentDTO> commentDTO = comments.stream().map(comment -> commentEntityToCommentDTO(comment))
				.collect(Collectors.toList());

		return commentDTO;
	}

	@Override
	public CommentDTO getCommentById(Long postId, Long commentId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment Does Not Belong to post");
		}

		return commentEntityToCommentDTO(comment);
	}

	@Override
	public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment Does Not Belong to post");
		}

		comment.setName(commentDTO.getName());
		comment.setEmail(commentDTO.getEmail());
		comment.setBody(commentDTO.getBody());

		Comment updatedComment = commentRepository.save(comment);

		return commentEntityToCommentDTO(updatedComment);
	}

	@Override
	public void deleteCommentById(Long postId, Long commentId) {
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment Does Not Belong to post");
		}
		
		commentRepository.deleteById(commentId);
		
	}

}
