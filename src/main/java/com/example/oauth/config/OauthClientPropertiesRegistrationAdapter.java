package com.example.oauth.config;


import java.util.HashMap;
import java.util.Map;

public final class OauthClientPropertiesRegistrationAdapter {

    public static Map<String, ClientRegistration> getOauthProviders(OauthClientProperties properties) {
        Map<String, ClientRegistration> oauthProvider = new HashMap<>();

        properties.getUser().forEach((key, value) -> oauthProvider.put(key,
                new ClientRegistration(value, properties.getProvider().get(key))));
        return oauthProvider;
    }
}
