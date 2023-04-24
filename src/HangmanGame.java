import java.util.*;

/** HangmanGame
 * @author Nephi Norton
 * HangmanGame() provides the initialization behavior for the class
 * run() provides the game loop for the class
 */
public class HangmanGame {
    public String word;
    public int stage = 0;
    public int guessesLeft;
    public ArrayList<Character> guessed = new ArrayList<>();

    public HangmanGame(String word) {
        // Initialize the Hangman game
        this.word = word.toUpperCase();

        int length = this.word.length();
        if (length <= 5) this.guessesLeft = 5;
        if (length > 5 && length <= 8) this.guessesLeft = 6;
        if (length > 8) this.guessesLeft = 8;
    }

    public void run() {
        do {
            // Margin
            System.out.println();
            System.out.println();

            // Print board
            System.out.println(HangmanBoardBuilder.buildBoard(this));

            // Get guess
            System.out.print("Enter guess: ");
            char guess;
            try {
                String guessedWord = WordGames.INPUT.nextLine().toUpperCase();
                if (this.word.equals(guessedWord)) {
                    this.guessesLeft = -1;
                    continue;
                } else {
                    guess = guessedWord.toCharArray()[0];
                }
            } catch (Exception e) {
                System.out.println("Error: Guess must be a letter from A-Z!");
                continue;
            }

            // Is guess a valid letter?
            if (!(guess >= 'A' && guess <= 'Z')) {
                System.out.println("Error: Guess must be a letter from A-Z!");
                continue;
            }

            this.guessed.add(guess);

            // Is guess right?
            if (!isCharInWord(guess)) {
                stage++;
                this.guessesLeft--;
            }

            // If the current word is equal to the winning word, exit out of loop.
            if (this.word.equals(HangmanBoardBuilder.transformWord(this.word, this.guessed))) this.guessesLeft = -1;
        } while (this.guessesLeft > 0);


        // Game loop over.
        if (this.guessesLeft == 0) {
            System.out.println(HangmanBoardBuilder.stages[8]);
            System.out.println("The word was: " + this.word + "\r\nThanks for playing!");

        } else {
            System.out.println(this.word);
            System.out.println("You won!");

        }
        System.out.println("Press [ENTER] to continue.");
        WordGames.INPUT.nextLine();
        System.out.println();
    }

    public ArrayList<Character> getGuessedWrong() {
        ArrayList<Character> wrong = new ArrayList<>();

        for (char c : this.guessed) {
            if (!isCharInWord(c)) {
                wrong.add(c);
            }
        }

        Set<Character> set = new LinkedHashSet<>(wrong);
        wrong.clear();
        wrong.addAll(set);
        return wrong;
    }

    private boolean isCharInWord(char c) {
        for (char character : this.word.toCharArray()) {
            if (character == c) {
                return true;
            }
        }
        return false;
    }
}
