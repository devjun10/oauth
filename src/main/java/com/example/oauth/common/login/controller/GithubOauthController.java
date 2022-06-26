package com.example.oauth.common.login.controller;

import com.example.oauth.common.configuration.oauth.configuration.ClientRegistration;
import com.example.oauth.common.configuration.oauth.configuration.InMemoryClientRegisterrRepository;
import com.example.oauth.common.login.controller.dto.LoginResponse;
import com.example.oauth.common.login.service.LoginService;
import com.example.oauth.common.login.service.Token;
import com.example.oauth.common.login.token.OauthClient;
import com.example.oauth.common.login.token.WebToken;
import com.example.oauth.common.login.token.WebTokenProvider;
import com.example.oauth.common.login.token.github.GithubToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class GithubOauthController implements OauthController {

    private final LoginService loginService;
    private final InMemoryClientRegisterrRepository inMemoryClientRegisterRepository;
    private final WebTokenProvider webTokenProvider;

    @Override
    @GetMapping("/callback")
    public ResponseEntity<LoginResponse> login(@RequestParam String code) {
        ClientRegistration clientRegistration = inMemoryClientRegisterRepository.findByRegistration(GithubToken.GITHUB);
        WebToken webToken = webTokenProvider.createToken(code, clientRegistration);
        OauthClient oauthClient = webTokenProvider.createOauthClient(webToken.getAccessToken(), clientRegistration);

        Token token = loginService.save(oauthClient);
        return ResponseEntity
                .ok(new LoginResponse(token.getAccessToken()));
    }
}
