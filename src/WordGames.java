import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordGames {
    public static final Scanner INPUT = new Scanner(System.in);
    public static ArrayList<String> wordBank = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        int selection;
        try {
            File file = new File("words_alpha.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                WordGames.wordBank.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Big error! Uh oh!");
            throw e;
        }

        do {
            WordGames.printMenu();

            // Grab input and make sure it exists/is valid.
            try {
                selection = WordGames.INPUT.nextInt();
                WordGames.INPUT.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Selection must be an integer ranging from 0-3. Exiting program!");
                selection = 0;
            }

            // Match selection to game.
            switch (selection) {
                case 0:
                    System.out.println("Thanks for playing!");
                    break;
                case 1:
                    WordleGame wordle = new WordleGame("", "");
                    wordle.run();
                    break;
                case 2:
                    Random random = new Random();
                    String word = WordGames.wordBank.get(random.nextInt(WordGames.wordBank.size()));
                    HangmanGame hangman = new HangmanGame(word);
                    hangman.run();
                    break;
                case 3:
                    CrosswordGame crossword = new CrosswordGame(new String[]{"AVALANCHE", "AMONG", "GARY", "WHY", "HELLO"});
                    break;
            }
        } while (selection != 0);
    }

    public static void printMenu() {
        String menu = """
                ┌────────────────┐
                │ # ── Game      │
                └────────────────┘
                  1 ── Wordle
                  2 ── Hangman
                  3 ── Crossword
                                        
                  0 ── Exit
                  
                Your Selection:\s""";
        System.out.print(menu);
    }
}