package com.example.oauth.business.core.member.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {

    private static final int MAX_NAME_LENGTH = 39;
    private String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    protected Name() {
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
