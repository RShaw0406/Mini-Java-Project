public interface ReplayInterface {
    // Removes any move from the Replay
    Move remove();

    // Clears all moves from the Replay
    void clear();

    // Returns the current number of moves in the Replay
    int getCurrentSize();

    // Checks if Replay is empty
    boolean isEmpty();

    // Adds a new move to the Replay
    boolean addNewEntry(Move newEntry);

    // Displays each of the moves in the Replay
    void replayMoves();
}
