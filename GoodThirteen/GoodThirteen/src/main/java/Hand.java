public class Hand implements HandInterface {
    // Global Variables
    private int numberOfCards;
    private boolean initialised = false;
    private static final int MAX_CAPACITY = 10;
    private final Card[] cards;

    // Checks if main.Hand is full
    private boolean isHandFull() {
        return numberOfCards == cards.length;
    }

    // Checks if main.Hand has been initialised
    private void checkInitialisation() {
        if (!initialised)
            throw new SecurityException("main.Hand object is not intialised properly");
    }

    // Removes a card from a specified index in the main.Hand
    private Card removeElementAt(int index) {
        Card result = null;
        if (!isEmpty() && index >= 0 && index < numberOfCards) {
            result = cards[index];
            cards[index] = cards[numberOfCards - 1];
            cards[numberOfCards - 1] = null;
            numberOfCards--;
        }
        return result;
    }

    // Creates a new instance of a main.Hand
    public Hand(int capacity) {
        if (capacity <= MAX_CAPACITY) {
            Card[] tempHand = new Card[capacity];
            cards = tempHand;
            numberOfCards = 0;
            initialised = true;
        } else throw new IllegalStateException("Attempt to create a hand where the capacity exceeds the maximum");
    }

    // Returns the current number of cards in the main.Hand
    public int getCurrentSize() {
        return numberOfCards;
    }

    // Checks if main.Hand is empty
    public boolean isEmpty() {
        return numberOfCards == 0;
    }

    // Adds a new card to the main.Hand
    public boolean addNewEntry(Card newEntry) {
        checkInitialisation();
        if (isHandFull()) return false;
        else {
            cards[numberOfCards++] = newEntry;
            return true;
        }
    }

    // Removes a specified card from the main.Hand
    public boolean remove(String anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfCards) {
            if (cards[index].toString().equals(anEntry)) found = true;
            else index++;
        }
        if (found) removeElementAt(index);
        return found;
    }

    // Checks if a specified card exists in the main.Hand
    public boolean contains(String anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfCards) {
            if (cards[index].toString().equals(anEntry)) found = true;
            else index++;
        }
        return found;
    }

    // Returns a single card in a main.Hand
    public Card getCard(String anEntry){
        boolean found = false;
        int index = 0;
        Card foundCard = null;
        while (!found && index < numberOfCards) {
            if (cards[index].toString().equals(anEntry)){
                found = true;
                foundCard = cards[index];
            }
            else{
                index++;
            }
        }
        return foundCard;
    }

    // Returns a main.Hand as a string
    public String toString() {
        int cardCount = 0;
        String strResult = "";
        for (int i = 0; i < numberOfCards; i++) {
            cardCount ++;
            strResult += "[" + cards[i] + "]" + " ";
        }
        return strResult;
    }

    // Returns all cards in a main.Hand
    public Card[] getCards() {
        return this.cards;
    }

    // Checks if main.Hand has valid moves
    public boolean getValidMoves(Hand hand) {
        boolean validMove = false;
        int numberOfValidMoves = 0;
        for (int i = 0; i < hand.getCurrentSize(); i++) {
            for (int j = i+1; j < hand.getCurrentSize(); j++) {
                Card currentCard = cards[i];
                Card nextCard = cards[j];
                int currentCardValue = currentCard.getRankValue() + 1;
                int nextCardValue = nextCard.getRankValue() + 1;
                int totalCardValue = nextCardValue + currentCardValue;

                if (currentCardValue == 13){
                    numberOfValidMoves++;
                }

                if (totalCardValue == 13){
                    numberOfValidMoves++;
                }
            }
        }
        if (numberOfValidMoves > 0){
            validMove = true;
        }
        return validMove;
    }

    // Returns a hint for a valid move in main.Hand
    public String getHint(Hand hand, String firstUserSelection, String secondUserSelection){
        String cardHint = "";
        if (firstUserSelection.equals("?"))
        {
            cardHint = hand.getFirstCardHint(hand);
        }
        else{
            cardHint = hand.getSecondCardHint(hand, firstUserSelection);
        }
        return cardHint;
    }

    // Returns a hint for the first card selection in main.Hand
    public String getFirstCardHint(Hand hand){
        String cardHint = "";

        if (hand.contains("King Hearts") || hand.contains("King Diamonds") || hand.contains("King Spades") || hand.contains("King Clubs"))
        {
            for (int i = 0; i < hand.getCurrentSize(); i++){
                Card currentCard = cards[i];
                if (currentCard.toString().contains("King"))
                {
                    cardHint = currentCard.toString();
                    return cardHint;
                }
            }
        }

        for (int i = 0; i < hand.getCurrentSize(); i++) {
            for (int j = i+1; j < hand.getCurrentSize(); j++) {
                Card currentCard = cards[i];
                Card nextCard = cards[j];
                int currentCardValue = currentCard.getRankValue() + 1;
                int nextCardValue = nextCard.getRankValue() + 1;
                int totalCardValue = nextCardValue + currentCardValue;

                if (currentCardValue == 13){
                    cardHint = currentCard.toString();
                    return cardHint;
                }

                if (totalCardValue == 13){
                    cardHint = currentCard.toString();
                    return cardHint;
                }
            }
        }
        return cardHint;
    }

    // Returns a hint for the second card selection in main.Hand
    public String getSecondCardHint(Hand hand, String firstUserSelection){
        String cardHint = "";
        Card firstCardSelected = hand.getCard(firstUserSelection);
        for (int i = 0; i < hand.getCurrentSize(); i++) {
            Card currentCard = cards[i];
            if (!firstCardSelected.toString().equals(currentCard.toString())){
                int currentCardValue = currentCard.getRankValue() + 1;
                int firstCardValue = firstCardSelected.getRankValue() + 1;
                int totalCardValue = firstCardValue + currentCardValue;

                if (totalCardValue == 13){
                    cardHint = currentCard.toString();
                    return cardHint;
                }
            }
        }
        return cardHint;
    }
}
