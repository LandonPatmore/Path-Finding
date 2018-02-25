package com.csc455.hw2;

import com.badlogic.gdx.graphics.Color;

import java.util.PriorityQueue;

public class Dijkstra {
    private final PriorityQueue<Cell> queue = new PriorityQueue<Cell>();
    private final Cell[][] grid = Grid.getGrid();

    public Dijkstra(){

    }

    public void run(int x, int y, int endX, int endY) throws InterruptedException {
        Cell c = grid[y][x];

        c.setValue(0);
        c.setVisited(true);
        queue.add(c);

        while (!queue.isEmpty()) {
            Thread.sleep(1);
            c = queue.poll();
            c.setVisited(true);

            for (Edge e : c.getEdges()) {
                final Cell child = e == null ? null : e.getChild();
                if (child != null && !child.isVisited()) {
                    child.setColor(new Color(.2f,.2f,.2f, 1));
                    int distance = Math.abs(e.getWeight() + c.getValue());

                    if (distance < child.getValue()) {
                        child.setValue(distance);
                        child.setPredecessor(c);
                        queue.add(child);
                    }
                }
            }
            if (c == grid[endY][endX]) {
                while(c != null){
                    c.setPathSymbol("-");
                    c.setColor(new Color(1,1,1,1));
                    c = c.getPredecessor();
                }
                return;
            }
        }
    }
}
