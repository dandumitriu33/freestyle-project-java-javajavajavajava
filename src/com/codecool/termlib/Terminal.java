package com.codecool.termlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.codecool.termlib.Color;

public class Terminal {

    public static void main(String[] args) {
        String x ="";
        Scanner sc = new Scanner(System.in);

        while (!x.equals("quit")) {
            System.out.print("enter command: ");
            x = sc.nextLine();
            List<String> myList = new ArrayList<String>(Arrays.asList(x.split(" ")));

            if (myList.get(0).equals("bgcolor")) {
                String bgc = myList.get(1).toUpperCase();
                Color myColor;
                switch(bgc) {
                    case "RED":
                        myColor = Color.RED;
                        setBgColor(myColor);
                        break;
                    case "GREEN":
                        myColor = Color.GREEN;
                        setBgColor(myColor);
                        break;
                    case "YELLOW":
                        myColor = Color.YELLOW;
                        setBgColor(myColor);
                        break;
                    case "BLUE":
                        myColor = Color.BLUE;
                        setBgColor(myColor);
                        break;
                    case "MAGENTA":
                        myColor = Color.MAGENTA;
                        setBgColor(myColor);
                        break;
                    case "CYAN":
                        myColor = Color.CYAN;
                        setBgColor(myColor);
                        break;
                    case "BLACK":
                        myColor = Color.BLACK;
                        setBgColor(myColor);
                        break;
                    case "WHITE":
                        myColor = Color.WHITE;
                        setBgColor(myColor);
                        break;
                }

            }
            else if (myList.get(0).equals("attribute")) {
                String attrib = myList.get(1).toUpperCase();
                Attribute myAttrib;
                switch (attrib) {
//                    case "BRIGHT":
//                        myAttrib = Attribute.BRIGHT;
//                        setBgColor(myColor);
//                        break;
//                    case "DIM":
//                        myColor = Color.GREEN;
//                        setBgColor(myColor);
//                        break;
                    case "UNDERSCORE":
//                        myAttrib = Attribute.UNDERSCORE;
                        setUnderline();
                        break;
//                    case "BLINK":
//                        myColor = Color.BLUE;
//                        setBgColor(myColor);
//                        break;
                    case "REVERSE":
//                        myColor = Color.MAGENTA;
                        reverse();
                        break;
//                    case "HIDDEN":
//                        myColor = Color.CYAN;
//                        setBgColor(myColor);
//                        break;
//                    case "RESET":
//                        myColor = Color.BLACK;
//                        setBgColor(myColor);
//                        break;
                }
            }
            else if (myList.get(0).equals("clr")) {
                System.out.println("clearing screen");
                clearScreen();
            }
            else {
                System.out.println("you entered: " + x);
            }


            }

    }

    /**
     * The beginning of control sequences.
     */
    // HINT: In \033 the '0' means it's an octal number. And 33 in octal equals 0x1B in hexadecimal.
    // Now you have some info to decode that page where the control codes are explained ;)
    private static final String CONTROL_CODE = "\033[";
    /**
     * Command for whole screen clearing.
     *
     * Might be partitioned if needed.
     */
    private static final String CLEAR = "2J";
    /**
     * Command for moving the cursor.
     */
    private static final String MOVE = "H";
    /**
     * Command for printing style settings.
     *
     * Handles foreground color, background color, and any other
     * styles, for example color brightness, or underlines.
     */
    private static final String STYLE = "m";

    /**
     * Reset printing rules in effect to terminal defaults.
     *
     * Reset the color, background color, and any other style
     * (i.e.: underlined, dim, bright) to the terminal defaults.
     */
    public void resetStyle() {
    }

    /**
     * Clear the whole screen.
     *
     * Might reset cursor position.
     */
    public static void clearScreen() {
//        System.out.println("\033[H\033[2J");
        System.out.println(CONTROL_CODE);
        System.out.println(CLEAR);
        System.out.println(CONTROL_CODE);
        System.out.println(MOVE);

    }

    /**
     * Move cursor to the given position.
     *
     * Positions are counted from one.  Cursor position 1,1 is at
     * the top left corner of the screen.
     *
     * @param x Column number.
     * @param y Row number.
     */
    public void moveTo(Integer x, Integer y) {
    }

    /**
     * Set the foreground printing color.
     *
     * Already printed text is not affected.
     *
     * @param color The color to set.
     */
    public void setColor(Color color) {
    }

    /**
     * Set the background printing color.
     *
     * Already printed text is not affected.
     *
     * @param color The background color to set.
     */
    public static void setBgColor(Color color) {
        switch(color) {
            case RED:
                System.out.print("\033[41m");
                break;
            case GREEN:
                System.out.print("\033[42m");
                break;
            case YELLOW:
                System.out.print("\033[43m");
                break;
            case BLUE:
                System.out.print("\033[44m");
                break;
            case MAGENTA:
                System.out.print("\033[45m");
                break;
            case CYAN:
                System.out.print("\033[46m");
                break;
            case BLACK:
                System.out.print("\033[40m");
                break;
            case WHITE:
                System.out.print("\033[47m");
                break;
        }
    }

    /**
     * Make printed text underlined.
     *
     * On some terminals this might produce slanted text instead of
     * underlined.  Cannot be turned off without turning off colors as
     * well.
     */
    public static void setUnderline() {
        System.out.println("\033[4m");
    }

    /**
     * Reverses the default text color and background color.
     */
    public static void reverse() {
        System.out.println("\033[7m");
    }


    /**
     * Move the cursor relatively.
     *
     * Move the cursor amount from its current position in the given
     * direction.
     *
     * @param direction Step the cursor in this direction.
     * @param amount Step the cursor this many times.
     */
    public void moveCursor(Direction direction, Integer amount) {
    }

    /**
     * Set the character diplayed under the current cursor position.
     *
     * The actual cursor position after calling this method is the
     * same as beforehand.  This method is useful for drawing shapes
     * (for example frame borders) with cursor movement.
     *
     * @param c the literal character to set for the current cursor
     * position.
     */
    public void setChar(char c) {
    }

    /**
     * Helper function for sending commands to the terminal.
     *
     * The common parts of different commands shall be assembled here.
     * The actual printing shall be handled from this command.
     *
     * @param commandString The unique part of a command sequence.
     */
    private void command(String commandString) {
    }
}
