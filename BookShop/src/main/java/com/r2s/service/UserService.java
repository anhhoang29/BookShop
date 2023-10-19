package com.r2s.service;

import com.r2s.dto.UserDto;
import com.r2s.entity.User;
import com.r2s.model.ActionResult;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ActionResult getAll();

    ActionResult save(User user);

    ActionResult getById(Long id);

    ActionResult updateUser(Long id, UserDto user);

    ActionResult createUser(UserDto user);

    ActionResult deleteUser(Long id);

}
