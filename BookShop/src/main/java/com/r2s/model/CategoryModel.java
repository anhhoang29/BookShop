package com.r2s.model;

import com.r2s.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
	private Integer categoryId;
	private String categoryName;
	
	public static CategoryModel transform(Category entity) {
		return CategoryModel.builder()
				.categoryId(entity.getCategoryId())
				.categoryName(entity.getCategoryName())
				.build();
	}
	
}
