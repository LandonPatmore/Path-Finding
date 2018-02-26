package com.csc455.hw2;

import java.util.Comparator;

class Edge implements Comparator<Edge> {
    private final Cell CHILD;
    private final int WEIGHT;

    /**
     * @param child  - child cell
     * @param weight - weight of this edge
     */
    Edge(Cell child, int weight) {
        this.CHILD = child;
        this.WEIGHT = weight;
    }

    /**
     * @return child of this edge
     */
    public Cell getChild() {
        return CHILD;
    }

    /**
     * @return weight
     */
    public int getWeight() {
        return WEIGHT;
    }

    @Override
    public int compare(Edge e1, Edge e2) {
        return Integer.compare(e1.WEIGHT, e2.WEIGHT);
    }
}
