package com.csc455.hw2;

public class Edge {
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
}
