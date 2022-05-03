package com.example.oauth.common.login.service;

import com.example.oauth.business.user.domain.User;
import com.example.oauth.business.user.repository.UserRepository;
import com.example.oauth.common.login.OauthService;
import com.example.oauth.common.login.token.WebToken;
import com.example.oauth.common.login.token.WebTokenProvider;
import com.example.oauth.common.login.token.configuration.ClientRegistration;
import com.example.oauth.common.login.token.configuration.InMemoryClientRegisterRepository;
import com.example.oauth.common.login.token.github.GithubClient;
import com.example.oauth.common.login.token.google.GoogleOauthUser;
import com.example.oauth.common.login.token.google.GoogleWebTokenProvider;
import com.example.oauth.common.login.token.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static com.example.oauth.common.login.token.configuration.OauthProvider.getOauthProvider;


@Service
public class GoogleOauthService implements OauthService {

    private final Logger logger = LoggerFactory.getLogger(GoogleOauthService.class);
    private final InMemoryClientRegisterRepository inMemoryClientRegisterRepository;
    private final UserRepository userRepository;
    private final FakeRedisRepository fakeRedisRepository;
    private final WebTokenProvider webTokenProvider;
    private final JwtTokenProvider jwtTokenProvider;

    public GoogleOauthService(InMemoryClientRegisterRepository inMemoryClientRegisterRepository, UserRepository userRepository, FakeRedisRepository fakeRedisRepository, GoogleWebTokenProvider githubWebTokenUtils, JwtTokenProvider jwtTokenProvider) {
        this.inMemoryClientRegisterRepository = inMemoryClientRegisterRepository;
        this.userRepository = userRepository;
        this.fakeRedisRepository = fakeRedisRepository;
        this.webTokenProvider = githubWebTokenUtils;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public String login(String provider, String code) {
        ClientRegistration clientRegistration = inMemoryClientRegisterRepository.findByRegistration(getOauthProvider(provider));
        WebToken webToken = webTokenProvider.createWebToken(clientRegistration, code);

        Map<String, String> userDetail = webTokenProvider.getUserDetailFrom(clientRegistration, webToken);
        GoogleOauthUser googleOauthUser = GoogleOauthUser.from(userDetail);
//        String jwtToken = jwtTokenProvider.createJwtToken(githubUser.getGithubId());

//        save(githubUser, jwtToken);
//        logger.info("토큰 발급: {}", jwtToken);
        return null;
    }

    private void save(GithubClient githubUser, String jwtToken) {
        logger.info("사용자 및 토큰 저장 {}, {}", githubUser.getGithubId(), jwtToken);
        Optional<User> findUser = userRepository.findByGithubId(githubUser.getGithubId());
        if (findUser.isPresent()) {
            findUser.get().publishUpdateEvent(githubUser.getName(), githubUser.getAvatarUrl(), githubUser.getEmail(),
                    githubUser.getLocation(), githubUser.getGithubId(), githubUser.getBio());
            return;
        }
        userRepository.save(new User(githubUser.getName(), null, githubUser.getAvatarUrl(), githubUser.getEmail(), githubUser.getLocation(), githubUser.getBio(), githubUser.getGithubId()));
        fakeRedisRepository.save(jwtToken);
    }
}
