package com.r2s.model;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.r2s.entity.Order;
import com.r2s.entity.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderModel {
    private Integer orderId;
    private Integer accountId;
    private String orderCode;
    private String phone;
    private Date orderDate;
    private BigDecimal shipPrice;
    private BigDecimal detailPrice;
    private BigDecimal totalAmountPrice;
    private String deliveryAddress;
    private List<OrderDetailModel> OrderDetails;
    private String status;

    public static OrderModel transform(Order order){
        return OrderModel.builder()
        		.orderId(order.getOrderId())
                .accountId(order.getAccount().getAccountId())
                .orderCode(order.getOrderCode())
                .phone(order.getPhone())
                .orderDate(order.getOrderDate())
                .OrderDetails(order.getOrderDetails().stream().map(OrderDetailModel::transform).collect(Collectors.toList()))
                .shipPrice(order.getShipPrice())
                .detailPrice(order.getDetailPrice())
                .totalAmountPrice(order.getTotalAmountPrice())
                .deliveryAddress(order.getDeliveryAddress())
                .status(order.getStatus())
                .build();
    }
}