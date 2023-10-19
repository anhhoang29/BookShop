//package com.r2s.controller;
//
//import com.r2s.dto.LoginDto;
//import com.r2s.dto.SignUpDto;
//import com.r2s.model.ActionResult;
//import com.r2s.model.ResponseBuild;
//import com.r2s.model.ResponseModel;
//import com.r2s.service.LoginService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1")
//@RequiredArgsConstructor
//@Tag(name = "login")
//public class LoginController {
//
//    @Autowired
//    private LoginService loginService;
//
//    @Autowired
//    private ResponseBuild responseBuild;
//
//    @PostMapping("/login")
//    public ResponseModel login(@RequestBody LoginDto login) {
//        ActionResult result =  loginService.login(login);
//        return responseBuild.build(result);
//    }
//
//    @PostMapping("/sign-up")
//    public ResponseModel signUp(@RequestBody SignUpDto signIn) {
//        ActionResult result = loginService.signUp(signIn);
//        return responseBuild.build(result);
//    }
//}
