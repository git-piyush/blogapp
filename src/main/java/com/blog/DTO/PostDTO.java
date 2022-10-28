package com.blog.DTO;

import java.util.Set;

import lombok.Data;

@Data
public class PostDTO {
	
	private Long id;
	private String title;	
	private String description;
	private String content;
	private Set<CommentDTO> comments; 
}
