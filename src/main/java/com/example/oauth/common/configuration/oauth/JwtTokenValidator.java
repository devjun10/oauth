package com.example.oauth.common.configuration.oauth;

import com.example.oauth.business.core.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidator {

    public String validateTime(String jwtToken) {
        return null;
    }

//    public String validateToken(String jwtToken) {
//        validatePar(jwtToken);
//        return null;
//    }
//
//    public void it() {
//        String githubId = "";
//        Member findMember = authenticationManager.findMemberByGithubId(githubId).orElseThrow();
//    }
//
//    public void validatePar(String jwtToken) {
//        if (jwtToken.isBlank()) {
//            throw new IllegalArgumentException();
//        }
//        String parsedToken = jwtToken.split(" ")[0];
//
//        if (!parsedToken.startsWith(BEARER)) {
//            throw new IllegalArgumentException();
//        }
//    }
//
//    public String get(String githubId) {
//        String redis = authenticationManager.findRefreshTokenByGithubId(githubId);
//
//        return null;
//    }
}
