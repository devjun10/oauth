package com.example.oauth.common.configuration.study;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "items")
public class ItemProperties {

    private final Map<String, ItemProvider> itemProvider = new HashMap<>();

    public Map<String, ItemProvider> getItemProvider() {
        return itemProvider;
    }

    public ItemProperties() {
    }

    @Getter
    static class ItemProvider {
        private String name;
        private List<String> imageUrls;

        public ItemProvider(String name, List<String> imageUrls) {
            this.name = name;
            this.imageUrls = imageUrls;
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", imageUrls=" + imageUrls +
                    '}';
        }
    }
}
