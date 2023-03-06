package com.fullboar.examples;

import com.fullboar.examples.sprites.Marquee;
import com.fullboar.examples.sprites.Platform;
import com.fullboar.examples.sprites.Player;
import com.fullboar.examples.subsystems.input.Keyboard;
import com.fullboar.examples.subsystems.input.UserInterface;
import com.fullboar.examples.subsystems.logic.GameLogic;
import com.fullboar.examples.subsystems.rendering.Color;
import com.fullboar.examples.subsystems.rendering.Renderer;
import com.fullboar.examples.utilities.StringUtils;

public class Game 
{
    private Renderer renderer = new Renderer(120, 40);
    private Keyboard keyboard = new Keyboard();
    private GameLogic logic = new GameLogic();
    private UserInterface ui = new UserInterface(renderer, keyboard, logic);

    private Marquee marquee = new Marquee(Color.MAGENTA);
    private Player player1 = new Player(Direction.RIGHT, Color.YELLOW);
    private Player player2 = new Player(Direction.LEFT, Color.GREEN);
    private Platform platform1 = new Platform(4, 7, player1.getFacing(), player1.getColor());
    private Platform platform2 = new Platform(63, 7, player2.getFacing(), player2.getColor());

    private GameState gameState = GameState.INTRO;
    private int turnCount = 0;

    private void intro() throws InterruptedException {
        marquee.setPosition(22, -11);

        while (marquee.getY() < 3) {
            renderer.clearSceen();
            renderer.display(marquee);
            renderer.displaySceen();

            marquee.moveDown(1);
            Thread.sleep(100);
        }

        gameState = GameState.RULES;
    }

    private void rules() {
        String input = ui.displayRulesAndConfirmNewGame(marquee);

        gameState = input.equalsIgnoreCase("Y")? GameState.SET_UP : GameState.EXITING;
    }

    private void setUp () {
        player1.setName(keyboard.getUserInput("PLAYER 1 NAME", player1.getColor()));
        player1.setOpponent(player2);
        player1.setPosition(platform1.getX()+24, platform1.getY()+10);

        player2.setName(keyboard.getUserInput("PLAYER 2 NAME", player2.getColor()));
        player2.setOpponent(player1);
        player2.setPosition(platform2.getX()+24, platform2.getY()+10);

        gameState = GameState.PLAYING_GAME;
    }

    private void playGame () {
        Player activePlayer = player1;
        Player inactivePlayer = player2;

        while (gameState != GameState.GAME_OVER) {
            TurnState turnState = TurnState.YIN_YANG;

            turnCount++;

            while (turnState != TurnState.OVER && gameState != GameState.GAME_OVER) {
                ui.displayGameState(player1, platform1, player2, platform2);

                switch (turnState) {
                    case YIN_YANG:
                        logic.updateYinYang(
                            activePlayer,
                            ui.getYinYang(activePlayer));
                        turnState = TurnState.FORWARD_BACKWARD;
                        break;

                    case FORWARD_BACKWARD:
                        logic.updateForwardBackward(
                            activePlayer, 
                            ui.getForwardBackward(activePlayer));
                        turnState = TurnState.PUSH_PULL;
                        break;

                    case PUSH_PULL:
                        logic.updatePushPull(
                            activePlayer, inactivePlayer,
                            ui.getPushPull(activePlayer));
                        turnState = TurnState.OVER;
                        break;

                    case OVER:
                        break;
                }

                if (logic.isGamOver(player1, platform1, player2, platform2)) {
                    gameState = GameState.GAME_OVER;
                }
            }

            activePlayer = activePlayer.getOpponent();
            inactivePlayer = inactivePlayer.getOpponent();
        }
    }

    private void gameOver () {
        renderer.clearSceen();

        ui.displayGameState(player1, platform1, player2, platform2);

        System.out.println(StringUtils.color("GAME OVER", Color.RED));

        if (player1.isOutOfBounds() && player2.isOutOfBounds()) {
            System.out.print(StringUtils.color("NO ONE", Color.YELLOW));
        } else if (player2.isOutOfBounds()) {
            renderer.displayPlayerName(player1);
        } else {
            renderer.displayPlayerName(player2);
        }

        System.out.println(" WINS");
        System.out.println(turnCount + " turns played");

        gameState = GameState.EXITING;
    }

    private void run() throws InterruptedException {
        while (gameState != GameState.EXITING) {
            switch (gameState) {
                case INTRO: 
                    intro(); 
                    break;

                case RULES: 
                    rules();
                    break;

                case SET_UP: 
                    setUp(); 
                    break;

                case PLAYING_GAME: 
                    playGame(); 
                    break;

                case GAME_OVER: 
                    gameOver(); 
                    break;

                case EXITING:
                    keyboard.close();
                    break;
            }
        }
    }

    public static void main( String[] args )
    {
        try {
            new Game().run();
        }
        catch (InterruptedException ex) {
            System.out.println(StringUtils.color("Something went very wrong", Color.RED));
        }
    }
}
