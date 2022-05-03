package com.example.oauth.common.login.repository;

import com.example.oauth.common.login.token.d.LogoutAccessToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LogoutAccessTokenRedisRepository extends CrudRepository<LogoutAccessToken, String> {
    Optional<LogoutAccessToken> findByUsername(String username);
}
