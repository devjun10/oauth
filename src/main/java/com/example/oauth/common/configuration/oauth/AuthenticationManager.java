package com.example.oauth.common.configuration.oauth;

import com.example.oauth.business.core.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationManager {

    private final AuthenticatorProvider authenticatorProvider;

    public Optional<Member> findMemberByGithubId(String githubId) {
        return authenticatorProvider.findMemberByGithubId(githubId);
    }

    public String findRefreshTokenByGithubId(String githubId) {
        return authenticatorProvider.findRefreshTokenByGithubId(githubId);
    }
}
