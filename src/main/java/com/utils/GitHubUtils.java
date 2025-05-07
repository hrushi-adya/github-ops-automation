package com.utils;

import org.kohsuke.github.GHRef;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.GHContent;

import java.io.IOException;
import java.util.LinkedHashMap;

import static com.constants.Constants.*;

public class GitHubUtils {
    public static GHRepository initiateGitHubClient() throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(REPO_ACCESS_TOKEN).build();
        GHRepository repo = github.getRepository(GITHUB_REPO_NAME);
        return repo;
    }

    public static LinkedHashMap<String, Object> createBranch(GHRepository repo) throws IOException {
        LinkedHashMap<String, Object> returnMap = new LinkedHashMap<>();
        GHRef baseRef = repo.getRef("heads/" + REPO_BASE_BRANCH);
        String sha = baseRef.getObject().getSha();
        System.out.println("Base branch SHA: " + sha);

        GHRef newRef = repo.createRef("refs/heads/" + System.getProperty(CLIENT_ID) + HYPHEN + System.getProperty(OPERATION) + HYPHEN + REPO_BRANCH, sha);
        System.out.println("Branch created: " + newRef.getRef());

        GHContent fileContent = repo.getFileContent(CLIENT_FILE_PATH, newRef.getRef());
        String json = fileContent.getContent();
        System.out.println("JSON content: " + json);

        returnMap.put("jsonString", json);
        returnMap.put("fileContent", fileContent);

        return returnMap;
    }

    public static void commitChanges(GHRepository repo, GHContent fileContent, String updatedJson) {
        try {
            repo.createContent()
                    .content(updatedJson)
                    .message("Update JSON in new branch")
                    .path(CLIENT_FILE_PATH)
                    .sha(fileContent.getSha())
                    .branch(System.getProperty(CLIENT_ID) + HYPHEN + System.getProperty(OPERATION) + HYPHEN + REPO_BRANCH)
                    .commit();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


