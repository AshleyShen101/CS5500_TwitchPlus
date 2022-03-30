package com.group.jupiter.service;

import com.group.jupiter.dao.RegisterDAO;
import com.group.jupiter.entity.db.User;
import com.group.jupiter.util.Util;
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
        String userId = user.getUserId();
        String password = user.getPassword();
        user.setPassword(Util.encryptPassword(userId, password));
        return registerDAO.register(user);
    }
}
