package com.gitops;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import static com.constants.Constants.*;

public class main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        acceptInput(args);
        printUserDetails();
    }

    public static void acceptInput(String[] args) {
        Options options = new Options();

        Option idOption = new Option("i", "id", true, "user id");
        idOption.setRequired(true);
        options.addOption(idOption);

        Option nameOption = new Option("n", "name", true, "user name");
        nameOption.setRequired(true);
        options.addOption(nameOption);

        Option organizationOption = new Option("o", "organization", true, "user organization");
        organizationOption.setRequired(true);
        options.addOption(organizationOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            String id = cmd.getOptionValue("id");
            String name = cmd.getOptionValue("name");
            String organization = cmd.getOptionValue("organization");

            System.setProperty(USER_ID, id);
            System.setProperty(USER_NAME, name);
            System.setProperty(USER_ORGANIZATION, organization);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Error while processing input", options);
            System.exit(1);
        }
    }

    public static void printUserDetails() {
        System.out.println("User ID: " + System.getProperty(USER_ID));
        System.out.println("User Name: " + System.getProperty(USER_NAME));
        System.out.println("User Organization: " + System.getProperty(USER_ORGANIZATION));
    }
}
