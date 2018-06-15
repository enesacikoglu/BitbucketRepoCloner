package com.cengenes.bitbucket.repo.cloner.api.controller;

import com.cengenes.bitbucket.repo.cloner.api.base.BaseIT;
import com.cengenes.bitbucket.repo.cloner.api.builder.CloneRequestBuilder;
import com.cengenes.bitbucket.repo.cloner.api.helper.JsonHelper;
import com.cengenes.bitbucket.repo.cloner.api.model.request.CloneRequest;
import com.cengenes.bitbucket.repo.cloner.api.model.response.RepoCloneResponse;
import com.cengenes.bitbucket.repo.cloner.api.model.response.ResponseStatusType;
import com.cengenes.bitbucket.repo.cloner.api.service.CloneCreateRequestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CloneRepoControllerIT extends BaseIT {

    @MockBean
    private CloneCreateRequestService cloneCreateRequestService;

    @Autowired
    private JsonHelper jsonHelper;


    @Test
    public void it_should_clone_successfully() throws Exception {
        //given
        CloneRequest cloneRequest = CloneRequestBuilder.aCloneRequest()
                .bitbucketServerUrl("https://stash.domain.com")
                .localRepoDirectory("/Users/enes.acikoglu/Desktop/Projects/Trendyol")
                .userName("enes")
                .password("password")
                .projectCount(61)
                .projectKey("MAR")
                .build();

        RepoCloneResponse repoCloneResponse = new RepoCloneResponse();
        repoCloneResponse.setStatus(ResponseStatusType.SUCCESS.getValue());

        given(cloneCreateRequestService.cloneRepos(any(CloneRequest.class))).willReturn(repoCloneResponse);

        //when and Then
        mockMvc.perform(post("/api/repo/clone")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonHelper.serializeToJson(cloneRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    public void it_should_clone_failed_due_to_missing_request_params() throws Exception {
        //given
        CloneRequest cloneRequest = CloneRequestBuilder.aCloneRequest()
                .bitbucketServerUrl("https://stash.domain.com")
                .build();

        //when and Then
        mockMvc.perform(post("/api/repo/clone")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonHelper.serializeToJson(cloneRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void it_should_clone_failed() throws Exception {
        //given
        CloneRequest cloneRequest = CloneRequestBuilder.aCloneRequest()
                .bitbucketServerUrl("https://stash.domain.com")
                .localRepoDirectory("/Users/enes.acikoglu/Desktop/Projects/Trendyol")
                .userName("enes")
                .password("password")
                .projectCount(61)
                .projectKey("MAR")
                .build();

        RepoCloneResponse repoCloneResponse = new RepoCloneResponse();
        repoCloneResponse.setStatus(ResponseStatusType.FAILURE.getValue());

        given(cloneCreateRequestService.cloneRepos(any(CloneRequest.class))).willReturn(repoCloneResponse);

        //when and Then
        mockMvc.perform(post("/api/repo/clone")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonHelper.serializeToJson(cloneRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value("failure"));
    }
}