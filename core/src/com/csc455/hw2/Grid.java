package com.csc455.hw2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Grid {

    private static Cell grid[][];
    private static boolean called = false;
    private static final int[] x = {0, 0, -1, 1};
    private static final int[] y = {-1, 1, 0, 0};

    public static void handleFile() {
        if (called) return;

        final FileHandle f = Gdx.files.internal("data/grid2.txt");
        final String input = f.readString();
        final String lines[] = input.split("\\r?\n");

        final int x = lines[0].split("\\t").length;
        final int y = lines.length;

        grid = new Cell[y][x];
        System.out.println("X: " + x + "\nY: " + y);

        for (int i = 0; i < lines.length; i++) {
            String line[] = lines[i].split("\\t");
            for (int j = 0; j < line.length; j++) {
                int val;
                try {
                    val = Integer.parseInt(line[j]);
                } catch (NumberFormatException e) {
                    val = Integer.MAX_VALUE;
                }

                grid[i][j] = new Cell(val);
            }
        }
        called = true;
        System.out.println("\nFile read in.");
        System.out.println("Adding Neighbors.");
        addEdges();
        System.out.println("Neighbors added.");
        System.out.println("Resetting Cells.");
        resetCells();
    }

    private static void addEdges() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = 0; k < x.length; k++) {
                    attemptAdd(i, j, k);
                }
            }
        }
    }

    private static void attemptAdd(int i, int j, int k) {
        try {
            final Cell parent = grid[i][j];
            final Cell child = grid[i + x[k]][j + y[k]];
            final int weight = Math.abs(parent.getValue() + child.getValue());
            final Edge edge = new Edge(parent, child, weight);
            grid[i][j].addEdge(edge);
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    private static void resetCells() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setValue(Integer.MAX_VALUE);
            }
        }
    }

    public static Cell[][] getGrid() {
        return grid;
    }
}
