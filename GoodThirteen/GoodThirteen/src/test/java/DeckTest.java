import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck testDeck;

    @Before
    public void setUp() {
        this.testDeck = new Deck();
    }

    @After
    public void tearDown() {
        this.testDeck = null;
    }

    @Test
    public void deck_creation_test() {
        int expected_deck_value = testDeck.getCards().length;
        int actual_deck_value = 52;
        assertEquals(expected_deck_value, actual_deck_value);
    }

    @Test
    public void deck_size_test() {
        Card card = new Card(3, 3);
        testDeck = new Deck();
        testDeck.addNewEntry(card);
        int expected_deck_size = testDeck.getCurrentSize();
        int actual_deck_size = 1;
        assertEquals(expected_deck_size, actual_deck_size);
    }

    @Test
    public void deck_empty_test() {
        testDeck = new Deck();
        boolean expected_deck_empty = testDeck.isEmpty();
        boolean actual_deck_empty = true;
        assertEquals(expected_deck_empty, actual_deck_empty);
    }

    @Test
    public void deck_add_card_test() {
        Card card = new Card(3, 3);
        testDeck = new Deck();
        boolean expected_deck_add_card = testDeck.addNewEntry(card);
        boolean actual_deck_add_card = true;
        assertEquals(expected_deck_add_card, actual_deck_add_card);
    }

    @Test
    public void deck_remove_card_test() {
        Card card = new Card(3, 3);
        testDeck = new Deck();
        testDeck.addNewEntry(card);
        boolean expected_deck_remove_card = testDeck.remove(card);
        boolean actual_deck_remove_card = true;
        assertEquals(expected_deck_remove_card, actual_deck_remove_card);
    }

    @Test
    public void deck_contains_card_test() {
        Card card = new Card(3, 3);
        testDeck = new Deck();
        testDeck.addNewEntry(card);
        boolean expected_deck_contains_card = testDeck.contains(card);
        boolean actual_deck_contains_card = true;
        assertEquals(expected_deck_contains_card, actual_deck_contains_card);
    }

    @Test
    public void deck_get_cards_test() {
        int expected_deck_value = testDeck.getCards().length;
        int actual_deck_value = 52;
        assertEquals(expected_deck_value, actual_deck_value);
    }

    @Test
    public void deck_deal_cards_test() {
        Card expected_dealt_card = testDeck.getCards()[1];
        Card actual_dealt_card = testDeck.dealCards(1);
        assertEquals(expected_dealt_card, actual_dealt_card);
    }

    @Test
    public void deck_shuffle_test() {
        Card[] expected_shuffled_cards = {
                new Card (1, 1),
                new Card(2,2 ),
                new Card(3,3 ),
                new Card (4,4),
        };
        Card[] actual_shuffle_cards = {
                new Card (1, 1),
                new Card(2,2 ),
                new Card(3,3 ),
                new Card (4,4),
        };
        DeckInterface.shuffleCards(actual_shuffle_cards);
        assertNotEquals(expected_shuffled_cards, actual_shuffle_cards);
    }


}