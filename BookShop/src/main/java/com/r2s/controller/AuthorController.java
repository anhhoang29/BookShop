package com.r2s.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.entity.Author;
import com.r2s.enums.ErrorCodeEnum;
import com.r2s.model.ActionResult;
import com.r2s.model.ResponseBuild;
import com.r2s.model.ResponseModel;
import com.r2s.service.AuthorService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "author")
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	@Autowired
	private ResponseBuild responseBuild;
	
	@GetMapping("/author")
	public ResponseModel getAllAuthor() {
		ActionResult result = null;
		try {
			result = authorService.getAllAuthor();
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}
	
	@GetMapping("/{authorId}")
	public ResponseModel getAuthorById(@PathVariable Integer authorId) {
		ActionResult result = null;
		try {
			result = authorService.getAuthorById(authorId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	
	@PostMapping("/author")
	public ResponseModel createAuthor(@RequestBody Author authors) {
		ActionResult result = null;
		try {
			result = authorService.createAuthor(authors);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	
	@DeleteMapping("/author/{authorId}")
	public ResponseModel deleteAuthor(@PathVariable Integer authorId) {
		ActionResult result = null;
		try {
			result = authorService.deleteAuthor(authorId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	@DeleteMapping("/author")
	public ResponseModel deleteAllAuthorId(@RequestParam List<Integer> authorId) {
		ActionResult result = null;
		try {
			result = authorService.deleteAllBYIds(authorId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	@PutMapping("/author/{authorId}")
	public ResponseModel updateAuthor(@PathVariable Integer authorId, @RequestBody Author updateAuthor) {
		ActionResult result = null;
		try {
			result = authorService.updatetAuthor(authorId,updateAuthor);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	
}
