package ru.ptrff;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;

public class TreeBuilder {
    private Graph graph;

    public TreeBuilder() {
        this.graph = graph().directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM));
    }

    public void addNode(String id, String lastId, String branch, String lastBranch, String message, String lastMessage) {

        Node parentNode = Factory.node(lastId)
                .with(Label.lines(
                        lastBranch,
                        lastMessage
                ));

        Node newNode = Factory.node(id)
                .with(Label.lines(
                        branch,
                        message
                ));

        graph = graph.with(parentNode.link(newNode));
    }


    public void saveTree(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Graphviz.fromGraph(graph).render(Format.PNG).toFile(new File(path.toString()));
            System.out.println("Tree saved to: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error: Unable to save the tree");
            e.printStackTrace();
        }
    }

    public void saveTreeAsGv(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Graphviz.fromGraph(graph).render(Format.DOT).toFile(new File(path.toString()));
            System.out.println("Tree saved to: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error: Unable to save the tree");
            e.printStackTrace();
        }
    }

}
