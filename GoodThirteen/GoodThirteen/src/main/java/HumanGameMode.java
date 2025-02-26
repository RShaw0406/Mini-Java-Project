// Import Scanner - needed to get user input
import java.util.Scanner;

public class HumanGameMode implements HumanGameModeInterface {
    // Global Variables
    static boolean validMovesRemaining = false;
    static boolean goAgain = true;
    private int remainingCards = 0;
    private Deck deck;
    private Hand hand;
    private Move move;

    // Creates a new instance of a HumanGameMode
    public HumanGameMode(){

    }

    // Main functionality of HumanGameMode
    public void startGame() {
        goAgain = true;
        while (goAgain){
            // Initialise new Deck object
            // This will generate the cards for the deck
            // Cards are shuffled here
            Deck deck = new Deck();

            // Initialise new Hand object (max capacity is 10)
            // Hand will have 10 cards to begin with
            Hand hand = new Hand(10);

            // Display Human Game message to user
            System.out.println("Human Game");
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

            System.out.println("Your starting hand is:");
            // Display the Hand to the user
            System.out.println(hand.toString() + "\n");

            // Display the number of cards left in the Deck
            remainingCards = deck.getCurrentSize() + hand.getCurrentSize();
            System.out.println("Cards remaining: " + remainingCards + "\n");

            validMovesRemaining = hand.getValidMoves(hand);

            Replay replay = new Replay(1000);

            while (validMovesRemaining){
                Scanner scanner = new Scanner(System.in);

                Card firstCardToRemove = null;

                // Prompt user to enter the card they want to remove
                System.out.println("Enter card to remove (Enter ? for a hint):");

                // Get user input for first card selection
                String firstUserSelection = scanner.nextLine();

                // User wants a hint for first card selection
                while (firstUserSelection.equals("?")){
                    // Get cardHint and display for user
                    String cardHint = hand.getHint(hand, firstUserSelection, "");
                    System.out.println("You chose to get a hint...\n");
                    System.out.println("Card hint:");
                    System.out.println(cardHint + "\n");

                    // Prompt user to enter the card they want to remove
                    System.out.println("Enter card to remove (Enter ? for a hint):");

                    // Get user input for first card selection
                    firstUserSelection = scanner.nextLine();
                }

                // Validate user input for first card selection
                // Check if input matches a card in hand
                firstUserSelection = Validation.validateCardSelection(firstUserSelection, "", true, hand);

                // Get card from Hand list
                firstCardToRemove = hand.getCard(firstUserSelection);

                // Set value of card
                // +1 to returned value to make it understandable to user
                int firstCardValue = firstCardToRemove.getRankValue() + 1;

                // Display second card to user
                System.out.println("You selected to remove " + firstCardToRemove.toString() + "\n");

                // Display value of first card to user
                System.out.println("The value of your first selection is " + firstCardValue + "\n");

                int cardsInDeck = deck.getCurrentSize();

                // First card selected to be removed is a King
                // Value is 13 so can be removed from hand
                int totalCardValue = 0;
                if (firstCardValue == 13)
                {
                    totalCardValue = firstCardValue;

                    String moveStartingHand = hand.toString();

                    // Remove card from hand
                    System.out.println("Removing " + firstCardToRemove.toString() + " from hand...");
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
                        System.out.println("Your current hand is:");
                        System.out.println(hand.toString() + "\n");
                    }

                    // Display the number of cards left in the Deck
                    remainingCards = deck.getCurrentSize() + hand.getCurrentSize();
                    System.out.println("Cards remaining: " + remainingCards + "\n");

                    // Check that user still has valid moves to make
                    validMovesRemaining = hand.getValidMoves(hand);

                    move = new Move(moveStartingHand, firstCardToRemove.toString(), "Cards remaining: " + remainingCards + "\n");
                    replay.addNewEntry(move);
                }
                // First card selected to be removed is not a King
                // User will need to attempt to remove another card to get to value of 13
                else{
                    if (hand.getCurrentSize() > 0){
                        // Display the Hand to the user
                        System.out.println("Your current hand is:");
                        System.out.println(hand.toString() + "\n");
                    }

                    Card secondCardToRemove = null;

                    // Prompt user to enter the card they want to remove
                    System.out.println("Enter card to remove (Enter ? for a hint):");

                    // Get user input for second card selection
                    String secondUserSelection = scanner.nextLine();

                    // User wants a hint for second card selection
                    while (secondUserSelection.equals("?")){
                        // Get cardHint and display for user
                        String cardHint = hand.getHint(hand, firstUserSelection, secondUserSelection);
                        System.out.println("You chose to get a hint...\n");
                        System.out.println("Card hint:");
                        System.out.println(cardHint + "\n");

                        // Prompt user to enter the card they want to remove
                        System.out.println("Enter card to remove (Enter ? for a hint):");

                        // Get user input for second card selection
                        secondUserSelection = scanner.nextLine();
                    }

                    // Validate user input for second card selection
                    // Check if input matches a card in hand
                    secondUserSelection = Validation.validateCardSelection(firstUserSelection, secondUserSelection, false, hand);

                    // Validate user input for second card selection
                    // Check if input matches the input for the first card selection
                    secondUserSelection = Validation.validateDifferentCardsSelected(firstUserSelection, secondUserSelection, hand);

                    // Get second card from Hand list
                    secondCardToRemove = hand.getCard(secondUserSelection);

                    // Set value of second card
                    // +1 to returned value to make it understandable to user
                    int secondCardValue = secondCardToRemove.getRankValue() + 1;

                    // Display second card to user
                    System.out.println("You selected to remove " + secondCardToRemove.toString() + "\n");

                    // Display value of second card to user
                    System.out.println("The value of your second selection is " + secondCardValue + "\n");

                    // Calculate total value of both cards
                    totalCardValue = firstCardValue + secondCardValue;

                    String moveStartingHand = hand.toString();

                    // Display second card to user
                    System.out.println("The total value of your selections is " + totalCardValue + "\n");

                    // Total value of cards to be removed is 13
                    if (totalCardValue == 13){
                        // Remove card from hand
                        System.out.println("Removing " + firstCardToRemove.toString() + " from hand...\n");
                        System.out.println("Removing " + secondCardToRemove.toString() + " from hand...\n");
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
                            System.out.println("Your current hand is:");
                            System.out.println(hand.toString() + "\n");
                        }

                        // Display the number of cards left in the Deck
                        remainingCards = deck.getCurrentSize() + hand.getCurrentSize();
                        System.out.println("Cards remaining: " + remainingCards + "\n");

                        // Check that user still has valid moves to make
                        validMovesRemaining = hand.getValidMoves(hand);

                        move = new Move(moveStartingHand, firstCardToRemove.toString() + " and " + secondCardToRemove.toString(), "Cards remaining: " + remainingCards + "\n");
                        replay.addNewEntry(move);
                    }
                    // Total value of cards to be removed is not 13
                    else{
                        // Display message to user
                        System.out.println("This is not a valid move... Please make selections again...\n");

                        if (hand.getCurrentSize() > 0){
                            // Display the Hand to the user
                            System.out.println("Your current hand is:");
                            System.out.println(hand.toString() + "\n");
                        }

                        move = new Move(moveStartingHand, firstCardToRemove.toString() + " and " + secondCardToRemove.toString(), "Invalid Move... \n Cards remaining: " + remainingCards + "\n");
                        replay.addNewEntry(move);
                    }
                }
            }
            // No Valid Moves left
            // User has either removed all cards or has no moves left to make
            gameOver(replay);
        }
    }

    // Functionality to play HumanGameMode again
    public void playAgain(){
        Scanner scanner = new Scanner(System.in);
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

    // Functionality to end HumanGameMode
    public void gameOver(Replay replay){
        Scanner scanner = new Scanner(System.in);
        // User has removed all cards
        // WINNER
        if (remainingCards == 0){
            System.out.println("Congratulations, you have won the game!\n");
        }
        // User cant make anymore moves
        // STALEMATE
        else{
            System.out.println("Bad luck, you can't make anymore moves...\n");
        }

        // Prompt user to input if they want to view a replay of their moves
        String message = ("Do you want to view a replay of the game? (Enter Y or N):");
        System.out.println(message);
        String viewReplay = scanner.nextLine();
        viewReplay = Validation.validateYesNo(viewReplay, message);

        // User wants to view a replay
        if (viewReplay.equalsIgnoreCase("y")){
            replay.replayMoves();
            playAgain();
        }
        // User does not want to view a replay
        // Ask if they want to play this game mode again
        else{
            replay.clear();
            playAgain();
        }
    }

    // Returns the Deck being used in the HumanGameMode
    public Deck getDeck() {
        return deck;
    }

    // Set the Deck to be used in the HumanGameMode
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    // Returns the Hand being used in the HumanGameMode
    public Hand getHand() {
        return hand;
    }

    // Set the Hand to be used in the HumanGameMode
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    // Returns a move from the HumanGameMode
    public Move getMove() {return move;}

    // Sets a move in the HumanGameMode
    public void setMove(Move move) {
        this.move = move;
    }

    // Returns goAgain from DemoGameMode
    public boolean getGoAgain(){return goAgain;}
}
