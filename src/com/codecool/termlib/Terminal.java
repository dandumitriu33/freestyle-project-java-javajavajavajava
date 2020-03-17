package com.codecool.termlib;

import java.util.*;

import com.codecool.termlib.Color;

public class Terminal {

    public static void main(String[] args) {
        String x ="";
        Scanner sc = new Scanner(System.in);

        while (!x.equals("quit")) {
            System.out.println(" ---- TermLib ----");
            System.out.print("Enter command: ");
            x = sc.nextLine();
            List<String> myList = new ArrayList<String>(Arrays.asList(x.split(" ")));

            myList.set(0, myList.get(0).toLowerCase());
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
                    case "BRIGHT":
                        brighten();
                        break;
                    case "DIM":
                        dim();
                        break;
                    case "UNDERSCORE":
                        setUnderline();
                        break;
                    case "BLINK":
                        blink();
                        break;
                    case "REVERSE":
                        reverse();
                        break;
                    case "HIDDEN":
                        hide();
                        break;
                    case "RESET":
                        resetStyle();
                        break;
                }
            }
            else if (myList.get(0).equals("move")) {
                String dir = myList.get(1).toUpperCase();
                int amount;
                switch(dir) {
                    case "UP":
                        amount = Integer.parseInt(myList.get(2));
                        moveCursor(Direction.UP, amount);
                        break;
                    case "DOWN":
                        amount = Integer.parseInt(myList.get(2));
                        moveCursor(Direction.DOWN, amount);
                        break;
                    case "FORWARD":
                        amount = Integer.parseInt(myList.get(2));
                        moveCursor(Direction.FORWARD, amount);
                        break;
                    case "BACKWARD":
                        amount = Integer.parseInt(myList.get(2));
                        moveCursor(Direction.BACKWARD, amount);
                        break;
                }

            }
            else if (myList.get(0).equals("clr")) {
                System.out.println("clearing screen");
                clearScreen();
            } else if (myList.get(0).equals("fgcolor")){

                Color textColor;
                String attribute = myList.get(1).toUpperCase();

                switch (attribute){

                    case "BLACK":
                        textColor = Color.BLACK;
                        setColor(textColor);
                        break;
                    case "RED":
                        textColor = Color.RED;
                        setColor(textColor);
                        break;
                    case "GREEN":
                        textColor = Color.GREEN;
                        setColor(textColor);
                        break;
                    case "YELLOW":
                        textColor = Color.YELLOW;
                        setColor(textColor);
                        break;
                    case "BLUE":
                        textColor = Color.BLUE;
                        setColor(textColor);
                        break;
                    case "MAGENTA":
                        textColor = Color.MAGENTA;
                        setColor(textColor);
                        break;
                    case "CYAN":
                        textColor = Color.CYAN;
                        setColor(textColor);
                        break;
                    case "WHITE":
                        textColor = Color.WHITE;
                        setColor(textColor);
                        break;
                    case "HELP":
                        System.out.println("This command changes the foreground color of the text.");
                        System.out.println("Example Usage: fgcolor RED -> changes the text color to red.");
                        System.out.println("Supported colors are: BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE");
                    default:
                        System.out.println(String.format("ERROR:The attribute %s is not a valid parameter for fgcolor function. Try fgcolor HELP for a detailed usage guide.", attribute));

                }


            }
            else if (myList.get(0).equals("quit")){
                clearScreen();
                System.out.println("Goodbye...");
            }
            else{
                System.out.println("ERROR: Command unrecognized");
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
    public static void resetStyle() {

        System.out.print(CONTROL_CODE + 0 + STYLE);
        clearScreen();
    }

    /**
     * Clear the whole screen.
     *
     * Might reset cursor position.
     */
    public static void clearScreen() {
//        System.out.println("\033[H\033[2J");
        System.out.print(CONTROL_CODE+CLEAR+CONTROL_CODE+MOVE);
//        System.out.println(CLEAR);
//        System.out.println(CONTROL_CODE);
//        System.out.println(MOVE);

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
    public static void setColor(Color color) {
        Map<String,String> colorCodesForeground = Map.ofEntries(
                new AbstractMap.SimpleEntry<String,String>("BLACK", "30"+STYLE),
                new AbstractMap.SimpleEntry<String,String>("RED", "31"+STYLE),
                new AbstractMap.SimpleEntry<String,String>("GREEN", "32"+STYLE),
                new AbstractMap.SimpleEntry<String,String>("YELLOW", "33"+STYLE),
                new AbstractMap.SimpleEntry<String,String>("BLUE", "34"+STYLE),
                new AbstractMap.SimpleEntry<String,String>("MAGENTA", "35"+STYLE),
                new AbstractMap.SimpleEntry<String,String>("CYAN", "36"+STYLE),
                new AbstractMap.SimpleEntry<String,String>("WHITE", "37"+STYLE)
        );
        switch (color) {
            case BLACK:
                System.out.print(CONTROL_CODE+colorCodesForeground.get("BLACK"));
                break;
            case RED:
                System.out.print(CONTROL_CODE+colorCodesForeground.get("RED"));
                break;
            case GREEN:
                System.out.print(CONTROL_CODE+colorCodesForeground.get("GREEN"));
                break;
            case YELLOW:
                System.out.print(CONTROL_CODE+colorCodesForeground.get("YELLOW"));
                break;
            case BLUE:
                System.out.print(CONTROL_CODE+colorCodesForeground.get("BLUE"));
                break;
            case MAGENTA:
                System.out.print(CONTROL_CODE+colorCodesForeground.get("MAGENTA"));
                break;
            case CYAN:
                System.out.print(CONTROL_CODE+colorCodesForeground.get("CYAN"));
                break;
            case WHITE:
                System.out.print(CONTROL_CODE+colorCodesForeground.get("WHITE"));
                break;


        }
        clearScreen();

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
        clearScreen();
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
     * Brightens the text. (Bold text in text editors and a lighter white.)
     */
    public static void brighten() {
        System.out.println("\033[1m");
    }

    /**
     * Dims the text. (Turns the text a darker shade of gray.)
     */
    public static void dim() {
        System.out.println("\033[2m");
    }

    /**
     * Hides the text.
     */
    public static void hide() {
        System.out.println("\033[8m");
    }

    /**
     * Creates a blinking effect.
     */
    public static void blink() {
        System.out.print(CONTROL_CODE+"5"+STYLE);
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
    public static void moveCursor(Direction direction, Integer amount) {
        switch(direction) {
            case UP:
                System.out.print("\033[" + amount + "A");
                break;
            case DOWN:
                System.out.print("\033[" + amount + "B");
                break;
            case FORWARD:
                System.out.print("\033[" + amount + "C");
                break;
            case BACKWARD:
                System.out.print("\033[" + amount + "D");
                break;
        }

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
