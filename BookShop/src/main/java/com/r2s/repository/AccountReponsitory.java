package com.r2s.repository;

import com.r2s.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountReponsitory extends JpaRepository<Account, Integer> {

        //Optional<Account> findByUserName(String userName);
        
        @Query("SELECT a FROM Account a WHERE a.userName = :username and a.status = true")
    	Account findByUsername(@Param("username") String username);


}
