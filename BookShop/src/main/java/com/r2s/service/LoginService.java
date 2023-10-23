package com.r2s.service;
//
import com.r2s.dto.LoginDto;
import com.r2s.dto.SignUpDto;
import com.r2s.model.ActionResult;


public interface LoginService {
   ActionResult login(LoginDto login);

    ActionResult signUp(SignUpDto signUp);
}
