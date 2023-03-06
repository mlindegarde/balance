package com.fullboar.examples.subsystems.input;

import java.util.Arrays;

import com.fullboar.examples.sprites.Marquee;
import com.fullboar.examples.sprites.Platform;
import com.fullboar.examples.sprites.Player;
import com.fullboar.examples.subsystems.logic.GameLogic;
import com.fullboar.examples.subsystems.rendering.Color;
import com.fullboar.examples.subsystems.rendering.Direction;
import com.fullboar.examples.subsystems.rendering.Layout;
import com.fullboar.examples.subsystems.rendering.Renderer;
import com.fullboar.examples.utilities.StringUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserInterface {
    private final Renderer renderer;
    private final Keyboard keyboard;
    private final GameLogic logic;

    public String displayRulesAndConfirmNewGame(Marquee marquee) {
        renderer.clearSceen();
        renderer.display(22, 3, marquee);

        renderer.display(26, 16, "Welcome to BALANCE - a sparring game of physical and mental strategy.");
        renderer.display(18, 28, "The goal is to knock your opponent off their grid while keeping yourself on your grid.");
        
        renderer.display(14, 24, "Players take turns, beginning with player 1. Each turn consists of three actions:");
        renderer.display(10, 26, "1) Your BREATH action allows you to move one space toward YIN (down) or YANG (up).");
        renderer.display(10, 27, "2) Your POSITION action allows you to move one space FORWARD (toward opponent) or BACK (away from opponent).");
        renderer.display(10, 28, "3) Finally, you choose an ACTION that will affect your opponent and - to a lesser extent - yourself.");

        renderer.display(37, 18, "BALANCE", Color.CYAN, Layout.LEFT_TO_RIGHT);
        renderer.display(18, 26, "BREATH", Color.CYAN, Layout.LEFT_TO_RIGHT);
        renderer.display(18, 27, "POSITION", Color.CYAN, Layout.LEFT_TO_RIGHT);
        renderer.display(36, 28, "ACTION", Color.CYAN, Layout.LEFT_TO_RIGHT);

        renderer.displaySceen();

        return keyboard.getUserInput("Start *NEW GAME* (Y/N)", Arrays.asList("Y", "N"));
    }

    public String getForwardBackward(Player player) {
        renderer.displayPlayerName(player);

        return keyboard.getUserInput("CHOOSE YOUR *POSITION* MOVE (FORWARD/BACK)", Arrays.asList("FORWARD", "BACK"));
    }
    
    public String getYinYang(Player player) {
        renderer.displayPlayerName(player);

        return keyboard.getUserInput("CHOOSE YOUR *BREATH* MOVE (YIN/YANG)", Arrays.asList("YIN", "YANG"));
    }

    public String getPushPull(Player player) {
        renderer.displayPlayerName(player);
        System.out.println(StringUtils.highlight("CHOOSE YOUR ACTION:\n"));
        System.out.println(StringUtils.highlight("*PUSH* = You forward 1, yang 1. Opponent back 2, yin 1"));
        System.out.println(StringUtils.highlight("*PULL* = You back 1, yin 1. Opponent forward 2, yang 1"));

        return keyboard.getUserInput("YOUR CHOICE (*PUSH*/*PULL*)", Arrays.asList("PUSH", "PULL"));
    }

    public void displayGameState (Player player1, Platform platform1, Player player2, Platform platform2) {
        renderer.clearSceen();

        displayPlatform(platform1);

        if (!logic.isPlayerOutOfBounds(player1, platform1)) {
            renderer.display(player1);
        }

        displayPlatform(platform2);

        if (!logic.isPlayerOutOfBounds(player2, platform2)) {
            renderer.display(player2);
        }

        renderer.displaySceen();
    }

    private void displayPlatform(Platform platform) {
        int x = platform.getX()-2;
        int y = platform.getY()-1;

        renderer.display(
            (platform.getFacing() == Direction.RIGHT)? x : x+54, 
            y+9, 
            "BACK", 
            Color.WHITE, 
            Layout.TOP_TO_BOTTOM);

        renderer.display(
            (platform.getFacing() == Direction.RIGHT)? x+54 : x, 
            y+8, 
            "FORWARD", 
            Color.WHITE, 
            Layout.TOP_TO_BOTTOM);

        renderer.display(x+27, y, "YANG", Color.WHITE, Layout.LEFT_TO_RIGHT);
        renderer.display(x+26, y+22, "YIN", Color.WHITE, Layout.LEFT_TO_RIGHT);
        
        renderer.display(platform);
    }
}
