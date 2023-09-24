package ru.ptrff;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Branch {

    private final String name;
    private String initMessage;
    private final List<Commit> commits;

    public Branch(String name) {
        this.name = name;
        this.commits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setInitMessage(String message){
        initMessage = message;
    }

    public String getInitMessage(){
        return initMessage;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void addCommit(Commit commit) {
        this.commits.add(commit);
    }
}
