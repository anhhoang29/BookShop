package com.r2s.entity;
import com.r2s.entity.Order;
import com.r2s.entity.Book;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer order_detailId;
	
	@Column(name = "quantity_order")
	private Integer quantityOrder;
	
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "oder_id", referencedColumnName= "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "book_id", referencedColumnName= "book_id")
	private Book book;
}
