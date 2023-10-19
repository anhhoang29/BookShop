package com.r2s.service;

import com.r2s.dto.BookDto;
import com.r2s.entity.Book;
import com.r2s.model.ActionResult;

import java.util.List;

public interface BookService {

    ActionResult getByBookId(Long id);

    ActionResult create(BookDto book);

    ActionResult update(Long id, BookDto book);

    ActionResult delete(Long id);

    ActionResult getAll();

}
