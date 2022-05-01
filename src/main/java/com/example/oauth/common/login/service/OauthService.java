package com.example.oauth.common.login.service;

import com.example.oauth.business.user.domain.User;
import com.example.oauth.business.user.repository.UserRepository;
import com.example.oauth.common.login.token.OauthUser;
import com.example.oauth.common.login.token.WebToken;
import com.example.oauth.common.login.token.WebTokenUtils;
import com.example.oauth.common.login.token.configuration.ClientRegistration;
import com.example.oauth.common.login.token.configuration.InMemoryClientRegisterrRepository;
import com.example.oauth.common.login.token.github.GithubUser;
import com.example.oauth.common.login.token.google.GoogleWebTokenUtils;
import com.example.oauth.common.login.token.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static com.example.oauth.common.login.token.configuration.OauthProvider.getOauthProvider;


@Service
@RequiredArgsConstructor
public class OauthService {

    private final Logger logger = LoggerFactory.getLogger(OauthService.class);
    private final InMemoryClientRegisterrRepository inMemoryClientRegisterRepository;
    private final UserRepository userRepository;
    private final FakeRedisRepository fakeRedisRepository;
    private final WebTokenUtils webTokenUtils;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String login(String provider, String code) {
        ClientRegistration clientRegistration = inMemoryClientRegisterRepository.findByRegistration(getOauthProvider(provider));
        WebTokenUtils webTokenUtils = new GoogleWebTokenUtils();
        HttpEntity<?> accessTokenRequest = webTokenUtils.getAccessTokenRequest(clientRegistration, code);
        WebToken webToken = webTokenUtils.getWebToken(clientRegistration, code, accessTokenRequest);

        Map<String, String> userDetail = webTokenUtils.getUserDetailFrom(clientRegistration, webToken);
        GithubUser githubUser = GithubUser.from(userDetail);
        OauthUser oauthUser = OauthUser.from(userDetail);
        String jwtToken = jwtTokenProvider.createJwtToken(githubUser.getGithubId());

        save(githubUser, jwtToken);
        logger.info("토큰 발급: {}", jwtToken);
        return null;
    }

    // TODO 분기문 제거
    private void save(GithubUser githubUser, String jwtToken) {
        logger.info("사용자 및 토큰 저장 {}, {}", githubUser.getGithubId(), jwtToken);
        Optional<User> findUser = userRepository.findByGithubId(githubUser.getGithubId());
        if (findUser.isPresent()) {
            findUser.get().publishUpdateEvent(githubUser.getName(), githubUser.getAvatarUrl(), githubUser.getEmail(),
                    githubUser.getLocation(), githubUser.getGithubId(), githubUser.getBio());
        } else {
            userRepository.save(new User(githubUser.getName(), null, githubUser.getAvatarUrl(), githubUser.getEmail(), githubUser.getLocation(), githubUser.getBio(), githubUser.getGithubId()));
        }
        fakeRedisRepository.save(jwtToken);
    }
}
