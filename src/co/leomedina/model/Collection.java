package co.leomedina.model;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private List<BoardGame> mBoardGames;

    public Collection() {
        mBoardGames = new ArrayList<BoardGame>();
    }

    public void addBoardGame(BoardGame boardGame) {
        mBoardGames.add(boardGame);
    }

    public int getBoardGameCount() {
        return mBoardGames.size();
    }

    public List<BoardGame> getRecommendation(int numPlayers) {
        List<BoardGame> recommendations = new ArrayList<BoardGame>();

        for (BoardGame game : mBoardGames) {
           if(game.getMinPlayers() >=  numPlayers) {
               recommendations.add(game);
           }
        }
        return recommendations;
    }
}