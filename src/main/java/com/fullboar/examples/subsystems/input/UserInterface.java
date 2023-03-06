package com.fullboar.examples.subsystems.input;

import java.util.Arrays;

import com.fullboar.examples.Direction;
import com.fullboar.examples.sprites.Marquee;
import com.fullboar.examples.sprites.Platform;
import com.fullboar.examples.sprites.Player;
import com.fullboar.examples.subsystems.logic.GameLogic;
import com.fullboar.examples.subsystems.rendering.BoxDrawing;
import com.fullboar.examples.subsystems.rendering.Color;
import com.fullboar.examples.subsystems.rendering.Layout;
import com.fullboar.examples.subsystems.rendering.Renderer;
import com.fullboar.examples.utilities.StringUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserInterface {
    private final Renderer renderer;
    private final Keyboard keyboard;
    private final GameLogic logic;

    public String displayRulesAndConfirmNewGame(Marquee marquee, Marquee marqueeShadow) {
        renderer.clearSceen();
        renderer.display(marqueeShadow);
        renderer.display(marquee);

        renderer.display(25, 16, "Welcome to BALANCE - a sparring game of physical and mental strategy.");
        renderer.display(18, 17, "The goal is to knock your opponent off their grid while keeping yourself on your grid.");
        
        renderer.display(19, 21, "Players take turns, beginning with player 1. Each turn consists of three actions:");
        renderer.display(19, 23, "1) Your BREATH action allows you to move one space toward YIN (down) or YANG (up).");
        renderer.display(19, 24, "2) Your POSITION action allows you to move one space FORWARD (toward opponent) or ");
        renderer.display(22, 25, "BACK (away from opponent).");
        renderer.display(19, 26, "3) Finally, you choose an ACTION that will affect your opponent and - to a");
        renderer.display(22, 27, "lesser extent - yourself.");

        renderer.display(36, 16, "BALANCE", Color.CYAN, Layout.LEFT_TO_RIGHT);
        renderer.display(27, 23, "BREATH", Color.CYAN, Layout.LEFT_TO_RIGHT);
        renderer.display(27, 24, "POSITION", Color.CYAN, Layout.LEFT_TO_RIGHT);
        renderer.display(45, 26, "ACTION", Color.CYAN, Layout.LEFT_TO_RIGHT);

        renderer.displaySceen();

        displaySeparator();
        return keyboard.getUserInput("Start *NEW GAME* (Y/N)", Arrays.asList("Y", "N"));
    }

    public String getForwardBackward(Player player) {
        displaySeparator();
        return keyboard.getUserInput(player, "CHOOSE YOUR *POSITION* MOVE (FORWARD/BACK)", Arrays.asList("FORWARD", "BACK"));
    }
    
    public String getYinYang(Player player) {
        displaySeparator();
        return keyboard.getUserInput(player, "CHOOSE YOUR *BREATH* MOVE (YIN/YANG)", Arrays.asList("YIN", "YANG"));
    }

    public String getPushPull(Player player) {
        System.out.println(BoxDrawing.DOUBLE_HORIZONTAL.repeat(120));
        renderer.displayPlayerName(player);
        System.out.println(StringUtils.highlight(" CHOOSE YOUR ACTION:\n"));
        System.out.println(StringUtils.highlight("*PUSH* = You forward 1, yang 1. Opponent back 2, yin 1"));
        System.out.println(StringUtils.highlight("*PULL* = You back 1, yin 1. Opponent forward 2, yang 1"));

        return keyboard.getUserInput("YOUR CHOICE (*PUSH*/*PULL*)", Arrays.asList("PUSH", "PULL"));
    }

    private void displaySeparator() {
        System.out.println(BoxDrawing.DOUBLE_HORIZONTAL.repeat(renderer.getScreenWidth()));
    }

    private void displayPlatform(Platform platform) {
        final int x = platform.getX()-2;
        final int y = platform.getY()-1;

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
}
