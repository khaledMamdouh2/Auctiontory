package com.auctiontory.controller.impl;

import com.auctiontory.controller.IControllerBase;
import com.auctiontory.controller.UserController;
import com.auctiontory.model.dal.UserDAO;
import com.auctiontory.model.entity.User;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Stateful
public class UserControllerImpl implements UserController {
    @Inject
    UserDAO userDao;

    public boolean isUser(String userName) {
        return userDao.isUSer(userName);
    }

    public User login(String userName, String password) {
        return userDao.login(userName, password);
    }

    public List<User> loadAll() {
        return userDao.loadAll();
    }

    public void save(User domain) {
        userDao.save(domain);
    }

    public void update(User domain) {
        userDao.update(domain);
    }

    public void delete(User domain) {
        userDao.delete(domain);
    }

    public User get(Serializable id) {
        return userDao.get(id);
    }
}