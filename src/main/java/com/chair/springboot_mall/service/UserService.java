package com.chair.springboot_mall.service;

import com.chair.springboot_mall.dto.UserRegisterRequest;
import com.chair.springboot_mall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}
