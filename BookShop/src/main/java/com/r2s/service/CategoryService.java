package com.r2s.service;

import java.util.List;

import com.r2s.entity.Category;
import com.r2s.model.ActionResult;

public interface CategoryService {
	ActionResult getCategories();
	ActionResult createCategory(Category category);
	ActionResult getCategoryById(Integer categoryId);
//	ActionResult deleteCategory(Integer categoryId);
	ActionResult updatetCategory(Integer categoryId, Category newCategory);
	//ActionResult deleteAllBYIds(List<Integer> categoryId);

}
