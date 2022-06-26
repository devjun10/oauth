package com.example.oauth.business.core.member.repository;


import com.example.oauth.business.core.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.githubProfile.githubId =?1")
    Optional<Member> findByGithubId(String githubId);
}
