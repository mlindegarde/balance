package com.fullboar.examples.utilities;

import com.fullboar.examples.subsystems.rendering.Color;

public class StringUtils {
    public static String highlight(String input) {
        return highlight(input, Color.CYAN);
    }

    public static String highlight(String input, String color) {
        return Color.RESET + input.replaceAll("\\*(?<keyword>[^*]+)\\*", color + "$1" + Color.RESET);
    }

    public static String color(String input, String color) {
        return color + input + Color.RESET;
    }
}
