package com.csc455.hw2;

import java.util.Comparator;

public class Edge implements Comparator<Edge> {
    private final Cell PARENT;
    private final Cell CHILD;
    private final int WEIGHT;

    public Edge(Cell parent, Cell child, int weight) {
        this.PARENT = parent;
        this.CHILD = child;
        this.WEIGHT = weight;
    }

    public Cell getParent() {
        return PARENT;
    }

    public Cell getChild() {
        return CHILD;
    }

    public int getWeight() {
        return WEIGHT;
    }

    @Override
    public int compare(Edge e1, Edge e2) {
        if (e1.WEIGHT < e2.WEIGHT) return -1;
        if (e1.WEIGHT > e2.WEIGHT) return 1;
        return 0;
    }
}
