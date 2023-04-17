import java.util.Scanner;
import java.util.Random;

public class WordleGame {
    private final Scanner input = new Scanner(System.in);
    private final Random rand = new Random();
    //Creating the colors for the background of the letters based on success of the user's guess
    private final String COLORGREEN = "\u001b[42m";
    private final String COLORYELLOW = "\u001b[43m";
    private final String RESETCOLOR = "\u001b[0m";

    //Creating fields to store words and guesses
    private String currentWord;
    private String currentGuess;

    //Storing the words the user can guess
    String[] words = {"CABIN", "CABLE", "EAGER", "EAGLE", "EARLY","FACES","HACKS","HABIT","LABOR",
            "MACHO", "NACHO", "PACER","PACKS","RABID","VEGAS", "YACHT","THIEF","AUDIO","AMONG","ABOVE",
            "AGONY", "APPLE", "LOOPS", "LANDS","AMUSE","ROUTE","RULES","BROWN","GREEN","WHITE","BLACK",
            "TASTY","TOWNS","DRINK","WORDS","WINGS","WINKS","TODAY","WEEKS","MONTH","STATE","STOOD","DATES", "CLASS",
            "GRASS","GLASS","TITLE", "TIMES", "GUESS","GAMES","TRUCK","ADIEU","AISLE","NYMPH","REACT","LATER",
            "TEARS","ALONE","ARISE","ABOUT","ATONE","SNARE","SNAKE","CREAM","PAINT","WORSE","SAUCE","ROAST","MEDIA",
            "ARRAY", "ROADS", "INDEX", "CHOSE", "GOODS", "TRIES"};

    public WordleGame(String word,String guess) {
        this.currentWord = word;
        this.currentGuess = guess;
        System.out.println("You chose to play Wordle! You will have 6 attempts to guess correctly. Good luck!");
    }

    public void run() {
        //Choosing a random word from the words array
        int wordsIndex = rand.nextInt(words.length);
        String correctWord = words[wordsIndex];
        this.currentWord = correctWord;
        //Create a for loop for the amount of attempts
        for (int attempts = 6; attempts > 0; attempts--) {
            String userGuess = "";
            //Create a while loop to check the user's word & show remaining attempts
            while (!userGuess.equals(correctWord) && attempts > 0){
                System.out.println("Attempts remaining: " + attempts);
                System.out.print("Your guess: ");
                userGuess = input.nextLine().toUpperCase();
                System.out.println();

                //Is the word from the user 5 letters?
                if (userGuess.length() != 5) {
                    System.out.println("Must be 5 letters. Try again!");
                    continue;
                }

                //Create a for loop to check each letter in user's word
                for (int i = 0; i<currentWord.length();i++){
                    boolean didBreak = false;

                    for (int j = 0; j<currentWord.length(); j++){
                        //if the user's letter is in the correct location
                        if (userGuess.charAt(i) == currentWord.charAt(i)){
                            System.out.print(COLORGREEN + userGuess.charAt(i) + RESETCOLOR);
                            didBreak = true;
                            break;
                        }
                        //if the user's letter is in the word but wrong location
                        if (userGuess.charAt(i) == (currentWord.charAt(j))){
                            System.out.print(COLORYELLOW + userGuess.charAt(i) + RESETCOLOR);
                            didBreak = true;
                            break;
                        }
                    }
                    //if user's letter is not in the word
                    if (!didBreak){
                        System.out.print(userGuess.charAt(i));
                    }
                }
                attempts--;
                System.out.println();
            }

            //If user correctly guesses the word the game ends & prompted to play again/play a different game
            if (userGuess.equals(currentWord)) {
                System.out.println("You guessed the word correctly! :)");
                System.out.println("Would you like to play again or play a different game? \n");
                break;
            }else{
                //If the user doesn't guess correctly they are prompted to try again/play a different game
                System.out.println("You got it wrong. The correct word is " + correctWord + ".");
                System.out.println("Would you like to play again or play a different game? \n");
            }
        }
    }
}