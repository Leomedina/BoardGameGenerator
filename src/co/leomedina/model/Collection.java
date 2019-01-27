package co.leomedina.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Collection {
    private List<BoardGame> mBoardGames;

    public Collection() {
        mBoardGames = new ArrayList<>();
    }

    public void exportTo(String fileName) {
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                PrintWriter writer = new PrintWriter(fos)
        ) {
            for (BoardGame boardGame : mBoardGames) {
                writer.printf("%s|%s|%d|%d%n",
                        boardGame.getName(),
                        boardGame.getDescription(),
                        boardGame.getMinPlayers(),
                        boardGame.getMaxPlayers());
            }
        } catch (IOException e) {
            System.out.printf("Problem Saving %s%n", fileName);
            e.printStackTrace();
        }
    }

    public void importFrom(String fileName) {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|");
                addBoardGame(new BoardGame(args[0],
                        args[1],
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3])));
            }
        } catch (IOException e) {
            System.out.printf("Problems loading %s%n", fileName);
            e.printStackTrace();
        }
    }

    public void addBoardGame(BoardGame boardGame) {
        mBoardGames.add(boardGame);
    }

    public int getBoardGameCount() {
        return mBoardGames.size();
    }

    public List<BoardGame> getRecommendation(int numPlayers) {
        List<BoardGame> recommendations = new ArrayList<>();

        for (BoardGame game : mBoardGames) {
            if (game.getMinPlayers() <= numPlayers && game.getMaxPlayers() >= numPlayers) {
               recommendations.add(game);
           }
        }
        return recommendations;
    }

    public ArrayList<BoardGame> getGames() {
        return (ArrayList<BoardGame>) mBoardGames;
    }
}