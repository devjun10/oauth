package com.example.oauth.common.login.token.configuration;

import org.springframework.util.Assert;

import java.util.Map;

public class InMemoryClientRegisterRepository {

    private Map<String, ClientRegistration> clientRegistration;

    public InMemoryClientRegisterRepository(Map<String, ClientRegistration> clientRegistration) {
        this.clientRegistration = clientRegistration;
    }

    public ClientRegistration findByRegistration(String name) {
        Assert.notNull(name, "name must not be null.");
        return clientRegistration.get(name);
    }

    public InMemoryClientRegisterRepository() {
    }
}



