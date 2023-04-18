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
    public static String buildBoard(HangmanGame hangman, String transformed) {
        String str = String.format("""
                ┌──────────┬─────────────────────────────────┐
                │ GUESSES: │ WRONG LETTERS:                  │
                │ %2d       │ %-32s│
                └──────────┴─────────────────────────────────┘
                """, hangman.guesses, hangman.guessedWrong);
        str += HangmanBoardBuilder.stages[hangman.stage];
        str += transformed;
        return str;
    }
}
