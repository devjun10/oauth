package com.example.oauth.common.login.token;

import java.util.Map;

public interface TokenParser {
    Map<String, String> getUserInformation(String userInformationString);

}
