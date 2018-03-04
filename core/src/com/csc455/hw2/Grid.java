package com.csc455.hw2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class Grid {

    private static Cell grid[][];
    private static final int[] x = {0, 0, -1, 1};
    private static final int[] y = {-1, 1, 0, 0};
    private static int xLength;
    private static int yLength;

    private static ArrayList<T_Cell> teleporters = new ArrayList<>();

    /**
     * Handles a file by taking it in and parsing it into a 2D Array
     */
    public static void handleFile() {
        final FileHandle f = Gdx.files.internal("data/grid3.txt");
        final String input = f.readString();
        final String lines[] = input.split("\\r?\n");

        xLength = lines[0].split(" ").length;
        yLength = lines.length;

        System.out.println("X: " + xLength);
        System.out.println("Y: " + yLength);

        grid = new Cell[yLength][xLength];


        for (int i = 0; i < lines.length; i++) {
            String line[] = lines[i].split(" ");
            for (int j = 0; j < line.length; j++) {
                try {
                    int val = Integer.parseInt(line[j]);
                    grid[i][j] = new Cell(val, false, false, j, i);
                } catch (NumberFormatException e) {
                    if (line[j].equals("F")) {
                        grid[i][j] = new I_Cell(Integer.MAX_VALUE, true, false, j, i);
                    } else if (line[j].contains("T")) {
                        T_Cell t = new T_Cell(0, false, true, line[j], j, i);
                        teleporters.add(t);
                        grid[i][j] = t;
                    }
                }
            }
        }
        System.out.println("\nFile read in.");
        System.out.println("Adding Neighbors.");
        addEdges();
        System.out.println("Neighbors added.");
        System.out.println("Resetting Cells.");
        resetCells();
    }

    /**
     * Adds edges to a cell
     */
    private static void addEdges() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = 0; k < x.length; k++) {
                    if (grid[i][j].isTeleporter()) {
                        addTeleporter((T_Cell) grid[i][j]);
                    }
                    attemptAdd(i, j, k);
                }
            }
        }
    }

    /**
     * Adds a teleporter to another teleporter
     *
     * @param c - child cell
     */
    private static void addTeleporter(T_Cell c) {
        if (teleporters.remove(c)) {
            Color color = new Color(new Random().nextFloat() + .5f, new Random().nextFloat() + .5f, new Random().nextFloat() + .5f, 1);

            for (T_Cell t : teleporters) {
                if (c.getName().equals(t.getName())) {
                    c.addEdge(new Edge(t, 0));
                    t.addEdge(new Edge(c, 0));
                    c.setColor(color);
                    t.setColor(color);
                    teleporters.remove(t);
                    return;
                }
            }
        }
    }

    /**
     * Attempts to add a neighbor to a cell
     *
     * @param i - x value
     * @param j - y value
     * @param k - k value
     */
    private static void attemptAdd(int i, int j, int k) {
        try {
            final Cell parent = grid[i][j];
            final Cell child = grid[i + x[k]][j + y[k]];
            final int weight = Math.abs(parent.getValue() + child.getValue());
            final Edge edge = new Edge(child, weight);
            grid[i][j].addEdge(edge);
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    /**
     * Resets the cells to Infinity after calculating their edge weights
     */
    private static void resetCells() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setValue(Integer.MAX_VALUE);
            }
        }
    }

    /**
     * @return grid 2D Array
     */
    public static Cell[][] getGrid() {
        return grid;
    }

    /**
     * @return x length
     */
    public static int getX() {
        return xLength;
    }

    /**
     * @return y length
     */
    public static int getY() {
        return yLength;
    }
}

