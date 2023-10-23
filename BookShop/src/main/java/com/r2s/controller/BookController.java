package com.r2s.controller;

import com.r2s.dto.BookDto;

import com.r2s.model.ActionResult;
import com.r2s.model.ResponseBuild;
import com.r2s.model.ResponseModel;
import com.r2s.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ResponseBuild responseBuild;

    @GetMapping("/books")
    public ResponseModel getAllBooks() {
        ActionResult result = bookService.getAll();
        return responseBuild.build(result);
    }

    @GetMapping("/books/{id}")
    public ResponseModel getBookById(Long id) {
        ActionResult result = bookService.getByBookId(id);
        return responseBuild.build(result);
    }

    @PostMapping("/books")
    public ResponseModel createBook(BookDto book) {
        ActionResult result = bookService.create(book);
        return responseBuild.build(result);
    }

    @PutMapping("/books/{id}")
    public ResponseModel updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        ActionResult result = bookService.update(id, bookDto);
        return responseBuild.build(result);
    }
    @DeleteMapping("/books/{id}")
    public ResponseModel deleteBook(@PathVariable Long id) {
        ActionResult result = bookService.delete(id);
        return responseBuild.build(result);
    }

}
