package com.group.jupiter.dao;

import com.group.jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.Objects;

@Repository
public class RegisterDAO {

    private final SessionFactory sessionFactory;

    public RegisterDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean register(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (PersistenceException | IllegalStateException e) {
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
            return false;
        } finally {
            if (Objects.nonNull(session)) {
                session.close();
            }
        }
        return true;
    }
}
