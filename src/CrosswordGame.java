import Crossword.Direction;
import Crossword.Coordinate;
import Crossword.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public CrosswordGame(String[] _words) {
        System.out.println(Arrays.toString(_words));

        ArrayList<String> words = new ArrayList<>(List.of(_words));
        placeWordAt(words.get(0), 10, 10, Direction.VERTICAL); // TODO: Dynamic resizing based on words in board


    }

    // Returns an array of boards, with each possibility given the list of words.
    public ArrayList<ArrayList<Word>> getBoardsFrom(ArrayList<Word> board, String[] words) {
        ArrayList<ArrayList<Word>> boards = new ArrayList<>();
        Word latestWord = board.get(board.size() - 1); // TODO: Make check all words

        // For each word in the remaining words: can we place them down?
        for (String word : words) {
            ArrayList<Coordinate> intersections = getValidIntersections(latestWord.word, word);
            if (intersections.isEmpty()) continue;

            // Each intersection: place down the word on a board. Are there any errors?
            for (Coordinate intersection : intersections) {
                Word testWord = getWordFromIntersection(board, latestWord, word, intersection);

                // Any errors in this placement?
                boolean flag = false;
                for (Word w2 : board) {
                    if (wordsConflict(testWord, w2)) flag = true;
                }
                if (flag) continue;

                // Make a new board to avoid duplicate words.
                ArrayList<Word> newBoard = new ArrayList<>(board);
                placeWordAtBoard(newBoard, testWord);

                boards.add(newBoard);
            }
        }

        return boards;
    }

    private Word getWordFromIntersection(ArrayList<Word> board, Word latestWord, String word, Coordinate intersection) {
        int x, y;

        if (latestWord.direction == Direction.HORIZONTAL) {
            x = latestWord.x + intersection.w1pos();
            y = latestWord.y - intersection.w2pos();
            return new Word(word, x, y, Direction.VERTICAL);
        } else {
            x = latestWord.x - intersection.w2pos();
            y = latestWord.y + intersection.w1pos();
            return new Word(word, x, y, Direction.HORIZONTAL);
        }
    }

    private void placeWordAt(String word, int x, int y, Direction dir) {
        placeWordAtBoard(this.board, word, x, y, dir);
    }

    private void placeWordAtBoard(ArrayList<Word> board, Word word) {
        placeWordAtBoard(board, word.word, word.x, word.y, word.direction);
    }

    private void placeWordAtBoard(ArrayList<Word> board, String word, int x, int y, Direction dir) {
        board.add(new Word(word, x, y, dir));
    }

    private ArrayList<Coordinate> getValidIntersections(String _word1, String _word2) {
        char[] word1 = _word1.toCharArray();
        char[] word2 = _word2.toCharArray();
        ArrayList<Coordinate> ret = new ArrayList<>();

        for (int i = 0; i < word1.length; i++) {
            for (int j = 0; j < word2.length; j++) {
                if (word1[i] == word2[j])  ret.add(new Coordinate(i, j));
            }
        }

        return ret;
    }

    private boolean wordsConflict(Word word1, Word word2) {
        char[] cha1 = word1.word.toCharArray();
        char[] cha2 = word2.word.toCharArray();

        // For each letter of word1:
        for (int i = 0; i < cha1.length; i++) {
            char letter1 = cha1[i];

            // Position of letter1?
            Coordinate pos1 = getIndexPos(word1, i);

            // For each letter of word2:
            for (int j = 0; j < cha2.length; j++) {
                char letter2 = cha2[j];

                // Position of letter2?
                Coordinate pos2 = getIndexPos(word2, j);
                if (pos1.equals(pos2)) {
                    if (!(letter1 == letter2)) return true;
                }
            }
        }
        return false;
    }

    private Coordinate getIndexPos(Word word, int index) {
        if (word.direction == Direction.HORIZONTAL) {
            return new Coordinate(word.x + index, word.y);
        } else {
            return new Coordinate(word.x, word.y + index);
        }
    }

    public void printBoard() {
        printBoard(this.board);
    }

    public void printBoard(ArrayList<Word> board) {
        if (this.board.isEmpty()) System.out.println("Board is empty - nothing to print!");

        char[][] boardArr = makeCharArrayOfBoard(board);

        _printBoard(boardArr);
    }

    // Unsafe method that will overwrite anything on the board with the latest stuff. Make sure your board is logically
    // sound before calling this!
    private char[][] makeCharArrayOfBoard(ArrayList<Word> board) {
        int boardLengthX = 1;
        int boardLengthY = 1;

        for (Word iter : board) {
            if (iter.direction == Direction.HORIZONTAL) {
                int test = iter.word.length() + iter.x;
                if (test > boardLengthX) boardLengthX = test;
            }
            if (iter.x > boardLengthX) boardLengthX = iter.x;
        }

        for (Word iter : board) {
            if (iter.direction == Direction.VERTICAL) {
                int test = iter.word.length() + iter.y;
                if (test > boardLengthY) boardLengthY = test;
            }
            if (iter.y > boardLengthY) boardLengthY = iter.y;
        }
        // What are these values?
        char[][] newBoard = new char[boardLengthY + 1][boardLengthX + 1];

        for (Word iter : board) {
            char[] wordArr = iter.word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {
                if (iter.direction == Direction.HORIZONTAL) {
                    newBoard[iter.y][iter.x + i] = wordArr[i];
                } else {
                    newBoard[iter.y + i][iter.x] = wordArr[i];
                }
            }
        }

        return newBoard;
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
