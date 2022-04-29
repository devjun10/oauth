package com.example.oauth.common.configuration;

import com.example.oauth.common.login.token.configuration.ClientRegistration;
import com.example.oauth.common.login.token.configuration.InMemoryClientRegisterrRepository;
import com.example.oauth.common.login.token.configuration.OauthClientProperties;
import com.example.oauth.common.login.token.configuration.OauthClientPropertiesRegistrationAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OauthClientProperties.class)
public class OauthConfiguration {

    private final OauthClientProperties oauthClientProperties;

    public OauthConfiguration(OauthClientProperties oauthClientProperties) {
        this.oauthClientProperties = oauthClientProperties;
    }

    @Bean
    public InMemoryClientRegisterrRepository inMemoryProviderRepository() {
        Map<String, ClientRegistration> providers = OauthClientPropertiesRegistrationAdapter.getOauthProviders(oauthClientProperties);
        return new InMemoryClientRegisterrRepository(providers);
    }

    public OauthClientProperties getOauthClientProperties() {
        return oauthClientProperties;
    }
}
