package com.example.oauth.config;

import java.util.Arrays;
import java.util.Map;

public enum OauthAttributes {
    GITHUB("github") {
        @Override
        public UserInformation of(Map<String, Object> attributes) {
            return UserInformation.builder()
                    .username(String.valueOf(attributes.get("id")))
                    .email((String) attributes.get("email"))
                    .name((String) attributes.get("name"))
                    .picture((String) attributes.get("avatar_url"))
                    .build();
        }
    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    public static UserInformation extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> providerName.equals(provider.providerName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of(attributes);
    }

    public abstract UserInformation of(Map<String, Object> attributes);
}
