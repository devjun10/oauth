package com.example.oauth.common.configuration.study;

import java.util.Map;

public class InMemoryItemRepository {
    private final Map<String, ItemRegistration> items;

    public InMemoryItemRepository(Map<String, ItemRegistration> items) {
        this.items = items;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "InMemoryItemRepository{" +
                "items=" + items +
                '}';
    }
}
