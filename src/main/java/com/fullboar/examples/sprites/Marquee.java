package com.fullboar.examples.sprites;

import com.fullboar.examples.Direction;

public class Marquee extends Sprite {
    @Override
    public String[] getImage() {
        return new String[] {
            "╔═════════════════════════════════════════════════════════════════════════╗",
            "║~*~                                                                   ~*~║",
            "║     BBBBBB      A     L          A     N     N   CCCCCC  EEEEEEE  !!    ║",
            "║     B     B    A A    L         A A    NN    N  C        E        !!    ║",
            "║     B     B   A   A   L        A   A   N N   N  C        E        !!    ║",
            "║     BBBBBB   AAAAAAA  L       AAAAAAA  N  N  N  C        EEEEE    !!    ║",
            "║     B     B  A     A  L       A     A  N   N N  C        E        !!    ║",
            "║     B     B  A     A  L       A     A  N    NN  C        E              ║",
            "║     BBBBBB   A     A  LLLLLLL A     A  N     N   CCCCCC  EEEEEEE  !!    ║",
            "║~*~                                                                   ~*~║",
            "╚═════════════════════════════════════════════════════════════════════════╝"
        };
    }

    public Marquee (String color) {
        super(0, 0, Direction.LEFT, color);
    }
}