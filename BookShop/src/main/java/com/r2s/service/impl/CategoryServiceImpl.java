package com.r2s.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.entity.Category;
import com.r2s.enums.ErrorCodeEnum;
import com.r2s.model.ActionResult;
import com.r2s.dto.CategoryOutDto;
import com.r2s.model.CategoryModel;
import com.r2s.repository.CategoryRepository;
import com.r2s.service.CategoryService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ActionResult getCategories() {
		ActionResult result = new ActionResult();

		List<Category> categories = categoryRepository.findAll();

		if (categories.isEmpty()) {
			result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
			return result;
		}

		List<CategoryModel> categoryModels = categories.stream().map(CategoryModel::transform)
				.collect(Collectors.toList());

		CategoryOutDto outDto = new CategoryOutDto();
		outDto.setCategories(categoryModels);
		outDto.setTotal(categoryModels.size());

		result.setData(outDto);

		return result;
	}

	@Override
	public ActionResult createCategory(Category category) {
		ActionResult result = new ActionResult();

		Category categoryTemp = categoryRepository.save(category);

		if (categoryTemp == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_CREATE_USER);
			return result;
		}

		result.setData(CategoryModel.transform(categoryTemp));
		return result;
	}

	@Override
	public ActionResult getCategoryById(Integer id) {
		ActionResult result = new ActionResult();

		Category category = categoryRepository.getCategoryById(id);
		if (category == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USER);
			return result;
		}
		result.setData(CategoryModel.transform(category));
		return result;
	}

	@Override
	public ActionResult deleteCategory(Integer id) {
		ActionResult result = new ActionResult();
		Category category = categoryRepository.getCategoryById(id);
		if (category == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USER);
			return result;
		} else {
			if (category.getBooks().isEmpty()) {
				categoryRepository.delete(category);
				result.setData(new String("Delete success!"));
			}else {
				result.setErrorCodeEnum(ErrorCodeEnum.INVALID_DELETE);
				return result;
			}
			
		}
		return result;
	}

	@Override
	public ActionResult updatetCategory(Integer id, Category newCategory) {
		ActionResult result = new ActionResult();

		Category categoryTemp = categoryRepository.getCategoryById(id);
		
		if (categoryTemp == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_UPDATE_USER);
			return result;
		} else {
			categoryTemp.setCategoryName(newCategory.getCategoryName());
			
		}
		
		Category categoryUpdate = categoryRepository.save(categoryTemp);

		if (categoryUpdate == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_UPDATE_USER);
			return result;
		}
		result.setData(CategoryModel.transform(categoryUpdate));
		return result;
	}

	@Override
	public ActionResult deleteAllBYIds(List<Integer> categoryId) {
		ActionResult result = new ActionResult();
		try {
			categoryRepository.deleteByIdIn(categoryId);
			result.setData(new String("Delete success!"));
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_DELETE);
			return result;
		}
		
		return result;
	}

}
