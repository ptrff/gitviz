package ru.ptrff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String applyRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    private static void createGraph(Branch branch, GitData data, String path) throws IOException {
        File file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;

        System.out.println(branch.getName());

        while ((line = bufferedReader.readLine()) != null) {
            String[] commitInfo = line.split(" ");
            String lastCommitId = commitInfo[0];
            String currentCommitId = commitInfo[1];
            String commitMessage = applyRegex(line, ": (.+)");


            Commit commit = null;

            if (line.contains("commit (initial)")) {
                // init commit
                System.out.println("Init " + lastCommitId + " " + currentCommitId + " " + commitMessage);
                commit = new Commit(currentCommitId, commitMessage);

            } else if (line.contains("commit")) {
                // commit
                System.out.println("Commit " + lastCommitId + " " + currentCommitId + " " + commitMessage);
                commit = new Commit(currentCommitId, lastCommitId, commitMessage);

            } else if (line.contains("merge")) {
                // merge from
                int mergeIndex = line.indexOf("merge");
                if (mergeIndex != -1) {
                    String mergeName = (line.substring(mergeIndex + "merge".length()).trim()).split(":")[0];
                    System.out.println("Merge from " + mergeName + " " + lastCommitId + " " + currentCommitId + " " + commitMessage);
                    commit = new Commit(currentCommitId, lastCommitId, commitMessage, mergeName, true);

                }
            } else if (line.contains("branch: Created from")) {
                // created from
                int branchIndex = line.indexOf("branch: Created from");
                if (branchIndex != -1) {
                    String branchName = line.substring(branchIndex + "branch: Created from".length()).trim();
                    System.out.println("Created from " + branchName + " " + lastCommitId + " " + currentCommitId + " " + commitMessage);
                    commit = new Commit(currentCommitId, lastCommitId, commitMessage, branchName);
                }
            }

            if(commit == null){
                System.out.println("ERRORR!!!!!!");
            }else{
                branch.addCommit(commit);
            }
        }

        data.addBranch(branch);
        bufferedReader.close();
    }

    public static void main(String[] args) throws IOException {
        String testPath = "C:/Users/ptrff/Desktop/konfigupr/gitviz/";
        String pathCommits = testPath.replace("\\", "/") + ".git/logs/refs/heads";

        GitData data = new GitData();

        File folder = new File(pathCommits);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                Branch branch = new Branch(file.getName());
                String filePath = file.getAbsolutePath().replace("\\", "/");
                createGraph(branch, data, filePath);
            }
        }

        data.makeFiles(testPath);
    }

}