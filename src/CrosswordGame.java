import Crossword.Coordinate;
import Crossword.Direction;
import Crossword.Index;
import Crossword.IndexedWord;
import Crossword.RatedBoard;
import Crossword.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/** CrosswordGame - Logic behind generating crossword puzzles and user interface for the game itself
 * @author Nephi Norton
  - Sort words by length
  - Place first word on board
  - Generate all possible boards from that board with the given words (see getBoardsFrom)
  - Rate each one based on squareness and amount of words it has
  - Take the highest one
  - Make sure no coordinates are negative

  - Begin user interaction with board.
 */

public class CrosswordGame {
    private ArrayList<Word> board = new ArrayList<>();

    public final String[] initialWords;

    public CrosswordGame(String[] _words) {
        Arrays.sort(_words);
        initialWords = _words;
        placeWordAt(_words[0], 0, 0, Direction.VERTICAL);

        System.out.println("Generating board. Please be patient, this could take a minute...");
        // Get list of possible boards
        ArrayList<ArrayList<Word>> possibleBoards = getBoardsFrom(this.board);
        ArrayList<RatedBoard> ratedBoards = new ArrayList<>();
        for (ArrayList<Word> boar : possibleBoards) {
            ratedBoards.add(new RatedBoard(boar, getBoardRating(boar, _words.length)));
        }

        // Choose one of the top 5 highest rated boards for variety
        ratedBoards.sort(Comparator.comparingDouble(RatedBoard::rating));
        // But first, make the thing an array.
        RatedBoard[] topBoards = new RatedBoard[5];
        for (int i = 0; i < topBoards.length; i++) {
            topBoards[i] = ratedBoards.get(i);
        }
        Random random = new Random();
        this.board = topBoards[random.nextInt(topBoards.length)].board();

        setBoardInPositiveIntegerRange(this.board);

        // Board has been selected. Now create a new board with IndexedWords.
        ArrayList<IndexedWord> indexedBoard = new ArrayList<>();

        // Index words
        int lastX = -1;
        int lastY = -1;
        int offset = 0;
        for (int i = 0; i < this.board.size(); i++) {
            Word word = this.board.get(i);
            if (word.x == lastX && word.y == lastY) {
                offset++;
            } else {
                lastX = word.x;
                lastY = word.y;
            }
            indexedBoard.add(new IndexedWord(word, new Index(word.direction, (i + 1 - offset)), false));
        }

        boolean won = false;
        do {
            printBoard(indexedBoard);
            // Print the index + (down or across, depending)
            for (IndexedWord indexedWord : indexedBoard) {
                System.out.print(indexedWord.index.index() + " ");
                System.out.println(((indexedWord.word.direction == Direction.HORIZONTAL) ? "ACROSS" : "DOWN") + ": " + lookupDefinition(indexedWord.word.word));
            }

            // Get guess
            System.out.print("Enter guess: ");
            String guess;
            try {
                guess = WordGames.INPUT.nextLine().toUpperCase();
            } catch (Exception e) {
                System.out.println("Error: Guess must be a word!");
                continue;
            }

            // Does the guess match any of the words here?
            boolean guessCorrect = false;
            for (IndexedWord word : indexedBoard) {
                if (word.word.word.toUpperCase().equals(guess)) {
                    word.revealed = true;
                    guessCorrect = true;
                }
            }

            if (guessCorrect) {
                System.out.println("Correct!");
            } else {
                System.out.println("Try again!");
            }

            // Win condition: No words hidden
            won = true;
            for (IndexedWord word : indexedBoard) {
                if (!word.revealed) {
                    won = false;
                    break;
                }
            }
        } while (!won);

        System.out.println();
        System.out.println("You won! Congratulations!");
        System.out.println("Press [ENTER] to continue!");
        WordGames.INPUT.nextLine();
    }

