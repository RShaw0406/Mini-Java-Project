public interface DemoGameModeInterface {
    // Main functionality of DemoGameMode
    void startGame();

    // Functionality to end DemoGameMode
    void gameOver();

    // Returns the Deck being used in the DemoGameMode
    Deck getDeck();

    // Set the Deck to be used in the DemoGameMode
    void setDeck(Deck deck);

    // Returns the Hand being used in the DemoGameMode
    Hand getHand();

    // Set the Hand to be used in the DemoGameMode
    void setHand(Hand hand);

    // Returns goAgain from DemoGameMode
    boolean getGoAgain();

    // Set whether to run DemoGameMode again
    void setGoAgain(boolean goAgain);
}

