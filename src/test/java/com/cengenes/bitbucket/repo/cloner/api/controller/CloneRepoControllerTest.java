package com.cengenes.bitbucket.repo.cloner.api.controller;

import com.cengenes.bitbucket.repo.cloner.api.base.BaseTest;
import com.cengenes.bitbucket.repo.cloner.api.builder.CloneRequestBuilder;
import com.cengenes.bitbucket.repo.cloner.api.model.request.CloneRequest;
import com.cengenes.bitbucket.repo.cloner.api.model.response.RepoCloneResponse;
import com.cengenes.bitbucket.repo.cloner.api.model.response.ResponseStatusType;
import com.cengenes.bitbucket.repo.cloner.api.service.CloneCreateRequestService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class CloneRepoControllerTest extends BaseTest {

    @InjectMocks
    private CloneRepoController cloneRepoController;

    @Mock
    private CloneCreateRequestService cloneCreateRequestService;

    @Test
    public void it_should_clone_repos_successfully() {
        //given
        CloneRequest cloneRequest = CloneRequestBuilder.aCloneRequest()
                .bitbucketServerUrl("https://stash.domain.com")
                .localRepoDirectory("/Users/enes.acikoglu/Desktop/Projects/Trendyol")
                .userName("enes")
                .password("password")
                .projectCount(61)
                .projectKey("MAR")
                .build();

        RepoCloneResponse cloneResponse = new RepoCloneResponse(ResponseStatusType.SUCCESS.getValue());
        when(cloneCreateRequestService.cloneRepos(cloneRequest)).thenReturn(cloneResponse);

        //when
        RepoCloneResponse expectedResponse = cloneRepoController.clone(cloneRequest);

        //then
        assertThat(expectedResponse).isNotNull();
        assertThat(expectedResponse.getStatus()).isEqualTo("success");
    }

    @Test
    public void it_should_clone_repos_failed() {
        //given
        CloneRequest cloneRequest = CloneRequestBuilder.aCloneRequest()
                .bitbucketServerUrl("https://stash.domain.com")
                .localRepoDirectory("/Users/enes.acikoglu/Desktop/Projects/Trendyol")
                .userName("enes")
                .password("password")
                .projectCount(61)
                .projectKey("MAR")
                .build();

        RepoCloneResponse cloneResponse = new RepoCloneResponse(ResponseStatusType.FAILURE.getValue());
        when(cloneCreateRequestService.cloneRepos(cloneRequest)).thenReturn(cloneResponse);

        //when
        RepoCloneResponse expectedResponse = cloneRepoController.clone(cloneRequest);

        //then
        assertThat(expectedResponse).isNotNull();
        assertThat(expectedResponse.getStatus()).isEqualTo("failure");
    }

}