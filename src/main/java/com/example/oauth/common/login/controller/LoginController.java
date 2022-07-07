package com.example.oauth.common.login.controller;

import com.example.oauth.common.configuration.login.InMemoryClientRegisterrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.oauth.common.login.token.github.GithubToken.GITHUB;


@RestController
@RequiredArgsConstructor
public class LoginController {

    private final InMemoryClientRegisterrRepository inMemoryClientRegisterrRepository;

    @GetMapping("/login")
    public ResponseEntity<String> loginHome() {
        return ResponseEntity.ok(inMemoryClientRegisterrRepository.findByRegistration(GITHUB).getLoginPage());
    }
}
