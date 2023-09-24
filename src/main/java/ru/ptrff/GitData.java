package ru.ptrff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitData {

    private final List<Branch> branches;

    public GitData() {
        branches = new ArrayList<>();
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }


    public void makeFiles(String path) {
        TreeBuilder builder = new TreeBuilder();

        for (Branch branch : branches) {
            List<Commit> commits = branch.getCommits();
            System.out.println(branch.getName());

            for (Commit commit : commits) {
                String mergeFrom = commit.getMergeFrom();
                String branchFrom = commit.getBranchFrom();

                out(commit.toString());
/*
                if (!commit.getLastId().startsWith("000000000")) {
                    builder.addNode(
                            branchFrom,
                            commit.getLastId(),
                            commit.getMessage(),
                            branch.getName(),
                            commit.getCurrentId(),
                            commit.getMessage()
                    );

                    if (mergeFrom != null) {
                        out("Merged from: " + mergeFrom);
                        builder.addNode(
                                mergeFrom,
                                commit.getLastId(),
                                "Merge",
                                branch.getName(),
                                commit.getCurrentId(),
                                commit.getMessage()
                        );
                    }
                }*/
            }
        }

//        builder.saveTreeAsGv(path);
//        builder.saveTree(path);
    }

    private void out(String... messages) {
        for (String message : messages)
            System.out.print(message + "  ");
        System.out.println();
    }
}
