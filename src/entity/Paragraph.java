package entity;

import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/17.
 */
public class Paragraph {
    private List<Sentence> sentenceList;

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
