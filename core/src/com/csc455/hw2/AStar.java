package com.csc455.hw2;

public class AStar extends Dijkstra {
    /**
     * @param x    - start x
     * @param y    - start y
     * @param endX - end x
     * @param endY - end y
     */
    AStar(int x, int y, int endX, int endY) {
        super(x, y, endX, endY);
    }

    /**
     * Adds Heuristic to distance calculation
     * @param e - edge
     * @param p - parent
     * @param c - child
     * @return distance
     */
    @Override
    public int calculateDistance(Edge e, Cell p, Cell c) {
        return Math.abs(e.getWeight() + p.getValue()) + (Math.abs(END_X - c.getX()) + Math.abs(END_Y - c.getY()
        ));
    }
}
