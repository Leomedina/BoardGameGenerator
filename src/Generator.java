import co.leomedina.model.BoardGame;
import co.leomedina.model.Collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Generator {
    private Collection mCollection;
    private BufferedReader mReader;
    private HashMap<String, String> mMenu;

    Generator(Collection collection) {
        mCollection = collection;
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mMenu = new HashMap<>();
        generateMenu();
    }

    private void generateMenu() {
        mMenu.put("Add", "Add a new BoardGame");
        mMenu.put("Rec", "Get Recommendations");
        mMenu.put("Collection", "display available games");
        mMenu.put("Quit", "Quit the program");
    }

    private String promptAction() throws IOException {
        System.out.printf("%nThere are %d options available: %n", mMenu.size());
        for (Map.Entry<String, String> option : mMenu.entrySet())
            System.out.printf("%s - %s%n", option.getKey(), option.getValue());
        System.out.printf("%nWhat do you want to do? %n");
        return mReader.readLine().trim().toLowerCase();
    }

    void run() {
        String choice = "";
        do try {
            choice = promptAction();
            switch (choice) {
                case "add":
                    mCollection.addBoardGame(promptForNewBoardGame());
                    break;
                case "rec":
                    System.out.println("What is the number of players? ");
                    int recPlayers = isValidPlayerNumber();
                    System.out.println("\nYour Recommendations are: ");
                    printListOfBoardGames(mCollection.getRecommendation(recPlayers));

                    break;
                case "collection":
                    System.out.println("\nAll Available Games: ");
                    printListOfBoardGames(mCollection.getGames());
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

    private BoardGame promptForNewBoardGame() throws IOException {
        int minPlayers;
        int maxPlayers;

        System.out.println("Name of the Game? ");
        String name = mReader.readLine();
        System.out.println("Short description? ");
        String description = mReader.readLine();

        System.out.println("What is the minimum number of players? ");
        minPlayers = isValidPlayerNumber();
        System.out.println("What is the maximum number of players? ");
        maxPlayers = isValidPlayerNumber();

        while (maxPlayers < minPlayers){
            System.out.println("Wroops, your max is lower than your min.\n" +
                    "What is the maximum number of players? ");
            maxPlayers = isValidPlayerNumber();
        }

        return new BoardGame(name, description, minPlayers, maxPlayers);
    }

    private int isValidPlayerNumber() throws IOException {
        int player;

        do try {
            player = Integer.parseInt(mReader.readLine());
            if(player < 0) {
                System.out.println("Sorry Wrong Number! Try again ");
                player = -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input! What's the number again? ");
            player = -1;
        } while (player == -1);

        return player;
    }

    private void printListOfBoardGames(List<BoardGame> recommendations) {
        int counter = 1;

        if (recommendations.isEmpty()) {
            System.out.println("\nSorry, no records today.");
        }
        for (BoardGame recommendation : recommendations) {
            System.out.printf("%d) %s%n", counter, recommendation.toString());
            counter++;
        }
    }
}