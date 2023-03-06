package com.fullboar.examples.sprites;

import com.fullboar.examples.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends Sprite {
    private boolean isOutOfBounds;

    private String name;
    Player opponent;

    @Override
    public String[] getImage() {
        return new String[] {
            "@@@"
        };
    }

    public Player (Direction facing, String color) {
        super (0, 0, facing, color);
    } 
}
