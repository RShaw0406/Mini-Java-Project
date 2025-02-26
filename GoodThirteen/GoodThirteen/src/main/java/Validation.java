// Import Scanner - needed to get user input
import java.util.Scanner;

public class Validation {

    // Validates and returns selected gameMode input by user
    public static String validateGameMode(String gameMode){
        Scanner scanner = new Scanner(System.in);
        while (!(gameMode.equals("1") || gameMode.equals("2") || gameMode.equals("3"))){
            System.out.println("Error: You must enter 1, 2, or 3...\n");
            System.out.println("Please select a game mode to play or to quit (Enter 1, 2, or 3):");
            gameMode = scanner.nextLine();
        }
        return gameMode;
    }

    // Validates and returns yes/no input by user
    public static String validateYesNo(String input, String message){
        Scanner scanner = new Scanner(System.in);
        while (!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n"))){
            System.out.println("Error: You must enter Y or N...\n");
            System.out.println(message);
            input = scanner.nextLine();
        }
        return input;
    }

    // Validates and returns the selected card input by user - checks that card is in hand
    public static String validateCardSelection (String firstUserSelection, String secondUserSelection, boolean firstCard, Hand hand){
        Scanner scanner = new Scanner(System.in);
        String input;
        if (firstCard){
            input = firstUserSelection;
        }
        else {
            input = secondUserSelection;
        }

        boolean cardExists;
        if (!hand.contains(input)){
            cardExists = false;
        }
        else{
            cardExists = true;
        }

        // Enter card does not exist in hand
        while (!cardExists){
            // Prompt user to enter the card they want to remove
            System.out.println("Error: You have not entered a valid card that is in your hand... Please try again...\n");
            // Display the Hand to the user
            System.out.println("Your current hand is:");
            System.out.println(hand.toString() + "\n");
            System.out.println("Enter card to remove (Enter ? for a hint):");
            input = scanner.nextLine();

            // User wants a hint for first card selection
            while (input.equals("?")){
                if (firstCard){
                    firstUserSelection = input;
                }
                else {
                    secondUserSelection = input;
                }
                // Get cardHint and display for user
                String cardHint = hand.getHint(hand, firstUserSelection, secondUserSelection);
                System.out.println("You chose to get a hint...\n");
                System.out.println("Card hint:");
                System.out.println(cardHint + "\n");
                // Prompt user to enter the card they want to remove
                System.out.println("Enter card to remove (Enter ? for a hint):");

                // Get user input for first card selection
                input = scanner.nextLine();
            }

            if (hand.contains(input)){
                cardExists = true;
            }
            else{
                cardExists = false;
            }
        }

        return input;
    }

    // Validates and returns the selected card input by user - checks that user has not input the same card twice
    public static String validateDifferentCardsSelected (String firstUserSelection, String secondUserSelection, Hand hand){
        Scanner scanner = new Scanner(System.in);

        boolean differentCards;
        if (firstUserSelection.toString().equals(secondUserSelection.toString())){
            differentCards = false;
        }
        else{
            differentCards = true;
        }

        // Entered cards are the same
        while (!differentCards){
            // Prompt user to enter the card they want to remove
            System.out.println("Error: You have already selected to remove this card... Please try again...\n");
            // Display the Hand to the user
            System.out.println("Your current hand is:");
            System.out.println(hand.toString() + "\n");
            System.out.println("Enter card to remove (Enter ? for a hint):");
            secondUserSelection = scanner.nextLine();

            // User wants a hint for first card selection
            while (secondUserSelection.equals("?")){
                // Get cardHint and display for user
                String cardHint = hand.getHint(hand, firstUserSelection, secondUserSelection);
                System.out.println("You chose to get a hint...\n");
                System.out.println("Card hint:");
                System.out.println(cardHint + "\n");
                // Prompt user to enter the card they want to remove
                System.out.println("Enter card to remove (Enter ? for a hint):");

                // Get user input for first card selection
                secondUserSelection = scanner.nextLine();
                secondUserSelection = validateCardSelection (firstUserSelection, secondUserSelection, false, hand);
            }

            secondUserSelection = validateCardSelection (firstUserSelection, secondUserSelection, false, hand);

            if (firstUserSelection.toString().equals(secondUserSelection.toString())){
                differentCards = false;
            }
            else{
                differentCards = true;
            }
        }
        return secondUserSelection;
    }
}
