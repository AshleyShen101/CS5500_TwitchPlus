package com.group.jupiter.entity.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginRequestBodyTest {

    @Test
    public void getUserId() {
        LoginRequestBody loginRequestBody = new LoginRequestBody("1234","1113183d7dc42beec92b6a393230db4b");
        assertEquals("1234", loginRequestBody.getUserId());
    }

    @Test
    public void getPassword() {
        LoginRequestBody loginRequestBody = new LoginRequestBody("1234","1113183d7dc42beec92b6a393230db4b");
        assertEquals("1113183d7dc42beec92b6a393230db4b",loginRequestBody.getPassword());
    }
}