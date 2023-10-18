package com.r2s.service;

import java.util.List;

import com.r2s.entity.Author;
import com.r2s.model.ActionResult;

public interface AuthorService {
	ActionResult getAllAuthor();
	ActionResult createAuthor(Author author);
	ActionResult updatetAuthor(Integer authorId, Author updatedAuthor);
	ActionResult getAuthorById(Integer authorId);
	ActionResult deleteAuthor(Integer authorId);
	ActionResult deleteAllBYIds(List<Integer> authorId);
	

	
}
