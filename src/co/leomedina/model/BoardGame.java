package co.leomedina.model;

public class BoardGame {
    private String mName;
    private String mDescription;
    private int mMinPlayers;
    private int mMaxPlayers;

    public BoardGame(String name, String description, int minPlayers, int maxPlayers) {
        mName = name;
        mDescription = description;
        mMinPlayers = minPlayers;
        mMaxPlayers = maxPlayers;
    }

    String getName() {
        return mName;
    }

    int getMinPlayers() {
        return mMinPlayers;
    }

    int getMaxPlayers() {
        return mMaxPlayers;
    }

    String getDescription() {
        return mDescription;
    }

    @Override
    public String toString() {
        return String.format("%s playable with %s to %s players",
                mName,
                mMinPlayers,
                mMaxPlayers);
    }
}
