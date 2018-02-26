package com.csc455.hw2;

public class tCell extends Cell {

    private String name;
    private boolean start;

    public tCell(int value, boolean impassable, boolean teleporter, String name) {
        super(value, impassable, teleporter);

        super.edges = new Edge[5];
        this.name = name;
        this.start = false;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }
}
