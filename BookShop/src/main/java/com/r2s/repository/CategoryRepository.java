package com.r2s.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.r2s.entity.Category;
import jakarta.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query("SELECT a FROM Category a WHERE a.id = :categoryId")
    Category getCategoryById(@Param("categoryId") Integer accountId);
	
	@Modifying
	@Transactional
	@Query("delete from Category a where a.id in(:categoryId)")
	void deleteByIdIn(List<Integer> categoryId);
}

