package com.cengenes.bitbucket.repo.cloner.api.controller;

import com.cengenes.bitbucket.repo.cloner.api.model.request.CloneRequest;
import com.cengenes.bitbucket.repo.cloner.api.model.response.RepoCloneResponse;
import com.cengenes.bitbucket.repo.cloner.api.service.CloneCreateRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/repo")
public class CloneRepoController {

    private final CloneCreateRequestService cloneCreateRequestService;

    @Autowired
    public CloneRepoController(final CloneCreateRequestService cloneCreateRequestService) {
        this.cloneCreateRequestService = cloneCreateRequestService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clone")
    public RepoCloneResponse clone(@Valid @RequestBody final CloneRequest cloneRequest) {
        return cloneCreateRequestService.cloneRepos(cloneRequest);
    }
}
