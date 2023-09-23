package ru.ptrff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitData {

    private final Map<String, String> commitAndMessage;
    private final List<String> nodes;
    private final List<String> edges;

    public GitData() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        commitAndMessage = new HashMap<>();
    }

    public void registerCommit(String name, String message) {
        commitAndMessage.put(name, message);
    }

    public Map<String, String> getCommitsAndMessages() {
        return commitAndMessage;
    }

    public void addNode(String node) {
        nodes.add(node);
    }

    public void addEdge(String from, String to) {
        edges.add(from + " -> " + to);
    }

    public void display() {
        System.out.println("Nodes:");
        for (String node : nodes) {
            System.out.println(node);
        }

        System.out.println("Edges:");
        for (String edge : edges) {
            System.out.println(edge);
        }
    }
}
