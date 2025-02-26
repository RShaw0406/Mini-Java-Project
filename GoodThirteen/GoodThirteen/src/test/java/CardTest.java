import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    private Card testCard;

    @Before
    public void setUp()  {
        this.testCard = new Card(10, 2);
    }

    @After
    public void tearDown()  {
        this.testCard = null;
    }

    @Test
    public void random_card_creation_test() {
        Card testCard = new Card();
        assertTrue(testCard.getRankValue() > -1);
        assertNotNull(testCard.getSuit());
    }

    @Test
    public void specific_card_creation_test() {
        String expected_suit = "Hearts";
        int expected_rank_value = 10;
        assertEquals(testCard.getSuit(), expected_suit);
        assertEquals(testCard.getRankValue(), expected_rank_value);
    }

    @Test
    public void to_string_test() {
        String expected_string_output = "Jack Hearts";
        String actual_string_output = testCard.toString();
        assertEquals(expected_string_output, actual_string_output);
    }

    @Test
    public void return_suit_test() {
        String expected_suit = "Hearts";
        String actual_suit = testCard.getSuit();
        assertEquals(expected_suit, actual_suit);
    }

    @Test
    public void return_rank_test() {
        String expected_rank = "Jack";
        String actual_rank = testCard.getRank();
        assertEquals(expected_rank, actual_rank);
    }

    @Test
    public void return_rank_value_test() {
        int expected_rank_value = 10;
        int actual_rank_value = testCard.getRankValue();
        assertEquals(expected_rank_value, actual_rank_value);
    }
}