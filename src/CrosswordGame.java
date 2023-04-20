import Crossword.Direction;
import Crossword.Intersection;
import Crossword.Word;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 Sort all the words by length, descending.
 Take the first word and place it on the board.
 Take the next word.
 Search through all the words that are already on the board and see if there are any possible intersections (any common letters) with this word.
 If there is a possible location for this word, loop through all the words that are on the board and check to see if the new word interferes.
 If this word doesn't break the board, then place it there and go to step 3, otherwise, continue searching for a place (step 4).
 Continue this loop until all the words are either placed or unable to be placed.

 */

public class CrosswordGame {
    private ArrayList<Word> board = new ArrayList<>();

    public CrosswordGame(String[] words) {
        Arrays.sort(words);
        System.out.println(Arrays.toString(words));

        System.out.println(getIntersections(words[0], words[1]));
    }

    public ArrayList<ArrayList<Word>> getBoardsFrom(ArrayList<Word> board, ArrayList<String> words) {
        ArrayList<ArrayList<Word>> boards = new ArrayList<>();
        Word latestWord = board.get(board.size() - 1);

        for (String word : words) {
            ArrayList<Intersection> intersections = getIntersections(latestWord.word, word);
            if (intersections.isEmpty()) continue;
            for (Intersection intersection : intersections) {

            }
        }

        return null;
    }

    private void placeWordAt(String word, int x, int y, Direction dir) {
        this.board.add(new Word(word, x, y, dir));
    }

    private ArrayList<Intersection> getIntersections(String _word1, String _word2) {
        char[] word1 = _word1.toCharArray();
        char[] word2 = _word2.toCharArray();
        ArrayList<Intersection> ret = new ArrayList<>();

        for (int i = 0; i < word1.length; i++) {
            for (int j = 0; j < word2.length; j++) {
                if (word1[i] == word2[j])  ret.add(new Intersection(i, j));
            }
        }

        return ret;
    }

    public void printBoard() {
        if (this.board.isEmpty()) System.out.println("Board is empty - nothing to print!");

        char[][] board = makeCharArrayOfBoard();

        _printBoard(board);

    }

    // Unsafe method that will overwrite anything on the board with the latest stuff. Make sure your board is logically
    // sound before calling this!
    private char[][] makeCharArrayOfBoard() {
        int boardLengthX = 1;
        int boardLengthY = 1;

        for (Word iter : this.board) {
            if (iter.direction == Direction.HORIZONTAL) {
                int test = iter.word.length() + iter.x;
                if (test > boardLengthX) boardLengthX = test;
            }
            if (iter.x > boardLengthX) boardLengthX = iter.x;
        }

        for (Word iter : this.board) {
            if (iter.direction == Direction.VERTICAL) {
                int test = iter.word.length() + iter.y;
                if (test > boardLengthY) boardLengthY = test;
            }
            if (iter.y > boardLengthY) boardLengthY = iter.y;
        }
        // What are these values?
        char[][] board = new char[boardLengthY + 1][boardLengthX + 1];

        for (Word iter : this.board) {
            char[] wordArr = iter.word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {
                if (iter.direction == Direction.HORIZONTAL) {
                    board[iter.y][iter.x + i] = wordArr[i];
                } else {
                    board[iter.y + i][iter.x] = wordArr[i];
                }
            }
        }

        return board;
    }
    private void _printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
