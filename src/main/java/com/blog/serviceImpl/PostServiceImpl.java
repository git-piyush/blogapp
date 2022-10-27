package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.DTO.PostDTO;
import com.blog.DTO.PostResponse;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
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
		Post post = postDTOToPostEntity(postDTO);

		Post newPost = postRepository.save(post);

		return postEntityToPostDTO(newPost);
	}

	// convert PostDTO to Post entity
	public Post postDTOToPostEntity(PostDTO postDTO) {
		Post post = new Post();
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		return post;
	}

	// convert Post Entity to PostDTO
	public PostDTO postEntityToPostDTO(Post newPost) {
		PostDTO newPostDto = new PostDTO();
		newPostDto.setId(newPost.getId());
		newPostDto.setTitle(newPost.getTitle());
		newPostDto.setDescription(newPost.getDescription());
		newPostDto.setContent(newPost.getContent());
		return newPostDto;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		//create pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> page = postRepository.findAll(pageable);
		
		//get the content from page
		List<Post> allPosts = page.getContent();
		
		List<PostDTO> allPostsDTO = allPosts.stream().map(post -> postEntityToPostDTO(post))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(allPostsDTO);
		postResponse.setPageNo(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setLast(page.isLast());
		postResponse.setTotalElements(page.getTotalElements());
		postResponse.setTotalPages(page.getTotalPages());
		return postResponse;
	}

	@Override
	public PostDTO getPostById(Long postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		return postEntityToPostDTO(post);
	}

	@Override
	public PostDTO updatePostById(PostDTO postDTO, Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());

		Post updatedPost = postRepository.save(post);

		return postEntityToPostDTO(updatedPost);
	}

	@Override
	public void deletePostById(Long postId) {
		postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepository.deleteById(postId);
	}

}
