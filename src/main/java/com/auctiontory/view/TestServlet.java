package com.auctiontory.view;

import com.auctiontory.controller.UserController;
import com.auctiontory.controller.impl.UserControllerImpl;
import com.auctiontory.model.entity.User;
import com.auctiontory.model.entity.UserType;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TestServlet", urlPatterns = "/Test")
public class TestServlet extends HttpServlet {

    @Inject
    UserController userControllerImpl;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User("ABC", "12345", "m@YYY.com", UserType.INDIVIDUAL);
        userControllerImpl.save(user);
        try {
            userControllerImpl.login(user.getUserName(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User anotherRef = userControllerImpl.get(user.getId());
        anotherRef.setEmail("aaaa@mail.com");
        userControllerImpl.update(anotherRef);
        userControllerImpl.delete(anotherRef);
        List<User> users = userControllerImpl.loadAll();
    }
}
