package com.fullboar.examples.sprites;

import com.fullboar.examples.Direction;

public class Platform extends Sprite {
    public static final int CELL_HEIGHT = 4;
    public static final int CELL_WIDTH = 10;

    @Override
    public String[] getImage() {
        // https://www.w3.org/TR/xml-entity-names/025.html
        return new String[] {
            "╔═════════╤═════════╤═════════╤═════════╤═════════╗",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "╟─────────┼─────────┼─────────┼─────────┼─────────╢",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "╟─────────┼─────────┼─────────┼─────────┼─────────╢",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "╟─────────┼─────────┼─────────┼─────────┼─────────╢",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "╟─────────┼─────────┼─────────┼─────────┼─────────╢",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "║         │         │         │         │         ║",
            "╚═════════╧═════════╧═════════╧═════════╧═════════╝"
        };
    }

    public Platform (int x, int y, Direction facing, String color) {
        super (x, y, facing, color);
    }
}