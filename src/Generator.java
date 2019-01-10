import co.leomedina.model.Collection;
import co.leomedina.model.BoardGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Generator {
    private Collection mCollection;
    private BufferedReader mReader;
    private HashMap<String, String> mMenu;

    Generator(Collection collection) {
        mCollection = collection;
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mMenu = new HashMap<String, String>();
        generateMenu();
        run();
    }

    private void generateMenu() {
        mMenu.put("Add", "Add a new BoardGame");
        mMenu.put("Quit", "Quit the program");
    }

    private String promptAction() throws IOException {
        System.out.printf("There are %d options available.%n%n", mMenu.size());
        for (Map.Entry<String, String> option : mMenu.entrySet())
            System.out.printf("%s - %s%n", option.getKey(), option.getValue());
        System.out.printf("%n What do you want to do? ");
        return mReader.readLine().trim().toLowerCase();
    }

    private void run() {
        String choice = "";
        do try {
            choice = promptAction();
            switch (choice) {
                case "add":
                    mCollection.addBoardGame(promptForBoardGame());
                    break;
                case "quit":
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Unknown Choice, Try again");
            }
        } catch (IOException ioe){
            System.out.println("Invalid Answer");
            ioe.printStackTrace();
        } while(!choice.equals("quit"));
    }

    private BoardGame promptForBoardGame() throws IOException {
        int minPlayers;
        int maxPlayers;


        System.out.println("Name of the Game? ");
        String name = mReader.readLine();
        System.out.println("Short description? ");
        String description = mReader.readLine();
        minPlayers = isValidPlayerNumber("minimum");
        maxPlayers = isValidPlayerNumber("maximum");

        while (maxPlayers < minPlayers){
            System.out.println("Wroops, your max is lower than your min. Try again");
            maxPlayers = isValidPlayerNumber("maximum");
        }

        return new BoardGame(name, description, minPlayers, maxPlayers);
    }

    private int isValidPlayerNumber(String parameter) throws IOException {
        int player = -1;

        do try {
            System.out.printf("What is the %s number of players? ", parameter);
            player = Integer.parseInt(mReader.readLine());
            if(player < 0) {
                System.out.println("Sorry Wrong Number! Try again ");
                player = -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input!");
        } while (player == -1);

        return player;
    }

}