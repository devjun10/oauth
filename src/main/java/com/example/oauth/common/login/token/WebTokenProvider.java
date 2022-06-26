package com.example.oauth.common.login.token;

import com.example.oauth.common.configuration.oauth.configuration.ClientRegistration;
import org.springframework.http.HttpEntity;

public abstract class WebTokenProvider implements TokenProvider {

    protected String CLIENT_ID = "client_id";
    protected String CODE = "code";
    protected String REDIRECT_URL = "redirect_url";
    protected String ACCESS_TOKEN = "access_token";
    protected String CLIENT_SECRET = "client_secret";

    public abstract WebToken createToken(String code, ClientRegistration clientRegistration);

    public abstract HttpEntity<?> createRequestBody(String code, ClientRegistration clientRegistration);

    public abstract OauthClient createOauthClient(String accessToken, ClientRegistration clientRegistration);
}
