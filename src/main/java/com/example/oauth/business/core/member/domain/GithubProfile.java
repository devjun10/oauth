package com.example.oauth.business.core.member.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class GithubProfile {

    private static final int MAX_PROFILE_IMAGE_LENGTH = 2048;

    private String githubId;
    private String avatarUrl;

    public GithubProfile(String githubId, String avatarUrl) {
        validateGithubProfile(githubId, avatarUrl);
        this.githubId = githubId;
        this.avatarUrl = avatarUrl;
    }

    protected GithubProfile() {
    }

    private void validateGithubProfile(String githubId, String avatarUrl) {
        if (githubId.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (avatarUrl.length() > MAX_PROFILE_IMAGE_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    public String getGithubId() {
        return githubId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GithubProfile)) return false;
        GithubProfile that = (GithubProfile) o;
        return getGithubId().equals(that.getGithubId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGithubId());
    }
}
