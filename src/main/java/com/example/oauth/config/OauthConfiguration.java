package com.example.oauth.config;

import com.example.oauth.repository.inmemory.InMemoryClientRegisterrRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OauthClientProperties.class)
public class OauthConfiguration {

    private final OauthClientProperties properties;

    public OauthConfiguration(OauthClientProperties properties) {
        this.properties = properties;
        System.out.println(properties);
    }

    // 추가된 부분
    @Bean
    public InMemoryClientRegisterrRepository inMemoryProviderRepository() {
        Map<String, ClientRegistration> providers = OauthClientPropertiesRegistrationAdapter.getOauthProviders(properties);
        return new InMemoryClientRegisterrRepository(providers);
    }
}
