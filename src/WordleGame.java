/*
Project Name: WordGames
Class Name: WordleGame
Author: Oscar Roman Marcial
Last Date Project was Modified: April 22, 2023
Description: This class builds the game Wordle for the WordGames project.
 */
import java.util.*;

public class WordleGame {
    private final Scanner input = new Scanner(System.in);

    //Creating the colors for the background of the letters based on success of the user's guess
    private final String COLORGREEN = "\u001b[42m";
    private final String COLORYELLOW = "\u001b[43m";
    private final String RESETCOLOR = "\u001b[0m";

    //Creating fields to store words
    private String currentWord;
    private String userGuess = "";

    public WordleGame(String word){
        this.currentWord = word;
        System.out.println("You chose to play Wordle! You will have 6 attempts to guess correctly. Good luck!");
    }

    public void run(){
        //Choosing a random word from the words array
        String wordChosen = RandomWord();
        String correctWord = wordChosen;
        this.currentWord = correctWord;
        char[] arrayUser = new char[5];
        char[] arrayWord = new char[5];
        for (int i = 0; i < currentWord.length(); i++){
            arrayWord[i] = wordChosen.charAt(i);
        }

        //Create a for loop for the amount of attempts
        for (int attempts = 6; attempts > 0; attempts--){
            //Create a while loop to check the user's word & show remaining attempts
            while (!userGuess.equals(correctWord) && attempts > 0){
                System.out.println("Attempts remaining: " + attempts);
                System.out.print("Your guess: ");
                userGuess = input.nextLine().toUpperCase();
                for (int i = 0; i < 5; i++){
                    arrayUser[i] = userGuess.charAt(i);
                    arrayWord[i] = wordChosen.charAt(i);
                }
                checkGuess(arrayUser, arrayWord);

                //Checking to see if the user enters 5 letters & in the alphabet
                if (userGuess.length() != 5) {
                    System.out.println("Must be 5 letters. Try again!");
                    continue;
                } else if (userGuess.length() == 5 && !alphabet(userGuess)) {
                    System.out.println("Must contain letters. Try again!");
                    continue;
                }
                attempts--;
                System.out.println();
            }

            //If user correctly guesses the word the game ends & prompted to play again/play a different game
            if (userGuess.equals(currentWord)){
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

    //Checks the user's word to see if it matches with the correct word.
    private boolean checkGuess(char[] userGuessArray, char[] currentWordArray){
        boolean guess = true;
        char[] answer = currentWordArray;
        int[] letterColor = new int[5];
        //if letter is in the correct position
        for (int i = 0; i < 5;i++){
            if (userGuessArray[i] == answer[i]){
                answer[i] = '-';
                letterColor[i] = 2;
            } else
                guess = false;
        }

        //if letter is in the word but wrong position
        for (int j = 0; j < 5; j++){
            for (int k = 0; k < 5; k++){
                //if the letter is not already colored
                if (userGuessArray[j] == answer[k] && letterColor[j] != 2){
                    letterColor[j] = 1;
                    answer[k] = '-';
                }
            }
        }
        //colors to match the letter
        for (int m = 0; m < 5; m++){
            if (letterColor[m] == 2){
                System.out.print(COLORGREEN + userGuessArray[m] + RESETCOLOR);
            }
            if (letterColor[m] == 1){
                System.out.print(COLORYELLOW + userGuessArray[m] + RESETCOLOR);
            }
            if (letterColor[m] == 0){
                System.out.print(userGuessArray[m]);
            }
        }
        System.out.println();
        return guess;
    }

    //A method to see if the user enters something other than the alphabet
    private static boolean alphabet(String s){
        boolean isInAlpha = true;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (! Character.isAlphabetic(c)){
                isInAlpha = false;
            }
        }
        return isInAlpha;
    }

    private static String RandomWord(){
        String[] words = {"CABIN", "CABLE", "EAGER", "EAGLE", "EARLY", "FACES", "HACKS", "HABIT", "LABOR",
                "MACHO", "NACHO", "PACER", "PACKS", "RABID", "VEGAS", "YACHT", "THIEF", "AUDIO", "AMONG", "ABOVE",
                "AGONY", "APPLE", "LOOPS", "LANDS", "AMUSE", "ROUTE", "RULES", "BROWN", "GREEN", "WHITE", "BLACK",
                "TASTY", "TOWNS", "DRINK", "WORDS", "WINGS", "WINKS", "TODAY", "WEEKS", "MONTH", "STATE", "STOOD", "DATES", "CLASS",
                "GRASS", "GLASS", "TITLE", "TIMES", "GUESS", "GAMES", "TRUCK", "ADIEU", "AISLE", "NYMPH", "REACT", "LATER",
                "TEARS", "ALONE", "ARISE", "ABOUT", "ATONE", "SNARE", "SNAKE", "CREAM", "PAINT", "WORSE", "SAUCE", "ROAST", "MEDIA",
                "ARRAY", "ROADS", "INDEX", "CHOSE", "GOODS", "TRIES"};
        return words[(int)(Math.random() * (words.length - 1))];
    }
}