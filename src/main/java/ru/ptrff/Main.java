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

        while ((line = bufferedReader.readLine()) != null) {
            String[] commitInfo = line.split(" ");
            String lastCommitId = commitInfo[0];
            String currentCommitId = commitInfo[1];
            String commitMessage = applyRegex(line, ": (.+)");
            String branchFrom = commitInfo[2];

            Commit commit = new Commit(lastCommitId, currentCommitId, commitMessage, branch.getName(), branchFrom, null);
            branch.addCommit(commit);
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