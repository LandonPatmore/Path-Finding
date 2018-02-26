package com.csc455.hw2;

import com.badlogic.gdx.graphics.Color;

class Cell implements Comparable<Cell> {
    private int value;
    private boolean visited;
    private int edgeNum;
    private Cell predecessor;
    private Color color;
    private boolean impassable;
    private boolean teleporter;
    Edge[] edges;

    /**
     * @param value      - value of the cell
     * @param impassable - is it impassable
     * @param teleporter - is it a teleporter
     */
    Cell(int value, boolean impassable, boolean teleporter) {
        this.value = value;
        this.edges = new Edge[4];
        this.visited = false;
        this.color = new Color(1, 1, 1, 1);
        this.impassable = impassable;
        this.teleporter = teleporter;
    }

    /**
     * @return whether it is a teleporter or not
     */
    public boolean isTeleporter() {
        return teleporter;
    }

    /**
     * @return whether it is impassable or not
     */
    public boolean isImpassable() {
        return impassable;
    }

    /**
     * @return color of cell
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color - color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param e - edge to add
     */
    public void addEdge(Edge e) {
        edges[edgeNum] = e;
        edgeNum++;
    }

    /**
     * @param predecessor - the cell prior to this one in the path
     */
    public void setPredecessor(Cell predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * @param value - value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return predecessor
     */
    public Cell getPredecessor() {
        return predecessor;
    }

    /**
     * @return edges
     */
    public Edge[] getEdges() {
        return edges;
    }

    /**
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return is visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param visited - has it been visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public int compareTo(Cell o) {
        return Integer.compare(this.value, o.value);
    }
}
