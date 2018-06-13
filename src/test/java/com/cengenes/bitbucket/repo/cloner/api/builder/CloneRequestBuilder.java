package com.cengenes.bitbucket.repo.cloner.api.builder;

import com.cengenes.bitbucket.repo.cloner.api.model.request.CloneRequest;

public final class CloneRequestBuilder {
    private String bitbucketServerUrl;
    private String projectKey;
    private String userName;
    private String password;
    private Integer projectCount;
    private String localRepoDirectory;

    private CloneRequestBuilder() {
    }

    public static CloneRequestBuilder aCloneRequest() {
        return new CloneRequestBuilder();
    }

    public CloneRequestBuilder bitbucketServerUrl(String bitbucketServerUrl) {
        this.bitbucketServerUrl = bitbucketServerUrl;
        return this;
    }

    public CloneRequestBuilder projectKey(String projectKey) {
        this.projectKey = projectKey;
        return this;
    }

    public CloneRequestBuilder userName(String userName) {
        this.userName = userName;
        return this;
    }

    public CloneRequestBuilder password(String password) {
        this.password = password;
        return this;
    }

    public CloneRequestBuilder projectCount(Integer projectCount) {
        this.projectCount = projectCount;
        return this;
    }

    public CloneRequestBuilder localRepoDirectory(String localRepoDirectory) {
        this.localRepoDirectory = localRepoDirectory;
        return this;
    }

    public CloneRequest build() {
        CloneRequest cloneRequest = new CloneRequest();
        cloneRequest.setBitbucketServerUrl(bitbucketServerUrl);
        cloneRequest.setProjectKey(projectKey);
        cloneRequest.setUserName(userName);
        cloneRequest.setPassword(password);
        cloneRequest.setProjectCount(projectCount);
        cloneRequest.setLocalRepoDirectory(localRepoDirectory);
        return cloneRequest;
    }
}
