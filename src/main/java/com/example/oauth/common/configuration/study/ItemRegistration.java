package com.example.oauth.common.configuration.study;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemRegistration {
    private String name;
    private List<String> imageUrls;

    public static ItemRegistration bind(ItemProperties.ItemProvider itemProvider) {
        return new ItemRegistration(itemProvider);
    }

    public ItemRegistration(ItemProperties.ItemProvider itemProvider) {
        this.name = itemProvider.getName();
        this.imageUrls = itemProvider.getImageUrls();
    }

}
