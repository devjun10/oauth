package com.example.oauth.common.login.controller.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
