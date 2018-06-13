package com.cengenes.bitbucket.repo.cloner.api.model.response;

public final class  RepoCloneResponse {


    private String status;

    public RepoCloneResponse() {
    }

    public RepoCloneResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
