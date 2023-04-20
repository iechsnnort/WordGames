import java.util.Arrays;
import java.util.Vector;

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
    private char[][] board = new char[5][5];

    public CrosswordGame(String[] _words) {
        Arrays.sort(_words);
        System.out.println(Arrays.toString(_words));

        printBoard();
        placeWordAt("Among", 2, 3, Direction.HORIZONTAL);
        printBoard();
    }

    private void placeWordAt(String word, int x, int y, Direction dir) {
        char[] wordArr = word.toCharArray();

        // Preliminary checks to make sure the board can fit the word, accommodate if it can't
        if (dir == Direction.HORIZONTAL) {
            if (this.board[0].length < (word.length() + x)) resizeArray((word.length() + x), this.board.length);
            for (int xPos = x; xPos < (word.length() + x); xPos++) {
                this.board[y][xPos] = wordArr[xPos - x];
            }
        }

        if (dir == Direction.VERTICAL) {
            if (this.board.length < (word.length() + y)) resizeArray(this.board[0].length, (word.length() + y));
        }
    }

    private void resizeArray(int x, int y) {
        char[][] newBoard = new char[x][y];

        for (int xPos = 0; xPos < newBoard[0].length; xPos++) {
            for (int yPos = 0; yPos < newBoard.length; yPos++) {
                newBoard[xPos][yPos] = this.board[xPos][yPos];
            }
        }

        this.board = newBoard;
    }

    private void printBoard() {
        for (char[] row : this.board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
