package com.r2s.repository;

import com.r2s.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User where account.userName =:username ")
    User findByUsername(@Param("username") String username);
}