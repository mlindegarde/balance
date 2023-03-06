package com.fullboar.examples.sprites;

import com.fullboar.examples.subsystems.rendering.Direction;

public class Marquee extends Sprite {
    @Override
    public String[] getImage() {
        return new String[] {
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            "X~*~                                                                   ~*~X",
            "X     BBBBBB      A     L          A     N     N   CCCCCC  EEEEEEE  !!    X",
            "X     B     B    A A    L         A A    NN    N  C        E        !!    X",
            "X     B     B   A   A   L        A   A   N N   N  C        E        !!    X",
            "X     BBBBBB   AAAAAAA  L       AAAAAAA  N  N  N  C        EEEEE    !!    X",
            "X     B     B  A     A  L       A     A  N   N N  C        E        !!    X",
            "X     B     B  A     A  L       A     A  N    NN  C        E              X",
            "X     BBBBBB   A     A  LLLLLLL A     A  N     N   CCCCCC  EEEEEEE  !!    X",
            "X~*~                                                                   ~*~X",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
        };
    }

    public Marquee (String color) {
        super(0, 0, Direction.LEFT, color);
    }
}