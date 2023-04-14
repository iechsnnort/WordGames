import java.util.ArrayList;
import java.util.Arrays;

public class HangmanGame {
    private String currentWord;
    private static int highScore = 0;
    private int guesses;
    private ArrayList<Character> guessedWrong = new ArrayList<Character>();
    private ArrayList<Character> guessedCorrect = new ArrayList<Character>();

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
            String feedback = transformWord(this.guessedCorrect);
            System.out.println();
            System.out.println();

            // Print board
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
            for (char character : this.currentWord.toUpperCase().toCharArray()) {
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

        if (this.guesses == 0) {
            System.out.println("You lost. Thanks for playing!");
        } else {
            System.out.println("You won! And only in " + this.guesses + " guesses! New high score!");
        }
    }

    private String transformWord(ArrayList<Character> lettersCorrect) {
        char[] untransformed = new char[this.currentWord.length()];
        Arrays.fill(untransformed, '_');

        for (char letter : lettersCorrect) {
            for (int i = 0; i < untransformed.length; i++) {
                if (this.currentWord.toUpperCase().toCharArray()[i] == letter) {
                    untransformed[i] = letter;
                }
            }
        }

        return Arrays.toString(untransformed);
    }
    public static int getHighScore() {
        return HangmanGame.highScore;
    }
}
