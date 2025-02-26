// Import Scanner - needed to get user input
import java.util.Scanner;

public class DemoGameMode implements DemoGameModeInterface {
    // Global Variables
    static boolean validMovesRemaining = false;
    static boolean goAgain = true;
    private int remainingCards = 0;
    private Deck deck;
    private Hand hand;

    // Creates a new instance of a DemoGameMode
    public DemoGameMode(){

    }

    // Main functionality of DemoGameMode
    public void startGame() {
        goAgain = true;
        while (goAgain){
            // Initialise new Deck object
            // This will generate the cards for the deck
            // Cards are shuffled here
            deck = new Deck();

            // Initialise new Hand object (max capacity is 10)
            // Hand will have 10 cards to begin with
            hand = new Hand(10);

            // Display Demo Game message to user
            System.out.println("Demo Game");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Loop over the cards in the Deck
            // Add cards in deck object to Deck list
            for (Card card : deck.getCards()){
                deck.addNewEntry(card);

                // Loop over the first 10 cards in the Deck
                // Add them to the Hand list
                for(int i = 0; i < 10; i++){
                    hand.addNewEntry(deck.dealCards(i));
                }
            }

            // Loop over the cards in the Hand list
            // Remove the cards in the Hand list from the Deck list
            for (Card card : hand.getCards()){
                if (deck.contains(card)){
                    deck.remove(card);
                }
            }

            // Display the Hand to the user
            System.out.println("CPU starting hand is:");
            System.out.println(hand.toString() + "\n");

            // Display the number of cards left in the Deck
            remainingCards = deck.getCurrentSize() + hand.getCurrentSize();
            System.out.println("Cards remaining: " + remainingCards + "\n");

            validMovesRemaining = hand.getValidMoves(hand);

            while (validMovesRemaining){
                Scanner scanner = new Scanner(System.in);

                Card firstCardToRemove = null;

                // Prompt user to fire next CPU move
                System.out.println("Press any key for CPU to make next move:");
                scanner.nextLine();

                // Use hint functionality to get CPU first card selection
                String cpuFirstCard = hand.getHint(hand, "?", "");

                // Get card from Hand list
                firstCardToRemove = hand.getCard(cpuFirstCard);

                // Set value of card
                // +1 to returned value to make it understandable to user
                int firstCardValue = firstCardToRemove.getRankValue() + 1;

                int cardsInDeck = deck.getCurrentSize();

                int totalCardValue = 0;

                // First card selected to be removed is a King
                // Value is 13 so can be removed from hand
                if (firstCardValue == 13)
                {
                    totalCardValue = firstCardValue;

                    // Remove card from hand
                    System.out.println("CPU removing " + firstCardToRemove.toString() + "\n");
                    hand.remove(firstCardToRemove.toString());

                    // Get the next card in the deck and add it to the hand
                    // If Deck still has cards in it
                    if (deck.getCurrentSize() > 0){
                        System.out.println("Dealing another card from the deck...\n");
                        hand.addNewEntry(deck.dealCards(0));
                    }

                    // Loop over the cards in the Hand list
                    // Remove the cards in the Hand list from the Deck list
                    for (Card card : hand.getCards()){
                        if (deck.contains(card)){
                            deck.remove(card);
                        }
                    }

                    if (hand.getCurrentSize() > 0){
                        // Display the Hand to the user
                        System.out.println("CPU current hand is:");
                        System.out.println(hand.toString() + "\n");
                    }

                    // Display the number of cards left in the Deck
                    remainingCards = deck.getCurrentSize() + hand.getCurrentSize();
                    System.out.println("Cards remaining: " + remainingCards + "\n");

                    // Check that user still has valid moves to make
                    validMovesRemaining = hand.getValidMoves(hand);
                }
                // First card selected to be removed is not a King
                // Remove another card to get to value of 13
                else{
                    Card secondCardToRemove = null;


                    // Use hint functionality to get CPU second card selection
                    String cpuSecondCard = hand.getHint(hand, cpuFirstCard, "?");

                    // Get second card from Hand list
                    secondCardToRemove = hand.getCard(cpuSecondCard);

                    // Set value of second card
                    // +1 to returned value to make it understandable to user
                    int secondCardValue = secondCardToRemove.getRankValue() + 1;

                    // Calculate total value of both cards
                    totalCardValue = firstCardValue + secondCardValue;

                    // Total value of cards to be removed is 13
                    if (totalCardValue == 13){
                        // Remove card from hand
                        System.out.println("CPU removing " + firstCardToRemove.toString() + " from hand...\n");
                        System.out.println("CPU removing " + secondCardToRemove.toString() + " from hand...\n");
                        hand.remove(firstCardToRemove.toString());
                        hand.remove(secondCardToRemove.toString());

                        // Get the next card in the deck and add it to the hand
                        // If Deck still has cards in it
                        if (cardsInDeck > 1){
                            System.out.println("Dealing another 2 cards from the deck...\n");
                            hand.addNewEntry(deck.dealCards(0));
                            hand.addNewEntry(deck.dealCards(1));
                        }
                        else if (cardsInDeck > 0){
                            System.out.println("Dealing another card from the deck...\n");
                            hand.addNewEntry(deck.dealCards(0));
                        }

                        // Loop over the cards in the Hand list
                        // Remove the cards in the Hand list from the Deck list
                        for (Card card : hand.getCards()){
                            if (deck.contains(card)){
                                deck.remove(card);
                            }
                        }

                        if (hand.getCurrentSize() > 0){
                            // Display the Hand to the user
                            System.out.println("CPU current hand is:");
                            System.out.println(hand.toString() + "\n");
                        }

                        // Display the number of cards left in the Deck
                        remainingCards = deck.getCurrentSize() + hand.getCurrentSize();
                        System.out.println("Cards remaining: " + remainingCards + "\n");

                        // Check that user still has valid moves to make
                        validMovesRemaining = hand.getValidMoves(hand);
                    }
                }
            }
            // No Valid Moves left
            // CPU has either removed all cards or has no moves left to make
            gameOver();
        }
    }

    // Functionality to end DemoGameMode
    public void gameOver(){
        Scanner scanner = new Scanner(System.in);
        // User has removed all cards
        // WINNER
        if (remainingCards == 0){
            System.out.println("CPU has won the game!\n");
        }
        // User cant make anymore moves
        // STALEMATE
        else{
            System.out.println("CPU can't make anymore moves...\n");
        }

        // Prompt user to input if they want to play this game mode again
        String message = ("Do you want to play this mode again? (Enter Y or N):");
        System.out.println(message);
        String playAgain = scanner.nextLine();
        playAgain = Validation.validateYesNo(playAgain, message);

        // User wants to play this game mode again
        if (playAgain.equalsIgnoreCase("y")){
            goAgain = true;
        }
        // User does not want to play this game mode again
        else{
            goAgain = false;
            GameInterface.returnToMainMenu(scanner);
        }
    }

    // Returns the Deck being used in the DemoGameMode
    public Deck getDeck() {
        return deck;
    }

    // Set the Deck to be used in the DemoGameMode
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    // Returns the Hand being used in the DemoGameMode
    public Hand getHand() {
        return hand;
    }

    // Set the Hand to be used in the DemoGameMode
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    // Returns goAgain from DemoGameMode
    public boolean getGoAgain() {
        return goAgain;
    }

    // Set whether to run DemoGameMode again
    public void setGoAgain(boolean goAgain) {this.goAgain = goAgain;}
}
