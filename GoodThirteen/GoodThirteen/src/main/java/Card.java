// Import Random - needed for generating a random Card
import java.util.Random;

public class Card implements CardInterface {
    // Global Variables
    private final int RANK, SUIT;
    private static final Random GENERATOR = new Random();
    private static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
    private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};

    // Creates a new instance of a random Card
    public Card() {
        RANK = GENERATOR.nextInt(RANKS.length);
        SUIT = GENERATOR.nextInt(SUITS.length);
    }

    // Creates a new instance of a Card with a specified rank and suit
    public Card(int rank, int suit) {
        RANK = rank;
        SUIT = suit;
    }

    // Returns the rank of a Card
    public String getRank() {
        return RANKS[RANK];
    }

    // Returns the rank value of a Card
    public int getRankValue(){
        return RANK;
    }

    // Returns the suit of a Card
    public String getSuit(){
        return SUITS[SUIT];
    }

    // Returns a Card as a string
    public String toString(){
        return getRank() + " " + getSuit();
    }
}