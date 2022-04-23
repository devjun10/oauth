package com.example.oauth.repository.inmemory;


import com.example.oauth.config.ClientRegistration;
import com.example.oauth.repository.ClientRegistrationRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryClientRegisterrRepository implements ClientRegistrationRepository {

    private Map<String, ClientRegistration> providers;

    public InMemoryClientRegisterrRepository(Map<String, ClientRegistration> providers) {
        this.providers = new HashMap<>(providers);
    }

    public ClientRegistration findByProviderName(String name) {
        return providers.get(name);
    }

    public InMemoryClientRegisterrRepository() {
    }
}
