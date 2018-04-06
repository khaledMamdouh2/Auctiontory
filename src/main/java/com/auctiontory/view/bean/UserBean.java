package com.auctiontory.view.bean;

import com.auctiontory.controller.UserController;
import com.auctiontory.model.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    @Inject
    private UserController userController;

    private String userName, password;

    private User user = new User();

    public String login() {
        user = null;
        user = userController.login(userName, password);
        if (user != null)
            return "index";
        else
            return null;
    }

    public User getUser() {
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String register() {
        userController.save(user);
        user = null;
        return "login";
    }
}
