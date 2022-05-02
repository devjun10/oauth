package com.example.oauth.common.login.token;

import java.util.Map;

public interface WebTokenParser {
    Map<String, String> getUserInformation(String userInformationString);

}
