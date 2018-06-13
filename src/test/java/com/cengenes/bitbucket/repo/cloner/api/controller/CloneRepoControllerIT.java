package com.cengenes.bitbucket.repo.cloner.api.controller;

import com.cengenes.bitbucket.repo.cloner.api.builder.CloneRequestBuilder;
import com.cengenes.bitbucket.repo.cloner.api.model.request.CloneRequest;
import com.cengenes.bitbucket.repo.cloner.api.model.response.RepoCloneResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CloneRepoControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void it_should_clone_successfully() {
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
        ResponseEntity<RepoCloneResponse> responseEntity = testRestTemplate.
                postForEntity("/api/repo/clone", cloneRequest, RepoCloneResponse.class);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getStatus()).isEqualTo("success");
    }
}