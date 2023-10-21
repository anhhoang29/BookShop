package com.r2s.controller;

import com.r2s.dto.UserDto;
import com.r2s.model.ActionResult;
import com.r2s.model.ResponseBuild;
import com.r2s.model.ResponseModel;
import com.r2s.service.UserService;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "user")

@RequestMapping("api/v1/users")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseBuild responseBuild;


    @GetMapping("/users")

    @GetMapping("/")

    public ResponseModel getAllUsers() {
        ActionResult result = userService.getAll();
        return responseBuild.build(result);
    }


    @GetMapping("/users/{id}")

    @GetMapping("/{id}")

    public ResponseModel getUserById(@PathVariable Long id) {
        ActionResult result = userService.getById(id);
        return responseBuild.build(result);
    }


    @PostMapping("/users")

    @PostMapping("/")

    public ResponseModel createUser(@RequestBody UserDto userDto) {
        ActionResult result = userService.createUser(userDto);
        return responseBuild.build(result);
    }


    @PutMapping("/users/{id}")

    @PutMapping("/{id}")

    public ResponseModel updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        ActionResult result = userService.updateUser(id, userDto);
        return responseBuild.build(result);
    }


    @DeleteMapping("/users/{id}")

    @DeleteMapping("/{id}")

    public ResponseModel deleteUser(@PathVariable Long id) {
        ActionResult result = userService.deleteUser(id);
        return responseBuild.build(result);
    }
}