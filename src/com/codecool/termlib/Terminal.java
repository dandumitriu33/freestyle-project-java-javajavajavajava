package com.codecool.termlib;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.codecool.termlib.Validation;

import com.codecool.termlib.Color;

public class Terminal {

    public static void main(String[] args) {
        try {
            loadingScreen();
        } catch (InterruptedException e) {
            System.out.println("");
        }

        String userInput ="";
        String validatedUserInput = "";
        String commandString = "";
        Scanner sc = new Scanner(System.in);
        List <String> commandHistory = new ArrayList<String>();
        while (!userInput.equals("quit")) {
            System.out.print(">");
            userInput = sc.nextLine();
            List<String> userInputList = new ArrayList<String>(Arrays.asList(userInput.split(" ")));
            validatedUserInput = Validation.userInputValidation(userInput);
            if (validatedUserInput.equals("help required")) {
                System.out.println("Invalid command. Please type help for more info.");;
            }
            else if (userInputList.get(0).toLowerCase().equals("bgcolor")) {
                commandString = Validation.validateCommandBgcolor(userInputList);
                command(commandString);
                commandHistory.add(commandString);
            }
            else if (userInputList.get(0).equals("attribute")) {
                commandString = Validation.validateCommandAttribute(userInputList);
                command(commandString);
                commandHistory.add(commandString);
            }
            else if (userInputList.get(0).equals("move")) {
                commandString = Validation.validateCommandMove(userInput);
                command(commandString);
                commandHistory.add(commandString);
            }
            else if (userInputList.get(0).equals("clear")) {
                if (userInputList.size()>1 && userInputList.get(1).toUpperCase().equals("HELP")) {
                    System.out.println("** Clears the screen without resetting the preferences.");
                    System.out.println("** Example: clear");
                    commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                }
                else if (userInputList.size()>1 && !userInputList.get(1).toUpperCase().equals("HELP")) {
                    System.out.println("Invalid clear command. Please type clear help for more info.");
                }
                else {
                    commandHistory.add(userInputList.get(0));
                    clearScreen();
                }
            } else if (userInputList.get(0).equals("fgcolor")){
                if (userInputList.size() < 2)
                {
                    userInputList.add(1,"White");
                    commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                }
                Color textColor;
                String attribute = userInputList.get(1).toUpperCase();

                switch (attribute){
                    case "BLACK":
                        textColor = Color.BLACK;
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        setColor(textColor);
                        break;
                    case "RED":
                        textColor = Color.RED;
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        setColor(textColor);
                        break;
                    case "GREEN":
                        textColor = Color.GREEN;
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        setColor(textColor);
                        break;
                    case "YELLOW":
                        textColor = Color.YELLOW;
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        setColor(textColor);
                        break;
                    case "BLUE":
                        textColor = Color.BLUE;
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        setColor(textColor);
                        break;
                    case "MAGENTA":
                        textColor = Color.MAGENTA;
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        setColor(textColor);
                        break;
                    case "CYAN":
                        textColor = Color.CYAN;
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        setColor(textColor);
                        break;
                    case "WHITE":
                        textColor = Color.WHITE;
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        setColor(textColor);
                        break;
                    case "HELP":
                        commandHistory.add(userInputList.get(0) + " " + userInputList.get(1));
                        System.out.println("** This command changes the foreground color of the text.");
                        System.out.println("** Example Usage: fgcolor RED -> changes the text color to red.");
                        System.out.println("** Supported colors are: BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE");
                        System.out.println("** If no parameter is given a default dark grey color is applied.");
                        break;
                    default:
                        System.out.println(String.format("ERROR:The attribute %s is not a valid parameter for fgcolor function. Try fgcolor HELP for a detailed usage guide.", attribute));

                }

            }
            else if (userInputList.get(0).equals("quit") && userInputList.size()==1){
                resetStyle();
                clearScreen();
                break;
            }
            else if (userInputList.get(0).equals("movecursor")){
                if (userInputList.size() < 3)
                {
                    if(userInputList.size() == 2)
                    {
                        String helper = userInputList.get(1);
                        helper = helper.toUpperCase();
                        if (helper.equals("HELP")){
                            System.out.println("** This function takes two integer arguments and moves the cursor to the specified position on the screen.");
                            System.out.println("** Example Usage: movecursor 12 50");
                            commandHistory.add(userInputList.get(0));
                        }
                        else {
                            System.out.println("ERROR: Unsuitable number of attributes, use {command} help to get more info.");
                        }
                    }
                    else {
                        System.out.println("ERROR: Unsuitable number of attributes, use {command} help to get more info.");
                    }
                }
                else {
                    String possibleWrong = null;
                    try {
                        possibleWrong = userInputList.get(1);
                        int arg1 = Integer.parseInt(userInputList.get(1));
                        possibleWrong = userInputList.get(2);
                        int arg2 = Integer.parseInt(userInputList.get(2));
                        moveTo(arg1, arg2);
                        commandHistory.add(userInputList.get(0) + " " + arg1 + " " + arg2);
                    } catch (Exception NumberFormatException) {
                        System.out.println(String.format("Argument %s is of unsuitable type, please use only integers for this operation or consult the help command.", possibleWrong));
                    }
                }
            }
            else if(userInputList.get(0).equals("glyph")){
                commandString = Validation.validateCommandGlyph(userInput);
                command(commandString);
                commandHistory.add(commandString);
            }
            else if(userInputList.get(0).equals("help")) {
                help();
                commandHistory.add(userInputList.get(0));
            }
            else if (userInputList.get(0).equals("debug_read"))
            {
                File textFile = new File("com/codecool/termlib/exampleText.txt");
                readFromFile(textFile);
                commandHistory.add(userInputList.get(0));
            }
            else if (userInputList.get(0).equals("debug_history")) {
                if (userInputList.size() > 1) {
                    String helper = userInputList.get(1);
                    helper = helper.toUpperCase();
                    if (helper.equals("HELP")) {
                        System.out.println("** This command prints the command history of the current session");
                        System.out.println("** Example Usage: history");
                    }
                    else {
                        System.out.println("ERROR: Invalid Parameter try using <command> help");
                    }
                } else {
                    if (commandHistory.size() == 0) {
                        System.out.println("** No commands entered");
                    } else {
                        System.out.println(" ");
                        for (String value : commandHistory) {
                            System.out.println("** " + value);
                        }
                        System.out.println(" ");
                    }

                }
            }
            else if (userInputList.get(0).equals("clock"))
            {
                if (userInputList.size() < 2)
                {
                    userInputList.add(1, "default");
                    String parameter = userInputList.get(1);
                    commandHistory.add(userInputList.get(0));
                    clock(parameter);

                } else {
                    String parameter = userInputList.get(1);
                    parameter = parameter.toUpperCase();
                    String[] possibleArgs = new String[] {"NYC", "HKG", "LON", "HELP"};
                    if (!Arrays.stream(possibleArgs).anyMatch(parameter::equals))
                    {
                        commandHistory.add(userInputList.get(0));
                    }
                    else
                    {
                        commandHistory.add(userInputList.get(0) + " " + parameter);
                    }
                    clock(parameter);
                }
            }
            else{
                System.out.println("ERROR: Command unrecognized. Type help for more info.");
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
    }

    /**
     * Clear the whole screen.
     *
     * Might reset cursor position.
     */
    public static void clearScreen() {
        System.out.print(CONTROL_CODE+CLEAR+CONTROL_CODE+MOVE);
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
    public static void moveTo(Integer x, Integer y) {
        System.out.print(CONTROL_CODE+x+';'+y+'f');
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
        System.out.print("\033[4m");
    }

    /**
     * Reverses the default text color and background color.
     */
    public static void reverse() {
        System.out.print("\033[7m");
    }

    /**
     * Brightens the text. (Bold text in text editors and a lighter white.)
     */
    public static void brighten() {
        System.out.print("\033[1m");
    }

    /**
     * Dims the text. (Turns the text a darker shade of gray.)
     */
    public static void dim() {
        System.out.print("\033[2m");
    }

    /**
     * Hides the text.
     */
    public static void hide() {
        System.out.print("\033[8m");
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
     * Displays information on commands, syntax mainly.
     */
    public static void help() {
        System.out.println("** Please type: <command> help for each command details.");
        System.out.println("** Available commands: bgcolor, fgcolor, move, movecursor, ");
        System.out.println("** attribute bright|dim|underscore|blink|hidden|reset|reverse,");
        System.out.println("** glyph, clear, quit.");
    }

    /**
     * Displays help info for the attribute command.
     */
    public static void helpAttribute() {
        System.out.println("** Set a text attribute by typing: attribute <attribute>");
        System.out.println("** Example attribute bright");
        System.out.println("** Choose from: bright, dim, underscore, blink, reverse, hidden, reset");
    }

    /**
     * Displays help info for the move command.
     */
    public static void helpMove() {
        System.out.println("** Move the cursor on the screen, relative to the current position.");
        System.out.println("** Example: move up 4 (will move the cursor up 4 lines and 1 down for typing)");
        System.out.println("** Distance parameter must be a positive integer (1, 2, 4, 30, 529).");
        System.out.println("** Choose from up, down, forward, backward. If parameters are too large, the cursor might be offscreen.");
    }

    /**
     * Displays help info for the glyph command.
     */
    public static void helpGlyph() {
        System.out.println("** Moves the cursor on the screen, to the given position and changes the character there to the glyph.");
        System.out.println("** Example: glyph @ 12 50");
        System.out.println("** The glyph character can be anything except empty space ' '. Coordinates must be positive integers.");
    }



    /**
     * Set the character displayed under the current cursor position.
     *
     * The actual cursor position after calling this method is the
     * same as beforehand.  This method is useful for drawing shapes
     * (for example frame borders) with cursor movement.
     *
     * @param c the literal character to set for the current cursor
     * position.
     */
    public static void setChar(char c, int x, int y) {
        //save cursor position
        System.out.print("\0337");
        //move to position
        moveTo(x,y);
        //modify character
        reverse();
        System.out.print(c);
        //return to initial position
        resetStyle();
        System.out.print("\0338");
    }

    /**
     * Helper function for sending commands to the terminal.
     *
     * The common parts of different commands shall be assembled here.
     * The actual printing shall be handled from this command.
     *
     * @param commandString The unique part of a command sequence.
     */
    private static void command(String commandString) {
        //bgcolor command
        if (commandString.substring(0, 7).equals("bgcolor")) {
            try {
                setBgColor(Color.valueOf(commandString.substring(8)));
            }
            catch (Exception e){
                System.out.println("Invalid parameter. Please type bgcolor help for more details.");
            };
        }
        //attribute command
        else if (commandString.substring(0, 9).equals("attribute")) {
            if (commandString.substring(10).equals("BRIGHT")) brighten();
            else if (commandString.substring(10).equals("DIM")) dim();
            else if (commandString.substring(10).equals("UNDERSCORE")) setUnderline();
            else if (commandString.substring(10).equals("BLINK")) blink();
            else if (commandString.substring(10).equals("REVERSE")) reverse();
            else if (commandString.substring(10).equals("HIDDEN")) hide();
            else if (commandString.substring(10).equals("RESET")) resetStyle();
            else if (commandString.substring(10).equals("HELP")) helpAttribute();
            else {
                System.out.println("Invalid parameter. Please type attribute help for more details.");
            }
        }
        //move command
        else if (commandString.substring(0, 4).toLowerCase().equals("move")) {
            List<String> userInputList = new ArrayList<String>(Arrays.asList(commandString.split(" ")));
            if (userInputList.get(1).toUpperCase().equals("UP")) {
                moveCursor(Direction.UP, Integer.parseInt(userInputList.get(2)));
            }
            else if (userInputList.get(1).toUpperCase().equals("DOWN")) {
                moveCursor(Direction.DOWN, Integer.parseInt(userInputList.get(2)));
            }
            else if (userInputList.get(1).toUpperCase().equals("FORWARD")) {
                moveCursor(Direction.FORWARD, Integer.parseInt(userInputList.get(2)));
            }
            else if (userInputList.get(1).toUpperCase().equals("BACKWARD")) {
                moveCursor(Direction.BACKWARD, Integer.parseInt(userInputList.get(2)));
            }
            else if (userInputList.get(1).toUpperCase().equals("HELP")) {
                helpMove();
            }
            else {
                System.out.println("Invalid parameter. Please type move help for more details.");
            }
        }
        // glyph command
        else if (commandString.substring(0, 5).toLowerCase().equals("glyph")) {
            List<String> userInputList = new ArrayList<String>(Arrays.asList(commandString.split(" ")));
            if (userInputList.get(1).toLowerCase().equals("help")) {
                helpGlyph();
            } else if (userInputList.get(1).toLowerCase().equals("invalid")) {
                System.out.println("Invalid parameter. Please type glyph help for more details.");
            }
            else {
                char symbol = userInputList.get(1).charAt(0);
                int positionX = Integer.parseInt(userInputList.get(2));
                int positionY = Integer.parseInt(userInputList.get(3));
                setChar(symbol, positionX, positionY);
            }

        }




    }

    /**
     * Loading Screen Generator for the interface
     */
    public static void loadingScreen() throws InterruptedException {

        System.out.println("-TermLib- A simple terminal emulator.");
        System.out.println("Loading....");

        System.out.print(CONTROL_CODE+"44"+STYLE + " ");
        for(int i = 0; i < 10; i++)
        {
            Thread.sleep(500);
            if (i < 3) {

                System.out.print(CONTROL_CODE+ "41"+ STYLE + " ");
                resetStyle();

            } else if (i < 6){
                System.out.print(CONTROL_CODE+ "43"+ STYLE + " ");
                resetStyle();
            } else if(i > 6){
                System.out.print(CONTROL_CODE+ "42"+ STYLE + " ");
                resetStyle();
            }
        }
        System.out.print(CONTROL_CODE+"44"+STYLE + " \n");
        resetStyle();
    }

    public static void readFromFile(File textFile)
    {
        try {
            Scanner text = new Scanner(textFile);

            while (text.hasNextLine()) {
                String data = text.nextLine();
                System.out.println(data);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("ERROR: File not found.");
        }
    }

    public static void clock (String time)
    {
        Date currentClock = new Date();
        DateFormat currentClockFormatted = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");

        switch( time ){
            case "HKG":
                currentClockFormatted.setTimeZone(TimeZone.getTimeZone("Asia/Hong_Kong"));
                System.out.println(String.format("Current time in Hong Kong: %s", currentClockFormatted.format(currentClock)));
                break;
            case "LON":
                currentClockFormatted.setTimeZone(TimeZone.getTimeZone("Europe/London"));
                System.out.println(String.format("Current time in London: %s", currentClockFormatted.format(currentClock)));
                break;
            case "NYC":
                currentClockFormatted.setTimeZone(TimeZone.getTimeZone("America/New_York"));
                System.out.println(String.format("Current time in New York City: %s", currentClockFormatted.format(currentClock)));
                break;
            case "HELP":
                System.out.println("** This command returns the current date and time.");
                System.out.println("** Example Usage: clock {parameter}");
                System.out.println("** Supported parameters are: HKG, NYC, LON");
                break;
            default:
                currentClockFormatted.setTimeZone(TimeZone.getDefault());
                System.out.println(String.format("Current time at your current location is: %s", currentClockFormatted.format(currentClock)));
                break;
        }
    }

}
