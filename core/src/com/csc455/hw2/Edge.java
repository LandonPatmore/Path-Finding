package com.csc455.hw2;

import java.util.Comparator;

public class Edge implements Comparator<Edge> {
    private final Cell CHILD;
    private final int WEIGHT;

    public Edge(Cell child, int weight) {
        this.CHILD = child;
        this.WEIGHT = weight;
    }

    public Cell getChild() {
        return CHILD;
    }

    public int getWeight() {
        return WEIGHT;
    }

    @Override
    public int compare(Edge e1, Edge e2) {
        return Integer.compare(e1.WEIGHT, e2.WEIGHT);
    }
}
