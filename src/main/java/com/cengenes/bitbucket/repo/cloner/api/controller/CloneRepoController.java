package com.cengenes.bitbucket.repo.cloner.api.controller;

import com.cengenes.bitbucket.repo.cloner.api.model.request.CloneRequest;
import com.cengenes.bitbucket.repo.cloner.api.model.response.RepoCloneResponse;
import com.cengenes.bitbucket.repo.cloner.api.model.response.ResponseStatusType;
import com.cengenes.bitbucket.repo.cloner.api.service.CloneCreateRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/repo")
public class CloneRepoController {


    private final CloneCreateRequestService cloneCreateRequest;

    @Autowired
    public CloneRepoController(CloneCreateRequestService cloneCreateRequest) {
        this.cloneCreateRequest = cloneCreateRequest;
    }

    @RequestMapping(value = "/clone", method = RequestMethod.POST)
    public ResponseEntity<RepoCloneResponse> send(@RequestBody final CloneRequest cloneRequest) {

       final RepoCloneResponse repoCloneResponse = cloneCreateRequest.cloneRepos(cloneRequest);

        return new ResponseEntity<>(repoCloneResponse, HttpStatus.CREATED);

    }





}
