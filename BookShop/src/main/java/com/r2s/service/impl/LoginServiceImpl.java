package com.r2s.service.impl;

import com.r2s.dto.LoginDto;
import com.r2s.dto.SignUpDto;
import com.r2s.entity.Account;
import com.r2s.entity.User;
import com.r2s.model.ActionResult;
import com.r2s.repository.AccountReponsitory;
import com.r2s.service.LoginService;
import com.r2s.service.UserService;
import com.r2s.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountReponsitory accountReponsitory;

    @Override
    public ActionResult login(LoginDto login) {
        ActionResult result = new ActionResult();
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
        String token = JwtUtils.generateToken(userDetails.getUsername());

        result.setData(token);
        return result;
    }

    @Override
    public ActionResult signUp(SignUpDto signUp) {
        ActionResult result = new ActionResult();
        User user = new User();
        user.setFirstName(signUp.getFirstName());
        user.setLastName(signUp.getLastName());
        user.setEmail(signUp.getEmail());

        Account account = new Account();
        account.setUserName(signUp.getUsername());
        account.setPassword(passwordEncoder.encode(signUp.getPassword()));
        account.setUser(user);
        account.setStatus(true);
        account.setCreatedDate(new Date(System.currentTimeMillis()));
//        account.setUpdatedDate(new Date());
        user.setAccount(account);
        userService.save(user);


        accountReponsitory.save(account);
        return result;
    }
}
