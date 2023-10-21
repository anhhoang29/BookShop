package com.r2s.controller;

import com.r2s.model.ActionResult;
import com.r2s.model.ResponseBuild;
import com.r2s.model.ResponseModel;
import com.r2s.service.AccountService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "account")

@RequestMapping("api/v1/accounts")

public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResponseBuild responseBuild;

    @GetMapping("/all")
    public ResponseModel getPaging(@RequestParam Integer page, @RequestParam Integer size) {
        ActionResult result = accountService.getAccounts(page, size);
        return responseBuild.build(result);
    }
}