package com.example.oauth.business.user.repository;

import com.example.oauth.business.user.domain.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByGithubId(String githubId);

    @Query("SELECT * FROM MEMBER WHERE MEMBER_ID = : memberId")
    void equalTo(@Param(("memberId")) String memberId);
}
