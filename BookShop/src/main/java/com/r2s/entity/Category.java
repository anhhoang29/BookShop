package com.r2s.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "category_name")
	private String categoryName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="Book_Category",
			joinColumns = @JoinColumn(name="categoryId"),
			inverseJoinColumns = @JoinColumn(name="bookId"))
	private List<Book> books;

	
	
}
