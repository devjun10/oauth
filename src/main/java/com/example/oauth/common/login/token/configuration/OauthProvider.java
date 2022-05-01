package com.example.oauth.common.login.token.configuration;

import com.example.oauth.common.exception.BusinessException;
import com.example.oauth.common.exception.common.OauthTypeException;

import java.util.Arrays;
import java.util.function.Predicate;

public enum OauthProvider {

    NAVER("naver"),
    KAKAO("kakao"),
    GITHUB("github"),
    GOOGLE("google");

    private final String oauthProvider;

    OauthProvider(String oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public static String getOauthProvider(String oauthType) {
        return Arrays.stream(values())
                .map(OauthProvider::getOauthProvider)
                .filter(equalsTo(oauthType))
                .findAny()
                .orElseThrow(() -> new BusinessException(OauthTypeException.INVALID_OAUTH_PROVIDER_EXCEPTION));
    }

    private static Predicate<String> equalsTo(String oauthType) {
        return provider -> provider.equals(oauthType);
    }

    public String getOauthProvider() {
        return oauthProvider;
    }
}
