package com.example.oauthsession.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {
	
	private Long categoryNo;
	private String categoryName;
	private String categorySection;

}
