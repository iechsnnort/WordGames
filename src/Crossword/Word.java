package Crossword;

/** Word
 * @author Nephi Norton
 * word class used to represent a word with a coordinate location on a cartesian plane.
 */
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

    @Override
    public String toString() {
        return "Word{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", word='" + word + '\'' +
                '}';
    }
}
