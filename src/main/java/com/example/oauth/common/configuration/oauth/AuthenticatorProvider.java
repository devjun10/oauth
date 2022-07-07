package com.example.oauth.common.configuration.oauth;

import com.example.oauth.business.core.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticatorProvider {

    private final MemberDetailService memberDetailService;

    public Optional<Member> findMemberByGithubId(String githubId) {
        return memberDetailService.findMemberByGithubId(githubId);
    }

    public String findRefreshTokenByGithubId(String githubId) {
        return memberDetailService.findRefreshTokenByGithubId(githubId);
    }
}
