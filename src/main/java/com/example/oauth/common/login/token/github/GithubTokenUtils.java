package com.example.oauth.common.login.token.github;

import com.example.oauth.common.login.token.TokenUtils;
import com.example.oauth.common.login.token.configuration.ClientRegistration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 *
 */
@Component
public class GithubTokenUtils implements TokenUtils {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String CODE = "code";

    private GithubTokenParser githubTokenParser;

    private GithubTokenUtils(GithubTokenParser githubTokenParser) {
        this.githubTokenParser = githubTokenParser;
    }

    @Override
    public HttpEntity<?> getAccessTokenRequest(ClientRegistration clientRegistration, String code) {
        Assert.notNull(clientRegistration, "Registration must be not null.");
        Assert.notNull(code, "Code must be not null.");
        MultiValueMap<String, String> headers = getHeader();
        MultiValueMap<String, String> payLoad = getPayLoad(clientRegistration, code);
        return new HttpEntity<>(payLoad, headers);
    }

    @Override
    public MultiValueMap<String, String> getHeader() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set(ACCEPT, APPLICATION_JSON_VALUE);
        return headers;
    }

    @Override
    public MultiValueMap<String, String> getPayLoad(ClientRegistration clientRegistration, String code) {
        MultiValueMap<String, String> payLoad = new LinkedMultiValueMap<>();
        payLoad.set(CLIENT_ID, clientRegistration.getClientId());
        payLoad.set(CLIENT_SECRET, clientRegistration.getClientSecret());
        payLoad.set(CODE, code);
        return payLoad;
    }

    @Override
    public HttpHeaders getAuthorizationIncludedHeader(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, accessToken);
        return headers;
    }

    @Override
    public Map<String, String> getUserDetailFrom(ClientRegistration clientRegistration, GithubToken gitToken) {
        Assert.notNull(clientRegistration, "Registration must be not null.");
        Assert.notNull(gitToken, "Token must be not null.");
        HttpHeaders authorizationIncludedHeader = getAuthorizationIncludedHeader(gitToken.getGithubAccessToken());
        ResponseEntity<String> response = new RestTemplate()
                .exchange(clientRegistration.getUserInfoUrl(), HttpMethod.GET,
                        new HttpEntity<String>(authorizationIncludedHeader), String.class);
        return getUserDetail(response.getBody());
    }

    @Override
    public Map<String, String> getUserDetail(String userInformationCode) {
        return githubTokenParser.getUserInformation(userInformationCode);
    }

}
