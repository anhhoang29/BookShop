package com.r2s.repository;

import com.r2s.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Book findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.author = ?1")
    Book findByAuthor(String author);

    @Query("SELECT b FROM Book b WHERE b.categories = ?1")
    Book findByCategory(String category);

    @Query("SELECT b FROM Book b WHERE b.price = ?1")
    Book findByPrice(String price);

    @Query("SELECT b FROM Book b WHERE b.bookId = ?1")
    Book findByBookId(Long bookId);

    List<Book> findAll();
}