    // Returns an array of boards, with each possibility given the list of words.
    public ArrayList<ArrayList<Word>> getBoardsFrom(ArrayList<Word> board) {
        ArrayList<ArrayList<Word>> boards = new ArrayList<>();
        ArrayList<String> words = getRemainingWords(board);

        // This adds every possible next board (1 word) to the boards variable.
        // For each word in the remaining words: can we place them down?
        for (Word latestWord : board) {
            for (String word : words) {
                ArrayList<Coordinate> intersections = getValidIntersections(latestWord.word, word);
                if (intersections.isEmpty()) continue;

                // Each intersection: place down the word on a board. Are there any errors?
                for (Coordinate intersection : intersections) {
                    Word testWord = getWordFromIntersection(latestWord, word, intersection);

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
        }

        // Reduce boards to the best 3. This prevents loops that will take longer than time itself...
        ArrayList<RatedBoard> ratedBoards = new ArrayList<>();
        for (ArrayList<Word> boar : boards) {
            ratedBoards.add(new RatedBoard(boar, getBoardRating(boar, this.initialWords.length)));
        }

        ratedBoards.sort(Comparator.comparingDouble(RatedBoard::rating));
        ArrayList<RatedBoard> boardsToUse = new ArrayList<>();
        if (ratedBoards.size() > 3) {
            boardsToUse.add(ratedBoards.get(0));
            boardsToUse.add(ratedBoards.get(1));
            boardsToUse.add(ratedBoards.get(2));
            boardsToUse.add(ratedBoards.get(3));
        } else {
            boardsToUse = ratedBoards;
        }

        ArrayList<ArrayList<Word>> boardsToAdd = new ArrayList<>();
        for (RatedBoard newBoard : boardsToUse) {
            boardsToAdd.addAll(getBoardsFrom(newBoard.board()));
        }

        boards.addAll(boardsToAdd);
        return boards;
    }

    private Word getWordFromIntersection(Word latestWord, String word, Coordinate intersection) {
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

    private String lookupDefinition(String word) {
        return WordGames.dictionary.get(word.toUpperCase());
    }

    private ArrayList<String> getRemainingWords(ArrayList<Word> board) {
        ArrayList<String> ret = new ArrayList<>(Arrays.asList(this.initialWords));

        for (Word word : board) {
            ret.remove(word.word);
        }

        return ret;
    }

    private float getBoardRating(ArrayList<Word> board, int initialWords) {
        float wordsRatio = initialWords / (float) board.size();

        // Bite me.
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

        float squareness = (boardLengthX >= boardLengthY) ? (float) boardLengthX / boardLengthY :
                (float) boardLengthY / boardLengthX;

        return  (wordsRatio * 3) + (squareness * 1);
    }

    private void setBoardInPositiveIntegerRange(RatedBoard board) {
        setBoardInPositiveIntegerRange(board.board());
    }

    private void setBoardInPositiveIntegerRange(ArrayList<Word> board) {
        // Give each word a real position.
        int lowestY = 0;
        for (Word word : board) {
            if (word.y < lowestY) lowestY = word.y;
        }
        if (lowestY < 0) {
            lowestY = Math.abs(lowestY);
        }
        int lowestX = 0;
        for (Word word : board) {
            if (word.x < lowestX) lowestX = word.x;
        }
        if (lowestX < 0) {
            lowestX = Math.abs(lowestX);
        }

        for (Word word : board) {
            word.y += lowestY;
            word.x += lowestX;
        }
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

    public void printBoard(ArrayList<IndexedWord> board) {
        if (this.board.isEmpty()) System.out.println("Board is empty - nothing to print!");

        char[][] boardArr = makeCharArrayOfBoard(board);

        _printBoard(boardArr);
    }

    // Unsafe method that will overwrite anything on the board with the latest stuff. Make sure your board is logically
    // sound before calling this!
    private char[][] makeCharArrayOfBoard(ArrayList<IndexedWord> board) {
        int boardLengthX = 1;
        int boardLengthY = 1;

        for (IndexedWord iter : board) {
            if (iter.word.direction == Direction.HORIZONTAL) {
                int test = iter.word.word.length() + iter.word.x;
                if (test > boardLengthX) boardLengthX = test;
            }
            if (iter.word.x > boardLengthX) boardLengthX = iter.word.x;
        }

        for (IndexedWord iter : board) {
            if (iter.word.direction == Direction.VERTICAL) {
                int test = iter.word.word.length() + iter.word.y;
                if (test > boardLengthY) boardLengthY = test;
            }
            if (iter.word.y > boardLengthY) boardLengthY = iter.word.y;
        }
        // A +1 just in case. You can never be too safe around arrays!
        char[][] newBoard = new char[boardLengthY + 1][boardLengthX + 1];

        for (IndexedWord iter : board) {
            if (!iter.revealed) {
                char[] wordArr = iter.word.word.toCharArray();
                for (int i = 0; i < wordArr.length; i++) {
                    if (iter.word.direction == Direction.HORIZONTAL) {
                        newBoard[iter.word.y][iter.word.x + i] = '_';
                    } else {
                        newBoard[iter.word.y + i][iter.word.x] = '_';
                    }
                }
            }
        }

        for (IndexedWord iter : board) {
            newBoard[iter.word.y][iter.word.x] = Integer.toString(iter.index.index()).toCharArray()[0];
        }

        for (IndexedWord iter : board) {
            char[] wordArr = iter.word.word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {
                if (iter.revealed) {
                    if (iter.word.direction == Direction.HORIZONTAL) {
                        newBoard[iter.word.y][iter.word.x + i] = wordArr[i];
                    } else {
                        newBoard[iter.word.y + i][iter.word.x] = wordArr[i];
                    }
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
