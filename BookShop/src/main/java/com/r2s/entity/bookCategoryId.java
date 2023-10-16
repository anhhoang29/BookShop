package com.r2s.entity;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class bookCategoryId implements Serializable {

	@Column(name = "book_id")
	private Integer bookId;
	
	@Column(name = "category_id")
	private Integer categoryId;
}
