package com.cengenes.bitbucket.repo.cloner.api.model.response;

public enum ResponseStatusType {

    SUCCESS("success"),
    FAILURE("failure");

    private final String value;

    private ResponseStatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}