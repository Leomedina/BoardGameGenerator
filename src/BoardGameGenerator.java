import co.leomedina.model.Collection;

public class BoardGameGenerator {

    public static void main(String[] args) {
        Collection collection = new Collection();
        collection.importFrom("Boardgames.txt");
        Generator generator = new Generator(collection);
        generator.run();

        System.out.println("Saving Collection...");
        collection.exportTo("Boardgames.txt");
    }
}
