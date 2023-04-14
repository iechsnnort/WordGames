import java.util.Scanner;

public class WordGames {
    public static final Scanner INPUT = new Scanner(System.in);
    public static void main(String[] args) {
        int selection;
        do {
            WordGames.printMenu();

            try {
                selection = WordGames.INPUT.nextInt();
                WordGames.INPUT.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Selection must be an integer ranging from 0-3. Exiting program!");
                selection = 0;
            }

            switch (selection) {
                case 0:
                    System.out.println("Thanks for playing!");
                    break;
                case 1:
                    break;
                case 2:
                    HangmanGame hangman = new HangmanGame("Among");
                    hangman.run();
                    break;
                case 3:
                    break;
            }
        } while (selection != 0);
    }

    public static void printMenu() {
        String menu = String.format("""
                # .. Game (High Score)
                
                1 .. Wordle (%d)     2 .. Hangman (%d)
                3 .. Crossword (%d)
                                        
                0 .. Exit
                Your Selection:\s""", 0, HangmanGame.HIGH_SCORE, 0); // Placeholder 0's
        System.out.print(menu);
    }
}