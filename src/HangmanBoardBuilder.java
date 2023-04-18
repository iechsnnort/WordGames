import java.util.ArrayList;
import java.util.Arrays;

public class HangmanBoardBuilder {
    public static String[] stages = {
            """
  ┌─────┐
  │
  │
  │
──┴──
""",
            """
  ┌─────┐
  │     ☹
  │
  │
──┴──
""",
            """
  ┌─────┐
  │     ☹
  │     │
  │
──┴──
""",
            """
  ┌─────┐
  │     ☹
  │    ─┤
  │
──┴──
""",
            """
  ┌─────┐
  │     ☹
  │    ─┼─
  │
──┴──
""",
            """
  ┌─────┐
  │     ☹
  │    ─┼─
  │    /
──┴──
""",
            """
  ┌─────┐
  │     ☹
  │    ─┼─
  │    / \\
──┴──
""",
            """
  ┌─────┐
  │     ☹   "Noooo!"
  │    ─┼─
  │    / \\
──┴──
""",
            """
  ┌─────┐
  │     ☹   "You lost!"
  │    ─┼─
  │    / \\
──┴──
"""
    };
    public static String buildBoard(HangmanGame hangman) {
        String str = String.format("""
                ┌──────────┬─────────────────────────────────┐
                │ GUESSES: │ WRONG LETTERS:                  │
                │ %-2d       │ %-32s│
                └──────────┴─────────────────────────────────┘
                """, hangman.guessesLeft, hangman.getGuessedWrong());
        str += HangmanBoardBuilder.stages[hangman.stage];
        str += transformWord(hangman.word, hangman.guessed);
        return str;
    }

    public static String transformWord(String word, ArrayList<Character> letters) {
        char[] untransformed = new char[word.length()];
        Arrays.fill(untransformed, '_');

        for (char letter : letters) {
            for (int i = 0; i < untransformed.length; i++) {
                if (word.toCharArray()[i] == letter) {
                    untransformed[i] = letter;
                }
            }
        }

        return new String(untransformed);
    }
}
