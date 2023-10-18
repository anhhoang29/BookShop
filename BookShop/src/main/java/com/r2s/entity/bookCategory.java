//package com.r2s.entity;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//import com.r2s.entity.Book;
//import com.r2s.entity.bookCategoryId;
//import com.r2s.entity.Category;
//import jakarta.persistence.Column;
//import jakarta.persistence.EmbeddedId;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Data;
//@Entity
//@Data
//@Table(name = "book_category")
//public class bookCategory implements Serializable{
//	@EmbeddedId
//	private bookCategoryId id;
//
//	@ManyToOne
//	@JoinColumn(name = "book_id", referencedColumnName= "bookId")
//	private Book book;
//
//	@ManyToOne
//	@JoinColumn(name = "category_id", referencedColumnName= "categoryId")
//	private Category category;
//
//
//}
