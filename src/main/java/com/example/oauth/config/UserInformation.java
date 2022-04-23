package com.example.oauth.config;

import com.example.oauth.domain.member.Member;
import com.example.oauth.domain.member.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInformation {
    private final String username;
    private final String email;
    private final String name;
    private final String picture;

    @Builder
    public UserInformation(String username, String email, String name, String picture) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.picture = picture;
    }

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .name(name)
                .avatarUrl(picture)
                .role(Role.USER)
                .build();
    }
}
