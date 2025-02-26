// Import Scanner - needed to get user input
import java.util.Scanner;

public interface GameInterface {
    // Main Menu functionality
    void getMainMenu(Scanner scanner, Game newGame);

    // Returns returnToMainMenu from Game
    boolean getReturnToMainMenu();

    // Returns the HumanGameMode being used in the Game
    HumanGameMode getHumanGameMode();

    // Sets the HumanGameMode to be used in the Game
    void setHumanGameMode(HumanGameMode newGame);

    // Returns the DemoGameMode being used in the Game
    DemoGameMode getDemoGameMode();

    // Set the DemoGameMode being used in the Game
    void setDemoGameMode(DemoGameMode newGame);

    // Return to Main Menu functionality
    static void returnToMainMenu(Scanner scanner){
        String message = ("Do you want to return to the Main Menu? (Enter Y or N):");
        System.out.println(message);
        String input = scanner.nextLine();
        input = Validation.validateYesNo(input, message);

        if (input.equalsIgnoreCase("y"))
        {
            Game.returnToMainMenu = true;
        }
        else {
            Game.returnToMainMenu = false;
        }
    }

}
