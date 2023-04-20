package Crossword;

public class Word {
    public int x;
    public int y;
    public Direction direction;
    public final String word;
    public Word(String word, int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.direction = dir;
        this.word = word.toUpperCase();
    }
}
