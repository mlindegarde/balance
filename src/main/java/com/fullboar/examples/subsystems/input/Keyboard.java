package com.fullboar.examples.subsystems.input;

import java.util.List;
import java.util.Scanner;

import com.fullboar.examples.subsystems.rendering.Color;
import com.fullboar.examples.utilities.StringUtils;

public class Keyboard {
    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput(String prompt, List<String> options) {
        boolean isInputValid = false;
        String input;

        do {
            System.out.print(StringUtils.highlight(prompt) + ": ");
            input = scanner.nextLine();

            isInputValid = options.stream().anyMatch(input::equalsIgnoreCase);

            if (!isInputValid) {
                System.out.println(StringUtils.color("It's a simple question. Try again.", Color.RED));
            }
        } while (!isInputValid);

        return input;
    }

    public String getUserInput(String prompt, String color) {
        System.out.print(StringUtils.color(prompt, color) + ": ");
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}