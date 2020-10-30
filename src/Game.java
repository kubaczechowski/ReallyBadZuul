import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game {

    private Player player;
    private Parser parser;
    private Room room; // just an instance we will see if needed
    //stack used for the back method
    private Stack<Room> roomsStack ;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        roomsStack = new Stack();
        createRooms();
        createItems();
        parser = new Parser();
    }



    private void createItems()
    {
        player.getCurrentRoom().addItem(new Item("Book",
                "an old, dusty book bound in gray leather" ,  44));
        //is it a good practice to create new things directly in constructor
        // or should I create a method that will
        //be called in the constructor""
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, theater, pub, lab, office, cellar;

        // create the rooms
        outside = new Room("outside the main entrance of the university", true);
        theater = new Room("in a lecture theater", true);
        pub = new Room("in the campus pub", true);
        lab = new Room("in a computing lab", true);
        office = new Room("in the computing admin office", true);

        // initialise room exits
        // outside.setExits(null, theater, lab, pub);
        //theater.setExits(null, null, null, outside);
        //pub.setExits(null, outside, null, null);
        // lab.setExits(outside, office, null, null);
        // office.setExits(null, null, null, lab);
        lab.setExit("north", outside);
        lab.setExit("east", office);

        cellar = new Room("in the cellar", true);

        office.setExit("down", cellar);
        cellar.setExit("up", office);

        player.setCurrentRoom(outside);
        //currentRoom = outside;  // start game outside
    }

    private void createPlayers()
    {
        //How can I create a new player???
        Player player1;
        player1 = new Player( 100);
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        //printLocationInfo();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("go")) {
                goRoom(command);
            }
        else if (commandWord.equals("quit")) {
                wantToQuit = quit(command);
            }
        else if(commandWord.equals("back"))
                {
                    goBack();
                }
        else if(commandWord.equals("cookie"))
        {
            player.eatCookie();

        }

        return wantToQuit;
        }



        // implementations of user commands:

        /**
         * Print out some help information.
         * Here we print some stupid, cryptic message and a list of the
         * command words.
         */
        private void printHelp ()
        {
            System.out.println("You are lost. You are alone. You wander");
            System.out.println("around at the university.");
            System.out.println();
            System.out.println("Your command words are:");
            //parser.showCommands();
            System.out.println(parser.getCommands());
        }

        /**
         * Try to go in one direction. If there is an exit, enter
         * the new room, otherwise print an error message.
         */
        private void goRoom (Command command)
        {
            if (!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");
                return; // what does it do??
            }

            String direction = command.getSecondWord();
            //
            //Room nextRoom = currentRoom.getExit(direction);
            //Program adds the room to the stack
            roomsStack.push(player.getCurrentRoom());

        }

    /**
     * Method uses the stack to do so.
     */
    private  void goBack()
        {
            player.setCurrentRoom(roomsStack.peek());
            //currentRoom= roomsStack.peek();
        }

        /**
         * "Quit" was entered. Check the rest of the command to see
         * whether we really quit the game.
         * @return true, if this command quits the game, false otherwise.
         */
        private boolean quit (Command command)
        {
            if (command.hasSecondWord()) {
                System.out.println("Quit what?");
                return false;
            } else {
                return true;  // signal that we want to quit
            }
        }

        private void printLocationInfo ()
        {
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println();
        }


    private void look ()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }
}
