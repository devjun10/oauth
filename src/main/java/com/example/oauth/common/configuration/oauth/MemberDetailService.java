package com.example.oauth.common.configuration.oauth;

import com.example.oauth.business.core.member.domain.Member;
import com.example.oauth.business.core.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class MemberDetailService {

    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByGithubId(String githubId) {
        return memberRepository.findByGithubId(githubId);
    }

    @Transactional(readOnly = true)
    public String getRefreshToken(String refreshToken) {
        return getValue(refreshToken);
    }

    @Transactional
    public String findRefreshTokenByGithubId(String githubId) {
        return getValue(githubId);
    }

    private String getValue(String value) {
        return redisTemplate.opsForValue().get(value);
    }
}
