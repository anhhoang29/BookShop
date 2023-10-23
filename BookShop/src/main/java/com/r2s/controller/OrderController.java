package com.r2s.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.r2s.enums.ErrorCodeEnum;
import com.r2s.model.ActionResult;
import com.r2s.model.ResponseBuild;
import com.r2s.model.ResponseModel;
import com.r2s.dto.OrderDto;
import com.r2s.dto.OrderOutDto;
import com.r2s.service.OrderService;
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ResponseBuild responseBuild;

	@GetMapping("/all") // admin
	public ResponseModel getAll(@RequestParam Integer page, @RequestParam Integer size) {
		ActionResult result = null;
		try {
			result = orderService.getAllOrders(page, size);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}

	@PostMapping("/") // user admin ?
	public ResponseModel create(@RequestBody OrderDto order) {
		ActionResult result = null;
		try {
			result = orderService.createOrder(order);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}

	@GetMapping("/{orderId}") // admin, user
	public ResponseModel getOrderById(@PathVariable Integer orderId) {
		ActionResult result = null;
		try {
			result = orderService.getOrderById(orderId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}

	@GetMapping("/by_account") //
	public ResponseModel getOrderByAccount(@Param(value = "page") Integer page,
			@Param(value = "size") Integer size) {
		ActionResult result = null;
		try {
			result = orderService.getOrderByAccount(page, size);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}	
	
	@PutMapping("/complete/{orderId}")
	public ResponseModel updateStatusCompleteOrder(@PathVariable Integer orderId) {
		ActionResult result = null;
		try {
			result = orderService.updateStatusCompleteOrder(orderId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}
	
	@PutMapping("/cancel/{orderId}")
	public ResponseModel updateStatusCancelOrder(@PathVariable Integer orderId) {
		ActionResult result = null;
		try {
			result = orderService.updateStatusCancelOrder(orderId);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}
}
