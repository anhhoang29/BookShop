package com.r2s.model;


import com.r2s.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel {
	private Integer authorId;
	private String authorName;
	private String biography;
	
	
	public static AuthorModel transform(Author entity) {
		return AuthorModel.builder()
				.authorId(entity.getAuthorId())
				.authorName(entity.getAuthorName())
				.biography(entity.getBiography())
				.build();
	}
}
