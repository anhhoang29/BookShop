package com.r2s.repository;

import com.r2s.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountReponsitory extends JpaRepository<Account, Integer> {

        Optional<Account> findByUserName(String userName);


}
