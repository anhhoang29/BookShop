package com.r2s.model;


import com.r2s.entity.Book;
import com.r2s.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {
    private Integer bookId;
    private String bookName;
    private String description;
    private String authorName;
    private List<String> categoryName;

    public static BookModel transform(Book entity) {
        return BookModel.builder()
                .bookId(entity.getBookId())
                .bookName(entity.getTitle())
                .description(entity.getDescription())
                .authorName(entity.getAuthor().getAuthorName())
                .categoryName(entity.getCategories().stream().map(Category::getCategoryName).toList())
                .build();
    }
}
