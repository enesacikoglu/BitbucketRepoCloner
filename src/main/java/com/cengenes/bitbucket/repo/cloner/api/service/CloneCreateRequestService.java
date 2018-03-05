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
import java.util.Optional;

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
        final Optional<JSONArray> repositories;
        try {
            repositories = obtainRepositories(cloneRequest);
            if (repositories.isPresent()) {
                for (int k = 0; k < repositories.get().length(); k++) {
                    JSONObject repository = (JSONObject) repositories.get().get(k);
                    String repoName = repository.getString("name");
                    log.debug("Repository name: {}", repoName);
                    String repoDir = cloneRequest.getLocalRepoDirectory() + "/" + cloneRequest.getProjectKey()+ "/" + repoName;
                    log.debug("Repository local directory where clone to {}", cloneRequest.getLocalRepoDirectory());
                    final JSONArray cloneURLs = (JSONArray) ((JSONObject) repository.get("links")).get("clone");
                    for (int r = 0; r < cloneURLs.length(); r++) {
                        if (((JSONObject) cloneURLs.get(r)).get("name").toString().equals("http")) {
                            log.debug("HTTP repository link for clone found.");
                            String repoURL = ((JSONObject) cloneURLs.get(r)).getString("href");
                            cloneRepository(cloneRequest.getUserName(),cloneRequest.getPassword(),repoDir,repoURL);
                        } else {
                            log.debug(((JSONObject) cloneURLs.get(r)).get("name").toString());
                        }
                    }
                }
                repoCloneResponse.setStatus(ResponseStatusType.SUCCESS.getValue());
            }
        } catch (UnirestException | GitAPIException e) {
            log.error("Error on obtaining Repos {}", e);
        }
        return repoCloneResponse;
    }

    private final Optional<JSONArray> obtainRepositories(final CloneRequest cloneRequest) throws UnirestException {
        log.info("Obtaining repos from {}", cloneRequest.getProjectKey());
        final Integer PROJECT_COUNT_TO_CLONE = cloneRequest.getProjectCount() != null ? cloneRequest.getProjectCount() : 100;
        final String REPO_URL = cloneRequest.getBitbucketServerUrl() + REST_API_SUFFIX + API_PROJECTS
                + "/" + cloneRequest.getProjectKey() + API_REPOSITORIES + "?limit=" + PROJECT_COUNT_TO_CLONE;

        return Optional.of(Unirest.get(REPO_URL)
                .basicAuth(cloneRequest.getUserName(), cloneRequest.getPassword())
                .header("accept", "application/json")
                .asJson()
                .getBody()
                .getObject().getJSONArray("values"));
    }

    private final void cloneRepository(final String userName,final String password,final String repoDir,final String repoURL) throws GitAPIException {
        log.info("Going to clone repo {},Repository will be stored at {}", repoURL,repoDir);
        Git.cloneRepository()
                .setURI(repoURL)
                .setDirectory(new File(repoDir))
                .setCloneAllBranches(true)
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password))
                .call();
    }
}
