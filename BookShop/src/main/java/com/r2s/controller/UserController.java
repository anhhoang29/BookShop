package com.r2s.controller;

import com.r2s.dto.UserDto;
import com.r2s.model.ActionResult;
import com.r2s.model.ResponseBuild;
import com.r2s.model.ResponseModel;
import com.r2s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseBuild responseBuild;

    @GetMapping("/")
    public ResponseModel getAllUsers() {
        ActionResult result = userService.getAll();
        return responseBuild.build(result);
    }

    @GetMapping("/{id}")
    public ResponseModel getUserById(@PathVariable Long id) {
        ActionResult result = userService.getById(id);
        return responseBuild.build(result);
    }

    @PostMapping("/")
    public ResponseModel createUser(@RequestBody UserDto userDto) {
        ActionResult result = userService.createUser(userDto);
        return responseBuild.build(result);
    }

    @PutMapping("/{id}")
    public ResponseModel updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        ActionResult result = userService.updateUser(id, userDto);
        return responseBuild.build(result);
    }

    @DeleteMapping("/{id}")
    public ResponseModel deleteUser(@PathVariable Long id) {
        ActionResult result = userService.deleteUser(id);
        return responseBuild.build(result);
    }
}