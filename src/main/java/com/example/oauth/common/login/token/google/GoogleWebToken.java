package com.example.oauth.common.login.token.google;

import com.example.oauth.common.login.token.WebToken;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleWebToken implements WebToken {

    public static final String GOOGLE = "google";

    private static final String TOKEN_DELIMETER = " ";

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    private long expiresIn;

    @JsonProperty("scope")
    private String scope;

    @Override
    public String getAccessToken() {
        return tokenType + TOKEN_DELIMETER + accessToken;
    }

    @Override
    public String toString() {
        return "GoogleToken{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                '}';
    }
}
