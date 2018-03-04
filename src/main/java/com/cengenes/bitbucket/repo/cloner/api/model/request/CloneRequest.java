package com.cengenes.bitbucket.repo.cloner.api.model.request;

public class CloneRequest {

    private String bitbucketServerUrl;

    private String projectKey;

    private String userName;

    private String password;

    private String projectCount;

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

    public String getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(String projectCount) {
        this.projectCount = projectCount;
    }

    public String getLocalRepoDirectory() {
        return localRepoDirectory;
    }

    public void setLocalRepoDirectory(String localRepoDirectory) {
        this.localRepoDirectory = localRepoDirectory;
    }
}
