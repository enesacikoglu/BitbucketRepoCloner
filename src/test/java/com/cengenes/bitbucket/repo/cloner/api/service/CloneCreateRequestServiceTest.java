package com.cengenes.bitbucket.repo.cloner.api.service;

import com.cengenes.bitbucket.repo.cloner.api.base.BaseTest;
import com.cengenes.bitbucket.repo.cloner.api.builder.CloneRequestBuilder;
import com.cengenes.bitbucket.repo.cloner.api.model.request.CloneRequest;
import com.cengenes.bitbucket.repo.cloner.api.model.response.RepoCloneResponse;
import com.cengenes.bitbucket.repo.cloner.api.model.response.ResponseStatusType;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class CloneCreateRequestServiceTest extends BaseTest{

    @InjectMocks
    private CloneCreateRequestService cloneCreateRequestService;

    @Test
    public void it_should_clone_repos() {
        //given

        CloneRequest cloneRequest = CloneRequestBuilder.aCloneRequest()
                .bitbucketServerUrl("https://stash.domain.com")
                .localRepoDirectory("/Users/enes.acikoglu/Desktop/Projects/Trendyol")
                .userName("enes")
                .password("password")
                .projectCount(61)
                .projectKey("MAR")
                .build();

        //when
        RepoCloneResponse repoCloneResponse = cloneCreateRequestService.cloneRepos(cloneRequest);

        //then
        assertThat(repoCloneResponse).isNotNull();
        assertThat(repoCloneResponse.getStatus()).isEqualTo("failure");
    }
}