package com.example.oauth.common.login.service;

import com.example.oauth.common.login.token.OauthClient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubUser implements OauthClient {

    @JsonProperty("login")
    private String githubId;

    @JsonProperty("avatar_url")
    private String profileImage;

    public GithubUser() {
    }

    @Override
    public String getClientId() {
        return githubId;
    }

    @Override
    public String getProfileImage() {
        return profileImage;
    }
}
