package com.example.oauth.common.login.controller;

import com.example.oauth.common.login.controller.dto.LoginResponse;
import com.example.oauth.common.login.service.GithubOauthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class OauthController {

    private GithubOauthService githubOauthService;

    public OauthController(GithubOauthService githubOauthService) {
        this.githubOauthService = githubOauthService;
    }

    @GetMapping("/api/login/oauth/{provider}")
    public ResponseEntity<LoginResponse> login(HttpServletRequest request, HttpServletResponse response, @PathVariable("provider") String provider, @RequestParam String code) throws IOException {
        String authorization = request.getHeader(AUTHORIZATION);
        if (authorization != null) {
            response.sendRedirect("");
        }
        return new ResponseEntity<>(new LoginResponse(githubOauthService.login(provider, code)), HttpStatus.OK);
    }
}
