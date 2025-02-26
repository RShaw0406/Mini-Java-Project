public interface MoveInterface {
    // Returns the Hand that was dealt during Move
    String getMoveHand();

    // Returns the Move that the user made
    String getMoveMove();

    // Returns the result of the Move made by the user
    String getMoveResult();

    // Returns the numberOfMoves made by user
    int getNumberOfMoves();
}
