package com.example.oauth.common.login.service;


import com.example.oauth.common.login.token.OauthClient;

public interface LoginService {

    Token save(OauthClient oauthClient);

    Token createToken(OauthClient githubUser);

    void saveToken(String key, String value);
}
