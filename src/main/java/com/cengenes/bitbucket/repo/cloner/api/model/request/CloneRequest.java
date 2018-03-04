package com.cengenes.bitbucket.repo.cloner.api.model.request;

import javax.validation.constraints.NotNull;

public class CloneRequest {

    @NotNull
    private String bitbucketServerUrl;

    @NotNull
    private String projectKey;

    @NotNull
    private String userName;

    @NotNull
    private String password;


    private Integer projectCount;

    @NotNull
    private String localRepoDirectory;


    public String getBitbucketServerUrl() {
        return bitbucketServerUrl;
    }

    public void setBitbucketServerUrl(String bitbucketServerUrl) {
        this.bitbucketServerUrl = bitbucketServerUrl;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public String getLocalRepoDirectory() {
        return localRepoDirectory;
    }

    public void setLocalRepoDirectory(String localRepoDirectory) {
        this.localRepoDirectory = localRepoDirectory;
    }
}
