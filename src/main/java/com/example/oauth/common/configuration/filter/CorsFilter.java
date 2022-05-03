package com.example.oauth.common.configuration.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.*;

@Component
@RequiredArgsConstructor
public class CorsFilter implements Filter {

    private static final String LOCALHOST_3000 = "http://localhost:3000";
    private static final String TRUE = "true";
    private static final String ALL_PATH = "*";
    private static final String MAX_AGE = "3600";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS_TYPE = "Origin, X-Requested-With, Content-Type, Accept, Authorization";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, LOCALHOST_3000);
        response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, TRUE);
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALL_PATH);
        response.setHeader(ACCESS_CONTROL_MAX_AGE, MAX_AGE);
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS_TYPE);

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }
}
