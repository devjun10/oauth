package com.example.oauth.common.login.token;

import com.example.oauth.common.login.token.configuration.ClientRegistration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface WebTokenProvider extends TokenProvider {
    Map<String, String> getUserDetail(String body);

    Map<String, String> getUserDetailFrom(ClientRegistration clientRegistration, WebToken gitWebToken);

    WebToken createWebToken(ClientRegistration clientRegistration, String code);
}


