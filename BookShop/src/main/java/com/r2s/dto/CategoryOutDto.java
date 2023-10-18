package com.r2s.dto;

import java.util.List;

import com.r2s.model.CategoryModel;
import lombok.Data;
@Data
public class CategoryOutDto {
	private List<CategoryModel> categories;
	private Integer total;
}

