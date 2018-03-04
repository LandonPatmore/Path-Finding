package com.csc455.hw2;

class T_Cell extends Cell {

    private String name;
    private boolean start;

    /**
     * @param value      - value of this cell
     * @param impassable - is it impassable
     * @param teleporter - is it a teleporter
     * @param name       - name of this cell
     */
    T_Cell(int value, boolean impassable, boolean teleporter, String name, int x, int y) {
        super(value, impassable, teleporter,x,y);

        super.edges = new Edge[5];
        this.name = name;
        this.start = false;
    }

    /**
     * @return start cell
     */
    public boolean isStart() {
        return start;
    }

    /**
     * @param start - if this cell is the start teleporter
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }
}
