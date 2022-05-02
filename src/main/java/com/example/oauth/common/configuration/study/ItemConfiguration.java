package com.example.oauth.common.configuration.study;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(ItemProperties.class)
public class ItemConfiguration {

    private final ItemProperties itemProperties;

    public ItemConfiguration(ItemProperties itemProperties) {
        this.itemProperties = itemProperties;
    }

    public ItemProperties getItemPropertiesConfiguration() {
        return itemProperties;
    }

    @Bean
    public InMemoryItemRepository inMemoryItemRepository() {
        Map<String, ItemRegistration> providers = Adapter.getItem(itemProperties);
        return new InMemoryItemRepository(providers);
    }
}
