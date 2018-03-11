package com.cengenes.bitbucket.repo.cloner.api.service;


import com.cengenes.bitbucket.repo.cloner.api.model.request.CloneRequest;
import com.cengenes.bitbucket.repo.cloner.api.model.response.RepoCloneResponse;
import com.cengenes.bitbucket.repo.cloner.api.model.response.ResponseStatusType;
import org.springframework.stereotype.Service;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class CloneCreateRequestService {

    private final Logger log = LogManager.getLogger(this.getClass());

    private final String REST_API_SUFFIX;
    private final String API_PROJECTS;
    private final String API_REPOSITORIES;

    public CloneCreateRequestService() {
        REST_API_SUFFIX = "/rest/api/1.0";
        API_PROJECTS = "/projects";
        API_REPOSITORIES = "/repos";
    }

    public final RepoCloneResponse cloneRepos(CloneRequest cloneRequest) {
        final RepoCloneResponse repoCloneResponse = new RepoCloneResponse(ResponseStatusType.FAILURE.getValue());
        // Obtain repos
        try {
            final Optional<JSONArray> repositories = obtainRepositories(cloneRequest);
            if (repositories.isPresent()) {
                final Stream<Object> objectStream = arrayToStream(repositories.get());
                objectStream.filter(Objects::nonNull).map(object->(JSONObject)object).forEach(repo -> {
                    String repoName = repo.getString("name");
                    String repoURL = repo.getString("href");
                            try {
                                cloneRepository(cloneRequest.getUserName(), cloneRequest.getPassword(), getLocalRepoDir(cloneRequest, repoName), repoURL);
                                repoCloneResponse.setStatus(ResponseStatusType.SUCCESS.getValue());
                            } catch (GitAPIException e) {
                                log.error("Error on cloning Repos {}", e);
                            }
                        }
                );
            }
        } catch (UnirestException e) {
            log.error("Error on obtaining Repos {}", e);
        }
        return repoCloneResponse;
    }

    private final Optional<JSONArray> obtainRepositories(final CloneRequest cloneRequest) throws UnirestException {
        log.info("Obtaining repos from {}", cloneRequest.getProjectKey());
        return Optional.of(Unirest.get(getRepoUrl(cloneRequest))
                .basicAuth(cloneRequest.getUserName(), cloneRequest.getPassword())
                .header("accept", "application/json")
                .asJson()
                .getBody()
                .getObject().getJSONArray("values"));
    }

    private final Stream<Object> arrayToStream(final JSONArray array) {
        return StreamSupport.stream(array.spliterator(), false);
    }

    private final String getLocalRepoDir(final CloneRequest cloneRequest, final String repoName) {
        return cloneRequest.getLocalRepoDirectory() + "/" + cloneRequest.getProjectKey() + "/" + repoName;
    }

    private final String getRepoUrl(final CloneRequest cloneRequest) {
        final Integer PROJECT_COUNT_TO_CLONE = cloneRequest.getProjectCount() != null ? cloneRequest.getProjectCount() : 100;
        return cloneRequest.getBitbucketServerUrl() + REST_API_SUFFIX + API_PROJECTS
                + "/" + cloneRequest.getProjectKey() + API_REPOSITORIES + "?limit=" + PROJECT_COUNT_TO_CLONE;
    }

    private final void cloneRepository(final String userName, final String password, final String repoDir, final String repoURL) throws GitAPIException {
        log.info("Going to clone repo {},Repository will be stored at {}", repoURL, repoDir);
        Git.cloneRepository()
                .setURI(repoURL)
                .setDirectory(new File(repoDir))
                .setCloneAllBranches(true)
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password))
                .call();
    }
}
