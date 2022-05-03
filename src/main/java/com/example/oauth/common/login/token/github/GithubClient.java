package com.example.oauth.common.login.token.github;


import com.example.oauth.business.user.domain.Bio;
import com.example.oauth.business.user.domain.User;
import com.example.oauth.common.login.token.OauthClient;
import lombok.Getter;

import java.util.Map;

@Getter
public class GithubClient implements OauthClient<User> {

    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String AVATAR_URL = "avatar_url";
    private static final String BIO = "bio";
    private static final String LOCATION = "location";
    private static final String HTML_URL = "html_url";
    private static final String GITHUB_ID = "githubId";
    private static final String USERDETAIL_DELIMETER = "/";
    private static final char KEY_DELIMETER = '"';

    private Long id;
    private final String email;
    private final String name;
    private final String avatarUrl;
    private final Bio bio;
    private final String location;
    private final String githubId;

    public GithubClient(Long id, String email, String name, String avatarUrl, Bio bio, String location, String githubId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
        this.location = location;
        this.githubId = githubId;
    }

    public static GithubClient from(Map<String, String> userDetail) {
        Long id = Long.parseLong(getAttribute(userDetail, ID));
        String email = getAttribute(userDetail, EMAIL);
        String name = getAttribute(userDetail, NAME).replaceAll("'\"'", "");
        Bio bio = Bio.valueOf(getAttribute(userDetail, BIO));
        String avatarUrl = getAttribute(userDetail, AVATAR_URL);
        String location = getAttribute(userDetail, LOCATION);
        String githubId = getGithubId(userDetail);
        return new GithubClient(id, email, name, avatarUrl, bio, location, githubId);
    }

    private static String getAttribute(Map<String, String> userDetail, String attribute) {
        if (attribute.equals(BIO)) {
            if (userDetail.get(BIO) == null) {
                return Bio.NOT_REGISTERED.name();
            }
        }
        if (attribute.equals(HTML_URL)) {
            return getGithubId(userDetail);
        }
        return userDetail.get(attribute);
    }

    private static String getGithubId(Map<String, String> userDetail) {
        String[] parsing = userDetail.get(HTML_URL).split(USERDETAIL_DELIMETER);
        String id = parsing[parsing.length - 1];
        userDetail.put(GITHUB_ID, id);
        return parsing[parsing.length - 1];
    }


    public static User toEntity(Map<String, String> userDetail) {
        return null;
    }
}
