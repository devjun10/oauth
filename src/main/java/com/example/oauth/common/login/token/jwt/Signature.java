package com.example.oauth.common.login.token.jwt;

public class Signature {

    private static final String TOKEN_DELIMETER = ".";
    private String signature;

    public Signature(Header header, PayLoad payLoad) {

    }

    @Override
    public String toString() {
        return "Signature{" +
                "signature='" + signature + '\'' +
                '}';
    }
}
