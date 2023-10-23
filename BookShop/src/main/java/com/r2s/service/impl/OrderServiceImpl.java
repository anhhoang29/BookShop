package com.r2s.service.impl;

import com.r2s.dto.AuthorDto;
import com.r2s.dto.AuthorOutDto;
import com.r2s.dto.BookDto;
import com.r2s.dto.CategoryDto;
import com.r2s.dto.OrderDetailDto;
import com.r2s.dto.OrderDto;
import com.r2s.dto.OrderOutDto;
import com.r2s.entity.Account;
import com.r2s.entity.Author;
import com.r2s.entity.Book;
import com.r2s.entity.Category;
import com.r2s.entity.Order;
import com.r2s.entity.OrderDetail;
import com.r2s.enums.ErrorCodeEnum;
import com.r2s.enums.OrderStatusEnum;
import com.r2s.model.ActionResult;
import com.r2s.model.AuthorModel;
import com.r2s.model.OrderModel;
import com.r2s.model.OrderDetailModel;
import com.r2s.repository.OrderRepository;
import com.r2s.repository.BookRepository;
import com.r2s.repository.AccountReponsitory;
import com.r2s.service.OrderService;
import com.r2s.utils.UserCurrentUtils;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional

public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AccountReponsitory accountRepository;
	@Autowired
	private BookRepository bookRepository;
	@Override
	public ActionResult getAllOrders(Integer page, Integer size) {
		ActionResult result = new ActionResult();
		Page<Order> ordersPage = orderRepository.findAll((PageRequest.of(page - 1, size)));
		if (ordersPage.isEmpty()) {
			result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
			return result;
		}

		List<OrderModel> orderModels = ordersPage.stream().map(OrderModel::transform).collect(Collectors.toList());

		if (orderModels.isEmpty()) {
			result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
			return result;
		}
		OrderOutDto OutDto = new OrderOutDto();

		OutDto.setOrders(orderModels);
		OutDto.setTotal(orderModels.size());
		result.setData(OutDto);
		return result;
	}
	@Override
	public ActionResult createOrder(OrderDto orderIn) {
		ActionResult result = new ActionResult();

		Account account = accountRepository.findByUsername(UserCurrentUtils.getCurrentUsernames());

		if (account == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_CREATE_USER);
			return result;
		}

		Order order = new Order();
		order.setAccount(account);
		order.setOrderCode(orderIn.getOrderCode());
		order.setPhone(orderIn.getPhone());
		order.setOrderDate(orderIn.getOrderDate());

		order.setShipPrice(orderIn.getShipPrice());
		order.setStatus(OrderStatusEnum.PENDING.getStatus());

		List<OrderDetailDto> dto = orderIn.getOrderDetails();

		BigDecimal orderDetailsPrice = new BigDecimal(0);

		List<OrderDetail> details = new ArrayList<>();
		for (OrderDetailDto orderDetailDto : dto) {
			OrderDetail orderDetail = new OrderDetail();
			Book book = bookRepository.findByBookId(orderDetailDto.getBookId().longValue());

			if (book == null ) {
				result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USER);
				return result;
			}

			if (book.getQuantityBook() < orderDetailDto.getQuantityOrder()) {
				result.setErrorCodeEnum(ErrorCodeEnum.NO_ENOUGH_BOOk);

				if (result.getErrorCodeEnum() != ErrorCodeEnum.OK) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
				return result;
			} else {
				book.setQuantityBook(book.getQuantityBook() - orderDetailDto.getQuantityOrder());
				bookRepository.save(book);
			}

			orderDetail.setOrder(order);
			orderDetail.setBook(book);
			orderDetail.setQuantityOrder(orderDetailDto.getQuantityOrder());

			BigDecimal a = BigDecimal.valueOf(orderDetailDto.getQuantityOrder());
			BigDecimal b = book.getPrice();
			BigDecimal m = b.multiply(a);
			orderDetail.setUnitPrice(m);

			orderDetailsPrice = orderDetailsPrice.add(m);

			details.add(orderDetail);

		}
		order.setOrderDetails(details);

		order.setDetailPrice(orderDetailsPrice);
		order.setTotalAmountPrice(orderDetailsPrice.add(orderIn.getShipPrice()));

		Order orderTemp = orderRepository.save(order);
		result.setData(OrderModel.transform(orderTemp));
		return result;
	}
	@Override
	public ActionResult getOrderById(Integer orderIn) {
		ActionResult result = new ActionResult();
		Order order = orderRepository.getOrderById(orderIn);
		if (order == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USER);
			return result;
		}

		result.setData(OrderModel.transform(order));
		return result;
	}
	@Override
	public ActionResult getOrderByAccount(Integer page, Integer size) {
		ActionResult result = new ActionResult();

		Account account = accountRepository.findByUsername(UserCurrentUtils.getCurrentUsernames());

		Page<Order> pageResult = orderRepository.getOrderByAccountId(account.getAccountId(), PageRequest.of(page - 1, size));

		if (pageResult.getNumberOfElements() == 0) {
			result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
			return result;
		}

		List<OrderModel> orderModels = pageResult.get().map(OrderModel::transform).collect(Collectors.toList());

		OrderOutDto outDto = new OrderOutDto();
		outDto.setOrders(orderModels);
		outDto.setTotal(pageResult.getNumberOfElements());

		result.setData(outDto);

		return result;
	}
	@Override
	public ActionResult updateStatusCompleteOrder(Integer orderIn) {
		ActionResult result = new ActionResult();
		Order order = orderRepository.getOrderById(orderIn);

		if (order == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USER);
			return result;
		}

		if (order.getStatus().equalsIgnoreCase(OrderStatusEnum.CANCEL.getStatus())) {
			result.setErrorCodeEnum(ErrorCodeEnum.CANCELED_ODER);
			return result;
		}

		order.setStatus(OrderStatusEnum.COMPLETE.getStatus());

		List<OrderDetail> details = order.getOrderDetails();
		result.setData(OrderModel.transform(order));
		return result;
	}
	@Override
	public ActionResult updateStatusCancelOrder(Integer orderIn) {
		ActionResult result = new ActionResult();
		Order order = orderRepository.getOrderById(orderIn);

		if (order == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USER);
			return result;
		}

		if (order.getStatus().equalsIgnoreCase(OrderStatusEnum.COMPLETE.getStatus())) {
			result.setErrorCodeEnum(ErrorCodeEnum.CANCELED_ODER);
			return result;
		}

		order.setStatus(OrderStatusEnum.CANCEL.getStatus());

		List<OrderDetail> details = order.getOrderDetails();

		result.setData(OrderModel.transform(order));
		return result;
	}
	
    
}
