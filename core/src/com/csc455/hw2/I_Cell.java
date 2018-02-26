package com.csc455.hw2;

import com.badlogic.gdx.graphics.Color;

class I_Cell extends Cell {

    /**
     * @param value      - value of this cell
     * @param impassable - is this cell impassable
     * @param teleporter - is this cell teleporter
     */
    I_Cell(int value, boolean impassable, boolean teleporter) {
        super(value, impassable, teleporter);
        if (impassable) {
            super.setColor(new Color(.5f, .5f, .5f, 1));
        }
    }
}
