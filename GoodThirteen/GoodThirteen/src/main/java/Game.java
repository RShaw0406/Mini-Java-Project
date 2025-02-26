// Import Scanner - needed to get user input
import java.util.Scanner;

public class Game implements GameInterface {
    // Global Variables
    static boolean returnToMainMenu = true;
    private HumanGameMode humanGameMode;
    private DemoGameMode demoGameMode;

    // Creates a new instance of a Game
    public Game(){

    }

    // Main functionality of Game
    public static void main(String[] args) {
        // Display message to user
        System.out.println("Welcome to Good Thirteen card game...\n");

        // Initialise new Game object
        Game newGame = new Game();

        // Initialise new Scanner object (used for user input)
        Scanner scanner = new Scanner(System.in);

        // Display Main Menu to user and get type of game user wants to run
        newGame.getMainMenu(scanner, newGame);
    }

    // Main Menu functionality
    public void getMainMenu(Scanner scanner, Game newGame){
        // Check if user wants to play game again
        while (returnToMainMenu){
            // Display Main Menu to user
            System.out.println("Main Menu");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("(1) Human Game");
            System.out.println("(2) Demo Game");
            System.out.println("(3) Quit\n");
            System.out.println("Please select a game mode to play or to quit (Enter 1, 2, or 3):");
            String gameMode = scanner.nextLine();

            // Validate user input for game mode selection
            gameMode = Validation.validateGameMode(gameMode);

            // User has selected Human Game
            if (gameMode.equals("1")){
                // Display Game Mode message to user
                System.out.println("\nCreating Human Game...\n");

                // Create new Human Game Mode
                newGame.setHumanGameMode(new HumanGameMode());

                // Start a new Human Game Mode
                humanGameMode.startGame();
            }
            // User has selected Demo Game
            else if (gameMode.equals("2")){
                System.out.println("\nCreating Demo Game...\n");

                // Create new Demo Game Mode
                newGame.setDemoGameMode(new DemoGameMode());

                // Start a new Demo Game Mode
                demoGameMode.startGame();
            }
            else{
                returnToMainMenu = false;
            }
        }
        System.out.println("\nLeaving Good Thirteen card game...");
    }

    // Returns returnToMainMenu from Game
    public boolean getReturnToMainMenu(){
        return returnToMainMenu;
    }

    // Returns the HumanGameMode being used in the Game
    public HumanGameMode getHumanGameMode() {
        return this.humanGameMode;
    }

    // Sets the HumanGameMode to be used in the Game
    public void setHumanGameMode(HumanGameMode newGame) {
        this.humanGameMode = newGame;
    }

    // Returns the DemoGameMode being used in the Game
    public DemoGameMode getDemoGameMode() {
        return this.demoGameMode;
    }

    // Set the DemoGameMode being used in the Game
    public void setDemoGameMode(DemoGameMode newGame) {
        this.demoGameMode = newGame;
    }
}
