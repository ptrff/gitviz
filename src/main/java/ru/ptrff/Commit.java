package ru.ptrff;

public class Commit {

    private String type; // init, newbranch, merge, commit

    private String branchFrom;
    private String mergeFrom;

    private String message;
    private String id;
    private String lastId;


    public Commit(String id, String message) {
        type = "init";
        this.id = id;
        this.message = message;
    }

    public Commit(String id, String lastId, String message) {
        type = "commit";
        this.id =id;
        this.lastId = lastId;
        this.message = message;
    }

    public Commit(String id, String lastId, String message, String branchFrom) {
        type = "newbranch";
        this.id =id;
        this.lastId = lastId;
        this.message = message;
        this.branchFrom = branchFrom;
    }

    public Commit(String id, String lastId, String message, String mergeFrom, boolean merge) {
        type = "merge";
        this.id =id;
        this.lastId = lastId;
        this.message = message;
        this.mergeFrom = mergeFrom;
    }


    public String getType() {
        return type;
    }

    public String getBranchFrom() {
        return branchFrom;
    }

    public String getMergeFrom() {
        return mergeFrom;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public String getLastId() {
        return lastId;
    }
}
