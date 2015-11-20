package entity;

import java.util.List;

/**
 * Created by Jaric Liao on 2015/11/20.
 */
public class DictionaryItem {
    // 合并模式：WL 词语表 或者 RE 正则表达式
    private String mode;
    // 正则表达式形式
    private String pattern;
    // 词语表形式
    private List<String> wordsList;
    // 合并后的词
    private String mergeTo;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }

    public String getMergeTo() {
        return mergeTo;
    }

    public void setMergeTo(String mergeTo) {
        this.mergeTo = mergeTo;
    }
}
