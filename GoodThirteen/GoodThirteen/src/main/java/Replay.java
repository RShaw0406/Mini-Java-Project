// Import Scanner - needed to get user input
import java.util.Scanner;

public class Replay implements ReplayInterface{
    // Global Variables
    private int numberOfMoves;
    private boolean initialised = false;
    private static final int MAX_CAPACITY = 1000;
    private final Move[] moves;

    // Checks if Replay is full
    private boolean isReplayFull() {
        return numberOfMoves == moves.length;
    }

    // Checks if Replay has been initialised
    private void checkInitialisation() {
        if (!initialised)
            throw new SecurityException("Replay object is not intialised properly");
    }

    // Removes a move from a specified index in the Hand
    private Move removeElementAt(int index) {
        Move result = null;
        if (!isEmpty() && index >= 0 && index < numberOfMoves) {
            result = moves[index];
            moves[index] = moves[numberOfMoves - 1];
            moves[numberOfMoves - 1] = null;
            numberOfMoves--;
        }
        return result;
    }

    // Creates a new instance of a Replay
    public Replay(int capacity) {
        if (capacity <= MAX_CAPACITY) {
            Move[] tempMoves = new Move[capacity];
            moves = tempMoves;
            numberOfMoves = 0;
            initialised = true;
        } else throw new IllegalStateException("Attempt to create a replay where the capacity exceeds the maximum");
    }

    // Removes any move from the Replay
    public Move remove() {
        return removeElementAt(numberOfMoves - 1);
    }

    // Clears all moves from the Replay
    public void clear() {
        while (!isEmpty()) remove();
    }

    // Returns the current number of moves in the Replay
    public int getCurrentSize() {
        return numberOfMoves;
    }

    // Checks if Replay is empty
    public boolean isEmpty() {
        return numberOfMoves == 0;
    }

    // Adds a new move to the Replay
    public boolean addNewEntry(Move newEntry) {
        checkInitialisation();
        if (isReplayFull()) return false;
        else {
            moves[numberOfMoves++] = newEntry;
            return true;
        }
    }

    // Displays each of the moves in the Replay
    public void replayMoves() {
        Scanner scanner = new Scanner(System.in);
        int moveNumber = 0;
        System.out.println("Starting Replay... \n");
        for (int i = 0; i < getCurrentSize(); i++) {
            moveNumber++;
            System.out.println("Move " + moveNumber + "\n");
            System.out.println("Hand:");
            System.out.println(moves[i].getMoveHand().toString() + "\n");
            System.out.println("User removed:");
            System.out.println(moves[i].getMoveMove() + "\n");
            System.out.println("Result:");
            System.out.println(moves[i].getMoveResult() + "\n");
            if (i == getCurrentSize() - 1){
                System.out.println("Press any key to end replay:");
            }
            else{
                System.out.println("Press any key for next move:");
            }
            scanner.nextLine();
        }
        System.out.println("Ending Replay... \n");
    }
}
