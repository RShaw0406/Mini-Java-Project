import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {

    private Hand testHand;

    @Before
    public void setUp() {
        this.testHand = new Hand(10);
    }

    @After
    public void tearDown() {
        this.testHand = null;
    }

    @Test
    public void hand_creation_test() {
        int expected_hand_value = testHand.getCards().length;
        int actual_hand_value = 10;
        assertEquals(expected_hand_value, actual_hand_value);
    }

    @Test
    public void hand_size_test() {
        Card card = new Card(3, 3);
        testHand = new Hand(1);
        testHand.addNewEntry(card);
        int expected_hand_size = testHand.getCurrentSize();
        int actual_hand_size = 1;
        assertEquals(expected_hand_size, actual_hand_size);
    }

    @Test
    public void hand_empty_test() {
        testHand = new Hand(10);
        boolean expected_hand_empty = testHand.isEmpty();
        boolean actual_hand_empty = true;
        assertEquals(expected_hand_empty, actual_hand_empty);
    }

    @Test
    public void hand_add_card_test() {
        Card card = new Card(3, 3);
        testHand = new Hand(10);
        boolean expected_hand_add_card = testHand.addNewEntry(card);
        boolean actual_hand_add_card = true;
        assertEquals(expected_hand_add_card, actual_hand_add_card);
    }

    @Test
    public void hand_remove_card_test() {
        Card card = new Card(3, 3);
        testHand = new Hand(10);
        testHand.addNewEntry(card);
        boolean expected_hand_remove_card = testHand.remove(card.toString());
        boolean actual_hand_remove_card = true;
        assertEquals(expected_hand_remove_card, actual_hand_remove_card);
    }

    @Test
    public void hand_contains_card_test() {
        Card card = new Card(3, 3);
        testHand = new Hand(10);
        testHand.addNewEntry(card);
        boolean expected_hand_contains_card = testHand.contains(card.toString());
        boolean actual_hand_contains_card = true;
        assertEquals(expected_hand_contains_card, actual_hand_contains_card);
    }

    @Test
    public void hand_get_card_test(){
        Card card = new Card(3, 3);
        testHand = new Hand(10);
        testHand.addNewEntry(card);
        Card expected_card = testHand.getCard(card.toString());
        Card actual_card = card;
        assertEquals(expected_card, actual_card);
    }

    @Test
    public void hand_get_cards_test() {
        int expected_hand_value = testHand.getCards().length;
        int actual_hand_value = 10;
        assertEquals(expected_hand_value, actual_hand_value);
    }
}