package com.group.jupiter.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseBody {

    @JsonProperty("user_id")
    private final String userId;

    @JsonProperty("first_name")
    private final String firstName;

    public LoginResponseBody(String userId, String firstName) {
        this.userId = userId;
        this.firstName = firstName;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }
}
