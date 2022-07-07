package com.example.oauth.common.login.token.github;

import com.example.oauth.common.configuration.login.ClientRegistration;
import com.example.oauth.common.login.service.GithubUser;
import com.example.oauth.common.login.token.OauthClient;
import com.example.oauth.common.login.token.WebToken;
import com.example.oauth.common.login.token.WebTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class GithubTokenProvider extends WebTokenProvider {

    @Override
    public WebToken createToken(String code, ClientRegistration clientRegistration) {
        HttpEntity<?> accessTokenRequest = createRequestBody(code, clientRegistration);
        return new RestTemplate()
                .postForEntity(clientRegistration.getTokenUrl(), accessTokenRequest, GithubToken.class)
                .getBody();
    }

    @Override
    public HttpEntity<?> createRequestBody(String code, ClientRegistration clientRegistration) {
        Assert.notNull(clientRegistration, "Registration must be not null.");
        Assert.notNull(code, "Code must be not null.");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set(ACCEPT, APPLICATION_JSON_VALUE);

        MultiValueMap<String, String> payLoad = new LinkedMultiValueMap<>();
        payLoad.set(CLIENT_ID, clientRegistration.getClientId());
        payLoad.set(CLIENT_SECRET, clientRegistration.getClientSecret());
        payLoad.set(CODE, code);
        payLoad.set(REDIRECT_URL, clientRegistration.getRedirectUri());

        return new HttpEntity<>(payLoad, headers);
    }

    @Override
    public OauthClient createOauthClient(String accessToken, ClientRegistration clientRegistration) {
        Assert.notNull(clientRegistration, "Registration must be not null.");
        Assert.notNull(accessToken, "Token must be not null.");

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add(ACCESS_TOKEN, accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, accessToken);

        return new RestTemplate().exchange(
                clientRegistration.getUserInfoUrl(), GET, new HttpEntity<String>(null, headers),
                GithubUser.class).getBody();
    }
}
