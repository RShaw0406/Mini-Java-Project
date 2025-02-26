public interface HandInterface {
    // Returns the current number of cards in the Hand
    public int getCurrentSize();

    // Checks if Hand is empty
    public boolean isEmpty();

    // Adds a new card to the Hand
    public boolean addNewEntry(Card newEntry);

    // Removes a specified card from the Hand
    public boolean remove(String anEntry);

    // Checks if a specified card exists in the Hand
    public boolean contains(String anEntry);

    // Returns a single card in a Hand
    public Card getCard(String anEntry);

    // Returns all cards in a Hand
    Card[] getCards();

    // Checks if Hand has valid moves
    boolean getValidMoves(Hand hand);

    // Returns a hint for a valid move in Hand
    String getHint(Hand hand, String firstUserSelection, String secondUserSelection);

    // Returns a hint for the first card selection in Hand
    String getFirstCardHint(Hand hand);

    // Returns a hint for the second card selection in Hand
    String getSecondCardHint(Hand hand, String firstUserSelection);
}
