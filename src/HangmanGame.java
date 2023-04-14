import java.util.ArrayList;
import java.util.Arrays;

public class HangmanGame {
    private String currentWord;
    public static int HIGH_SCORE = 0;
    private int guesses;
    private ArrayList<Character> guessedWrong = new ArrayList<>();
    private ArrayList<Character> guessedCorrect = new ArrayList<>();

    public HangmanGame(String word) {
        // Initialize the Hangman game
        this.currentWord = word.toUpperCase();
        // Map word length to amount of guesses (<=5: 4, <=8: 5, >8: 6, or some other values)
        // There is a better way to do this
        int length = this.currentWord.length();
        if (length <= 5) this.guesses = 4;
        if (length > 5 && length <= 8) this.guesses = 5;
        if (length > 8) this.guesses = 6;
    }

    public void run() {
        do {
            System.out.println();
            System.out.println();

            // Print board
            String feedback = transformWord(this.guessedCorrect);
            System.out.println(feedback);
            System.out.println("Guesses left: " + this.guesses);
            System.out.println("Wrong letters: " + this.guessedWrong);

            // Get guess
            System.out.print("Enter guess: ");
            char guess;
            try {
                guess = WordGames.INPUT.nextLine().toUpperCase().toCharArray()[0];
            } catch (Exception e) {
                System.out.println("Error: Guess must be a letter from A-Z!");
                continue;
            }

            // Is guess a valid letter?
            if (!(guess >= 'A' && guess <= 'Z')) {
                System.out.println("Error: Guess must be a letter from A-Z!");
                continue;
            }

            // Is guess right?
            boolean correct = false;
            for (char character : this.currentWord.toCharArray()) {
                if (character == guess) {
                    correct = true;
                    break;
                }
            }

            if (correct) {
                // Guess is right
                this.guessedCorrect.add(guess);
            } else {
                // Add wrong guess
                if (!(this.guessedWrong.contains(guess))) this.guessedWrong.add(guess);
                this.guesses--;
            }

        } while (this.guesses > 0);

        // Game loop over.
        if (this.guesses == 0) {
            System.out.println("You lost. Thanks for playing!");
        } else {
            System.out.println("You won with" + this.guesses + " guesses left!");
            if (this.guesses > HangmanGame.HIGH_SCORE) HangmanGame.HIGH_SCORE = this.guesses;
        }
    }

    // Returns a string filled with _'s the length of the current word, with the correct letters replaced if found in
    // lettersCorrect
    private String transformWord(ArrayList<Character> lettersCorrect) {
        char[] untransformed = new char[this.currentWord.length()];
        Arrays.fill(untransformed, '_');

        for (char letter : lettersCorrect) {
            for (int i = 0; i < untransformed.length; i++) {
                if (this.currentWord.toCharArray()[i] == letter) {
                    untransformed[i] = letter;
                }
            }
        }

        return Arrays.toString(untransformed);
    }
}
