package com.example.oauth.common.configuration.jpa.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Deleted {

    private boolean deleted;

    public Deleted() {
        this.deleted = init();
    }

    private boolean init() {
        return false;
    }

    public void isTrue() {
        this.deleted = true;
    }

}
