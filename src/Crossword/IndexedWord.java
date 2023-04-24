package Crossword;

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
