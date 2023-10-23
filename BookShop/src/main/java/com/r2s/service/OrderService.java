package com.r2s.service;

import com.r2s.model.ActionResult;
import com.r2s.dto.OrderDto;

public interface OrderService {
	ActionResult getAllOrders(Integer page, Integer size); 

    ActionResult createOrder(OrderDto orderIn);
    
    ActionResult getOrderById(Integer orderIn);
    
    ActionResult getOrderByAccount(Integer page, Integer size); //tìm order theo tài khoản
    
    ActionResult updateStatusCompleteOrder(Integer orderIn);
    
    ActionResult updateStatusCancelOrder(Integer orderIn);
    
}
