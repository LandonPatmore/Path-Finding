package com.csc455.hw2;

import java.util.Comparator;

public class Cell implements Comparator<Cell> {
    private int value;
    private Edge[] edges;
    private boolean visited;
    private int edgeNum;

    public Cell(int value) {
        this.value = value;
        this.edges = new Edge[4];
        this.visited = false;
    }

    public void addEdge(Edge e) {
        if (edgeNum == 4) return;

        edges[edgeNum] = e;
        edgeNum++;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public int getValue() {
        return value;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public int compare(Cell o1, Cell o2) {
        if (o1.value < o2.value) return -1;
        if (o1.value > o2.value) return 1;
        return 0;
    }
}
