package com.group.jupiter.dao;

import com.group.jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class LoginDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public LoginDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String verifyLogin(String userId, String password) {
        String firstName = "";

        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, userId);
            if (Objects.nonNull(user) && user.getPassword().equals(password)) {
                firstName = user.getFirstName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstName;
    }
}
