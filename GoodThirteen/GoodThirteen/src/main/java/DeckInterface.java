// Import Random - needed for shuffling Deck
import java.util.Random;

public interface DeckInterface {
    // Returns the current number of cards in the Deck
    public int getCurrentSize();

    // Checks if Deck is empty
    public boolean isEmpty();

    // Adds a new card to the Deck
    public boolean addNewEntry(Card newEntry);

    // Removes a specified card from the Deck
    public boolean remove(Card anEntry);

    // Checks if a specified card exists in the Deck
    public boolean contains(Card anEntry);

    // Returns all cards in a Deck
    Card[] getCards();

    // Returns a single card at a specified index in a Deck
    Card dealCards(int index);

    // Randomly shuffles cards in Deck
    static void shuffleCards(Card[] cards) {
        int index;
        Card card;
        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            card = cards[index];
            cards[index] = cards[i];
            cards[i] = card;
        }
    }
}