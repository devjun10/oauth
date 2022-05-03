package com.example.oauth.common.login.token;

public interface TokenValidator {
    String validate(String jwtToken);
}
