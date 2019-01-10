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

    public String getName() {
        return mName;
    }

    public int getMinPlayers() {
        return mMinPlayers;
    }

    public int getMaxPlayers() {
        return mMaxPlayers;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getSynopsis() {
        return "Name: " +
                mName +
                "%nDescription: " +
                mDescription +
                "%nPlayers: " +
                mMinPlayers +
                " - " +
                mMaxPlayers;
    }

    @Override
    public String toString() {
        return String.format("%s is playable with %s to %s players",
                mName,
                mMinPlayers,
                mMaxPlayers);
    }
}
