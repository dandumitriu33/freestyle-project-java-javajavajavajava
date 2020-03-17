package com.codecool.termlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validation {

//    public static void main(String[] args) {
//
//    }

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







}