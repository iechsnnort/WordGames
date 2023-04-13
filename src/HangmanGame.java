import java.util.ArrayList;
import java.util.List;

public class HangmanGame {
    private String currentWord;
    private static int highScore = 0;
    private int guesses;
    private ArrayList<Character> guessed = new ArrayList<Character>();

    public HangmanGame(String word) {
        // Initialize the Hangman game
        this.currentWord = word;
        // Map word length to amount of guesses (<+5: 4, <=8: 5, <13: 6, or some other values)
        // There is a better way to do this
        int length = this.currentWord.length();
        if (length <= 5) this.guesses = 4;
        if (length > 5 && length <= 8) this.guesses = 5;
        if (length > 8) this.guesses = 6;
    }

    public void run() {
        do {
            // This is where the game loop and game logic happens
            // Print board
            System.out.println("Guesses left: " + this.guesses);
            System.out.println(this.guessed);
            // Get guess
            System.out.print("Enter guess: ");
            char guess;
            try {
                guess = WordGames.INPUT.nextLine().toUpperCase().toCharArray()[0];
            } catch (Exception e) {
                System.out.println("Error: Guess must be a letter from A-Z!");
                break;
            }

            // Is guess a valid letter?
            if (!(guess >= 'A' && guess <= 'Z')) {
                System.out.println("Error: Guess must be a letter from A-Z!");
                continue;
            }

            // Feedback from guess
            // Is guess right?
            if (List.of(this.currentWord.toCharArray()).contains(guess)) {
                // Guess is right
                // Transform word string
            } else {
                // Add wrong guess
                if (!(this.guessed.contains(guess))) this.guessed.add(guess);
            }

        } while (true); // Replace true with some condition, or simply a break statement.
    }

    private String transformWord(ArrayList<Character> guesses) {
        return "";
    }
    public static int getHighScore() {
        return HangmanGame.highScore;
    }
}
