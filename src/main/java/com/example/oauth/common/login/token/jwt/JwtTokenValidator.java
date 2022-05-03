package com.example.oauth.common.login.token.jwt;


import com.example.oauth.common.login.token.WebTokenValidator;

public class JwtTokenValidator implements WebTokenValidator {

    private static final String PREFIX = "Bearer";

    @Override
    public String validate(String authorizationHeader) {
        return null;
    }

    private void validateTokenValidation(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(PREFIX)) {
            throw new IllegalArgumentException("Invalid Token");
        }
    }

    private void validateTokenDuration(String authorizationHeader) {

    }
}
