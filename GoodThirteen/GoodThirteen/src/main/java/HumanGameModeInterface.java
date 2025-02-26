public interface HumanGameModeInterface {
    // Main functionality of HumanGameMode
    void startGame();

    // Functionality to play HumanGameMode again
    void playAgain();

    // Functionality to end HumanGameMode
    void gameOver(Replay replay);

    // Returns the Deck being used in the HumanGameMode
    Deck getDeck();

    // Set the Deck to be used in the HumanGameMode
    void setDeck(Deck deck);

    // Returns the Hand being used in the HumanGameMode
    Hand getHand();

    // Set the Hand to be used in the HumanGameMode
    void setHand(Hand hand);

    // Returns a move from the HumanGameMode
    Move getMove();

    // Sets a move in the HumanGameMode
    void setMove(Move move);

    // Returns goAgain from DemoGameMode
    boolean getGoAgain();

}
