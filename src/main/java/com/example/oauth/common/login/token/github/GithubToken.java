package com.example.oauth.common.login.token.github;

import com.example.oauth.common.login.token.WebToken;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GithubToken implements WebToken {

    public static final String GITHUB = "github";
    private static final String TOKEN_DELIMETER = " ";

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("scope")
    private String scope;

    public String getAccessToken() {
        return tokenType + TOKEN_DELIMETER + accessToken;
    }
}

