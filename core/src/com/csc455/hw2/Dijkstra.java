package com.csc455.hw2;

import com.badlogic.gdx.graphics.Color;

import java.util.PriorityQueue;

class Dijkstra implements Runnable {
    private final PriorityQueue<Cell> queue = new PriorityQueue<>();
    private final Cell[][] grid = Grid.getGrid();

    private final int X;
    private final int Y;
    final int END_X;
    final int END_Y;

    /**
     * @param x    - start x
     * @param y    - start y
     * @param endX - end x
     * @param endY - end y
     */
    Dijkstra(int x, int y, int endX, int endY) {
        this.X = x;
        this.Y = y;
        this.END_X = endX;
        this.END_Y = endY;
    }

    /**
     * Runs the algorithm
     */
    public void run() {

        Cell c = grid[Y][X];

        c.setValue(0);
        c.setVisited(true);
        queue.add(c);

        while (!queue.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
            c = queue.poll();
            c.setVisited(true);

            for (Edge e : c.getEdges()) {
                final Cell child = e == null ? null : e.getChild();

                if (child != null && !child.isVisited()) {
                    int distance = calculateDistance(e, c, child);

                    if (c instanceof T_Cell && child instanceof T_Cell) {
                        ((T_Cell) c).setStart(true);

                        setDistance(distance, c, child);

                        break;
                    } else if (c instanceof T_Cell && !((T_Cell) c).isStart()) {
                        setDistance(distance, c, child);
                    }

                    setDistance(distance, c, child);
                    if (!child.isImpassable() && !child.isTeleporter()) {
                        child.setColor(new Color(.2f, .2f, .2f, 1));
                    }
                }

                if (checkFound(c)) {
                    return;
                }
            }
        }
    }

    /**
     * Adds the distance of the parent value and edge weight
     * @param e - edge
     * @param p - parent cell
     * @param c - child cell
     * @return distance
     */
    public int calculateDistance(Edge e, Cell p, Cell c) {
        return Math.abs(e.getWeight() + p.getValue());
    }

    /**
     * Checks to see if it can change the value of a cell and add a predecessor to it
     *
     * @param distance - the new distance
     * @param c        - parent cell
     * @param child    - child cell
     */
    private void setDistance(int distance, Cell c, Cell child) {
        if (distance < child.getValue()) {
            child.setValue(distance);
            child.setPredecessor(c);
            queue.add(child);
        }
    }

    /**
     * Checks to see if the algorithm is done.  If it is, it builds the path on screen
     *
     * @param c - destination cell
     * @return - whether or not the cell was found
     */
    private boolean checkFound(Cell c) {
        if (c == grid[END_Y][END_X]) {
            int path = 0;
            while (c != null) {
                path += c.getValue();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                }
                c.setColor(new Color(0, 0, 0, 1));
                c = c.getPredecessor();
            }
            System.out.println(path);
            return true;
        }
        return false;
    }
}
