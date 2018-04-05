package com.auctiontory.model.dal.impl;

import com.auctiontory.model.dal.UserDAO;
import com.auctiontory.model.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("userDao")
public class UserDaoImpl implements UserDAO, Serializable {

    @PersistenceContext(unitName = "AuctionsPU")
    private EntityManager em;

    public User login(String userName, String password) {
        User user = null;
        List<User> users = em.createNamedQuery("User.findByUserNameAndPassword")
                .setParameter("userName", userName)
                .setParameter("password", password)
                .getResultList();
        if (users != null && users.size() > 0) {
            user = users.get(0);
        }
        return user;
    }

    public boolean isUSer(String userName) {
        boolean isUser = false;

        List<User> users = em.createNamedQuery("User.findByUserName")
                .setParameter("userName", userName)
                .getResultList();
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            if (user != null) {
                isUser = true;
            } else {
                isUser = false;
            }
        } else {
            isUser = false;
        }

        return isUser;
    }

    public List<User> loadAll() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    public void save(User domain) {
        em.persist(domain);
    }

    public void update(User domain) {
        em.merge(domain);
    }

    public void delete(User domain) {
        User user = em.find(User.class, domain.getId());
        em.remove(user);
    }


    public User get(Serializable id) {
        return em.find(User.class, id);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
