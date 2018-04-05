package com.auctiontory.view;

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
    UserControllerImpl userEjbBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User("ABC", "12345", "m@YYY.com", UserType.INDIVIDUAL);
        userEjbBean.save(user);
        try {
            userEjbBean.login(user.getUserName(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User anotherRef = userEjbBean.get(user.getId());
        anotherRef.setEmail("aaaa@mail.com");
        userEjbBean.update(anotherRef);
        userEjbBean.delete(anotherRef);
        List<User> users = userEjbBean.loadAll();
    }
}
