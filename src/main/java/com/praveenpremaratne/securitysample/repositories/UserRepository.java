package com.praveenpremaratne.securitysample.repositories;

import com.praveenpremaratne.securitysample.entities.User;
import com.praveenpremaratne.securitysample.interfaces.UserInterface;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepository implements UserInterface{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u", User.class);
        try {
            return query.getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        if (username.isEmpty()) return null;
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:userName", User.class);
        query.setParameter("userName", username);
        try {
            return (User) query.getSingleResult();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
