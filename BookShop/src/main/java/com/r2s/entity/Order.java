package com.r2s.entity;
import com.r2s.entity.Account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
	
	@Column(name = "order_code")
	private String orderCode;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "ship_price")
	private BigDecimal shipPrice;
	
	@Column(name = "detail_price")
	private BigDecimal detailPrice;
	
	@Column(name = "total_amount_price")
	private BigDecimal totalAmountPrice;
	
	
	@Column(name = "delivery_address")
	private String deliveryAddress;
	
	@Column(name = "status")
	private String status;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> OrderDetails;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName= "account_id")
	private Account account;
	
}
