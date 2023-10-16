package com.r2s.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import com.r2s.entity.Author;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class Book implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer bookId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "publication_year")
	private Integer publicationYear;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName= "authorId")
	private Author author;
	
	
}
