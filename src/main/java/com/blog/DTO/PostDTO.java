package com.blog.DTO;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDTO {
	
	private Long id;
	
	@NotEmpty
	@Size(min = 2, message="Post title should be at least 2 charecters")
	private String title;
	
	@NotEmpty
	@Size(min=10, message="Post Description should be at least 10 Charecters")
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<CommentDTO> comments; 
}
