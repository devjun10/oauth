package com.example.oauth.business.user.repository;

import com.example.oauth.business.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByGithubId(String githubId);
}
