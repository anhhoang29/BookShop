package com.r2s.model;


import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.r2s.entity.OrderDetail;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailModel {
	private Integer orderDetailId;
	private String orderDetailCode;
	private Integer orderId;
	private Integer bookId;
	private Integer quantityOrder;
	private BigDecimal unitPrice;
	
	public static OrderDetailModel transform(OrderDetail entity) {
		return OrderDetailModel.builder()
				.orderDetailId(entity.getOrderDetailId())
				.orderDetailCode(entity.getOrderDetailCode())
				.orderId(entity.getOrder().getOrderId())
				.bookId(entity.getBook().getBookId())
				.quantityOrder(entity.getQuantityOrder())
				.unitPrice(entity.getUnitPrice())
				.build();
	}
}
