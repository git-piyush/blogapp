package com.blog.DTO;

import java.util.Set;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class PostDTO {
	
	private Long id;
	
	@NotNull
	@Size(min = 2, message="Post title should be at least 2 charecters")
	private String title;
	
	@NotNull
	@Size(min=10, message="Description should be at least 10 Charecters")
	private String description;
	
	@NotNull
	private String content;
	private Set<CommentDTO> comments; 
}
