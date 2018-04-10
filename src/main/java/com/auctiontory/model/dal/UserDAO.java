package com.auctiontory.model.dal;

import com.auctiontory.model.entity.User;

public interface UserDAO extends IDaoBase<User> {

    User login(String userName, String password);

    boolean isUSer(String userName);

    boolean emailUsed(String email);

}
