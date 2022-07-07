package com.example.oauth.common.configuration.oauth;

import com.example.oauth.business.core.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenValidator tokenValidator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerInterceptor)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);

        // Auth 어노테이션이 없다면 따로 권한체크를 하지 않는다.
        if (auth == null) {
            return true;
        }

        String jwtToken = (String) request.getAttribute("accessToken");
        validate(jwtToken);
        // Redis를 타지 않는 로직
        boolean validToken = false;

        // Redis를 타는 로직
        if (validToken) {
//            String githubId = tokenValidator.validateToken(jwtToken);
            String githubId = "";
            if (githubId != null) {
                request.setAttribute("githubId", githubId);
                return true;
            }
            return true;
        }
        return false;
    }

    private void validate(String jwtToken) {

    }
}
