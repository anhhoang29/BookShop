package com.r2s.service.impl;

import com.r2s.entity.User;
import com.r2s.model.ActionResult;
import com.r2s.model.UserModel;
import com.r2s.repository.UserRepository;
import com.r2s.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userReponsitory;

    @Override
    @Transactional
    public ActionResult getAll() {
        ActionResult result = new ActionResult();
        List<User> users = userReponsitory.findAll();
        result.setData(
                users.stream()
                        .map(UserModel::transform)
                        .collect(Collectors.toList())
        );
        return result;
    }

    @Override
    public ActionResult save(User user) {
        ActionResult result = new ActionResult();
        try {
            userReponsitory.save(user);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
