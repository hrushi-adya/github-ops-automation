package com.gitops;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import java.util.LinkedHashMap;

import com.utils.Mapper;
import com.utils.GitHubUtils;
import static com.constants.Constants.*;

public class main {
    public static void main(String[] args) {
        try {

            // Accept Input
            acceptInput(args);
            System.out.println("printing client details: ");
            printClientDetails();
            // Initiate GitHub Operations
            GHRepository repo = GitHubUtils.initiateGitHubClient();
            // Create a new Branch
            LinkedHashMap<String, Object> branchContentMap = GitHubUtils.createBranch(repo);
            String json = (String) branchContentMap.get("jsonString");
            // Update the JSON File
            String updatedJson = Mapper.UpdateClientDetails(json);
            GHContent fileContent = (GHContent) branchContentMap.get("fileContent");
            // Commit the code changes to the new branch
            GitHubUtils.commitChanges(repo, fileContent, updatedJson);
            // Create a Pull Request
            String branchName = System.getProperty(CLIENT_ID) + HYPHEN + System.getProperty(OPERATION) + HYPHEN + REPO_BRANCH;
            String prTitle = System.getProperty(CLIENT_ID) + HYPHEN + System.getProperty(OPERATION);
            repo.createPullRequest(prTitle, branchName, REPO_BASE_BRANCH, "Automated PR for JSON Update");

        } catch (Exception e) {
            System.out.println("Error while running GitOps Automation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void acceptInput(String[] args) {
        Options options = new Options();

        Option idOption = new Option("i", "id", true, "user id");
        idOption.setRequired(true);
        options.addOption(idOption);

        Option nameOption = new Option("n", "name", true, "user name");
        nameOption.setRequired(false);
        options.addOption(nameOption);

        Option organizationOption = new Option("o", "organization", true, "user organization");
        organizationOption.setRequired(false);
        options.addOption(organizationOption);

        Option operationOption = new Option("op", "operation", true, "operation to perform");
        operationOption.setRequired(true);
        options.addOption(operationOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            String id = cmd.getOptionValue("id");
            String name = cmd.getOptionValue("name");
            String organization = cmd.getOptionValue("organization");
            String operation = cmd.getOptionValue("operation");

            System.setProperty(CLIENT_ID, id);
            if(name != null) {
                System.setProperty(CLIENT_NAME, name);
            }
            if(organization != null) {
                System.setProperty(CLIENT_ORGANIZATION, organization);
            }

            if(operation.equalsIgnoreCase(ADD_CLIENT) ||
                    operation.equalsIgnoreCase(UPDATE_CLIENT) ||
                    operation.equalsIgnoreCase(DELETE_CLIENT)) {
                System.setProperty(OPERATION, operation);
            } else {
                System.out.println("Invalid operation. Valid Operations are add, update, delete");
                formatter.printHelp("invalid operation", options);
                System.exit(1);
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Error while processing input", options);
            System.exit(1);
        }
    }

    public static void printClientDetails() {
        System.out.println("User ID: " + System.getProperty(CLIENT_ID));
        System.out.println("User Name: " + System.getProperty(CLIENT_NAME));
        System.out.println("User Organization: " + System.getProperty(CLIENT_ORGANIZATION));
        System.out.println("Operation: " + System.getProperty(OPERATION));
    }
}
