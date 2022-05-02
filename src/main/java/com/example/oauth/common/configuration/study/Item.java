package com.example.oauth.common.configuration.study;

import com.example.oauth.common.login.token.configuration.ClientRegistration;
import com.example.oauth.common.login.token.configuration.OauthClientProperties;
import lombok.Getter;

import java.util.List;

@Getter
public class Item {
    private String name;
    private List<String> imageUrls;

    public Item(String name, List<String> imageUrls) {
        this.name = name;
        this.imageUrls = imageUrls;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", imageUrls=" + imageUrls +
                '}';
    }
}
