package com.example.oauth.common.login.controller;

import com.example.oauth.common.login.controller.dto.LoginResponse;
import com.example.oauth.common.login.service.OauthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    private OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/api/login/oauth/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable("provider") String provider, @RequestParam String code) {
        return new ResponseEntity<>(new LoginResponse(oauthService.login(provider, code)), HttpStatus.OK);
    }
}
