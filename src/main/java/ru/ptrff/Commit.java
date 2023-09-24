package ru.ptrff;

public class Commit {
    private String lastCommitId;
    private String currentCommitId;
    private String commitMessage;
    private String branchName;
    private String branchFrom;
    private String mergeFrom;


    public Commit(String lastCommitId, String currentCommitId, String commitMessage,
                  String branchName, String branchFrom, String mergeFrom) {
        this.lastCommitId = lastCommitId;
        this.currentCommitId = currentCommitId;
        this.commitMessage = commitMessage;
        this.branchName = branchName;
        this.branchFrom = branchFrom;
        this.mergeFrom = mergeFrom;
    }

    public String getLastId() {
        return lastCommitId;
    }

    public String getCurrentId() {
        return currentCommitId;
    }

    public String getMessage() {
        return commitMessage;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchFrom() {
        return branchFrom;
    }

    public String getMergeFrom() {
        return mergeFrom;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "lastCommitId='" + lastCommitId + '\'' +
                ", currentCommitId='" + currentCommitId + '\'' +
                ", commitMessage='" + commitMessage + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchFrom='" + branchFrom + '\'' +
                ", mergeFrom='" + mergeFrom + '\'' +
                '}';
    }
}
