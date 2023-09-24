package ru.ptrff;

import java.util.*;

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

            for (int i = 0; i < commits.size(); i++) {
                Commit commit = commits.get(i);

                switch (commit.getType()) {
                    case "init":
                        break;
                    case "commit":
                        builder.addNode(
                                commit.getId(),
                                commit.getLastId(),
                                branch.getName(),
                                branch.getName(),
                                commit.getMessage(),
                                commits.get(i - 1).getMessage()
                        );
                        break;
                    case "newbranch":
                        /*builder.addNode(
                                commit.getId(),
                                commit.getId(),
                                branch.getName(),
                                commit.getBranchFrom(),
                                commit.getMessage(),
                                getMessageFromAnotherBranch(commit.getId(), commit.getBranchFrom())
                        );*/
                        break;
                    case "merge":
                        builder.addNode(
                                commit.getId(),
                                commit.getLastId(),
                                branch.getName(),
                                commit.getMergeFrom(),
                                commit.getMessage(),
                                getMessageFromAnotherBranch(commit.getLastId(), commit.getMergeFrom())
                        );
                        break;
                    default:
                        System.out.println("ERROR: commit type not found");
                        break;
                }
            }
        }

        builder.saveTreeAsGv(path);
        builder.saveTree(path);
    }

    private String getMessageFromAnotherBranch(String id, String branchName) {
        for (Branch branch : branches) {
            if (Objects.equals(branch.getName(), branchName)) {
                for (Commit commit : branch.getCommits()) {
                    if (Objects.equals(commit.getId(), id)) {
                        return commit.getMessage();
                    }
                }
            }
        }
        System.out.println("Message not found for " + id + " in branch " + branchName);
        return "ERROR";
    }

}
