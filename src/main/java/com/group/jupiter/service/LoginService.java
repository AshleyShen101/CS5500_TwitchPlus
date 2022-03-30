package com.group.jupiter.service;

import com.group.jupiter.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginDAO loginDAO;

    @Autowired
    public LoginService(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public String verifyLogin(String userId, String password) {
        return loginDAO.verifyLogin(userId, password);
    }
}
