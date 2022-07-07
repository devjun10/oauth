package com.example.oauth.common.login.service;

import com.example.oauth.business.core.member.domain.Member;
import com.example.oauth.business.core.member.repository.MemberRepository;
import com.example.oauth.common.login.token.OauthClient;
import com.example.oauth.common.login.token.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Optional;

import static com.example.oauth.common.login.token.jwt.JwtTokenProvider.FIFTEEN_MINUTES;
import static com.example.oauth.common.login.token.jwt.JwtTokenProvider.TWO_WEEKS;


@Service
@RequiredArgsConstructor
public class GithubLoginService implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(GithubLoginService.class);
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    @Override
    public Token save(OauthClient oauthClient) {
        Optional<Member> findMember = memberRepository.findByGithubId(oauthClient.getClientId());
        if (findMember.isEmpty()) {
            Member member = new Member(oauthClient.getClientId(), oauthClient.getProfileImage());
            memberRepository.save(member);
        }
        return createToken(oauthClient);
    }

    @Override
    public Token createToken(OauthClient oauthClient) {
        String accessToken = jwtTokenProvider.createToken(oauthClient.getClientId(), FIFTEEN_MINUTES);
        String refreshToken = jwtTokenProvider.createToken(oauthClient.getClientId(), TWO_WEEKS);
        saveToken(oauthClient.getClientId(), refreshToken);
        return new Token(accessToken, refreshToken);
    }

    @Override
    public void saveToken(String key, String value) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, value, Duration.ofDays(14));
        logger.info("토큰 저장:{}", value);
    }
}

