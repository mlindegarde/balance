package com.fullboar.examples.sprites;

import com.fullboar.examples.subsystems.rendering.Direction;

import lombok.Getter;

@Getter
public abstract class Sprite {
    protected boolean isTransparent = false;

    protected String color;
    protected String[] image;

    protected int x;
    protected int y;
    protected Direction facing;

    public Sprite (int x, int y, Direction facing, String color) {
        this.x = x;
        this.y = y;
        this.facing = facing;
        this.color = color;
    }

    public void setPosition (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveDown (int distance) {
        y += distance;
    }

    public void moveUp (int distance) {
        y -= distance;
    }

    public void moveBack (int distance) {
        int modifier = (facing == Direction.LEFT) ? 1 : -1;

        x += distance * modifier;
    }

    public void moveForward (int distance) {
        int modifier = (facing == Direction.LEFT) ? 1 : -1;

        x -= distance * modifier;
    }
}
