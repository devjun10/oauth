package com.example.oauth.config;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ClientRegistration {
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;
    private final String tokenUrl;
    private final String userInfoUrl;

    public ClientRegistration(OauthClientProperties.User user, OauthClientProperties.Provider provider) {
        this(user.getClientId(), user.getClientSecret(), user.getRedirectUri(), provider.getTokenUri(), provider.getUserInfoUri());
    }

    @Builder
    public ClientRegistration(String clientId, String clientSecret, String redirectUrl, String tokenUrl, String userInfoUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
        this.tokenUrl = tokenUrl;
        this.userInfoUrl = userInfoUrl;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "OauthProvider{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", tokenUrl='" + tokenUrl + '\'' +
                ", userInfoUrl='" + userInfoUrl + '\'' +
                '}';
    }
}
