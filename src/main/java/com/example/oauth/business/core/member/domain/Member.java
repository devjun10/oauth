package com.example.oauth.business.core.member.domain;

import com.example.oauth.common.configuration.jpa.entity.Deleted;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private GithubProfile githubProfile;

    @Embedded
    private Deleted deleted;

    public Member(String githubId, String profileImageUrl) {
        registGithubProfile(githubId, profileImageUrl);
        this.deleted = initDelete();
    }

    protected Member() {
    }

    private void registGithubProfile(String githubId, String profileImageUrl) {
        this.githubProfile = new GithubProfile(githubId, profileImageUrl);
    }

    private Deleted initDelete() {
        return new Deleted();
    }

    public String getGithubId(){
        return this.githubProfile.getGithubId();
    }
}
