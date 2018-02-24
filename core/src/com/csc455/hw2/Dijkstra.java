package com.csc455.hw2;

import java.util.PriorityQueue;

public class Dijkstra {
    private static PriorityQueue<Cell> queue = new PriorityQueue<Cell>();
    private static final Cell[][] grid = Grid.getGrid();

    public static Cell run(int x, int y, int endX, int endY) {
        Cell currentCell = grid[x][y];
        currentCell.setValue(0);

        currentCell.setVisited(true);
        queue.add(currentCell);

        while (!queue.isEmpty()) {
            final Cell c = queue.poll();
            c.setVisited(true);
            int distance;
            for (Edge e : c.getEdges()) {
                if (e!= null && !e.getChild().isVisited()) {
                    distance = Math.abs(e.getWeight() + c.getValue());
                    if (distance < e.getChild().getValue()) {
                        e.getChild().setValue(distance);
                        e.getChild().setPredecessor(c);
                        queue.add(e.getChild());
                    }
                }
            }
            if (c == grid[endY][endX]) {
                return c;
            }
        }
        return null;
    }
}
