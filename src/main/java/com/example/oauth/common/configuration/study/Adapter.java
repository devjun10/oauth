package com.example.oauth.common.configuration.study;

import java.util.HashMap;
import java.util.Map;

public class Adapter {
    public static Map<String, ItemRegistration> getItem(ItemProperties provider) {
        Map<String, ItemRegistration> items = new HashMap<>();
        provider.getItemProvider().forEach((key, value) ->
                items.put(key, ItemRegistration.bind(value)));
        return items;
    }
}
