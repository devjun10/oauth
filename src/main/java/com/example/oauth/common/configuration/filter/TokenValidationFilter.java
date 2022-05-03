package com.example.oauth.common.configuration.filter;

import com.example.oauth.common.exception.token.TokenTypeException;
import com.example.oauth.common.login.token.TokenValidator;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenValidationFilter implements Filter {

    //    private static final String issuer = "sidedish";
//    private static final long tokenValidTime = 6 * 60 * 60 * 1000L;
//    private static String serverSecretKey;
    private final TokenValidator tokenValidator;

    public static void initServerSecretKey() {
//        serverSecretKey = Base64.getEncoder().encodeToString(SecretUtil.serverSecret().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (validateToken(authorizationHeader)) {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean validateToken(String jwtToken) {
        try {
            tokenValidator.validate(jwtToken);
        } catch (ExpiredJwtException e) {
            throw new InvalidJwtTokenException(TokenTypeException.EXPIRED_JWT_EXCEPTION);
        } catch (UnsupportedJwtException e) {
            throw new InvalidJwtTokenException(TokenTypeException.UNSUPPORTED_JWT_EXCEPTION);
        } catch (MalformedJwtException e) {
            throw new InvalidJwtTokenException(TokenTypeException.MALFORMED_JWT_EXCEPTION);
        } catch (SecurityException e) {
            throw new InvalidJwtTokenException(TokenTypeException.SIGNATURE_EXCEPTION);
        } catch (IllegalArgumentException e) {
            throw new InvalidJwtTokenException(TokenTypeException.ILLEGAL_ARGUMENT_EXCEPTION);
        }
    }

}
