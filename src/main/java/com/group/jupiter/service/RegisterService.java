package com.group.jupiter.service;

import com.group.jupiter.dao.RegisterDAO;
import com.group.jupiter.entity.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final RegisterDAO registerDAO;

    @Autowired
    public RegisterService(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }

    public boolean register(User user) {
        return registerDAO.register(user);
    }
}
