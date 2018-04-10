package com.auctiontory.controller;

import com.auctiontory.model.entity.User;

public interface UserController extends IControllerBase<User> {
    boolean isUser(String userName);

    User login(String userName, String password);

    boolean emailUsed(String email);
}
