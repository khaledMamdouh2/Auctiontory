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

    private boolean registered = false;
    private boolean loggedIn = false;
    private boolean incorrectLogin = false;


    private User user = new User();

    public String login() {
        String ret = null;
        user = null;
        user = userController.login(userName, password);
        if (user != null) {
            loggedIn = true;
            ret = "index";
        } else {
            loggedIn = false;
            incorrectLogin = true;
        }
        return ret;
    }

    public String logout() {
        user = new User();
        loggedIn = false;
        incorrectLogin = false;
        return "index";
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
        registered = true;
        return null;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isIncorrectLogin() {
        return incorrectLogin;
    }

    public void setIncorrectLogin(boolean incorrectLogin) {
        this.incorrectLogin = incorrectLogin;
    }

}
