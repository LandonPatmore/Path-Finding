package com.csc455.hw2;

import com.badlogic.gdx.graphics.Color;

public class Cell implements Comparable<Cell> {
    private int value;
    private boolean visited;
    private int edgeNum;
    private Cell predecessor;
    private Color color;
    private boolean impassable;
    private boolean teleporter;
    protected Edge[] edges;

    public Cell(int value, boolean impassable, boolean teleporter) {
        this.value = value;
        this.edges = new Edge[4];
        this.visited = false;
        this.color = new Color(1,1,1,1);
        this.impassable = impassable;
        this.teleporter = teleporter;
    }

    public boolean isTeleporter() {
        return teleporter;
    }

    public boolean isImpassable() {
        return impassable;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void addEdge(Edge e) {
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
