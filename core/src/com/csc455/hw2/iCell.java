package com.csc455.hw2;

import com.badlogic.gdx.graphics.Color;

public class iCell extends Cell{

    public iCell(int value, boolean impassable, boolean teleporter) {
        super(value, impassable, teleporter);
        if(impassable){
            super.setColor(new Color(.5f,.5f,.5f,1));
        }
    }
}
