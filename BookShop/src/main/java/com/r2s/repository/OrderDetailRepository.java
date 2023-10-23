package com.r2s.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{

}
