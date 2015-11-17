package entity;

import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/17.
 */
public class Sentence {

    private List<Word> wordlist;

    public List<Word> getWordlist() {
        return wordlist;
    }

    public void setWordlist(List<Word> wordlist) {
        this.wordlist = wordlist;
    }
}
