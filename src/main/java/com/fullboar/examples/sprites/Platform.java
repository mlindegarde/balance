package com.fullboar.examples.sprites;

import com.fullboar.examples.Direction;

public class Platform extends Sprite {
    public static final int CELL_HEIGHT = 4;
    public static final int CELL_WIDTH = 10;

    @Override
    public String[] getImage() {
        return new String[] {
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "X         X         X         X         X         X",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
        };
    }

    public Platform (int x, int y, Direction facing, String color) {
        super (x, y, facing, color);
    }
}