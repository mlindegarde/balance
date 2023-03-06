package com.fullboar.examples.subsystems.logic;

import com.fullboar.examples.sprites.Platform;
import com.fullboar.examples.sprites.Player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameLogic {
    public void updateForwardBackward(Player player, String input) {
        if (input.equalsIgnoreCase("FORWARD")) {
            player.moveForward(Platform.CELL_WIDTH);
        } else {
            player.moveBack(Platform.CELL_WIDTH);
        }
    }

    public void updateYinYang(Player player, String input) {
        if (input.equalsIgnoreCase("YIN")) {
            player.moveDown(Platform.CELL_HEIGHT);
        } else {
            player.moveUp(Platform.CELL_HEIGHT);
        }
    }

    public void updatePushPull(Player activePlayer, Player inactivePlayer, String input) {
        if (input.equalsIgnoreCase("PUSH")) {
            activePlayer.moveForward(Platform.CELL_WIDTH);
            activePlayer.moveUp(Platform.CELL_HEIGHT);
    
            inactivePlayer.moveBack(2 * Platform.CELL_WIDTH);
            inactivePlayer.moveDown(Platform.CELL_HEIGHT);
        } else {
            activePlayer.moveBack(Platform.CELL_WIDTH);
            activePlayer.moveDown(Platform.CELL_HEIGHT);
    
            inactivePlayer.moveForward(2 * Platform.CELL_WIDTH);
            inactivePlayer.moveUp(Platform.CELL_HEIGHT);
        }
    }

    public boolean isPlayerOutOfBounds(Player player, Platform platform) {
        return (player.getX() < platform.getX() ||
            player.getX() > platform.getX() + 50 ||
            player.getY() < platform.getY() ||
            player.getY() > platform.getY() + 20);
    }

    public boolean isGamOver(Player player1, Platform platform1, Player player2, Platform platform2) {
        player1.setOutOfBounds(isPlayerOutOfBounds(player1, platform1));
        player2.setOutOfBounds(isPlayerOutOfBounds(player2, platform2));

        return player1.isOutOfBounds() || player2.isOutOfBounds();
    }
}
