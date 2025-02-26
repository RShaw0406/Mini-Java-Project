public class Deck implements DeckInterface {
    // Global Variables
    private int numberOfCards;
    private boolean initialised = false;
    private static final int MAX_CAPACITY = 52;
    private final Card[] cards;

    // Checks if Deck is full
    private boolean isDeckFull() {
        return numberOfCards == cards.length;
    }

    // Checks if Deck has been initialised
    private void checkInitialisation() {
        if (!initialised)
            throw new SecurityException("Deck object is not intialised properly");
    }

    // Removes a card from a specified index in the Deck
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

    // Creates a new instance of a Deck
    public Deck() {
        cards = new Card[52];
        for (int thisCard = 0; thisCard < MAX_CAPACITY; thisCard++) {
            this.cards[thisCard] = new Card(thisCard % 13, thisCard / 13);
        }
        initialised = true;
        DeckInterface.shuffleCards(this.cards);
    }

    // Returns the current number of cards in the Deck
    public int getCurrentSize() {
        return numberOfCards;
    }

    // Checks if Deck is empty
    public boolean isEmpty() {
        return numberOfCards == 0;
    }

    // Adds a new card to the Deck
    public boolean addNewEntry(Card newEntry) {
        checkInitialisation();
        if (isDeckFull()) return false;
        else {
            cards[numberOfCards++] = newEntry;
            return true;
        }
    }

    // Removes a specified card from the Deck
    public boolean remove(Card anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfCards) {
            if (cards[index].equals(anEntry)) found = true;
            else index++;
        }
        if (found) removeElementAt(index);
        return found;
    }

    // Checks if a specified card exists in the Deck
    public boolean contains(Card anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfCards) {
            if (cards[index++].equals(anEntry)) found = true;
        }
        return found;
    }

    // Returns all cards in a Deck
    public Card[] getCards() {
        return this.cards;
    }

    // Returns a single card at a specified index in a Deck
    public Card dealCards(int index) {
        return this.cards[index];
    }

}
