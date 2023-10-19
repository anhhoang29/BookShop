package com.r2s.service.impl;

import com.r2s.entity.Account;
import com.r2s.entity.Role;
import com.r2s.entity.User;
import com.r2s.model.CustomUserDetailImpl;
import com.r2s.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//loadUserByUsername là hàm mặc định của spring security
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Account account = user.getAccount();
        return new CustomUserDetailImpl(account.getUserName(), account.getPassword(), account.getRoles());
    }


}