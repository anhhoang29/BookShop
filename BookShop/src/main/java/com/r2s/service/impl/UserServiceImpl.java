package com.r2s.service.impl;

import com.r2s.dto.UserDto;
import com.r2s.entity.Account;
import com.r2s.entity.User;
import com.r2s.model.ActionResult;
import com.r2s.model.UserModel;
import com.r2s.repository.UserRepository;
import com.r2s.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public ActionResult getAll() {
        ActionResult result = new ActionResult();
        List<User> users = userRepository.findAll();
        result.setData(
                users.stream()
                        .map(UserModel::transform)
                        .collect(Collectors.toList())
        );
        return result;
    }

    @Override
    public ActionResult save(User user) {
        return null;
    }

    @Override
    public ActionResult getById(Long id) {
        ActionResult result = new ActionResult();
        try {
            User user = userRepository.findById(id);
            result.setData(UserModel.transform(user));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public ActionResult updateUser(Long id, UserDto userDto) {
        ActionResult result = new ActionResult();
        try {
            User user = userRepository.findById(id);
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            user.setPhoneNumber(userDto.getPhoneNumber());

            if (user.getAccount() != null) {
                user.getAccount().setUserName(userDto.getUsername());
                user.getAccount().setPassword(userDto.getPassword());
            }

            userRepository.save(user);
            result.setData(UserModel.transform(user));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public ActionResult createUser(UserDto userDto) {
        ActionResult result = new ActionResult();
        try {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            user.setPhoneNumber(userDto.getPhoneNumber());

            Account account = new Account();
            account.setUserName(userDto.getUsername());
            account.setPassword(userDto.getPassword());
            account.setUser(user);
            user.setAccount(account);

            userRepository.save(user);
            result.setData(UserModel.transform(user));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public ActionResult deleteUser(Long id) {
        ActionResult result = new ActionResult();
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}