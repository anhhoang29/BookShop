package com.r2s.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.r2s.entity.Author;

import jakarta.transaction.Transactional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	@Query("SELECT a FROM Author a WHERE a.id = :authorId")
	Author getAuthorById(@Param("AuthorId") Integer authorId);
	
	@Modifying
	@Transactional
	@Query("delete from Author a where a.id in(:authorId)")
	void deleteByIdIn(List<Integer> authorId);
}
