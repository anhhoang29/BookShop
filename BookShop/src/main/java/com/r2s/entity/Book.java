package com.r2s.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.r2s.dto.AuthorOutDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    
    @Column(name = "quantity_book")
    private Integer quantityBook;

    //	@ManyToOne
//	@JoinColumn(name = "author_id", referencedColumnName= "authorId")
//	private Author author;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    @JsonIgnore // to avoid infinite loop when serializing JSON response (Jackson) - https://stackoverflow.com/a/41414416
    private Author author;


    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="book_category",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    @JsonIgnore
    private List<Category> categories;


}
