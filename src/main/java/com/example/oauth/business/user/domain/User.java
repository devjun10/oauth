package com.example.oauth.business.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Objects;

public class User {

    @Id
    private Long userId;
    private String githubName;
    private String githubUsername;
    private String githubAvatarUrl;
    private String email;
    private String location;
    private String githubId;
    private Bio bio;

    private String googleId;


    @PersistenceConstructor
    public User(String githubName, String githubUsername, String githubAvatarUrl, String email, String location, Bio bio, String githubId) {
        this.userId = userId;
        this.githubName = githubName;
        this.githubUsername = githubUsername;
        this.githubAvatarUrl = githubAvatarUrl;
        this.email = email;
        this.location = location;
        this.bio = bio;
        this.githubId = githubId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getGithubName() {
        return githubName;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public String getGithubAvatarUrl() {
        return githubAvatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public Bio getBio() {
        return bio;
    }

    public String getGithubId() {
        return githubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public void publishUpdateEvent(String name, String avatarUrl, String email, String location, String githubId, Bio bio) {
    }
}
