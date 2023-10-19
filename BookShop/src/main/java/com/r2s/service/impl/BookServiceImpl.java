package com.r2s.service.impl;

import com.r2s.dto.AuthorDto;
import com.r2s.dto.AuthorOutDto;
import com.r2s.dto.BookDto;
import com.r2s.dto.CategoryDto;
import com.r2s.entity.Author;
import com.r2s.entity.Book;
import com.r2s.entity.Category;
import com.r2s.model.ActionResult;
import com.r2s.model.AuthorModel;
import com.r2s.model.BookModel;
import com.r2s.repository.BookRepository;
import com.r2s.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {



    @Autowired
    BookRepository bookRepository;

    @Override
    public ActionResult getByBookId(Long id) {
        ActionResult actionResult = new ActionResult();
        try {
            Book book = bookRepository.findByBookId(id);
            actionResult.setData(BookModel.transform(book));
        } catch (Exception e) {
            actionResult.setSuccess(false);
            actionResult.setMessage(e.getMessage());
        }
        return actionResult;
    }

    @Override
    public ActionResult create(BookDto book) {
        ActionResult actionResult = new ActionResult();
        try {
            List<Author> authors = book.getAuthors().stream()
                    .map(authorDto -> {
                        Author author = new Author();
                        author.setAuthorName(authorDto.getAuthorName());
                        return author;
                    })
                    .collect(Collectors.toList());

            List<Category> categories = book.getCategories().stream()
                    .map(categoryDto -> {
                        Category category = new Category();
                        category.setCategoryName(categoryDto.getCategoryName());
                        return category;
                    })
                    .collect(Collectors.toList());

            Book book1 = new Book();
            book1.setTitle(book.getTitle());
            book1.setPrice(book.getPrice());
            book1.setAuthor((Author) authors);
            book1.setCategories(categories);
            book1.setDescription(book.getDescription());

            bookRepository.save(book1);

            actionResult.setSuccess(true);
            actionResult.setMessage("Book created successfully");
            actionResult.setData(BookModel.transform(book1));
        } catch (Exception e) {
            actionResult.setSuccess(false);
            actionResult.setMessage(e.getMessage());
        }

        return actionResult;
    }


    @Override
    public ActionResult update(Long id, BookDto book) {
        ActionResult actionResult = new ActionResult();
        try {
            Book book1 = bookRepository.findByBookId(id);

            if (book1 != null) {
                book1.setTitle(book.getTitle());

                List<Author> authors = book.getAuthors().stream()
                        .map(authorDto -> {
                            Author author = new Author();
                            author.setAuthorName(authorDto.getAuthorName());
                            return author;
                        })
                        .collect(Collectors.toList());
                book1.setAuthor((Author) authors);

                book1.setPrice(book.getPrice());

                List<Category> categories = book.getCategories().stream()
                        .map(categoryDto -> {
                            Category category = new Category();
                            category.setCategoryName(categoryDto.getCategoryName());
                            return category;
                        })
                        .collect(Collectors.toList());
                book1.setCategories(categories);

                book1.setDescription(book.getDescription());

                bookRepository.save(book1);
                actionResult.setData(BookModel.transform(book1));
            } else {
                actionResult.setSuccess(false);
                actionResult.setMessage("Book not found");
            }
        } catch (Exception e) {
            actionResult.setSuccess(false);
            actionResult.setMessage(e.getMessage());
        }
        return actionResult;
    }

    @Override
    public ActionResult delete(Long id) {
        ActionResult actionResult = new ActionResult();
        try {
            Book book = bookRepository.findByBookId(id);
            if (book != null) {
                bookRepository.delete(book);
                actionResult.setData(BookModel.transform(book));
            } else {
                actionResult.setSuccess(false);
                actionResult.setMessage("Book not found");
            }
        } catch (Exception e) {
            actionResult.setSuccess(false);
            actionResult.setMessage(e.getMessage());
        }
        return actionResult;
    }

    @Override
    public ActionResult getAll() {

        ActionResult actionResult = new ActionResult();
        try {
            actionResult.setData(bookRepository.findAll());
        } catch (Exception e) {
            actionResult.setSuccess(false);
            actionResult.setMessage(e.getMessage());
        }
        return actionResult;
    }



}
