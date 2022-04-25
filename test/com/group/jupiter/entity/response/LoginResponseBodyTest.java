package com.group.jupiter.entity.response;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginResponseBodyTest {

    @Test
    public void getUserId() {
        LoginResponseBody loginResponseBody = new LoginResponseBody("1234","alan");
        assertEquals("1234",loginResponseBody.getUserId());
    }

    @Test
    public void getFirstName() {
        LoginResponseBody loginResponseBody = new LoginResponseBody("1234","alan");
        assertEquals("alan",loginResponseBody.getFirstName());
    }
}