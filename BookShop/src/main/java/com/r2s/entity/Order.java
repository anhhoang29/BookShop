package com.r2s.entity;
import com.r2s.entity.Account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Timestamp;
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
@Table(name = "order")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "order_date")
	private Timestamp orderDate;
	
	@Column(name = "total_amount")
	private BigDecimal totalAmount;
	
	@Column(name = "delivery_address")
	private String deliveryAddress;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName= "account_id")
	private Account account;
	
}
