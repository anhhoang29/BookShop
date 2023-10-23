package com.r2s.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;


@Data
public class OrderDto {
	private String orderCode;
	private String phone;
	private Date orderDate;
	private List<OrderDetailDto> orderDetails;
	private BigDecimal shipPrice;
	private String deliveryAddress;
	
}
