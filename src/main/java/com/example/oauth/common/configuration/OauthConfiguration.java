package com.example.oauth.common.configuration;

import com.example.oauth.common.login.token.configuration.ClientRegistration;
import com.example.oauth.common.login.token.configuration.InMemoryClientRegisterRepository;
import com.example.oauth.common.login.token.configuration.OauthClientProperties;
import com.example.oauth.common.login.token.configuration.OauthClientPropertiesRegistrationAdapter;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Configuration
@EnableConfigurationProperties(OauthClientProperties.class)
public class OauthConfiguration {

    private final OauthClientProperties oauthClientProperties;

    public OauthConfiguration(OauthClientProperties oauthClientProperties) {
        this.oauthClientProperties = oauthClientProperties;
    }

    @Bean
    public InMemoryClientRegisterRepository inMemoryClientRegisterRepository() {
        Map<String, ClientRegistration> providers = OauthClientPropertiesRegistrationAdapter.getOauthProviders(oauthClientProperties);
        return new InMemoryClientRegisterRepository(providers);
    }
}


