public class Move implements MoveInterface {
    // Global Variables
    private static int numberOfMoves = 0;
    private final String hand;
    private final String move;
    private final String result;

    // Creates a new instance of a Move
    public Move(String moveHand, String moveMove, String moveResult) {
        numberOfMoves++;
        this.hand = moveHand;
        this.move = moveMove;
        this.result = moveResult;
    }

    // Returns the Hand that was dealt during Move
    public String getMoveHand() {
        return this.hand;
    }

    // Returns the Move that the user made
    public String getMoveMove() {
        return this.move;
    }

    // Returns the result of the Move made by the user
    public String getMoveResult() {
        return this.result;
    }

    // Returns the numberOfMoves made by user
    public int getNumberOfMoves() {return numberOfMoves;}

}
