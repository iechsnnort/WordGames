package Crossword;

/** IndexedWord
 * @author nephi norton
 * Word with an associated Index (see Index.java) as well as a revealed status.
 */
public class IndexedWord {
    public Word word;
    public Index index;
    public boolean revealed;

    public IndexedWord(Word word, Index index, boolean revealed) {
        this.word = word;
        this.index = index;
        this.revealed = revealed;
    }
}
