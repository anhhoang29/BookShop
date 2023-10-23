package com.r2s.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookDto {

    String title;
    String description;
    String authorName;
    String categoryName;
    String Language;
    BigDecimal price;
    Integer quantityBook;

    private List<AuthorDto> authors;
    private List<CategoryDto> categories;
}

