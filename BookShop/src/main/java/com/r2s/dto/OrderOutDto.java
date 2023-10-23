package com.r2s.dto;

import lombok.Data;
import com.r2s.model.OrderModel;

import java.util.List;

@Data
public class OrderOutDto {
    private List<OrderModel> orders;
    private Integer total;
}