package com.codecool.termlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validation {

    /**
     * Initial validation - if it is a command in general.
     *
     * Each command has it's own validation depending on parameters and details.
     *
     * @param userInput String typed by user.
     * @return string typed by user if valid or "help required" to provide info
     */

    public static String userInputValidation(String userInput) {
        List<String> userInputList = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        String[] mainCommands = {"bgcolor", "fgcolor", "attribute", "move", "movecursor", "clear", "help"};
        for (String mainCommand : mainCommands) {
            if (userInputList.get(0).toLowerCase().equals(mainCommand)) {
                return userInput;
            }
        }
        return "help required";
    }

    /**
     * bgcolor command validation.
     */

    public static String validateCommandBgcolor(List<String> userInputList) {
        String bgc = userInputList.get(1).toUpperCase();
        switch(bgc) {
            case "RED":
                return "bgcolor RED";
            case "GREEN":
                return "bgcolor GREEN";
            case "YELLOW":
                return "bgcolor YELLOW";
            case "BLUE":
                return "bgcolor BLUE";
            case "MAGENTA":
                return "bgcolor MAGENTA";
            case "CYAN":
                return "bgcolor CYAN";
            case "BLACK":
                return "bgcolor BLACK";
            case "WHITE":
                return "bgcolor WHITE";
            case "HELP":
                System.out.println("** Set a background color by typing: bgcolor <color>");
                System.out.println("** Example: bgcolor red");
                System.out.println("** Choose from: red, green, blue, yellow, cyan, magenta, black, white");
                break;
        }
        return "bgcolor INVALID";
    }

    /**
     * attribute command validation.
     *
     */

    public static String validateCommandAttribute(List<String> userInputList) {
        String attrib = userInputList.get(1).toUpperCase();
        switch (attrib) {
            case "BRIGHT":
                return "attribute BRIGHT";
            case "DIM":
                return "attribute DIM";
            case "UNDERSCORE":
                return "attribute UNDERSCORE";
            case "BLINK":
                return "attribute BLINK";
            case "REVERSE":
                return "attribute REVERSE";
            case "HIDDEN":
                return "attribute HIDDEN";
            case "RESET":
                return "attribute RESET";
            case "HELP":
                return "attribute HELP";
        }
        return "attribute INVALID";
    }




}