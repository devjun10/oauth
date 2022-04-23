package com.example.oauth.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String name;
    private String email;
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String username, String name, String avatarUrl, Role role) {
        this.username = username;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.role = role;
    }

    @Builder
    public Member(String username, String name, String email, String avatarUrl, Role role) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.role = role;
    }

    public Member update(Member member) {
        this.username = member.getUsername();
        this.name = member.getName();
        this.avatarUrl = member.getAvatarUrl();
        return this;
    }

    public Member update(String username, String name, String picture) {
        this.username = username;
        this.name = name;
        this.avatarUrl = picture;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public String getRole2() {
        return role.getKey();
    }
}
