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
        String[] mainCommands = {"bgcolor", "fgcolor", "attribute", "move", "movecursor", "clear", "glyph", "quit", "help", "debug_read","history", "clock"};
        if (userInputList.get(0).equals("quit") && userInputList.size()>1) {
            return "help required";
        }
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
     * fgcolor command validation
     */
    public static String validateCommandFgcolor(List<String> userInputList) {
        if (userInputList.size() != 2) {
            return "fgcolor INVALID";
        }
        String fgc = userInputList.get(1).toUpperCase();
        switch (fgc) {
            case "BLACK":
                return "fgcolor BLACK";
            case "RED":
                return "fgcolor RED";
            case "GREEN":
                return "fgcolor GREEN";
            case "YELLOW":
                return "fgcolor YELLOW";
            case "BLUE":
                return "fgcolor BLUE";
            case "MAGENTA":
                return "fgcolor MAGENTA";
            case "CYAN":
                return "fgcolor CYAN";
            case "WHITE":
                return "fgcolor WHITE";
            case "HELP":
                return "fgcolor HELP";
        }
        return "fgcolor INVALID";
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

    /**
     * move command validation
     * @param userInput
     */
    public static String validateCommandMove(String userInput) {
        List<String> userInputList = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        if (userInputList.size()>3) return "move INVALID";
        if (userInputList.get(1).toUpperCase().equals("UP") && validatePositiveInteger(userInputList.get(2))) return userInput;
        else if (userInputList.get(1).toUpperCase().equals("DOWN") && validatePositiveInteger(userInputList.get(2))) return userInput;
        else if (userInputList.get(1).toUpperCase().equals("FORWARD") && validatePositiveInteger(userInputList.get(2))) return userInput;
        else if (userInputList.get(1).toUpperCase().equals("BACKWARD") && validatePositiveInteger(userInputList.get(2))) return userInput;
        else if (userInputList.get(1).toUpperCase().equals("HELP")) return "move HELP";
        else return "move INVALID";
    }

    /**
     * movecursor command validation
     */
    public static String validateCommandMovecursor(String userInput) {
        List<String> userInputList = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        if (userInputList.size() > 3 || userInputList.size() < 2) return "movecursor INVALID";
        else if (userInputList.size() == 2 && userInputList.get(1).toLowerCase().equals("help"))
            return "movecursor HELP";
        else if (userInputList.size() == 3 &&
                validatePositiveInteger(userInputList.get(1)) &&
                validatePositiveInteger(userInputList.get(2))) {
            return userInputList.get(0).toLowerCase() + " " + userInputList.get(1) + " " + userInputList.get(2);
        } else {
            return "movecursor INVALID";
        }
    }

    /**
     * clear command validation
     */
    public static String validateCommandClear(String userInput) {
        List<String> userInputList = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        if (userInputList.size() == 2 && userInputList.get(1).toLowerCase().equals("help")) return "clear HELP";
        else if (userInputList.size() == 1) return "clear";
        else {
            return "clear INVALID";
        }
    }



    /**
     * validates a positive integer (coming in as a string)
     */
    public static boolean validatePositiveInteger(String number) {
        try {
            int temp = Integer.parseInt(number);
            return temp > 0;
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * glyph command validation
     */
    public static String validateCommandGlyph (String userInput) {
        List<String> userInputList = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        if  (userInputList.size()==2 && userInputList.get(1).toLowerCase().equals("help")) {
            return "glyph HELP";
        }
        else if (userInputList.size() != 4) return "glyph INVALID";
        if (validatePositiveInteger(userInputList.get(2)) && validatePositiveInteger(userInputList.get(3))) {
            return userInput;
        }
        else {
            return "glyph INVALID";
        }
    }

    /**
     * clock command validation
     */
    public static String validateCommandClock (String userInput) {
        List<String> userInputList = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        if (userInputList.size() == 1 && userInputList.get(0).toLowerCase().equals("clock")) {
//            userInputList.add(1, "default");
//            String parameter = userInputList.get(1);
//            commandHistory.add(userInputList.get(0));
//            clock(parameter);
            return "clock DEFAULT";

        }
        else if(userInputList.size() >= 2 && userInputList.get(0).toLowerCase().equals("clock")) {
            String[] possibleArgs = new String[] {"NYC", "HKG", "LON", "HELP"};
            String parameter = userInputList.get(1).toUpperCase();
            if (!Arrays.stream(possibleArgs).anyMatch(parameter::equals))
            {
               return "clock DEFAULT";
            }
            else
            {
                return userInputList.get(0).toLowerCase() + " " + userInputList.get(1).toUpperCase();
            }
        }
        return "clock DEFAULT";

    }
    public static String validateCommandHistory (String userInput, List commandHistory) {
        List<String> userInputList = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
        if (userInputList.size() > 1 && userInputList.get(0).toLowerCase().equals("history")) {
            String helper = userInputList.get(1);
            helper = helper.toUpperCase();
            if (helper.equals("HELP")) {
                return "history HELP";
            }
            else {
                return "history ERROR_COMMAND";

            }
        } else {
            if (commandHistory.size() == 0) {
                return "history ERROR_NO_HISTORY";
            } else {
                return "history SUCCESS";
            }

        }

    }

}