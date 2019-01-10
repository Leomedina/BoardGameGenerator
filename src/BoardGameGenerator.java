import co.leomedina.model.BoardGame;
import co.leomedina.model.Collection;

public class BoardGameGenerator {

    public static void main(String[] args) {
        BoardGame explodingKittens = new BoardGame(
                "Exploding Kittens",
                "A game of russian-roulette with cats",
                4,
                8);

        BoardGame miceAndMystics = new BoardGame(
                "Mice and Mystics",
                "D&D Game of mice",
                2,
                4);

        Collection collection = new Collection();

        addGames(explodingKittens, collection);
        addGames(miceAndMystics, collection);

        System.out.printf("%n" + collection.getRecommendation(4).toString() + "%n");

        Generator generator = new Generator(collection);
    }

    private static void addGames(BoardGame game, Collection collection) {
        collection.addBoardGame(game);
        System.out.printf("%nAdding ... %n");
        System.out.printf(game.getSynopsis() + "%n");
        System.out.printf("Total Game Count: %s%n", collection.getBoardGameCount());
    }
}
