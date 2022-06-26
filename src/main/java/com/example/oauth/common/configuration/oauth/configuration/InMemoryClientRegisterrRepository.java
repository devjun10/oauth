package com.example.oauth.common.configuration.oauth.configuration;

import java.util.Map;

public class InMemoryClientRegisterrRepository {
    private Map<String, ClientRegistration> clientRegistration;

    public InMemoryClientRegisterrRepository(Map<String, ClientRegistration> clientRegistration) {
        this.clientRegistration = clientRegistration;
    }

    public ClientRegistration findByRegistration(String name) {
        return clientRegistration.get(name);
    }

    public InMemoryClientRegisterrRepository() {
    }
}
