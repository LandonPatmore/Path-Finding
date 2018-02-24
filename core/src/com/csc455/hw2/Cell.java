package com.csc455.hw2;

import java.util.Comparator;

public class Cell implements Comparable<Cell> {
    private int value;
    private Edge[] edges;
    private boolean visited;
    private int edgeNum;
    private Cell predecessor;
    private String pathSymbol;

    public Cell(int value) {
        this.value = value;
        this.edges = new Edge[4];
        this.visited = false;
        this.pathSymbol = ".";
    }

    public String getPathSymbol() {
        return pathSymbol;
    }

    public void setPathSymbol(String pathSymbol) {
        this.pathSymbol = pathSymbol;
    }

    public void addEdge(Edge e) {
        if (edgeNum == 4) return;

        edges[edgeNum] = e;
        edgeNum++;
    }

    public void setPredecessor(Cell predecessor) {
        this.predecessor = predecessor;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Cell getPredecessor() {
        return predecessor;
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
    public int compareTo(Cell o) {
        if (this.value < o.value) return -1;
        if (this.value > o.value) return 1;
        return 0;
    }
}
