package com.r2s.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.entity.Category;
import com.r2s.enums.ErrorCodeEnum;
import com.r2s.model.ActionResult;
import com.r2s.model.ResponseBuild;
import com.r2s.model.ResponseModel;
import com.r2s.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ResponseBuild responseBuild;
	
	@GetMapping("/")
	public ResponseModel getAccountPaging() {
		ActionResult result = null;
		try {
			result = categoryService.getCategories();
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	
	@PostMapping("/")
	public ResponseModel insertAccount(@RequestBody Category category) {
		ActionResult result = null;
		try {
			result = categoryService.createCategory(category);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	
	@GetMapping("/{categoryId}")
	public ResponseModel getAccountById(@PathVariable Integer categoryId) {
		ActionResult result = null;
		try {
			result = categoryService.getCategoryById(categoryId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseModel deleteAccount(@PathVariable Integer categoryId) {
		ActionResult result = null;
		try {
			result = categoryService.deleteCategory(categoryId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	
	@DeleteMapping("/")
	public ResponseModel deleteAllCategoryById(@RequestParam List<Integer> categoryId) {
		ActionResult result = null;
		try {
			result = categoryService.deleteAllBYIds(categoryId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
	
	@PutMapping("/{categoryId}")
	public ResponseModel updateAccount(@PathVariable Integer categoryId, @RequestBody Category category) {
		ActionResult result = null;
		try {
			result = categoryService.updatetCategory(categoryId,category);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result); 
	}
}
