import java.util.Scanner;

public class WordGames {
    public static final Scanner INPUT = new Scanner(System.in);
    public static void main(String[] args) {
        int selection;
        do {
            WordGames.printMenu();

            try {
                selection = WordGames.INPUT.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Selection must be an integer ranging from 0-3. Exiting program!");
                selection = 0;
            }
        } while (selection != 0);
    }

    public static void printMenu() {
        String menu = """
                1 .. Wordle     2 .. Hangman
                3 .. Crossword
                
                0 .. Exit
                Your Selection: """;
        System.out.print(menu);
    }
}