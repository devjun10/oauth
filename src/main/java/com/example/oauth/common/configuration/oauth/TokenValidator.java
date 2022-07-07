package com.example.oauth.common.configuration.oauth;

import com.example.oauth.business.core.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    private static final String BEARER = "bearer";

    private final JwtTokenValidator jwtTokenValidator;
    private final AuthenticationManager authenticationManager;

}
