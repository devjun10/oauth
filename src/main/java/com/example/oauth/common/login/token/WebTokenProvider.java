package com.example.oauth.common.login.token;

import com.example.oauth.common.login.token.configuration.ClientRegistration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Component
public interface WebTokenProvider extends TokenProvider {
    HttpEntity<?> getAccessTokenRequest(ClientRegistration registration, String code);

    MultiValueMap<String, String> getHeader();

    MultiValueMap<String, String> getPayLoad(ClientRegistration registration, String code);

    HttpHeaders getAuthorizationIncludedHeader(String accessToken);

    Map<String, String> getUserDetail(String body);

    Map<String, String> getUserDetailFrom(ClientRegistration clientRegistration, WebToken gitWebToken);

    WebToken getWebToken(ClientRegistration clientRegistration, String code);
}
