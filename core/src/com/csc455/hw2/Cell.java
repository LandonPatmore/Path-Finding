package com.csc455.hw2;

import java.util.Comparator;

public class Cell implements Comparator<Cell> {
    private final int VALUE;
    private Edge[] edges;
    private boolean visited;
    private int edgeNum;

    public Cell(int value) {
        this.VALUE = value;
        this.edges = new Edge[4];
        this.visited = false;
    }

    public void addEdge(Edge e) {
        if (edgeNum == 4) return;

        edges[edgeNum] = e;
        edgeNum++;
    }

    public int getValue() {
        return VALUE;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public int compare(Cell o1, Cell o2) {
        if (o1.VALUE < o2.VALUE) return -1;
        if (o1.VALUE > o2.VALUE) return 1;
        return 0;
    }
}
