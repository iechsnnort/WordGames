import java.util.ArrayList;
import java.util.Arrays;

public class HangmanGame {
    public String currentWord;
    public static int HIGH_SCORE = 0;
    public int stage = 0;
    public int guesses; //TODO: change access modifier here and fix up boardbuilder
    public ArrayList<Character> guessedWrong = new ArrayList<>();
    public ArrayList<Character> guessedCorrect = new ArrayList<>();

    public HangmanGame(String word) {
        // Initialize the Hangman game
        this.currentWord = word.toUpperCase();
        // There is a better way to do this
        int length = this.currentWord.length();
        if (length <= 5) this.guesses = 5;
        if (length > 5 && length <= 8) this.guesses = 6;
        if (length > 8) this.guesses = 8;
    }

    public void run() {
        do {
            System.out.println();
            System.out.println();

            // Print board
            System.out.println(HangmanBoardBuilder.buildBoard(this, this.transformWord(this.guessedCorrect)));

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
                stage++;
            }

            if (this.currentWord.equals(transformWord(this.guessedCorrect))) this.guesses = -1;
        } while (this.guesses > 0);


        // Game loop over.
        if (this.guesses == 0) {
            System.out.println(HangmanBoardBuilder.stages[6]);
            System.out.println("You lost. The word was: " + this.currentWord + "\r\nThanks for playing!");

        } else {
            System.out.println("You won!");
        }
        System.out.println();
        System.out.println();
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

        return new String(untransformed);
    }
}
