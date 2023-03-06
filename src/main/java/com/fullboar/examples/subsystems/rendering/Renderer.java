package com.fullboar.examples.subsystems.rendering;

import com.fullboar.examples.sprites.Player;
import com.fullboar.examples.sprites.Sprite;
import com.fullboar.examples.utilities.StringUtils;

public class Renderer {
    private Pixel[][] buffer;
    private int screenWidth;
    private int screenHeight;

    public Renderer (int screenWidth, int screenHeight) {
        buffer = new Pixel[screenHeight][screenWidth];
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void display(int x, int y, Pixel pixel) {
        buffer[y][x] = pixel;
    }

    public void display(Sprite sprite) {
        display (sprite.getX(), sprite.getY(), sprite);
    }

    public void display(int x, int y, Sprite sprite) {
        String[] image = sprite.getImage();
        String color = sprite.getColor();

        int height = image.length;
        int width = image[0].length();

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++){
                char value = image[r].charAt(c);

                int yPos = y+r;
                int xPos = x+c;

                if (!(shouldClip(xPos, yPos) || shouldSkipPixel(sprite.isTransparent(), value))) {
                    buffer[yPos][xPos] = new Pixel(color, image[r].charAt(c));
                }
            }
        }
    }

    public void display(int x, int y, String message, String color, Layout layout) {
        int posX = x;
        int posY = y;

        int dX = (layout == Layout.LEFT_TO_RIGHT)? 1 : 0;
        int dY = (layout == Layout.TOP_TO_BOTTOM)? 1 : 0;

        for (int i = 0; i < message.length(); i++) {
            if (posY < screenHeight && posX < screenWidth) {
                buffer[posY][posX] = new Pixel(color, message.charAt(i));
            }

            posX += dX;
            posY += dY;
        }
    }

    public void display(int x, int y, String message) {
        display(x, y, message, Color.WHITE, Layout.LEFT_TO_RIGHT);
    }

    public void displaySceen() {
        for (int i = 0; i < buffer.length; i++) {
            renderLine(buffer[i]);
        }
    }

    public void displayPlayerName (Player player) {
        System.out.print(StringUtils.color(player.getName() + "!", player.getColor()));
    }

    public void clearSceen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int r = 0; r < buffer.length; r++) {
            for (int c = 0; c < buffer[r].length; c++) {
                buffer[r][c] = null;
            }
        } 
    }

    private boolean shouldClip(int x, int y) {
        if (x < 0 || y < 0) {
            return true;
        }

        if (x >= screenWidth || y >= screenHeight) {
            return true;
        }

        return false;
    }

    private boolean shouldSkipPixel(boolean isTransparent, char value) {
        return value == ' ' && isTransparent;
    }

    private void renderLine(Pixel[] pixels) {
        StringBuilder sb = new StringBuilder();

        for (Pixel pixel : pixels) {
            if (pixel != null) {
                sb.append(pixel.getColor());
                sb.append(pixel.getValue());
            } else {
                // sb.append(Color.BACKGROUND_BLUE);
                sb.append(" ");
            }
        }

        System.out.println(sb + Color.RESET);
    }
}
